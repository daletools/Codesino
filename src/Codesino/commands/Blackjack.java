package Codesino.commands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

import java.util.ArrayList;
import java.util.Collections;

public class Blackjack {
    Deck shoe;
    Deck dealerHand;
    Deck playerHand;
    Blackjack(SlashCommandInteractionEvent event) {
        shoe = new Deck(4);
        dealerHand = new Deck();
        playerHand = new Deck();
    }
}

class Player {

}

class Deck {
    private ArrayList<Card> cards;
    private ArrayList<Card> discard;

    Deck() {
        cards = new ArrayList<>();
    }

    Deck(int numDecks) {
        cards = new ArrayList<>();
        discard = new ArrayList<>();

        for (int i = 0; i < numDecks; i++) {
            for (int suit = 0; suit < 4; suit++) {
                String suitName = switch (suit) {
                    case 0 -> {
                        yield "Hearts";
                    }
                    case 1 -> {
                        yield "Clubs";
                    }
                    case 2 -> {
                        yield "Diamonds";
                    }
                    case 3 -> {
                        yield "Spades";
                    }
                    default -> throw new IllegalStateException("Unexpected value: " + suit);
                };
                for (int rank = 0; rank < 13; rank++) {
                    cards.add(new Card(rank, suitName));
                }
            }
        }

        Collections.shuffle(cards);
    }

    void shuffle() {
        Collections.shuffle(cards);
    }

    Card draw() {
        Card card = cards.remove(0);
        if (cards.isEmpty()) {
            cards.addAll(discard);
            discard.clear();
        }
        return card;
    }

    void discard(Card card) {
        discard.add(card);
    }

    int getBlackjackTotal() {
        int sum = 0;
        for (Card card : cards) {
            if (card.getRank() == 1) {
                sum += 11;
            } else {
                sum += Math.min(10, card.getRank());
            }
        }
        if (sum > 21) {
            for (Card card : cards) {
                if (card.getRank() == 1) {
                    sum -= 10;
                    if (sum <= 21) {
                        break;
                    }
                }
            }
        }
        return sum;
    }
}

class Card {
    private int rank;
    private String suit;

    Card(int rank, String suit) {
        this.rank = rank;
        this.suit = suit;
    }

    int getRank() {
        return rank + 1;
    }

    String getSuit() {
        return suit;
    }


}