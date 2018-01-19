package commandline;

public class Computer extends Player {

	public Computer(String name, Deck playerDeck) {
		super(name, playerDeck);

	}
	
	public void promptUser() {
    	drawCard(); // USER needs to draw a card before being prompted
	System.out.println("Choose your category!");
	String x = topCard.cardToString();
	System.out.println(x);
	
}
	public int chooseCategory() {

		int[] values = topCard.getAllValues();

		int max = values[0];
		int index = 0;

		for (int i = 1; i < values.length; i++) {
			if (max < values[i]) {
				max = values[i];
				index = i;
			}
		}
		return index;
	}
}
