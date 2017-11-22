package lifegame09B15028;

import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

public class GraphView extends JPanel implements BoardListener{
	private BoardModel model;

	public GraphView(BoardModel m){
		model = m;

	}

	@Override
	public void paint(Graphics g){
		super.paint(g);

		int maxCells = model.getCols() * model.getRows();
		float heightPerCell = (float)this.getHeight() / (maxCells + 1);

		ArrayList<boolean[][]> list = model.cellsList();
		int k = 1;
		int width = this.getWidth() / 33;
		int previousX = 0, previousY = 0, nowX, nowY;


		for(boolean[][] cells: list){
			int num = maxCells;
			for(int i=0; i<model.getCols(); i++){
				for(int j=0; j<model.getRows(); j++){
					if(!cells[j][i]){
						num--;
					}
				}
			}

			nowX = k * width;
			nowY =getHeight() - (int)((num + 10) * heightPerCell);
			g.fillRect(nowX, nowY, 3, 3);
			if(previousX != 0){
				g.drawLine(previousX + 1, previousY + 1, nowX + 1, nowY + 1);
			}
			previousX = nowX;
			previousY = nowY;
			k++;
		}

		int num = maxCells;
		for(int i=0; i<model.getCols(); i++){
			for(int j=0; j<model.getRows(); j++){
				if(!model.isAlive(i, j)){
					num--;
				}
			}
		}
		nowX = k * width;
		nowY =getHeight() - (int)((num + 10) * heightPerCell);

		g.fillRect(nowX, nowY, 3, 3);
		if(previousX != 0){
			g.drawLine(previousX + 1, previousY + 1, nowX + 1, nowY + 1);
		}
	}

	public void updated(BoardModel model){
		this.repaint();
	}
}
