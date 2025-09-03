import java.util.*;

public class AceRace {

    public Deck deck;
    public Card[][] allColumns;
    public List<Card> mysteryCards;


    public AceRace(Deck deck) {

        this.deck = deck;
        Card[] column0 = new Card[4];
        Card[] column1 = new Card[4];
        Card[] column2 = new Card[4];
        Card[] column3 = new Card[4];
        Card[] column4 = new Card[4];
        Card[] column5 = new Card[4];
        Card[] column6 = new Card[4];
        this.allColumns = new Card[][]{column6, column5, column4, column3, column2, column1, column0};
        this.mysteryCards = new ArrayList<>();
        populateAces();
        populateMysteryCards();

    }

    public void populateAces() {

        int aceIndex = 0;
        for (int i = 0; i < this.deck.getCards().size(); i++) {
            Card card = this.deck.getCards().get(i);
            if (card.getRank() == Card.Rank.ACE) {
                this.allColumns[6][aceIndex++] = card;
                this.deck.getCards().remove(i);
                i--;
            }
        }
    }

    public void populateMysteryCards() {

        this.deck.shuffleDeck();
        for (int i = 0; i < 4; i++) {
            this.mysteryCards.add(this.deck.removeTopCardFromDeck());
        }
    }

    public void playRound() {

        Card suitToMove = this.deck.removeTopCardFromDeck();
        System.out.println(suitToMove + " was played. " + suitToMove.getSuit().toString().toUpperCase() + " advances.");
        advanceAce(suitToMove.getSuit());
        displayColumns();
        checkForMysteryActivation();
        checkWinCondition();

    }

    public Card[][] getAllColumns() {
        return this.allColumns;
    }

    public int getSmallestAceRow(Card[][] aceRows) {

        for (int i = 6; i > 0; i--) {
            Card[] aceArray = aceRows[i];
            if (aceArray != null) {
                for (Card card : aceArray) {
                    if (card != null) {
                        switch (i) {
                            case 6: return 1;
                            case 5: return 2;
                            case 4: return 3;
                            case 3: return 4;
                            case 2: return 5;
                            case 1: return 6;
                            case 0: return 7;
                        }
                    }
                }
            }
        }
        return -1;
    }

    public void checkWinCondition() {

        for (Card card : this.allColumns[0]) {
            if (card != null) {
                System.out.println(card + " wins the game!");
                System.exit(0);
            }
        }
    }

    public void checkForMysteryActivation() {

        for (int i = 0; i < 4; i++) {
            mysteryCardActivated((i + 2), i);
        }
    }

    public void mysteryCardActivated(int column, int cardActivated) {

        if (getSmallestAceRow(this.allColumns) > column && !this.mysteryCards.get(cardActivated).getIsRevealed()) {
            this.mysteryCards.get(cardActivated).setIsRevealed(true);
            System.out.println(this.mysteryCards.get(cardActivated) + " was revealed! " + this.mysteryCards.get(cardActivated).getSuit().toString().toUpperCase() + " is retracted.");
            retractAce(this.mysteryCards.get(cardActivated).getSuit());
            displayColumns();
        }
    }

    public void retractAce(Card.Suit suit) {

        Card aceToAdvance = null;
        int columnIndex = 0;
        int aceIndex = 0;
        boolean found = false;
        for (int i = 0; i < this.allColumns.length; i++) {
            if (found) {
                break;
            }
            columnIndex = i;
            aceIndex = 0;
            for (Card card : this.allColumns[i]) {
                if (card != null && card.getSuit() == suit) {
                    aceToAdvance = card;
                    found = true;
                    break;
                }
                aceIndex++;
            }
        }
        this.allColumns[columnIndex][aceIndex] = null;
        this.allColumns[columnIndex + 1][aceIndex] = aceToAdvance;
    }

    public void advanceAce(Card.Suit suit) {

        Card aceToAdvance = null;
        int columnIndex = 0;
        int aceIndex = 0;
        boolean found = false;
        for (int i = 0; i < this.allColumns.length; i++) {
            if (found) {
                break;
            }
            columnIndex = i;
            aceIndex = 0;
            for (Card card : this.allColumns[i]) {
                if (card != null && card.getSuit() == suit) {
                    aceToAdvance = card;
                    found = true;
                    break;
                }
                aceIndex++;
            }
        }
        this.allColumns[columnIndex][aceIndex] = null;
        this.allColumns[columnIndex - 1][aceIndex] = aceToAdvance;
    }

    public void displayColumns() {

        int columnNumber = 7;
        int mysteryCard = 3;
        for (int i = 0; i < 7; i++) {
            if (i == 0 || i == 6 || i == 1) {
                displayColumn(this.allColumns[i], columnNumber);
                columnNumber--;
            } else {
                displayColumn(this.allColumns[i], columnNumber, this.mysteryCards.get(mysteryCard));
                columnNumber--;
                mysteryCard--;
            }
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

    public void displayColumn(Card[] column, int columnNumber, Card mysteryCard) {

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
        if (!mysteryCard.getIsRevealed()) {
            System.out.print(" ??");
        } else {
            System.out.print(" " + mysteryCard);
        }
        System.out.println();
    }

}
