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

}
