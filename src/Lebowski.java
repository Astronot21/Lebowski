import java.util.*;

public class Lebowski {
 // VARIABLES
    LinkedList<Player> players;
    Random rand = new Random();
    public Deck deck;
    private int currentPlayerIndex = 0;
    private int directionOfPlay = 1;

    public Lebowski() {
        startGame();
    }

    public void startGame() {
        System.out.println("Welcome to the Lebowski!");
        createDeck(); // produces a shuffled deck
        determinePlayers(); // creates players and deals them their cards
        play();
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
                final byte MAX_PLAYERS = 5;
                final byte MIN_PLAYERS = 3;
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
        input.close();
        players = new LinkedList<>();

        for (int i = 0; i < numPlayers; i++) {
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

    private void play() {
        currentPlayerIndex = rand.nextInt(players.size()); // set random index to go first
        // Game loop
        Player currentPlayer = getCurrentPlayer(); // select a random player to go first
        System.out.println(currentPlayer.getName() + "'s turn");
        nextTurn();
        currentPlayer = getCurrentPlayer(); // select a random player to go first
        System.out.println(currentPlayer.getName() + "'s turn");
        nextTurn();
        currentPlayer = getCurrentPlayer(); // select a random player to go first
        System.out.println(currentPlayer.getName() + "'s turn");
        nextTurn();
        currentPlayer = getCurrentPlayer(); // select a random player to go first
        System.out.println(currentPlayer.getName() + "'s turn");

        reverseDirection();
        nextTurn();
        currentPlayer = getCurrentPlayer(); // select a random player to go first
        System.out.println(currentPlayer.getName() + "'s turn");
        nextTurn();
        currentPlayer = getCurrentPlayer(); // select a random player to go first
        System.out.println(currentPlayer.getName() + "'s turn");
        nextTurn();
        currentPlayer = getCurrentPlayer(); // select a random player to go first
        System.out.println(currentPlayer.getName() + "'s turn");
        nextTurn();
        currentPlayer = getCurrentPlayer(); // select a random player to go first
        System.out.println(currentPlayer.getName() + "'s turn");

    }

    public void nextTurn(){
        currentPlayerIndex = (currentPlayerIndex + directionOfPlay + players.size()) % players.size();
    }

    public void reverseDirection(){
        directionOfPlay *= -1;
    }

    public Player getCurrentPlayer(){
        return players.get(currentPlayerIndex);
    }

}
