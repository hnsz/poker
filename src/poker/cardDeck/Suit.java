package poker.cardDeck;

public enum Suit {
    SPADES,
    HEARTS,
    DIAMONDS,
    CLUBS;

    @Override
    public String toString() {
        switch (this) {
            case SPADES:
                return "\u2660";
            case HEARTS:
                return "\u2665";
            case DIAMONDS:
                return "\u2666";
            case CLUBS:
                return "\u2663";
            default:
                return "";
        }
    }
}
