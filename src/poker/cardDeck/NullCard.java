package poker.cardDeck;

public class NullCard extends Card {

    public NullCard() {
        super(null, null);
    }

    @Override
    public Integer toInteger() {
        return 0;
    }

}
