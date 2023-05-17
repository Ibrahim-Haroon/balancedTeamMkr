import java.lang.StringBuilder
import java.util.PriorityQueue
import kotlin.math.max

class Player() {
    var name: String? = null;
    var onRealTeam: Boolean = false;
    var yearsPlayed: Double = 0.0;
    var skillLevel: Double = 0.0;
    var generalAthleticAbility: Double = 0.0;
    fun createTeams(Participants: List<Player>, numPlayersPerTeam: Int): List<List<String?>> {
        val numOfTeams: Int = Participants.size / numPlayersPerTeam;
        val maxHeap: PriorityQueue<Player> = PriorityQueue(playerComparator);
        val teams: List<MutableList<String?>> = List(numOfTeams) { mutableListOf() };
        var teamTurn: Int = 0;

        maxHeap.addAll(Participants);

        while (maxHeap.isNotEmpty()) {
            teams[teamTurn].add(maxHeap.remove().name);
            teamTurn = (teamTurn + 1) % numOfTeams;
        }

        return teams;
    }

    val playerComparator = Comparator<Player> { player1, player2 ->
        compareValuesBy(player1, player2,
            { player -> if (player.onRealTeam) 0 else -1 }, // the player on a real time is considered better
            { player -> player.yearsPlayed }, // the player who has most experience is better
            { player -> player.skillLevel }, // better skill level -> greater player
            { player -> -player.generalAthleticAbility } // more athletic -> greater player
        )
    }

}

fun main(args: Array<String>) {
    //test
}


//    val playerComparator = Comparator<Player> {player1, player2 ->
//        if (player1.onRealTeam && player2.onRealTeam) {
//            //compare by YOE, skill level, and general athletic ability
//            if (player1.yearsPlayed > player2.yearsPlayed ||
//                player1.skillLevel > player2.skillLevel   ||
//                player1.generalAthleticAbility < player2.generalAthleticAbility) {
//                -1
//            }
//            else {
//                1
//            }
//        }
//        else if (player1.onRealTeam) {
//            -1;
//        }
//        else if (player2.onRealTeam) {
//            1;
//        }
//        else {
//            if (player1.yearsPlayed > player2.yearsPlayed ||
//                player1.skillLevel > player2.skillLevel   ||
//                player1.generalAthleticAbility < player2.generalAthleticAbility) {
//                -1
//            }
//            else {
//                1
//            }
//        }
//
//        if (player1.yearsPlayed == player2.yearsPlayed) {
//            //compare by skill level and general athletic ability
//            if (player1.skillLevel > player2.skillLevel) {
//                -1
//            }
//            else if (player1.generalAthleticAbility > player2.generalAthleticAbility) {
//                -1
//            }
//            else {
//                1
//            }
//        }
//        else if (player1.yearsPlayed > player2.yearsPlayed) {
//            //compare skill level between them and general athletic ability
//            if (player1.skillLevel > player2.skillLevel) {
//                -1
//            }
//            else if (player1.skillLevel < player2.skillLevel ||
//                     player1.generalAthleticAbility < player2.generalAthleticAbility) {
//                1
//            }
//            else {
//                0
//            }
//        }
//        else {
//            1;
//        }
//    }