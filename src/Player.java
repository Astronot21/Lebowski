import java.util.ArrayList;
import java.util.List;

public class Player {
    String name;

    ArrayList<Card> inHand; // refers to cards held in hand that the player can see
    ArrayList<Card> offHand_fu; // refers to the face up cards in the player's off-hand
    ArrayList<Card> offHand_fd; // refers to the face down cards that the player can not yet see

    Player (String name){
        this.inHand = new ArrayList<Card>();
        this.offHand_fu = new ArrayList<Card>();
        this.offHand_fd = new ArrayList<Card>();
        this.name = name;
    }

    @Override
    public String toString(){
        return "Name: " + this.name + "\nIn-hand: " + inHand + "\nOff-hand: " + offHand_fu + "\nOff-hand: " + offHand_fd + "\n";
    }

    public String showInHand(){
        return this.inHand.toString();
    }

    public String showOffHandFaceUp(){
        return this.offHand_fu.toString();
    }

    public String showOffHandFaceDown(){
        return this.offHand_fd.toString();
    }

    public boolean canPlayFromInHand(){
        return !this.inHand.isEmpty();
    }

    public boolean canPlayFromFaceDownHand(){
        return inHand.isEmpty() && !offHand_fu.isEmpty();
    }

    public boolean canPlayFromFaceUpHand(){
        return inHand.isEmpty() && offHand_fu.isEmpty() && !offHand_fd.isEmpty();
    }

    public String getName(){
        return this.name;
    }

    public Card getCardFromHand(String cardName, ArrayList<Card> hand){
        for (Card card : hand){
            if (card.toString().equalsIgnoreCase(cardName)){
                return card;
            }
        }
        return null;
    }

    public void playFromHand(Card card, ArrayList<Card> hand){
        hand.remove(card);
    }

}
