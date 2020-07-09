package strategy

class Player(private val name: String, private val strategy: Strategy) {
    private var winCount: Int = 0
    private var loseCount: Int = 0
    private var gameCount: Int = 0

    fun nextHand() : Hand {
        return this.strategy.nextHand()
    }

    fun win() {
        this.strategy.study(true)
        this.winCount++
        this.gameCount++
    }

    fun lose() {
        this.strategy.study(false)
        this.loseCount++
        this.gameCount++
    }

    fun even() {
        this.gameCount++
    }

    override fun toString(): String {
        return "[${this.name} : ${this.gameCount} games, ${this.winCount} win, ${this.loseCount} lose]"
    }
}