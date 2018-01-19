package commandline;

public class Computer extends Player {

	public Computer(String name, Deck playerDeck) {
		super(name, playerDeck);

	}

	//modified this from the original to set the values within player instead
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
	
	@Override
	public void altChooseCategory(){
	    int[] values = topCard.getAllValues();

	    int max = values[0];
	    int index = 0;

	    for (int i = 1; i < values.length; i++) {
		if (max < values[i]) {
		    max = values[i];
		    index = i;
		}
	    }
	    
	    setChosenCatIndex(index);
	    setChosenCat(values[index]);
	    System.out.println("##################" + getName() + "###################");
	    System.out.println(topCard.cardToString());
	    System.out.println(getName() + " has chosen " + topCard.getSelectedCategory(index));
	}

	@Override
	public void promptUser() {
	    // TODO Auto-generated method stub
	    
	}
}
