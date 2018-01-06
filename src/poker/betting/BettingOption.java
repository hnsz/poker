package poker.betting;

public abstract class BettingOption {
    private Integer _amount;
    public BettingOption(Integer amount) {
        _amount = amount;
    }
    public void setAmount(Integer amount) {
        _amount = amount;
    }
    public Integer getAmount() {
        return _amount;
    }

}