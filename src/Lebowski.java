import java.util.*;

public class Lebowski {
 // VARIABLES
    ArrayList<Card> deck;
    LinkedList<Player> players;
    Random rand = new Random();
    private final byte MAX_PLAYERS = 5;
    private final byte MIN_PLAYERS = 3;

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

    public Lebowski() {
        startGame();
    }

    public void startGame() {
       int numPlayers = determinePlayers();
       players = new LinkedList<>();
       buildDeck(); // clean deck
       shuffleDeck();
       System.out.println("==============");
       System.out.println("PLAYERS");
       System.out.println("==============");
       for (int i = 1; i <= numPlayers; i++) {
            /* TODO: Deal each player an in-hand using the first 3 cards from the shuffled deck
               TODO: Deal each player an off-hand using the next 6 cards from the shuffled deck
               TODO: The last player needs to point to the first player
             */
            players.add(new Player("Player " + i));
       }
        System.out.println(players);
    }

    private int determinePlayers() {
        Scanner input = new Scanner(System.in);
        int players = 0;
        System.out.println("Welcome to the Lebowski!");

        while (true){
            System.out.println("How many players will be playing? >> ");
            try {
                players = input.nextInt();
                if (players <= MAX_PLAYERS && players >= MIN_PLAYERS){
                    break;
                } else {
                    System.out.println("Player count must be between " + MIN_PLAYERS + " and " + MAX_PLAYERS);
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer number of players.");
                input.next();
            }
        }

        return players;
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
        System.out.println("The shuffled deck is " + deck);
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
        System.out.println("The deck is " + deck);
    }

}
