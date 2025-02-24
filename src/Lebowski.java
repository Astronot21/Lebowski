import java.util.*;

public class Lebowski {
 // VARIABLES
    LinkedList<Player> players;
    Random rand = new Random();
    public Deck deck;
    private int currentPlayerIndex = 0;
    private int directionOfPlay = 1;
    Scanner input = new Scanner(System.in);

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

//        System.out.println("==============");
//        System.out.println("PLAYERS");
//        System.out.println("==============");
//        System.out.println(players);
//
//        System.out.println("Remaining deck: " + deck.toString());
    }

    private void play() {
        currentPlayerIndex = rand.nextInt(players.size()); // set random index to go first
        Player currentPlayer = players.get(currentPlayerIndex); // get first random player
        DiscardPile discardPile = new DiscardPile();

        // Start game loop
        while (true) {
            if (currentPlayerIndex == 0) {
                if (currentPlayer.canPlayFromInHand()){
                    System.out.println("Your in-hand is: " + currentPlayer.showInHand());
                    Card cardSelected = null;
                    while (cardSelected == null) {
                        System.out.println("Which card would you like to play?");
                        String cardString = input.next().trim(); // removes spacing in input
                        // use helper function
                        cardSelected = currentPlayer.getCardFromHand(cardString, currentPlayer.inHand);
                        int cardRank = Integer.parseInt(cardSelected.getValue()); // get rank and store as integer
                        // TODO: Handle case where rank may be > 10 (royal cards)
                        if (currentPlayer.getNumRankedCards(currentPlayer.inHand, cardRank) > 1){
                            System.out.println("Multiple cards of rank " + cardRank + "found");
                        }

                        // TODO: Call function to perform special actions for certain cards


                        if (cardSelected == null){
                            System.out.println("Please enter a valid card from your hand.");
                        }
                    }
                    System.out.println("Your selected card: " + cardSelected);
                    currentPlayer.playFromHand(cardSelected, currentPlayer.inHand);
                    discardPile.addCard(cardSelected); // add played card on top of the discard pile
                    System.out.println(discardPile);
                    System.out.println(deck);

                    // Replenish hand if necessary
                    if (!currentPlayer.checkInHand()) {
                        if (!deck.isEmpty()) {
                            for (int i = 1; i <= (3 - currentPlayer.inHand.size()); i++) {
                                System.out.println("Card from deck added to hand");
                                currentPlayer.inHand.add(deck.drawCard()); // add top card from deck to player hand
                            }
                        } else {
                            System.out.println("Deck is empty.");
                        }
                    } else {
                        System.out.println("Player hand already contains 3 or more cards");
                    }

                } else if (currentPlayer.canPlayFromFaceUpHand()){
                    // TODO: Add similar logic to play from off hand (face up cards)
                    System.out.println(currentPlayer.showOffHandFaceUp());
                } else if (currentPlayer.canPlayFromFaceDownHand()){
                    System.out.println(currentPlayer.showOffHandFaceDown());
                    // TODO: Add similar logic to play from off hand (face down cards)
                }
            } else {
                System.out.println("Computer's turn");
                // TODO: Add logic for AI to play, similar to player, except without input
            }
            nextTurn();
        } // end game loop
    }

    public void nextTurn(){
        currentPlayerIndex = (currentPlayerIndex + directionOfPlay + players.size()) % players.size();
    }

    public void reverseDirection(){
        // used when Queen is played onto discard pile
        directionOfPlay *= -1;
    }

    public Player getCurrentPlayer(){
        return players.get(currentPlayerIndex);
    }

}
