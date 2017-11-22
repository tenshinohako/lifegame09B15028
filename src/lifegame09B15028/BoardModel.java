package lifegame09B15028;

import java.util.ArrayList;
import java.util.Arrays;

public class BoardModel {
	private int cols;
	private int rows;
	private boolean[][] cells;

	private ArrayList<BoardListener> listeners;
	private ArrayList<boolean[][]> undoList = new ArrayList<boolean[][]>();

	public BoardModel(int c, int r){
		cols = c;
		rows = r;

		cells = new boolean[rows][cols];

		listeners = new ArrayList<BoardListener>();
	}

	public int getCols(){
		return cols;
	}

	public int getRows(){
		return rows;
	}

	public void changeCellState(int x, int y){
		cells[y][x] = !cells[y][x];
		this.fireUpdate();
	}

	public void addListener(BoardListener listener){
		listeners.add(listener);
	}

	private void fireUpdate(){
		for(BoardListener listener: listeners){
			listener.updated(this);
		}
	}

	public void next(){
		int numOfLiveAround;
		boolean[][] tempCells = new boolean[rows][cols];

		addCellsToList();

		for(int i=0; i<rows; i++){
			for(int j=0; j<cols; j++){

				numOfLiveAround = 0;

				for(int k=0; k<3; k++){
					for(int m=0; m<3; m++){
						if(0 <= i-1+k && i-1+k < rows && 0 <= j-1+m && j-1+m < cols){
							if(k != 1 || m != 1){
								if(cells[i-1+k][j-1+m] == true){
									numOfLiveAround++;
								}
							}
						}
					}
				}

				if(cells[i][j] == true){
					if(numOfLiveAround == 2 || numOfLiveAround == 3){
						tempCells[i][j] = true;
					}else{
						tempCells[i][j] = false;
					}
				}else{
					if(numOfLiveAround == 3){
						tempCells[i][j] = true;
					}else{
						tempCells[i][j] = false;
					}
				}
			}
		}

		cells = tempCells;
		this.fireUpdate();
	}

	public void undo(){
		cells = undoList.get(undoList.size()-1);
		undoList.remove(undoList.size()-1);
		this.fireUpdate();
	}

	public boolean isUndoable(){
		if(undoList.size() <= 0){
			return false;
		}else{
			return true;
		}
	}

	public boolean isAlive(int x, int y){
		return cells[y][x];
	}

	public ArrayList<boolean[][]> cellsList(){
		return undoList;
	}

	public void addCellsToList(){
		if(undoList.size() > 31){
			undoList.remove(0);
		}
		undoList.add(cells);
	}

	public void cellsCopy(){
		boolean[][] tempCells = new boolean[rows][cols];
		for(int i=0; i<cols; i++){
			tempCells[i] = Arrays.copyOf(cells[i], rows);
		}
		cells = tempCells;
	}

}
