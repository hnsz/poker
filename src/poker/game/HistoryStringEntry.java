package poker.game;

public class HistoryStringEntry extends HistoryEntry{
    private String _str;

    HistoryStringEntry(String str) {
        _str = str;
    }
    public String writeString() {
        return _str;
    }
}
