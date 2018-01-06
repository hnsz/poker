package poker._test

import poker.Rank


class RankTest extends GroovyTestCase {
    void testCompareTo() {
        Rank H, L;
        H = Rank.ACE;
        L = Rank.KING;

        assert(H > L);
        assert(L < H);
        assert(L == Rank.KING);
        assert(H.compareTo(L) > 0);
        assert(L.compareTo(H) < 1);
        assert(H.compareTo(Rank.ACE) == 0);
    }

    void testEquals() {
        Rank H,L;
        H = Rank.ACE;
        L = Rank.KING;

        assert(H == Rank.ACE);
        assert(H != L);
        assert(H != Rank.TWO);
    }
}
