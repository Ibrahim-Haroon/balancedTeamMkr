import java.lang.StringBuilder
import java.util.PriorityQueue
import kotlin.math.max

class Player() {
    var name: String? = null;
    var onRealTeam: Boolean = false;
    var yearsPlayed: Double = 0.0;
    var skillLevel: Double = 0.0;
    var generalAthleticAbility: Double = 0.0;

    companion object {
        val playerComparator = Comparator<Player> { player1, player2 ->
            compareValuesBy(player1, player2,
                { player -> if (player.onRealTeam) 0 else -1 }, // the player on a real time is considered better
                { player -> player.yearsPlayed }, // the player who has most experience is better
                { player -> player.skillLevel }, // better skill level -> greater player
                { player -> -player.generalAthleticAbility } // more athletic -> greater player
            )
        }
    }
}

fun createTeams(Participants: List<Player>, numPlayersPerTeam: Int): List<List<String?>> {
    val numOfTeams: Int = Participants.size / numPlayersPerTeam;
    val maxHeap: PriorityQueue<Player> = PriorityQueue(Player.playerComparator);
    val teams: List<MutableList<String?>> = List(numOfTeams) { mutableListOf() };
    var teamTurn: Int = 0;

    maxHeap.addAll(Participants);

    while (maxHeap.isNotEmpty()) {
        teams[teamTurn].add(maxHeap.remove().name);
        teamTurn = (teamTurn + 1) % numOfTeams;
    }

    return teams;
}

fun main() {
    val Participants: MutableList<Player> = mutableListOf();

    while (true) {
        val input = readln()
        if (input.isBlank()) {
            break
        }
        val line = input.split(", ")
        val newPlayer: Player = Player()
        newPlayer.name = line[0]
        newPlayer.onRealTeam = line[1] == "Yes"
        newPlayer.yearsPlayed = line[2].toDouble()
        newPlayer.skillLevel = line[3].toDouble()
        newPlayer.generalAthleticAbility = line[4].toDouble()
        Participants.add(newPlayer)
    }

    val teams = createTeams(Participants, 5);

    println(teams);
}
