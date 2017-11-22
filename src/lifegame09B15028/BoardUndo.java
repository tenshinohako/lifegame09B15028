package lifegame09B15028;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BoardUndo implements ActionListener{
	
	private BoardModel model;
	
	public BoardUndo(BoardModel m){
		model = m;
	}
	
	public void actionPerformed(ActionEvent e){
		model.undo();		
	}
	
}
