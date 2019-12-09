interface ICondition<I> {
    fun doAction(data: I): I
}