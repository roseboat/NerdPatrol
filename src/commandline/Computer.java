package commandline;

public class Computer extends Player {

	public Computer(String name, Deck playerDeck) {
		super(name, playerDeck);

	}


	public int chooseCategory() {
		
		int a = topCard.getCargo();
		int b = topCard.getfirePower();
		int c = topCard.getRange();
		int d = topCard.getSize();
		int e = topCard.getSpeed();
		
		int[] arraylist= new int[] {a,b,c,d,e};
		
		arraylist.max();
		
		
		
	
	}

}
