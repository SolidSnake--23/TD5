package fr.umlv.card;

import static fr.umlv.card.Suit.CLUB;
import static fr.umlv.card.Suit.HEART;
import static fr.umlv.card.Suit.SPADE;
import static fr.umlv.card.Suit.DIAMOND;

import java.util.List;

public class Main {

	public static void main(String[] args) {
		int winner;
		StringBuilder str = new StringBuilder();
		/*
		 * QUESTION POUH
		Suit[] suits = {CLUB, HEART};
		List<Deck> deck = new ArrayList<Deck>();
		deck.add(Deck.createShuffleDeck(13, HEART, CLUB));
		deck.add(Deck.createShuffleDeck(13, SPADE, DIAMOND));*/
		Deck deck = Deck.createShuffleDeck(13, HEART, CLUB, SPADE, DIAMOND);
		List<Deck> playerDeck = deck.splitDeck();
		
		str.append("Deck 1 : \n").append(playerDeck.get(0))
			.append("Deck 2 : \n").append(playerDeck.get(1));
		// Jouer tant que le jeu n'est pas fini
		while((winner = Deck.fight(playerDeck)) == 3) {
			str.append("\n------\n")
				.append(playerDeck.get(0).getCardList().get(0))
				.append(" vs ")
				.append(playerDeck.get(1).getCardList().get(0))
				.append("\nTailles : ")
				.append(playerDeck.get(0).getCardList().size())
				.append(" ")
				.append(playerDeck.get(1).getCardList().size());
		} 
		str.append("\nWinner : Joueur ").append(winner+1);
		// Affichage du jeu
		System.out.println(str);
		
		/*
		 * EX 2
		List<Card> cards = deck.getCardList();
		cards.remove(0);
		System.out.println(deck.getCardList());
		System.out.println(cards);*/
	}
}
