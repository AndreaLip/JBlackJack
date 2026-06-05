package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

/**
 * The BJCards class represents a deck of cards used in a blackjack game.
 * It includes methods for shuffling the deck and dealing cards.
 */
public class BJCards
{
	/**
     * The list containing the deck of cards.
     */
    private List<Card> deck = new ArrayList<>();

    /**
     * Constructs a new BJCards object and initializes the deck with 6 sets of 52 cards each.
     * The deck is shuffled after being created.
     */
    public BJCards() 
    {
        String[] suits = {"clubs", "hearts", "spades", "diamonds"};
        
        for (int i = 0; i < 6; i++)
        {
            int index = 0;   
            while (index < 52) 
            {
                for (int value = 1; value <= 13; value++)
                    for (int suitIndex = 0; suitIndex < 4; suitIndex++) 
                    {
                        deck.add(new Card(suits[suitIndex], value));
                        index++;
                    }
            }
        }          
        shuffle(); // Shuffles the deck after creating it
    }

    /**
     * Gets the number of cards remaining in the deck.
     * 
     * @return the number of cards in the deck
     */
    public int countCards() 
    {
        return deck.size();
    }
    
    /**
     * Gets the card at the specified index.
     * 
     * @param index the index of the card to retrieve
     * @return the card at the specified index
     */
    public Card getCard(int index) 
    {
        return deck.get(index);
    }

    /**
     * Shuffles the deck of cards.
     * The deck is shuffled twice to ensure randomness.
     */
    public void shuffle() 
    {
        Random random = new Random();
        int deckSize = deck.size();
        int j = 0;
        while (j < 2)
        {
            for (int i = deckSize - 1; i > 0; i--) 
            {
                int randomIndex = random.nextInt(i + 1);
                // Swap elements
                Card temp = deck.get(i);
                deck.set(i, deck.get(randomIndex));
                deck.set(randomIndex, temp);
            }
            j++;
        }    
    }

    /**
     * Deals a card from the top of the deck.
     * The card is removed from the deck.
     * 
     * @return the dealt card
     */
    public Card dealCard() 
    {
        Card card = deck.get(0);
        deck = deck.subList(1, deck.size());
        return card;
    }
    
    /**
     * Generates a hash code for the BJCards object based on the deck of cards.
     * 
     * @return the hash code
     */
    @Override
    public int hashCode()
    {
        return Objects.hash(deck);
    }

    /**
     * Compares this BJCards object to the specified object for equality.
     * 
     * @param obj the object to compare with
     * @return true if the decks are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        BJCards other = (BJCards) obj;
        return Objects.equals(deck, other.deck);
    }

    /**
     * The CardSuits enum represents the four suits of cards.
     */
    public enum CardSuits 
    {
        CLUBS, SPADES, DIAMONDS, HEARTS;
    }

    /**
     * The CardValues enum represents the values of cards.
     */
    public enum CardValues 
    {
        ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING;
    }

    /**
     * The Card class represents a single card in the deck.
     * It includes the suit, value, and blackjack value of the card.
     */
    public class Card 
    {
        private CardSuits suit;
        private CardValues value;
        private int jbvalue;

        /**
         * Constructs a new Card with the specified suit and value.
         * 
         * @param suit the suit of the card (clubs, spades, diamonds, hearts)
         * @param value the value of the card (1 to 13)
         */
        public Card(String suit, int value) 
        {
            this.suit = switch (suit) 
            {
                case "clubs" -> this.suit = CardSuits.CLUBS;
                case "spades" -> this.suit = CardSuits.SPADES;
                case "diamonds" -> this.suit = CardSuits.DIAMONDS;
                case "hearts" -> this.suit = CardSuits.HEARTS;
                default -> this.suit = null;
            };
            
            this.value = switch (value) 
            {
                case 1 -> this.value = CardValues.ACE;
                case 2 -> this.value = CardValues.TWO;
                case 3 -> this.value = CardValues.THREE;
                case 4 -> this.value = CardValues.FOUR;
                case 5 -> this.value = CardValues.FIVE;
                case 6 -> this.value = CardValues.SIX;
                case 7 -> this.value = CardValues.SEVEN;
                case 8 -> this.value = CardValues.EIGHT;
                case 9 -> this.value = CardValues.NINE;
                case 10 -> this.value = CardValues.TEN;
                case 11 -> this.value = CardValues.JACK;
                case 12 -> this.value = CardValues.QUEEN;
                case 13 -> this.value = CardValues.KING;                    
                default -> this.value = null;
            };
            
            if (value <= 10) this.jbvalue = value;
            else if (value > 10) this.jbvalue = 10;
        }

        /**
         * Generates a hash code for the Card object based on its value.
         * 
         * @return the hash code
         */
        @Override
        public int hashCode()
        {
            final int prime = 31;
            int result = 1;
            result = prime * result + getEnclosingInstance().hashCode();
            result = prime * result + Objects.hash(value);
            return result;
        }

        /**
         * Compares this Card object to the specified object for equality.
         * 
         * @param obj the object to compare with
         * @return true if the cards are equal, false otherwise
         */
        @Override
        public boolean equals(Object obj)
        {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            Card other = (Card) obj;
            if (!getEnclosingInstance().equals(other.getEnclosingInstance()))
                return false;
            return value == other.value;
        }

        /**
         * Gets the blackjack value of the card.
         * 
         * @return the blackjack value of the card
         */
        public int getJbvalue()
        {
            return jbvalue;
        }

        /**
         * Returns a string representation of the card, including its value and suit.
         * 
         * @return a string representation of the card
         */
        @Override
        public String toString() 
        {
            return value + " " + suit;
        }

        /**
         * Gets the enclosing BJCards instance.
         * Useful for accessing external class fields.
         * @return the enclosing BJCards instance
         */
        private BJCards getEnclosingInstance()
        {
            return BJCards.this;
        }
    }
}