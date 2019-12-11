package fr.alasdiablo.automata.link

interface IBasicLink<O> {
    fun checkIfValid(value: O): Boolean
}