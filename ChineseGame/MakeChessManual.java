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

	public void recordChessboard(ChessPiece piece, int startI, int startJ, int endI,
			int endJ) {
		Point pStart = new Point(startI, startJ);
		Point pEnd = new Point(endI, endJ);
		MoveStep step = new MoveStep(pStart, pEnd);
		Manual.add(step);

		String Chesstype = piece.Chesstype();
		String name = piece.getName();
		String m = "#" + Chesstype + name + ": " + startI + numberToLetter(startJ)
				+ " 到 " + endI + numberToLetter(endJ);
		text.append(m);
		if (piece.Chesstype().equals(board.blackcolor))
			text.append("\n");
	}

	public void recorddieChess(Object object) {
		dieChess.add(object);
	}

	public LinkedList getManual() {
		return Manual;
	}
	//悔棋
	public void actionPerformed(ActionEvent e) {
		int position = text.getText().lastIndexOf("#");
		if (position != -1)
			text.replaceRange("", position, text.getText().length());
		//移動
		if (Manual.size() > 0) {
			MoveStep lastStep = (MoveStep) Manual.getLast();
			Manual.removeLast();
			Object qizi = dieChess.getLast();
			dieChess.removeLast();
			String temp = qizi.toString();
			if (temp.equals("nodie")) {
				int startI = lastStep.pStart.x;
				int startJ = lastStep.pStart.y;
				int endI = lastStep.pEnd.x;
				int endJ = lastStep.pEnd.y;
				ChessPiece piece = point[endI][endJ].getPiece();

				point[startI][startJ].setPiece(piece, board);
				(point[endI][endJ]).sethaveChess(false);

				if (piece.Chesstype().equals(board.redcolor)) {
					board.redmove = true;
					board.blackmove = false;
				}
				if (piece.Chesstype().equals(board.blackcolor)) {
					board.blackmove = true;
					board.redmove = false;
				}
			} else { //吃子
				ChessPiece removedPiece = (ChessPiece) qizi;
				int startI = lastStep.pStart.x;
				int startJ = lastStep.pStart.y;
				int endI = lastStep.pEnd.x;
				int endJ = lastStep.pEnd.y;
				ChessPiece piece = point[endI][endJ].getPiece();
				point[startI][startJ].setPiece(piece, board);
				point[endI][endJ].setPiece(removedPiece, board);
				(point[endI][endJ]).sethaveChess(true);

				if (piece.Chesstype().equals(board.redcolor)) {
					board.redmove = true;
					board.blackmove = false;
				}
				if (piece.Chesstype().equals(board.blackcolor)) {
					board.blackmove = true;
					board.redmove = false;
				}
			}
		}
	}
}
