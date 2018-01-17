package commandline;

import java.util.ArrayList;
import java.util.Collections;

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
		
		ArrayList<Integer> list = new ArrayList<Integer>(5);
		list.add(a);
		list.add(b);
		list.add(c);
		list.add(d);
	
	list.add(e);
		
		Collections.max(list);
}
}
