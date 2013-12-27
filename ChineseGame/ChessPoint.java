package ChineseChess;

/**
 * 找座標、點棋子
 */
public class ChessPoint {
	/** 棋子座標**/
	int x, y;
	
	/** 座標上是否有棋子*/
	boolean haveChess;
	
	/** 改座標的棋子 */
	ChessPiece piece = null;
	
	/** 座標所屬棋盤 */
	ChessBoard board = null;

	public ChessPoint(int x, int y, boolean boo) {
		this.x = x;
		this.y = y;
		haveChess = boo;
	}

	public boolean isPiece() {
		return haveChess;
	}

	public void sethaveChess(boolean boo) {
		haveChess = boo;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	// 設置點棋子
	public void setPiece(ChessPiece piece, ChessBoard board) {
		this.board = board;
		this.piece = piece;
		board.add(piece);
		int w = (board.unitWidth);
		int h = (board.unitHeight);
		piece.setBounds(x - w / 2, y - h / 2, w, h);// 棋子位置，寬度，高度
		haveChess = true;
		board.validate();
	}

	public ChessPiece getPiece() {
		return piece;
	}

	public void reMovePiece(ChessPiece piece, ChessBoard board) {
		this.board = board;
		this.piece = piece;
		board.remove(piece);
		board.validate();
		haveChess = false;
	}
}
