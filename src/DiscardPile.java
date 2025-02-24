import java.util.Collections;
import java.util.Stack;


public class DiscardPile{
    private Stack<Card> pile;

    public DiscardPile(){
        this.pile = new Stack<>();
    }

    public void addCard(Card card){
        this.pile.add(card); // adds a card to the top of the pile
    }

    public Card removeCard(){
        return this.pile.pop();
    }

    public Card getTopCard(){
        // used to give the top card to the player as punishment when applicable
        return this.pile.peek();
    }

    @Override
    public String toString(){
        return "DiscardPile = " + pile;
    }
}
