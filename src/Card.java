import java.util.Arrays;

public class Card implements Comparable<Card> {
    private String suit;
    private String rank;

    public Card(String suit, String rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public String getSuit() {
        return suit;
    }

    public String getRank() {
        return rank;
    }

    public String toString() {
        return rank + " of " + suit;
    }

    public int compareTo(Card other) {
        // Implement the compareTo method to allow sorting of Card objects
        // based on their rank
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};
        int thisRankIndex = Arrays.asList(ranks).indexOf(this.rank);
        int otherRankIndex = Arrays.asList(ranks).indexOf(other.rank);
        return Integer.compare(thisRankIndex, otherRankIndex);
    }
}