import java.util.*;

public class Lebowski {
 // VARIABLES
    LinkedList<Player> players;
    Random rand = new Random();
    private final byte MAX_PLAYERS = 5;
    private final byte MIN_PLAYERS = 3;
    public Deck deck;

    public Lebowski() {
        startGame();
    }

    public void startGame() {
        System.out.println("Welcome to the Lebowski!");
        createDeck(); // produces a shuffled deck
        determinePlayers();
    }

    private void createDeck() {
        deck = new Deck();
    }

    private void determinePlayers() {
        Scanner input = new Scanner(System.in);
        int numPlayers = 0;

        while (true){
            System.out.println("How many players will be playing? >> ");
            try {
                numPlayers = input.nextInt();
                if (numPlayers <= MAX_PLAYERS && numPlayers >= MIN_PLAYERS){
                    break;
                } else {
                    System.out.println("Player count must be between " + MIN_PLAYERS + " and " + MAX_PLAYERS);
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer number of players.");
                input.next();
            }
        }
        players = new LinkedList<>();

        for (int i = 0; i < numPlayers; i++) {
            /* TODO: Deal each player an in-hand using the first 3 cards from the shuffled deck
               TODO: Deal each player an off-hand using the next 6 cards from the shuffled deck
               TODO: The last player needs to point to the first player
             */
            players.add(new Player("Player " + (i+1)));
            // deal hands
            for (int j = 0; j < 3; j++) {
                players.get(i).inHand.add(deck.drawCard());
                players.get(i).offHand_fd.add(deck.drawCard());
                players.get(i).offHand_fu.add(deck.drawCard());
            }
        }

        System.out.println("==============");
        System.out.println("PLAYERS");
        System.out.println("==============");
        System.out.println(players);

        System.out.println("Remaining deck: " + deck.toString());
    }
}
