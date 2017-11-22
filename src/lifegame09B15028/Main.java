package lifegame09B15028;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Main implements Runnable{

	public static void main(String[] args){
		SwingUtilities.invokeLater(new Main());
	}

	public void run(){
		BoardModel model = new BoardModel(20, 20);
		BoardView view = new BoardView(model);
		BoardNext next = new BoardNext(model);
		BoardUndo undo = new BoardUndo(model);
		BoardNew newButton = new BoardNew(this);
		GraphView gView = new GraphView(model);

		//グラフを表示するウィンドウ
		JFrame gframe = new JFrame();
		gframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		gframe.setTitle("Graph");
		gframe.setMinimumSize(new Dimension(300, 200));

		//グラフを表示するパネル
		JPanel graphPanel = new JPanel();
		gframe.setContentPane(graphPanel);
		graphPanel.setPreferredSize(new Dimension(800, 400));
		graphPanel.setLayout(new BorderLayout());
		graphPanel.add(gView, BorderLayout.CENTER);

		gframe.pack();
		gframe.setVisible(true);


		//ウィンドウを表示
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Lifegame");
		frame.setMinimumSize(new Dimension(300, 200));

		//盤面を描くパネル
		JPanel base = new JPanel();
		frame.setContentPane(base);
		base.setPreferredSize(new Dimension(700, 800));
		base.setLayout(new BorderLayout());
		base.add(view, BorderLayout.CENTER);


		//ボタンを設置するパネル
		JPanel buttonPanel = new JPanel();
		base.add(buttonPanel, BorderLayout.SOUTH);
		buttonPanel.setLayout(new FlowLayout());

		//Nextボタン
		JButton buttonNext = new JButton();
		buttonNext.setText("Next");
		buttonPanel.add(buttonNext);
		buttonNext.addActionListener(next);

		//Undoボタン
		JButton buttonUndo = new JButton();
		buttonUndo.setText("Undo");
		buttonUndo.setEnabled(false);
		buttonPanel.add(buttonUndo);
		buttonUndo.addActionListener(undo);

		//NewGameボタン
		JButton buttonNew = new JButton();
		buttonNew.setText("New Game");
		buttonPanel.add(buttonNew);
		buttonNew.addActionListener(newButton);

		frame.pack();
		frame.setVisible(true);

		model.addListener(new CheckEnable(buttonUndo));
		model.addListener(view);
		model.addListener(gView);

	}
}