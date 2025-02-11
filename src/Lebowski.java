import java.util.ArrayList;
import java.util.Random;

public class Lebowski {
 // VARIABLES
    ArrayList<Card> deck;
    Random rand = new Random();

    private static class Card{
        String value;
        String suit;

        public Card(String value, String suit){
            this.value = value;
            this.suit = suit;
        }

        public String getValue(){
            return value;
        }

        public String getSuit(){
            return suit;
        }

        public String toString(){
            return (value + "-" + suit);
        }
    }

    private class Player{
        ArrayList<Card> inHand; // refers to cards held in hand that the player can see
        ArrayList<Card> offHand; // refers to the face down cards that the player can not yet see

        Player (){
            /*
             TODO: Populate the player's in-hand with 3 random cards
             TODO: Populate the player's off-hand with 3 known cards and 3 unknown cards
            */
        }

    }

    public Lebowski() {
        startGame();
    }

    public void buildDeck(){
        deck = new ArrayList<Card>();
        String[] suits = {"H", "C", "D", "S"};
        String[] values = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};

        // Create ordered deck
        for (String suit : suits) {
            for (String value : values) {
                Card card = new Card(value, suit);
                deck.add(card);
            }
        }
        System.out.println("The Deck is " + deck);
    }

    public void startGame() {
        buildDeck(); // clean deck
        shuffleDeck();
    }

    private void shuffleDeck() {
        // for each card, exchange it with another card at a random index
        for (int i = 0; i < deck.size(); i++) {
            int index = rand.nextInt(deck.size());
            Card currentCard = deck.get(i);
            Card randomCard = deck.get(index);
            deck.set(i, randomCard);
            deck.set(index, currentCard);
        }
        System.out.println("The Deck is " + deck);
    }

}
