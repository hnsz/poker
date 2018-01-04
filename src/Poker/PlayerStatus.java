package Poker;

public enum PlayerStatus {
    WAITING_FOR_GAME,
    BUYING_IN,

    SITTING_OUT,
    INACTIVE,
    DISCONNECTED,
    WAITING_FOR_BB,
    POSTED,
    PLAYING,
    IN_HAND,

    FOLD,
    MUCK,
    CHECK,
    CALL,
    BET,
    RAISE,
    RERAISE,
    ALL_IN
}
