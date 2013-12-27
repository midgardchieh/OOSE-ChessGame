package ChineseChess;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/** 
 * 棋盤 
 */
public class ChessBoard extends JPanel implements MouseListener,
		MouseMotionListener {
	public ChessPoint point[][];
	public int unitWidth, unitHeight;
	private int xAxisLength, yAxisLength;
	private int x, y;
	private Image img;
	protected Image pieceImg;
	private boolean move = false;
	public String redcolor = "紅方", blackcolor = "黑方";
	ChessPiece redcar1, redcar2, redHorse1, redHorse2, redElephant1, redElephant2, redGeneral, redWarroir1, redWarroir2, redSoldier1, redSoldier2, redSoldier3, redSoldier4,
			redSoldier5, redGun1, redGun2;
	ChessPiece blackCar1, blackCar2, blackHorse1, blackHorse2, blackGeneral, blackWarroir1, blackWarroir2, blackSoldier1, blackSoldier2, blackSoldier3, blackSoldier4, blackSoldier5, blackElephant1,
			blackElephant2, blackGun1, blackGun2;

	int startX, startY;
	int startI, startJ;
	public boolean redmove = true, blackmove = false;
	Rule rule = null;

	public ChessBoard(int w, int h, int r, int c) {
		setLayout(null);
		addMouseListener(this);
		addMouseMotionListener(this);
		Color bc = getBackground();
		unitWidth = w;
		unitHeight = h;
		xAxisLength = r;
		yAxisLength = c;

		point = new ChessPoint[r + 1][c + 1];

		for (int i = 1; i <= r; i++) {
			for (int j = 1; j <= c; j++) {
				point[i][j] = new ChessPoint(i * unitWidth, j * unitHeight,
						false);
			}
		}

		rule = new Rule(this, point);

		img = Toolkit.getDefaultToolkit().getImage("board.jpg");
		pieceImg = Toolkit.getDefaultToolkit().getImage("piece.gif");
		
		redcar1 = new ChessPiece("俥", Color.red, bc, w - 4, h - 4, this);
		redcar1.setChesstype(redcolor);
		redcar2 = new ChessPiece("俥", Color.red, bc, w - 4, h - 4, this);
		redcar2.setChesstype(redcolor);
		redHorse1 = new ChessPiece("傌", Color.red, bc, w - 4, h - 4, this);
		redHorse1.setChesstype(redcolor);
		redHorse2 = new ChessPiece("傌", Color.red, bc, w - 4, h - 4, this);
		redHorse2.setChesstype(redcolor);
		redGun1 = new ChessPiece("炮", Color.red, bc, w - 4, h - 4, this);
		redGun1.setChesstype(redcolor);
		redGun2 = new ChessPiece("炮", Color.red, bc, w - 4, h - 4, this);
		redGun2.setChesstype(redcolor);
		redElephant1 = new ChessPiece("相", Color.red, bc, w - 4, h - 4, this);
		redElephant1.setChesstype(redcolor);
		redElephant2 = new ChessPiece("相", Color.red, bc, w - 4, h - 4, this);
		redElephant2.setChesstype(redcolor);
		redWarroir1 = new ChessPiece("仕", Color.red, bc, w - 4, h - 4, this);
		redWarroir1.setChesstype(redcolor);
		redWarroir2 = new ChessPiece("仕", Color.red, bc, w - 4, h - 4, this);
		redWarroir2.setChesstype(redcolor);
		redGeneral = new ChessPiece("帥", Color.red, bc, w - 4, h - 4, this);
		redGeneral.setChesstype(redcolor);
		redSoldier1 = new ChessPiece("兵", Color.red, bc, w - 4, h - 4, this);
		redSoldier1.setChesstype(redcolor);
		redSoldier2 = new ChessPiece("兵", Color.red, bc, w - 4, h - 4, this);
		redSoldier2.setChesstype(redcolor);
		redSoldier3 = new ChessPiece("兵", Color.red, bc, w - 4, h - 4, this);
		redSoldier3.setChesstype(redcolor);
		redSoldier4 = new ChessPiece("兵", Color.red, bc, w - 4, h - 4, this);
		redSoldier4.setChesstype(redcolor);
		redSoldier5 = new ChessPiece("兵", Color.red, bc, w - 4, h - 4, this);
		redSoldier5.setChesstype(redcolor);

		blackGeneral = new ChessPiece("將", Color.black, bc, w - 4, h - 4, this);
		blackGeneral.setChesstype(blackcolor);
		blackWarroir1 = new ChessPiece("士", Color.black, bc, w - 4, h - 4, this);
		blackWarroir1.setChesstype(blackcolor);
		blackWarroir2 = new ChessPiece("士", Color.black, bc, w - 4, h - 4, this);
		blackWarroir2.setChesstype(blackcolor);
		blackCar1 = new ChessPiece("車", Color.black, bc, w - 4, h - 4, this);
		blackCar1.setChesstype(blackcolor);
		blackCar2 = new ChessPiece("車", Color.black, bc, w - 4, h - 4, this);
		blackCar2.setChesstype(blackcolor);
		blackGun1 = new ChessPiece("包", Color.black, bc, w - 4, h - 4, this);
		blackGun1.setChesstype(blackcolor);
		blackGun2 = new ChessPiece("包", Color.black, bc, w - 4, h - 4, this);
		blackGun2.setChesstype(blackcolor);
		blackElephant1 = new ChessPiece("象", Color.black, bc, w - 4, h - 4, this);
		blackElephant1.setChesstype(blackcolor);
		blackElephant2 = new ChessPiece("象", Color.black, bc, w - 4, h - 4, this);
		blackElephant2.setChesstype(blackcolor);
		blackHorse1 = new ChessPiece("馬", Color.black, bc, w - 4, h - 4, this);
		blackHorse1.setChesstype(blackcolor);
		blackHorse2 = new ChessPiece("馬", Color.black, bc, w - 4, h - 4, this);
		blackHorse2.setChesstype(blackcolor);
		blackSoldier1 = new ChessPiece("卒", Color.black, bc, w - 4, h - 4, this);
		blackSoldier1.setChesstype(blackcolor);
		blackSoldier2 = new ChessPiece("卒", Color.black, bc, w - 4, h - 4, this);
		blackSoldier2.setChesstype(blackcolor);
		blackSoldier3 = new ChessPiece("卒", Color.black, bc, w - 4, h - 4, this);
		blackSoldier3.setChesstype(blackcolor);
		blackSoldier4 = new ChessPiece("卒", Color.black, bc, w - 4, h - 4, this);
		blackSoldier4.setChesstype(blackcolor);
		blackSoldier5 = new ChessPiece("卒", Color.black, bc, w - 4, h - 4, this);
		blackSoldier5.setChesstype(blackcolor);
		point[1][10].setPiece(redcar1, this);
		point[2][10].setPiece(redHorse1, this);
		point[3][10].setPiece(redElephant1, this);
		point[4][10].setPiece(redWarroir1, this);
		point[5][10].setPiece(redGeneral, this);
		point[6][10].setPiece(redWarroir2, this);
		point[7][10].setPiece(redElephant2, this);
		point[8][10].setPiece(redHorse2, this);
		point[9][10].setPiece(redcar2, this);
		point[2][8].setPiece(redGun1, this);
		point[8][8].setPiece(redGun2, this);
		point[1][7].setPiece(redSoldier1, this);
		point[3][7].setPiece(redSoldier2, this);
		point[5][7].setPiece(redSoldier3, this);
		point[7][7].setPiece(redSoldier4, this);
		point[9][7].setPiece(redSoldier5, this);

		point[1][1].setPiece(blackCar1, this);
		point[2][1].setPiece(blackHorse1, this);
		point[3][1].setPiece(blackElephant1, this);
		point[4][1].setPiece(blackWarroir1, this);
		point[5][1].setPiece(blackGeneral, this);
		point[6][1].setPiece(blackWarroir2, this);
		point[7][1].setPiece(blackElephant2, this);
		point[8][1].setPiece(blackHorse2, this);
		point[9][1].setPiece(blackCar2, this);
		point[2][3].setPiece(blackGun1, this);
		point[8][3].setPiece(blackGun2, this);
		point[1][4].setPiece(blackSoldier1, this);
		point[3][4].setPiece(blackSoldier2, this);
		point[5][4].setPiece(blackSoldier3, this);
		point[7][4].setPiece(blackSoldier4, this);
		point[9][4].setPiece(blackSoldier5, this);

	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		int imgWidth = img.getWidth(this);
		int imgHeight = img.getHeight(this);// 獲得圖片的寬度與高度
		int FWidth = getWidth();
		int FHeight = getHeight();// 獲得視窗的寬度與高度
		int x = (FWidth - imgWidth) / 2;
		int y = (FHeight - imgHeight) / 2;
		g.drawImage(img, x, y, null);

		for (int j = 1; j <= yAxisLength; j++) {
			g.drawLine(point[1][j].x, point[1][j].y, point[xAxisLength][j].x,
					point[xAxisLength][j].y);
		}
		for (int i = 1; i <= xAxisLength; i++) {
			if (i != 1 && i != xAxisLength) {
				g.drawLine(point[i][1].x, point[i][1].y, point[i][yAxisLength - 5].x,
						point[i][yAxisLength - 5].y);
				g.drawLine(point[i][yAxisLength - 4].x, point[i][yAxisLength - 4].y,
						point[i][yAxisLength].x, point[i][yAxisLength].y);
			} else {
				g.drawLine(point[i][1].x, point[i][1].y, point[i][yAxisLength].x,
						point[i][yAxisLength].y);
			}
		}

		g.drawLine(point[4][1].x, point[4][1].y, point[6][3].x, point[6][3].y);
		g.drawLine(point[6][1].x, point[6][1].y, point[4][3].x, point[4][3].y);
		g.drawLine(point[4][8].x, point[4][8].y, point[6][yAxisLength].x,
				point[6][yAxisLength].y);
		g.drawLine(point[4][yAxisLength].x, point[4][yAxisLength].y, point[6][8].x,
				point[6][8].y);

		for (int i = 1; i <= xAxisLength; i++) {
			g.drawString("" + i, i * unitWidth, unitHeight / 2);
		}
		int j = 1;
		for (char c = 'A'; c <= 'J'; c++) {
			g.drawString("" + c, unitWidth / 4, j * unitHeight);
			j++;
		}

	}

	/**滑鼠按下事件*/
	public void mousePressed(MouseEvent e) {
		ChessPiece piece = null;
		Rectangle rect = null;
		if (e.getSource() == this)
			move = false;
		if (move == false)
			if (e.getSource() instanceof ChessPiece) {
				piece = (ChessPiece) e.getSource();
				startX = piece.getBounds().x;
				startY = piece.getBounds().y;

				rect = piece.getBounds();
				for (int i = 1; i <= xAxisLength; i++) {
					for (int j = 1; j <= yAxisLength; j++) {
						int x = point[i][j].getX();
						int y = point[i][j].getY();
						if (rect.contains(x, y)) {
							startI = i;
							startJ = j;
							break;
						}

					}
				}
			}
	}

	public void mouseMoved(MouseEvent e) {
	}

	/**滑鼠拖動事件*/
	public void mouseDragged(MouseEvent e) {

		ChessPiece piece = null;
		if (e.getSource() instanceof ChessPiece) {
			piece = (ChessPiece) e.getSource();

			move = true;

			e = SwingUtilities.convertMouseEvent(piece, e, this);
		}

		if (e.getSource() == this) {
			if (move && piece != null) {
				x = e.getX();
				y = e.getY();
				if (redmove && ((piece.Chesstype()).equals(redcolor))) {
					piece.setLocation(x - piece.getWidth() / 2,
							y - piece.getHeight() / 2);
				}
				if (blackmove && (piece.Chesstype().equals(blackcolor))) {
					piece.setLocation(x - piece.getWidth() / 2,
							y - piece.getHeight() / 2);
				}
			}
		}
	}

	/**鬆開滑鼠事件*/
	public void mouseReleased(MouseEvent e) {
		ChessPiece piece = null;
		move = false;
		Rectangle rect = null;
		if (e.getSource() instanceof ChessPiece) {
			piece = (ChessPiece) e.getSource();
			rect = piece.getBounds();

			e = SwingUtilities.convertMouseEvent(piece, e, this);
		}
		if (e.getSource() == this) {
			boolean containChessPoint = false;
			int x = 0, y = 0;
			int m = 0, n = 0;
			if (piece != null) {
				for (int i = 1; i <= xAxisLength; i++) {
					for (int j = 1; j <= yAxisLength; j++) {
						x = point[i][j].getX();
						y = point[i][j].getY();
						if (rect.contains(x, y)) {

							containChessPoint = true;
							m = i;
							n = j;
							break;
						}

					}
				}
			}
			if (piece != null && containChessPoint) {
				Color pieceColor = piece.getChesscolor();
				if (point[m][n].isPiece()) {
					Color c = (point[m][n].getPiece()).getChesscolor();
					if (pieceColor.getRGB() == c.getRGB()) {
						piece.setLocation(startX, startY);

						(point[startI][startJ]).sethaveChess(true);
					} else {
						boolean ok = rule.movePieceRule(piece, startI, startJ,
								m, n);
						if (ok) {
							ChessPiece pieceRemoved = point[m][n].getPiece();
							point[m][n].reMovePiece(pieceRemoved, this);
							point[m][n].setPiece(piece, this);
							(point[startI][startJ]).sethaveChess(false);
							rule.isWine(pieceRemoved);
							if (piece.Chesstype().equals(redcolor)) {
								redmove = false;
								blackmove = true;
							}
							if (piece.Chesstype().equals(blackcolor)) {
								blackmove = false;
								redmove = true;
							}
							validate();
							repaint();
						} else {
							piece.setLocation(startX, startY);
							(point[startI][startJ]).sethaveChess(true);
						}
					}

				} else {

					boolean ok = rule
							.movePieceRule(piece, startI, startJ, m, n);
					if (ok) {
						point[m][n].setPiece(piece, this);
						(point[startI][startJ]).sethaveChess(false);

						if (piece.Chesstype().equals(redcolor)) {
							redmove = false;
							blackmove = true;
						}
						if (piece.Chesstype().equals(blackcolor)) {
							blackmove = false;
							redmove = true;
						}
					} else {
						piece.setLocation(startX, startY);
						(point[startI][startJ]).sethaveChess(true);
					}
				}
			}

			if (piece != null && !containChessPoint) {
				piece.setLocation(startX, startY);
				(point[startI][startJ]).sethaveChess(true);
			}
		}
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mouseClicked(MouseEvent e) {
	}
}