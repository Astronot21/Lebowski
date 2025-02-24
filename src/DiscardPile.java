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
        return this.pile.peek();
    }
}
