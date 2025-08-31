import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {

        Deck deck = new Deck();
        AceRace newRace = new AceRace(deck);
        deck.shuffleDeck();
        newRace.displayColumns();
        newRace.advanceAce(Card.Suit.c);
        newRace.displayColumns();

    }

}
