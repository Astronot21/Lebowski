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

    public void populateInHand(List<Card> cards){
        if (cards.size() == 3){
            this.inHand = new ArrayList<>(cards);
        } else{
            throw new IllegalArgumentException("Only three face-up cards are allowed");
        }
    }

    public void populateOffHand(List<Card> cards){
        if (cards.size() == 6){
            this.offHand_fu = new ArrayList<>(cards.subList(0, 3));
            this.offHand_fd = new ArrayList<>(cards.subList(3, 6));
        }
        else{
            throw new IllegalArgumentException("Only six off-hand cards are allowed");
        }
    }

    @Override
    public String toString(){
        return "Name: " + this.name + "\nIn-hand: " + inHand + "\nOff-hand: " + offHand_fu + "\n";
    }

}
