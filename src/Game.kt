import java.time.Year
import java.util.*
import kotlin.properties.Delegates
import kotlin.random.Random.Default.nextInt

//FOR HAVING THE ONLINE VERSION YOU HAVE TO LOOK AT ROW 83/84 THERE IS THE LINE THAT NEEDS TO BE CHANGED (AS WELL AS THE GAMELOOP ALLOWING MORE THAN 5 TRYS)
class Game {
    var playerinput: Int = 0
    var digitArray: ArrayList<Int> = arrayListOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)
    // val createUniqueFourDigitNumber = { (0..9).shuffled().take(4).joinToString("") }     take 4 nimmt die ersten 4, shuffle mixed das set vorher; take liefert liste

    var randomArray: Array<Int> = arrayOf(0, 0, 0, 0)
    var inputArray: Array<Int> = arrayOf(0, 0, 0, 0)
    var ergebnisArray: ArrayList<Char> = arrayListOf('0', '0', '0', '0')


    fun createRandomArray(): Array<Int> {
        for (indi in randomArray.indices) {
            var del: Int = nextInt(0, digitArray.size) // nimm random indizes aus der arraylist
            randomArray[indi] = digitArray[del]
            digitArray.removeAt(del)//löscht das element damit es kein zweites mal gepicked werden kann
        }
        return randomArray
    }


    fun startGame() {

        //generate a random array with 4 digits where no digit occur twice
        createRandomArray()
        //randomArray.forEach { print(it) }
        var gameCounter: Int = 0


        while (gameCounter < 5) {
            println("write a 4 digit guess and press Enter")
            playerinput = Integer.valueOf(readLine())
            //make an Array of four digits out of the Int
            //could have used toCharArray() - less code
            inputArray[0] = playerinput / 1000
            inputArray[1] = (playerinput % 1000) / 100
            inputArray[2] = ((playerinput % 1000) % 100) / 10
            inputArray[3] = (((playerinput % 1000) % 100) % 10)

            for (indizes in inputArray.indices) {
                for (indi in randomArray.indices) {
                    //wenn an richtiger stelle liegt
                    if (indi == indizes && inputArray[indizes] == randomArray[indi]) {
                        ergebnisArray[indizes] = 'Y'
                        break
                    }
                    //ist enthalten aber an anderer Stelle
                    else if (indi != indizes && inputArray[indizes] == randomArray[indi]) {
                        ergebnisArray[indizes] = 'O'
                        break
                    } else if (indi == 3) {
                        ergebnisArray[indizes] = 'X'
                    }
                }
            }
            //check if guessed right
            var test: String = String(ergebnisArray.toCharArray())
            if (test == "YYYY"){
                println("   .\n" +
                        "  .\n" +
                        " . .\n" +
                        "  ...\n" +
                        "\\~~~~~/\n" +
                        " \\   /\n" +
                        "  \\ /\n" +
                        "   V\n" +
                        "   |\n" +
                        "   |\n" +
                        "  ---")
                println("You won - sexy little beast!")
                this.gameMenu();
                return
            }


            println("-------------------------------------")
            println("your result for the " + (gameCounter + 1) + " try: ")
            /*ergebnisArray.shuffle()
            ergebnisArray.forEach { print(it)} FOR ONLINE VERSION:ergebnisArray.forEach { print(it)*/
            ergebnisArray.forEach { print(it)}
            println("\ntries left: " +(4-gameCounter))
            println("-------------------------------------")
            gameCounter++
            if (gameCounter == 5){
                var ergebnis:String = randomArray.joinToString()
                println("THE NUMBER WAS $ergebnis")
                println("┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼\n" +
                        "███▀▀▀██┼███▀▀▀███┼███▀█▄█▀███┼██▀▀▀\n" +
                        "██┼┼┼┼██┼██┼┼┼┼┼██┼██┼┼┼█┼┼┼██┼██┼┼┼\n" +
                        "██┼┼┼▄▄▄┼██▄▄▄▄▄██┼██┼┼┼▀┼┼┼██┼██▀▀▀\n" +
                        "██┼┼┼┼██┼██┼┼┼┼┼██┼██┼┼┼┼┼┼┼██┼██┼┼┼\n" +
                        "███▄▄▄██┼██┼┼┼┼┼██┼██┼┼┼┼┼┼┼██┼██▄▄▄\n" +
                        "┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼\n" +
                        "███▀▀▀███┼▀███┼┼██▀┼██▀▀▀┼██▀▀▀▀██▄┼\n" +
                        "██┼┼┼┼┼██┼┼┼██┼┼██┼┼██┼┼┼┼██┼┼┼┼┼██┼\n" +
                        "██┼┼┼┼┼██┼┼┼██┼┼██┼┼██▀▀▀┼██▄▄▄▄▄▀▀┼\n" +
                        "██┼┼┼┼┼██┼┼┼██┼┼█▀┼┼██┼┼┼┼██┼┼┼┼┼██┼\n" +
                        "███▄▄▄███┼┼┼─▀█▀┼┼─┼██▄▄▄┼██┼┼┼┼┼██▄\n" +
                        "┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼\n" +
                        "┼┼┼┼┼┼┼┼██┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼██┼┼┼┼┼┼┼┼┼\n" +
                        "┼┼┼┼┼┼████▄┼┼┼▄▄▄▄▄▄▄┼┼┼▄████┼┼┼┼┼┼┼\n" +
                        "┼┼┼┼┼┼┼┼┼▀▀█▄█████████▄█▀▀┼┼┼┼┼┼┼┼┼┼\n" +
                        "┼┼┼┼┼┼┼┼┼┼┼█████████████┼┼┼┼┼┼┼┼┼┼┼┼\n" +
                        "┼┼┼┼┼┼┼┼┼┼┼██▀▀▀███▀▀▀██┼┼┼┼┼┼┼┼┼┼┼┼\n" +
                        "┼┼┼┼┼┼┼┼┼┼┼██┼┼┼███┼┼┼██┼┼┼┼┼┼┼┼┼┼┼┼\n" +
                        "┼┼┼┼┼┼┼┼┼┼┼█████▀▄▀█████┼┼┼┼┼┼┼┼┼┼┼┼\n" +
                        "┼┼┼┼┼┼┼┼┼┼┼┼███████████┼┼┼┼┼┼┼┼┼┼┼┼┼\n" +
                        "┼┼┼┼┼┼┼┼▄▄▄██┼┼█▀█▀█┼┼██▄▄▄┼┼┼┼┼┼┼┼┼\n" +
                        "┼┼┼┼┼┼┼┼▀▀██┼┼┼┼┼┼┼┼┼┼┼██▀▀┼┼┼┼┼┼┼┼┼\n" +
                        "┼┼┼┼┼┼┼┼┼┼▀▀┼┼┼┼┼┼┼┼┼┼┼▀▀┼┼┼┼┼┼┼┼┼┼┼\n" +
                        "┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼")
                this.gameMenu();

            }
        }

    }


    fun helpMenu() {
        println("-------------------------------------")
        println(
            "The object of the game is to guess the hidden four digit number. " +
                    "There are no repeated digits in the number. " +
                    "Once you have guessed, a code will be shown that indicates how successful your guess was."
        )
        println("\n--you have 5 trys--")
        println("X indicates a wrong digit")
        println("Y indicates a correct digit in correct place")
        println("O indicates a corecct digit in the wrong place")
        println("-------------------------------------")
        println("\n -->press 1 to return to main menu or 2 to Exit<-- ")
        playerinput = Integer.valueOf(readLine())
        when (playerinput) {
            1 -> this.gameMenu()
            2 -> return
        }
    }


    fun gameMenu() {
        println("Welcome to BrainBasher")
        println("1. Start New Game")
        println("2. Help")
        println("3. Exit")
        playerinput = Integer.valueOf(readLine()) //TODO prüfen ob das dann strings auslässt

        //Kotlin Switchcase
        when (playerinput) {
            1 -> this.startGame()
            2 -> this.helpMenu()
            3 -> return
        }

    }

}