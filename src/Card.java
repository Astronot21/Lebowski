public class Card {
    private final Rank value;
    private final Suit suit;

    public Card(Rank value, Suit suit){
        this.value = value;
        this.suit = suit;
    }

    public enum Rank{
        TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8), NINE(9), TEN(10),
        JACK(11), QUEEN(12), KING(13), ACE(14);

        private final int value;

        Rank(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    public enum Suit{
        HEARTS, DIAMONDS, CLUBS, SPADES
    }

    public int getValue(){
        return value.getValue();
    }

    public Suit getSuit(){
        return suit;
    }

    @Override
    public String toString(){
        return (value + "-" + suit);
    }
}
