package fr.umlv.card;

public class Card {
	private final int value;
	private final Suit suit;

	public Card(int value, Suit suit) {
		this.value = value;
		this.suit = suit;
	}
	
	public boolean upperThan(Card card) {
		// Cartes identiques
		if(this.value == card.value)
			return false;
		// Cas des As
		if(this.value == 1) // la carte est un As
			return true;
		if(card.value == 1) // l'autre carte est un As
			return false;
		// Autres valeurs
		return this.value > card.value;
	}
	
	public boolean lowerThan(Card card) {
		// Cartes identiques
		if(this.value == card.value)
			return false;
		return !this.upperThan(card); // Inverse de l'autre cas
	}
	
	public boolean equalsThan(Card card) {
		return !this.upperThan(card) && !this.lowerThan(card);
	}
  
	/*
	 * Question 8
	 */
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Card))
			return false;

		Card card = (Card) obj;
		return value == card.value 
			&& suit == card.suit;
	}
	
	public Suit getSuit() {
		return suit;
	}
	
	public int getValue() {
		return value;
	}
	
	protected String toString(StringBuilder value) {
		value.append(" of ");
		value.append(suit);
		return value.toString();
	}

	@Override
	public String toString() {
		StringBuilder stringbuilder = new StringBuilder();
		switch (value) {
			default:	stringbuilder.append(value);		break;
			case 1:		stringbuilder.append("As");			break;
			case 11:	stringbuilder.append("Jack");		break;
			case 12:	stringbuilder.append("Queen");		break;
			case 13:	stringbuilder.append("King");		break;
		}
		return this.toString(stringbuilder);
	}
}