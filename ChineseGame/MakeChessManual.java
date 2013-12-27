package ChineseChess;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;

/**
 * 製作棋譜的類別
 */
public class MakeChessManual extends JPanel implements ActionListener {
	JTextArea text = null;
	JScrollPane scroll = null;
	ChessBoard board = null;
	ChessPoint[][] point;
	LinkedList Manual = null;
	LinkedList dieChess = null;
	JButton buttonUndo;
	int i = 0;

	public MakeChessManual(ChessBoard board, ChessPoint[][] point) {
		this.board = board;
		this.point = point;
		text = new JTextArea();
		scroll = new JScrollPane(text);
		Manual = new LinkedList();
		dieChess = new LinkedList();
		buttonUndo = new JButton("悔棋");
		buttonUndo.setFont(new Font("標楷體", Font.PLAIN, 18));
		setLayout(new BorderLayout());
		add(scroll, BorderLayout.CENTER);
		add(buttonUndo, BorderLayout.SOUTH);
		buttonUndo.addActionListener(this);
	}

	public char numberToLetter(int n) {
		char c = '\0';
		switch (n) {
		case 1:
			c = 'A';
			break;
		case 2:
			c = 'B';
			break;
		case 3:
			c = 'C';
			break;
		case 4:
			c = 'D';
			break;
		case 5:
			c = 'E';
			break;
		case 6:
			c = 'F';
			break;
		case 7:
			c = 'G';
			break;
		case 8:
			c = 'H';
			break;
		case 9:
			c = 'I';
			break;
		case 10:
			c = 'J';
			break;
		}
		return c;
	}

	public LinkedList getManual() {
		return Manual;
	}

	public void actionPerformed(ActionEvent e) {

	}
}
