import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;

public class CardGame {
    private ArrayList<Card> deck;

    public CardGame() {
        deck = new ArrayList<Card>();

        // Create a standard deck of 52 cards
        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"}; //declares an array of strings called suits which contains the names of the four suits in a standard deck of playing cards: Hearts, Diamonds, Clubs, and Spades.
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};  //array of strings called ranks which contains the names of the thirteen ranks in a standard deck of playing cards: 2, 3, 4, 5, 6, 7, 8, 9, 10, Jack, Queen, King, and Ace.
        for (String suit : suits) {         //create a nested for loop that iterates through each suit and rank combination. For each suit and rank combination, a new Card object is created using the new keyword and the Card constructor, passing in the current suit and rank as arguments. Each Card object is added to a List object called deck.
            for (String rank : ranks) {
                deck.add(new Card(suit, rank));
            }
        }


    }
    public Card playCardFromDeck() { //that returns a Card object from the top of a deck of cards.
        if (deck.isEmpty()) {   // checks if the deck is empty by calling the isEmpty() method on the deck object. If the deck is empty, the method returns null, indicating that there are no more cards left in the deck to play.
            return null;
        } else {       // If the deck is not empty, the method removes the card at index 0 from the deck object using the remove(0) method, which removes and returns the first element in the list. This card is then returned by the method using the return keyword.
            return deck.remove(0);
        }
    }

    public void shuffle() {
        Collections.shuffle(deck);
    } //shuffles the deck of cards using the Collections.shuffle() method. This method randomly reorders the elements of the deck list, shuffling the cards.

    public ArrayList<Card>[] deal(int numPlayers) {   //deals the cards from the deck to a specified number of players. It takes an integer argument numPlayers that specifies the number of players to deal cards to.
        // Initialize an array to hold the hands of each player
        ArrayList<Card>[] hands = new ArrayList[numPlayers];
        for (int i = 0; i < numPlayers; i++) {  // create a new ArrayList<Card> object for each player and store it in the hands array. This creates an empty hand for each player.
            hands[i] = new ArrayList<Card>();
        }

        // Deal the cards randomly to each player
        Random random = new Random(); //generate random numbers for selecting cards from the deck.
        int remainingCards = deck.size(); // calculates the number of cards remaining in the deck using the deck.size() method and stores it in an integer variable called remainingCards.
        for (int i = 0; i < numPlayers; i++) {  // iterate over each player's hand in the hands array
            while (hands[i].size() < remainingCards / (numPlayers - i)) {  // add cards to the current player's hand until the hand contains the appropriate number of cards.
                Card card = deck.remove(random.nextInt(deck.size())); // generates a random integer between 0 (inclusive) and the size of the deck (exclusive), which corresponds to a random index of a card in the deck.
                hands[i].add(card); // selected card is then removed from the deck using the remove() method and added to the current player's hand using the add() method.
            }
            remainingCards -= hands[i].size();  // subtract the number of cards added to the current player's hand, and the loop continues to deal cards to the next player.
        }

        return hands;
    }

    public void addCard(Card card) {
        deck.add(card);
    } // adds a card to the deck by calling the add() method on the deck ArrayList.

    public Card playCardFromHand(ArrayList<Card> hand, int index) {
        return hand.remove(index);
    } // removes a card from a specified index of a given hand.

    public Iterator<Card> iterator() {
        return deck.iterator();
    } // method returns an iterator over the deck of cards by calling the iterator() method on the deck ArrayList.
    public Iterator<Card> suitIterator(String suit) {  // returns an iterator over the cards in the deck that belong to a specified suit
        ArrayList<Card> cardsInSuit = new ArrayList<Card>(); // creates a new ArrayList<Card> called cardsInSuit to hold the cards of the specified suit
        for (Card card : deck) { // to iterate over the cards in the deck and add any cards that belong to the specified suit to the cardsInSuit ArrayList.
            if (card.getSuit().equals(suit)) { //
                cardsInSuit.add(card);
            }
        }
        return cardsInSuit.iterator();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        CardGame game = new CardGame(); //creates a new cardgame object called name
        System.out.println("Initial deck:");
        System.out.println(game.getDeck());

        game.shuffle();
        System.out.println("Shuffled deck:");
        System.out.println(game.getDeck());

        System.out.print("Enter the number of players: ");
        int numPlayers = scanner.nextInt();

        ArrayList<Integer> numDealings = new ArrayList<>(); //used to keep track of how many cards are dealt to each player.
        int totalDealings = 0;

        for (int i = 1; i <= numPlayers; i++) {
            System.out.print("Enter the number of dealings for player " + i + ": ");
            int numDeals = scanner.nextInt();
            numDealings.add(numDeals); // stores the number of dealings for each player
            totalDealings += numDeals; // track of the total number of dealings
        }

        if (totalDealings > game.getDeck().size()) { //otal number of dealings is greater than the size of the deck of cards available in the game.
            System.out.println("Not enough cards in the deck to deal " + totalDealings + " cards.");
            return; //prints an error message and returns
        }

        ArrayList<Card>[] hands = new ArrayList[numPlayers]; //If there are enough cards in the deck to deal, the program initializes an array of ArrayLists called hands, where each ArrayList represents the cards in a player's hand.
        for (int i = 0; i < numPlayers; i++) {
            hands[i] = new ArrayList<Card>();
        }

        int currentPlayer = 0;
        for (int numDeals : numDealings) { // contains the number of dealings for each player in the game.
            for (int i = 0; i < numDeals; i++) {
                Card card = game.playCardFromDeck(); // calls the playCardFromDeck method to get a card from the deck
                hands[currentPlayer].add(card); // adds that card to the ArrayList representing the current player's hand
            }
            currentPlayer++;
        }

        System.out.println("Player hands before playing:"); //prints the initial hands of each player
        for (int i = 0; i < hands.length; i++) {
            System.out.println("Player " + (i + 1) + ": " + hands[i]);
        }

        for (int round = 1; round <= 13; round++) {
            System.out.println("Round " + round + ":");

            // Simulate playing the game
            for (int i = 0; i < hands.length; i++) { // loops through each players hand
                Collections.sort(hands[i]); //sorts the player's hand
                System.out.print("Player " + (i + 1) + ", enter the index of the card you want to play, or enter -1 to add a card to your hand: ");
                int index = scanner.nextInt();
                if (index == -1) {
                    System.out.print("Enter the rank of the card you want to add (2-10, Jack, Queen, King, or Ace): ");
                    String rank = scanner.next();
                    System.out.print("Enter the suit of the card you want to add (Hearts, Diamonds, Clubs, or Spades): ");
                    String suit = scanner.next();
                    Card cardToAdd = new Card(suit, rank);
                    hands[i].add(cardToAdd);
                    System.out.println("Player " + (i + 1) + " added: " + cardToAdd);
                } else {
                    Card cardToPlay = game.playCardFromHand(hands[i], index);
                    System.out.println("Player " + (i + 1) + " played: " + cardToPlay);
                    game.addCard(cardToPlay);
                }
            }

            // Sort the cards in each player's hand after each round
            for (int i = 0; i < hands.length; i++) {
                Collections.sort(hands[i]);
            }

            // Print the updated player hands
            for (int i = 0; i < hands.length; i++) {
                System.out.println("Player " + (i + 1) + ": " + hands[i]);
            }

            // Print the cards left in the deck after each round
            System.out.println("Cards left in deck after round " + round + ": " + game.getDeck().size());

            // Check if the game is over
            if (game.getDeck().isEmpty()) {
                System.out.println("Game over!");
                break;
            }
        }

        // Print the final state of the deck and player hands
        System.out.println("Final deck: " + game.getDeck());
        System.out.println("Player hands at end of game:");
        for (int i = 0; i < hands.length; i++) {
            System.out.println("Player " + (i + 1) + ": " + hands[i]);
        }
    }



    public ArrayList<Card> getDeck() {
        return deck;
    }
}