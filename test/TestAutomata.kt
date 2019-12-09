fun main() {
    numberDecector()
}

fun numberDecector() {
    val str = "kjffgjds dgd5gdfg 5s4 fgqdg88 gqg6465 d465 d \n f465s 6fqqdf 4"
    val state = State<String?>()
    state.addOutput(state, object : ICondition<String?> {
        override fun doAction(data: String?): String? {
            if (data!!.isEmpty()) {
                return null
            }
            val c = data.toCharArray()[0]
            val newDate = data.substring(1, data.length)
            when(c.toInt()) {
                 in 48..57 -> {
                     println(c)
                     return newDate
                 }
            }
            return newDate
        }
    })
    state.callAndExecute(str)
}