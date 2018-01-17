package commandline;

public class Computer extends Player {

	public Computer(String name, Deck playerDeck) {
		super(name, playerDeck);

	}

	public int chooseCategory() {

		int[] values = topCard.getAllValues();

		int index = 0;

		for (int i = 0; i < values.length; i++) {
			for (int j = 1; j < values.length; j++) {
				if (values[i] < values[j]) {
					index++;
				}
			}
		}
		return index;
	}

}
