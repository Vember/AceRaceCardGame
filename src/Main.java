import java.util.HashMap;
import java.util.Map;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        Deck deck = new Deck();
        AceRace newRace = new AceRace(deck);
        newRace.displayColumns();
        System.out.println("Press enter to advance round!");

        while (true) {

            String input = scan.nextLine();
            newRace.playRound();

        }
    }
}
