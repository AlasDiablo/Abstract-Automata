class State<I> {

    private var data: I? = null

    private val listOfOutputState: MutableMap<State<I>, ICondition<I>>

    init {
        this.listOfOutputState = HashMap()
    }

    fun callAndExecute(data: I) {
        this.data = data
        this.listOfOutputState.forEach { (state, condition) ->
            val tmp = condition.doAction(data)
            if (tmp != null) {
                state.callAndExecute(tmp)
            }
        }
    }

    fun addOutput(state: State<I>, condition: ICondition<I>) {
        this.listOfOutputState[state] = condition
    }

}