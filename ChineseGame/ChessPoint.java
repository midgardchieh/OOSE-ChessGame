package ChineseChess;

/**
 * ��y�СB�I�Ѥl
 */
public class ChessPoint {
	/** �Ѥl�y��**/
	int x, y;
	
	/** �y�ФW�O�_���Ѥl*/
	boolean haveChess;
	
	/** ��y�Ъ��Ѥl */
	ChessPiece piece = null;
	
	/** �y�Щ��ݴѽL */
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

	// �]�m�I�Ѥl
	public void setPiece(ChessPiece piece, ChessBoard board) {
		this.board = board;
		this.piece = piece;
		board.add(piece);
		int w = (board.unitWidth);
		int h = (board.unitHeight);
		piece.setBounds(x - w / 2, y - h / 2, w, h);// �Ѥl��m�A�e�סA����
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
