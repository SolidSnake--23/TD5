package fr.umlv.card;

public class Wildcard extends Card {
	public Wildcard(int value, Suit suit) {
		super(value, suit);
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof Card;
	}
  
	@Override
	public String toString() {
		return super.toString(new StringBuilder("Joker"));
	}
}
