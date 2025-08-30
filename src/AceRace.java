import java.util.*;

public class AceRace {

    public HashMap<Integer, Card[]> columns;
    public Deck deck;

    public AceRace(Deck deck) {

        this.columns = new HashMap<>();
        this.deck = deck;
        initColumns();
        populateAces();

    }

    public void initColumns() {

        Card[] aceColumns;
        for (int i = 6; i >= 0; i--) {
            aceColumns = new Card[4];
            this.columns.put(i, aceColumns);
        }
    }

    public void populateAces() {

        Card[] aceColumn = this.columns.get(0);
        int aceIndex = 0;
        for (int i = 0; i < this.deck.getCards().size(); i++) {
            Card card = this.deck.getCards().get(i);
            if (card.getRank() == Card.Rank.ACE) {
                aceColumn[aceIndex++] = card;
                this.deck.getCards().remove(i);
                i--;
            }
        }
    }

    public void displayColumns() {

        Set<Integer> keySet = this.columns.keySet();
        List<Integer> sortedKeys = new ArrayList<>(keySet);
        sortedKeys.sort(Collections.reverseOrder());

        for (Integer key : sortedKeys) {
            Card[] aceArray = this.columns.get(key);
            System.out.print("Row " + key + " ");
            for (Card ace : aceArray) {
                System.out.print("|");
                if (ace != null) {
                    System.out.print(" " + ace.toString() + " ");
                } else {
                    System.out.print("    ");
                }
                System.out.print("|");
            }
            System.out.println();
        }
    }

}
