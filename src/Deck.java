import java.util.Collections;
import java.util.Stack;

public class Deck {
    private Stack<Card> deck;

    public Deck() {
        this.deck = new Stack<>();
        initialiseDeck();
        shuffleDeck();
    }

    private void initialiseDeck(){
        String[] suits = {"H", "C", "D", "S"};
        String[] values = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};

        // Create ordered deck
        for (String suit : suits) {
            for (String value : values) {
                Card card = new Card(value, suit);
                deck.add(card);
            }
        }
//        System.out.println("The deck is " + deck);
    }

    private void shuffleDeck(){
        Collections.shuffle(deck);
//        System.out.println("The shuffled deck is " + deck);
    }

    public Card drawCard(){
        if (deck.isEmpty()) {
            throw new IllegalStateException("The deck is empty");
        }
        return deck.pop();
    }

    public boolean isEmpty(){
        return deck.isEmpty();
    }

    @Override
    public String toString(){
        return deck.toString();
    }
}
