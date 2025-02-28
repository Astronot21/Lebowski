import java.util.*;

public class Lebowski {
    // VARIABLES
    LinkedList<Player> players;
    Random rand = new Random();
    public Deck deck;
    private int currentPlayerIndex = 0;
    private int directionOfPlay = 1;
    Scanner input = new Scanner(System.in);

    // Used for text coloring
    String RESET = "\u001B[0m";
    String RED = "\u001B[31m";
    String GREEN = "\u001B[32m";
    String YELLOW = "\u001B[33m";


    public Lebowski() {
        startGame();
    }

    public void startGame() {
        System.out.println(GREEN + "Welcome to the Lebowski!" + RESET);
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
                    System.out.println(RED + "Player count must be between " + MIN_PLAYERS + " and " + MAX_PLAYERS + RESET);
                }
            } catch (InputMismatchException e) {
                System.out.println(RED + "Invalid input. Please enter an integer number of players." + RESET);
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
        DiscardPile discardPile = new DiscardPile();

        // Start game loop
        while (true) {
        Player currentPlayer = players.get(currentPlayerIndex); // get first random player
            if (currentPlayerIndex == 0) {
                if (currentPlayer.canPlayFromInHand()){
                    System.out.println("Your in-hand is: " + currentPlayer.showInHand());
                    Card cardSelected = null;
                    while (cardSelected == null) {
                        System.out.println("Which card would you like to play?");
                        String cardString = input.next().trim(); // removes spacing in input
                        cardSelected = currentPlayer.getCardFromHand(cardString, currentPlayer.inHand);
                        if (cardSelected == null) {
                            System.out.println(RED + "Please enter a valid card from your hand." + RESET);
                        }
                    }

                    // TODO: Call function to perform special actions for certain cards

                    int cardRank = cardSelected.getValue();
                    if (currentPlayer.getNumRankedCards(currentPlayer.inHand, cardRank) > 1) {
                        System.out.println(YELLOW + "Multiple cards of rank " + cardRank + " found" + RESET);
                        // TODO: Ask user how many they want to play from that rank
                    }
                    System.out.println(GREEN + "Your selected card: " + cardSelected + RESET);

                    if (checkValidPlay(cardSelected, discardPile)) {
                        currentPlayer.playFromHand(cardSelected, currentPlayer.inHand);
                        discardPile.addCard(cardSelected); // add played card on top of the discard pile
                        System.out.println(discardPile);
                    } else {
                        System.out.println(RED + "Must play a card of higher rank!" + RESET);
                        // Adds current discard pile to player hand, since they made a mistake
                        for (int i = 0; i <= discardPile.getPileSize(); i++){
                            currentPlayer.inHand.add(discardPile.removeCard());
                        }
                            System.out.println(YELLOW + "Discard pile emptied" + RESET);
                    }

                    // Replenish hand if necessary
                   replenish(currentPlayer);

                } else if (currentPlayer.canPlayFromFaceUpHand()){
                    System.out.println(currentPlayer.showOffHandFaceUp());
                    // TODO: Add similar logic to play from off hand (face up cards)
                } else if (currentPlayer.canPlayFromFaceDownHand()){
                    System.out.println(currentPlayer.showOffHandFaceDown());
                    // TODO: Add similar logic to play from off hand (face down cards)
                }
            } else {
                System.out.println("\nComputer's turn");
                System.out.println(currentPlayer);
                if (currentPlayer.canPlayFromInHand()){
                    System.out.println(YELLOW + "CPU to play from in-hand" + RESET);
                    Card cardSelected = currentPlayer.inHand.get(rand.nextInt(currentPlayer.inHand.size()));
                    System.out.println("Card selected: " + cardSelected);
                    if (checkValidPlay(cardSelected, discardPile)) {
                        currentPlayer.playFromHand(cardSelected, currentPlayer.inHand); // remove card from hand
                        discardPile.addCard(cardSelected); // add card to top of discard pile
                        System.out.println(discardPile); // show new discard pile
                    }
                    else{
                        System.out.println(RED + "CPU played card of lower rank." + RESET);
                        for (int i = 0; i <= discardPile.getPileSize(); i++){
                            currentPlayer.inHand.add(discardPile.removeCard());
                        }
                        System.out.println(YELLOW + "Discard pile emptied" + RESET);
                    }

                    // Replenish hand if necessary
                    replenish(currentPlayer);

                } else if (currentPlayer.canPlayFromFaceUpHand()){
                    System.out.println(YELLOW + "CPU to play from face up" + RESET);
                } else if (currentPlayer.canPlayFromFaceDownHand()){
                    System.out.println(YELLOW + "CPU to play from face down" + RESET);
                }
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

    private boolean checkValidPlay(Card playedCard, DiscardPile discardPile) {
        // Determines if the card that is to be played has a rank that is valid based on the discard pile given (higher rank except in the case of a 7)
        if (discardPile.getPileSize() == 0){
            return true; // since anything can be played on an empty discard pile
        }
        return (playedCard.getValue() >= discardPile.getTopCard().getValue());
    }

    public void replenish(Player currentPlayer) {
        // Check if in-hand already has 3 or more cards
        if (!currentPlayer.checkInHand()) {
            if (!deck.isEmpty()) {
                for (int i = 1; i <= (3 - currentPlayer.inHand.size()); i++) {
                    System.out.println(GREEN + "Card from deck added to hand" + RESET);
                    currentPlayer.inHand.add(deck.drawCard()); // add top card from deck to player hand
                }
            } else {
                System.out.println("Deck is empty.");
            }
        } else {
            System.out.println(YELLOW + "Player hand already contains 3 or more cards" + RESET);
        }
        System.out.println("New in-hand: " + currentPlayer.showInHand());
    }
}
