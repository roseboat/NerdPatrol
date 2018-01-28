package commandline;

/**
 * Computer class extends the Player class and it models the behaviour of a computer player.
 * 
 * */

public final class Computer extends Player {

	
	
	public Computer(String name, Deck playerDeck) {
		super(name, playerDeck);
	}
	
	public void promptUser() {
		
		StringBuilder computerTurn = new StringBuilder(name + "'s top card:\r\n");
		computerTurn.append(heldCard.cardToString());
		System.out.println(computerTurn);
	
}
	public int chooseCategory() {

		int[] values = heldCard.getAllValues();

		int max = values[0];
		int index = 0;

		for (int i = 0; i < values.length; i++) {
			if (max < values[i]) {
				max = values[i];
				index = i;
			}
		}
		System.out.println(getName()+" has chosen " + heldCard.getSelectedCategory(index));
		System.out.println();
	    setChosenCat(heldCard.getAllValues()[index]);
		return index;
	}
}
