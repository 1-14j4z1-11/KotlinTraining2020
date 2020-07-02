package observer

abstract class NumberGenerator {
    private val observers = ArrayList<Observer>()

    fun addObserver(observer: Observer) {
        this.observers.add(observer)
    }

    fun deleteObserver(observer: Observer) {
        this.observers.remove(observer)
    }

    protected fun notifyObservers() {
        val number = this.number

        for (observer in this.observers) {
            observer.update(this, number)
        }
    }

    abstract val number: Int

    abstract fun execute()
}