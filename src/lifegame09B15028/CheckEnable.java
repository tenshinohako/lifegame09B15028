package lifegame09B15028;

import javax.swing.JButton;

public class CheckEnable implements BoardListener{
	
	private JButton undo;
	
	public CheckEnable(JButton u){
		undo = u;
	}
	
	@Override
	public void updated(BoardModel model){
		undo.setEnabled(model.isUndoable());
	}
}
