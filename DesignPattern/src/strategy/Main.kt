package strategy

import kotlin.random.Random

fun main() {
    val seed1 = Random.nextInt()
    val seed2 = Random.nextInt()
    val player1 = Player("Taro", WinningStrategy(seed1))
    val player2 = Player("Hana", ProbStrategy(seed2))

    for (i in 0..9999) {
        val nextHand1 = player1.nextHand()
        val nextHand2 = player2.nextHand()

        when {
            (nextHand1.isStrongerThan(nextHand2)) -> {
                println("Winner:$player1")
                player1.win()
                player2.lose()
            }
            (nextHand1.isWeakerThan(nextHand2)) -> {
                println("Winner:$player2")
                player1.lose()
                player2.win()
            }
            else -> {
                println("Even...")
                player1.even()
                player2.even()
            }
        }
    }

    println("Total result:")
    println(player1.toString())
    println(player2.toString())
}