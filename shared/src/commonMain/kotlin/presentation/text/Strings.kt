package presentation.text

expect object Strings {

    fun get(id: String, quantity: Int): String

    fun get(id: String): String

    fun format(id: String, vararg args: Any): String
}