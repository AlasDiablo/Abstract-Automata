package fr.alasdiablo.automata.state

import fr.alasdiablo.automata.link.IBasicLink

abstract class BasicState<I, O> {

    protected var data: I? = null

    private val listOfOutputState: MutableMap<BasicState<I, O>, IBasicLink<O>>

    init {
        this.listOfOutputState = HashMap()
    }

    abstract fun dataToLink(data: I?): O?

    abstract fun actionOnCall(data: O)

    fun callAndExecute(data: I?) {
        this.data = data
        val dataToLink: O = this.dataToLink(this.data) ?: return
        this.listOfOutputState.forEach { (state, link) ->
            if (link.checkIfValid(dataToLink)) {
                state.actionOnCall(dataToLink)
                state.callAndExecute(this.data)
            }
        }
    }

    fun addOutput(state: BasicState<I, O>, link: IBasicLink<O>) {
        this.listOfOutputState[state] = link
    }
}