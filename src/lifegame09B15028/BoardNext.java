package lifegame09B15028;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BoardNext implements ActionListener{
	
	private BoardModel model;
	
	public BoardNext(BoardModel m){
		model = m;
	}
	
	public void actionPerformed(ActionEvent e){
		model.next();
	}
	
}
