package ChineseChess;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * 棋子
 */
public class ChessPiece extends JLabel {
	String name; // 棋子名字
	Color backColor = null, foreColor;// 背景色和前景色
	String colortype = null;
	ChessBoard board = null;
	int width, height;// 大小

	public ChessPiece(String name, Color fc, Color bc, int width, int height,
			ChessBoard board) {// 棋子構造
		this.name = name;
		this.board = board;
		this.width = width;
		this.height = height;
		foreColor = fc;
		backColor = bc;
		setSize(width, height);
		setBackground(bc);
		addMouseMotionListener(board);
		addMouseListener(board);
	}

	// 繪製棋子
	public void paint(Graphics g) {		
		g.drawImage(board.pieceImg, 2, 2, width-2, height-2, null);
		g.setColor(foreColor);
		g.setFont(new Font("標楷體", Font.BOLD, 26));
		g.drawString(name, 7, height - 8);// 在棋子上繪製棋子名稱
		g.setColor(Color.black);
		//g.drawOval(1, 1, width - 1, height - 1);
		float lineWidth = 2.3f;
	    ((Graphics2D)g).setStroke(new BasicStroke(lineWidth));
	    ((Graphics2D)g).drawOval(2, 2, width-2, height-2);
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public String getName() {
		return name;
	}

	public Color getChesscolor() {
		return foreColor;
	}

	public void setChesstype(String type) {
		colortype = type;
	}

	public String Chesstype() {
		return colortype;
	}
}
