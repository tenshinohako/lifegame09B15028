package lifegame09B15028;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BoardNew implements ActionListener{
	
	private Main main;
	
	public BoardNew(Main m){
		main = m;
	}
	
	public void actionPerformed(ActionEvent e){
		main.run();
	}
}
