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
        // Create ordered deck
       for (Card.Suit suit : Card.Suit.values()) {
           for (Card.Rank rank : Card.Rank.values()) {
               deck.push(new Card(rank, suit));
           }
       }
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
        return "Deck is " + deck.toString();
    }
}
