import java.util.*;

public class AceRace {

    public Deck deck;
    public Card[] column0;
    public Card[] column1;
    public Card[] column2;
    public Card[] column3;
    public Card[] column4;
    public Card[] column5;
    public Card[] column6;
    public Card[][] allColumns;


    public AceRace(Deck deck) {

        this.deck = deck;
        this.column0 = new Card[4];
        this.column1 = new Card[4];
        this.column2 = new Card[4];
        this.column3 = new Card[4];
        this.column4 = new Card[4];
        this.column5 = new Card[4];
        this.column6 = new Card[4];
        this.allColumns = new Card[][]{column6, column5, column4, column3, column2, column1, column0};
        populateAces();

    }

    public void populateAces() {

        int aceIndex = 0;
        for (int i = 0; i < this.deck.getCards().size(); i++) {
            Card card = this.deck.getCards().get(i);
            if (card.getRank() == Card.Rank.ACE) {
                this.column0[aceIndex++] = card;
                this.deck.getCards().remove(i);
                i--;
            }
        }
    }

    public void advanceAce(Card.Suit suit) {

        Card aceToAdvance = null;
        int columnIndex = 0;
        int aceIndex = 0;
        for (int i = 0; i < this.allColumns.length; i++) {
            columnIndex = i;
            aceIndex = 0;
            for (Card card : this.allColumns[i]) {
                if (card != null) {
                    if (card.getSuit() == suit) {
                        System.out.println("Found!");
                        aceToAdvance = card;
                        break;
                    }
                }
                aceIndex++;
            }
        }
        this.allColumns[columnIndex][aceIndex] = null;
        this.allColumns[columnIndex - 1][aceIndex] = aceToAdvance;
    }

    public void displayColumns() {

        int columnNumber = 7;
        for (Card[] column : this.allColumns) {
            displayColumn(column, columnNumber);
            columnNumber--;
        }
    }

    public void displayColumn(Card[] column, int columnNumber) {

        System.out.print("Row " + columnNumber + " ");
        for (Card ace : column) {
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
