package poker.cardDeck;


public enum Rank {
    TWO("2", 2),
    THREE("3", 3),
    FOUR("4", 4),
    FIVE("5", 5),
    SIX("6", 6),
    SEVEN("7", 7),
    EIGHT("8", 8),
    NINE("9", 9),
    TEN("T", 10),
    JACK("J", 11),
    QUEEN("Q", 12),
    KING("K", 13),
    ACE("A", 14);

    private String _str;
    private Integer _num;
    Rank(String s, Integer i) {
        _str=s;
        _num=i;
    }

    public Integer toInteger() {
        return _num;
    }
    @Override
    public String toString() {
        return _str;
    }
}
