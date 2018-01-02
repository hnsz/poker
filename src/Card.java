public class Card {
    private String _rank;
    private String _suit;

    public Card(String rank, String suit)
    {
        _rank = rank;
        _suit = suit;
    }

    @Override
    public String toString() {
        return "Card{" +
                "_rank='" + _rank + '\'' +
                ", _suit='" + _suit + '\'' +
                '}';
    }
}
