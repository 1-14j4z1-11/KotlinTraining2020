package observer

class DigitObserver : Observer {
    override fun update(generator: NumberGenerator, number: Int) {
        println("DigitObserver:$number")
        Thread.sleep(100)
    }
}