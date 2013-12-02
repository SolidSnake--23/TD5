package fr.umlv.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
	private ArrayList<Card> cards;
	
	/*
	 * QUESTION 2
	public Deck(int nbCardPerSuit, Suit[] suits) {
		cards = new ArrayList<Card>();
		// Pour chaque couleur 
		for(Suit s : suits) {
			// Pour tout les nombres
			for(int i=1; i <= nbCardPerSuit; i++) {
				cards.add(new Card(i, s));
			}
		}
	}*/

	/* 
	 * QUESTION 3
	public Deck(int nbCardPerSuit, Suit... suits) {
		cards = new ArrayList<Card>();
		// Pour chaque couleur 
		for(Suit s : suits) {
			// Pour tout les nombres
			for(int i=1; i <= nbCardPerSuit; i++) {
				cards.add(new Card(i, s));
			}
		}
	}*/

	/* 
	 * QUESTION 4*/
	private Deck() {
		cards = new ArrayList<Card>();
	}

	public static Deck createDeck(int nbCardPerSuit, Suit... suits) {
		Deck deck = new Deck();
		// Pour chaque couleur 
		for(Suit s : suits) {
			// Pour tout les nombres
			for(int i=1; i <= nbCardPerSuit; i++) {
				deck.cards.add(new Card(i, s));
			}
		}
		return deck;
	}

	public static Deck createShuffleDeck(int nbCardPerSuit, Suit... suits) {
		Deck deck = Deck.createDeck(nbCardPerSuit, suits);
		Collections.shuffle(deck.cards);
		return deck;
	}

	public static Deck createShuffleDeck(int nbwild, int nbCardPerSuit, Suit... suits) {
		Deck deck = Deck.createDeck(nbwild, nbCardPerSuit, suits);
		Collections.shuffle(deck.cards);
		return deck;
	}
	
	public List<Deck> splitDeck() {
		List<Deck> newDecks = new ArrayList<Deck>();
		newDecks.add(new Deck());
		newDecks.add(new Deck());
		List<Card> cards = this.getCardList();
		int sizeList = cards.size();
		
		// Nombre de cartes pair
		if(this.cards.size()%2 != 0)
			throw new IllegalArgumentException("The number of cards can't be split");
		
		// Première partie des cartes dans le premier Deck et la seconde dans l'autre Deck
		for(int i=0; i < sizeList; i++) {
			if(i < sizeList/2) {
				newDecks.get(0).cards.add(cards.get(i));
			} else {
				newDecks.get(1).cards.add(cards.get(i));
			}
		}
		
		return newDecks;
	}
	
	
	public static int fightWinner(List<Deck> decks) {
		Deck deckAlpha = decks.get(0);
		Deck deckBeta  = decks.get(1);
		
		// Le joueur Alpha gagne
		if(deckBeta.cards.size() == 0)
					return 0;
		// Le joueur Beta gagne
		if(deckAlpha.cards.size() == 0)
					return 1;
		// Pas de gagnant
		return 3;
	}
	
	public static int fight(List<Deck> decks) {
		Deck deckAlpha = decks.get(0);
		Deck deckBeta  = decks.get(1);

		fightCards(deckAlpha, deckBeta);
		
		return fightWinner(decks);
	}
	
	public static int fightCards(Deck deckAlpha, Deck deckBeta) {
		int lastCardAlpha, lastCardBeta;
		lastCardAlpha = 0;
		lastCardBeta  = 0;
		
		if(deckAlpha.cards.get(lastCardAlpha).
			upperThan(deckBeta.cards.get(lastCardBeta))) {
			// Ajoute la derniere carte d'un Deck à l'autre
			deckAlpha.cards.add(deckBeta.cards.get(lastCardBeta));
			// Suppression de la derniere carte
			deckBeta.cards.remove(lastCardBeta);
			return 0;
		} 
		if(deckBeta.cards.get(lastCardBeta).
			upperThan(deckAlpha.cards.get(lastCardAlpha))) {
			// Ajoute la derniere carte d'un Deck à l'autre
			deckBeta.cards.add(deckAlpha.cards.get(lastCardAlpha));
			// Suppression de la derniere carte
			deckAlpha.cards.remove(lastCardAlpha);
			return 1;
		} 
		
		// Temporisation des cartes égales
		Card cardAlpha = deckAlpha.cards.get(lastCardAlpha);
		Card cardBeta  = deckBeta.cards.get(lastCardBeta);
		deckBeta.cards.remove(lastCardBeta);
		deckAlpha.cards.remove(lastCardAlpha);
		
		/* ============= 
		 * 
		 * EXECPTION SI LES CARTES EGALES SONT LES DERNIERES
		 * 
		 * =============
		 */
		
		if(deckBeta.cards.size() == 0 || deckAlpha.cards.size() == 0) {
			// Beta perds
			if(deckBeta.cards.size() == 0)	{
				return 1;
			}
			// Beta gagne
			return 0;
		}
		
		
		// Nouveau tour
		switch(fightCards(deckAlpha, deckBeta)) {
		// Rajout des cartes temporisées suivant le nouveau tour
		case 0:
			deckAlpha.cards.add(cardBeta);
			deckAlpha.cards.add(cardAlpha);
			return 0;
		case 1:
			deckBeta.cards.add(cardBeta);
			deckBeta.cards.add(cardAlpha);
			return 1;
		}
		
		return 3;
		
	}
	
	

	/* 
	 * EX 3.3
	public static Deck createDeck(int nbwild, int nbCardPerSuit, Suit... suits) {
		Deck deck = new Deck();
		// Pour chaque couleur 
		for(Suit s : suits) {
			// Pour tout les nombres
			for(int i=1; i <= nbCardPerSuit; i++) {
				deck.cards.add(new Card(i, s));
			}
		}
		for(int i=1; i <= nbwild; i++) {
			deck.cards.add(new Wildcard(i, HEART));
		}
		return deck;
	}*/

	public static Deck createDeck(int nbwild, int nbCardPerSuit, Suit... suits) {
		Deck deck = new Deck();
		// Pour chaque couleur 
		for(Suit s : suits) {
			// Pour tous les joker
			for(int i=1; i <= nbwild; i++) {
				deck.cards.add(new Wildcard(i, s));
			}
			// Pour tout les nombres
			for(int i=1; i <= nbCardPerSuit; i++) {
				deck.cards.add(new Card(i, s));
			}
		}
		return deck;
	}

	/*
	 * QUESTION 7
	 */
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Deck))
			return false;

		Deck deck = (Deck) obj;
		return cards.equals(deck.cards);
	}

	/*
	 * Ex2
	 */
	public List<Card> getCardList() {
		/*
		 * EX2.2
		return (List<Card>) cards.clone();*/
		return Collections.unmodifiableList(cards);
	}

	@Override 
	public String toString() {
		StringBuilder str = new StringBuilder();
		for(Card c : cards) {
			str.append(c.toString()).append("\n");
		}
		return str.toString();
	}

}
