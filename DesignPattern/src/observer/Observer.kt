package observer

interface Observer {
    fun update(generator: NumberGenerator, number: Int)
}