package observer

class GraphObserver : Observer {
    override fun update(generator: NumberGenerator, number: Int) {
        print("GraphObserver:")
        for (i in 0 until number) {
            print("*")
        }
        println()

        Thread.sleep(100)
    }
}