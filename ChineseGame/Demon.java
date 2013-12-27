package ChineseChess;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

/**
 * 演示棋譜
 */
public class Demon extends JPanel implements ActionListener {
	public JButton replay = null, next = null;
	LinkedList Manual = null;
	int index = -1;
	ChessBoard board = null;
	JTextArea text;
	JSplitPane splitH = null, splitV = null;

	public Demon(ChessBoard board) {
		this.board = board;
		replay = new JButton("重新演示");
		next = new JButton("下一步");
		replay.addActionListener(this);
		next.addActionListener(this);
		text = new JTextArea();
		setLayout(new BorderLayout());
		JScrollPane pane = new JScrollPane(text);
		JPanel p = new JPanel(new GridLayout(3, 2));
		p.add(next);
		p.add(replay);
		splitV = new JSplitPane(JSplitPane.VERTICAL_SPLIT, pane, p);
		splitH = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, board, splitV);
		splitV.setDividerSize(5);
		splitV.setDividerLocation(400);
		splitH.setDividerSize(5);
		splitH.setDividerLocation(460);
		add(splitH, BorderLayout.CENTER);
		validate();
	}

	public void setManual(LinkedList Manual) {
		this.Manual = Manual;
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

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == next) {
			index++;
			if (index < Manual.size()) {
				playonestep(index);
			} else {
				playover("棋譜演示完畢");
			}
		}
		if (e.getSource() == replay) {
			board = new ChessBoard(45, 45, 9, 10);
			splitH.remove(board);
			splitH.setDividerSize(5);
			splitH.setDividerLocation(460);
			splitH.setLeftComponent(board);
			splitH.validate();
			index = -1;
			text.setText(null);
		}

	}

	

	public void playonestep(int index) {
		MoveStep step = (MoveStep) Manual.get(index);
		Point pStart = step.pStart;
		Point pEnd = step.pEnd;
		int startI = pStart.x;
		int startJ = pStart.y;
		int endI = pEnd.x;
		int endJ = pEnd.y;
		ChessPiece piece = (board.point)[startI][startJ].getPiece();
		if ((board.point)[endI][endJ].isPiece() == true) {
			ChessPiece pieceRemoved = (board.point)[endI][endJ].getPiece();
			(board.point)[endI][endJ].reMovePiece(pieceRemoved, board);
			board.repaint();
			(board.point)[endI][endJ].setPiece(piece, board);
			(board.point)[startI][startJ].sethaveChess(false);
			board.repaint();
		} else {
			(board.point)[endI][endJ].setPiece(piece, board);
			(board.point)[startI][startJ].sethaveChess(false);

		}
		String Chesstype = piece.Chesstype();
		String name = piece.getName();
		String m = "#" + Chesstype + name + ": " + startI + numberToLetter(startJ)
				+ " 到 " + endI + numberToLetter(endJ);
		text.append(m);
		if (piece.Chesstype().equals(board.blackcolor))
			text.append("\n");
	}

	public void playover(String message) {
		splitH.remove(board);
		splitH.setDividerSize(5);
		splitH.setDividerLocation(460);
		JLabel label = new JLabel(message);
		label.setFont(new Font("標楷體", Font.BOLD, 40));
		label.setForeground(Color.blue);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		splitH.setLeftComponent(label);
		splitH.validate();
	}
}