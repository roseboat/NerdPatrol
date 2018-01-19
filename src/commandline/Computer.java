package commandline;

public class Computer extends Player {

	public Computer(String name, Deck playerDeck) {
		super(name, playerDeck);

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
