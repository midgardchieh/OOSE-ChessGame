﻿package ChineseChess;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

/**
 * 棋子移動規則 
 */
public class Rule {
	ChessBoard board = null;
	ChessPiece piece = null;
	ChessPoint point[][];
	int startI, startJ, endI, endJ;

	public Rule(ChessBoard board, ChessPoint point[][]) {
		this.board = board;
		this.point = point;
	}

	public void isWine(ChessPiece piece) {
		this.piece = piece;
		if (piece.getName() == "將" || piece.getName() == "帥") {
			if (piece.colortype == "紅方") {
				JOptionPane.showMessageDialog(null, "黑方  勝利！");
			} else {
				JOptionPane.showMessageDialog(null, "紅方  勝利！");
			}
		}
	}

	public boolean movePieceRule(ChessPiece piece, int startI, int startJ,
			int endI, int endJ) {
		this.piece = piece;
		this.startI = startI;
		this.startJ = startJ;
		this.endI = endI;
		this.endJ = endJ;
		int minI = Math.min(startI, endI);
		int maxI = Math.max(startI, endI);
		int minJ = Math.min(startJ, endJ);
		int maxJ = Math.max(startJ, endJ);
		boolean moveornot = false;
		if (piece.getName().equals("車")) {
			if (startI == endI) {
				int j = 0;
				for (j = minJ + 1; j <= maxJ - 1; j++) {
					if (point[startI][j].isPiece()) {
						moveornot = false;
						break;
					}
				}
				if (j == maxJ) {
					moveornot = true;
				}
			} else if (startJ == endJ) {
				int i = 0;
				for (i = minI + 1; i <= maxI - 1; i++) {
					if (point[i][startJ].isPiece()) {
						moveornot = false;
						break;
					}
				}
				if (i == maxI) {
					moveornot = true;
				}
			} else {
				moveornot = false;
			}

		} else if (piece.getName().equals("俥")) {
			if (startI == endI) {
				int j = 0;
				for (j = minJ + 1; j <= maxJ - 1; j++) {
					if (point[startI][j].isPiece()) {
						moveornot = false;
						break;
					}
				}
				if (j == maxJ) {
					moveornot = true;
				}
			} else if (startJ == endJ) {
				int i = 0;
				for (i = minI + 1; i <= maxI - 1; i++) {
					if (point[i][startJ].isPiece()) {
						moveornot = false;
						break;
					}
				}
				if (i == maxI) {
					moveornot = true;
				}
			} else {
				moveornot = false;
			}

		}else if (piece.getName().equals("馬")) {
			int xAxle = Math.abs(startI - endI);
			int yAxle = Math.abs(startJ - endJ);

			if (xAxle == 2 && yAxle == 1) {
				if (endI > startI) {
					if (point[startI + 1][startJ].isPiece()) {
						moveornot = false;
					} else {
						moveornot = true;
					}
				}
				if (endI < startI) {
					if (point[startI - 1][startJ].isPiece()) {
						moveornot = false;
					} else {
						moveornot = true;
					}
				}

			}else if (xAxle == 1 && yAxle == 2) {
				if (endJ > startJ) {
					if (point[startI][startJ + 1].isPiece()) {
						moveornot = false;
					} else {
						moveornot = true;
					}
				}
				if (endJ < startJ) {
					if (point[startI][startJ - 1].isPiece()) {
						moveornot = false;
					} else {
						moveornot = true;
					}
				}

			} else {
				moveornot = false;
			}
		} else if (piece.getName().equals("傌")) {
			int xAxle = Math.abs(startI - endI);
			int yAxle = Math.abs(startJ - endJ);

			if (xAxle == 2 && yAxle == 1) {
				if (endI > startI) {
					if (point[startI + 1][startJ].isPiece()) {
						moveornot = false;
					} else {
						moveornot = true;
					}
				}
				if (endI < startI) {
					if (point[startI - 1][startJ].isPiece()) {
						moveornot = false;
					} else {
						moveornot = true;
					}
				}

			}else if (xAxle == 1 && yAxle == 2) {
				if (endJ > startJ) {
					if (point[startI][startJ + 1].isPiece()) {
						moveornot = false;
					} else {
						moveornot = true;
					}
				}
				if (endJ < startJ) {
					if (point[startI][startJ - 1].isPiece()) {
						moveornot = false;
					} else {
						moveornot = true;
					}
				}

			} else {
				moveornot = false;
			}
		} else if (piece.getName().equals("象")) {
			int centerI = (startI + endI) / 2;
			int centerJ = (startJ + endJ) / 2;
			int xAxle = Math.abs(startI - endI);
			int yAxle = Math.abs(startJ - endJ);
			if (xAxle == 2 && yAxle == 2 && endJ <= 5) {
				if (point[centerI][centerJ].isPiece()) {
					moveornot = false;
				} else {
					moveornot = true;
				}
			} else {
				moveornot = false;
			}
		} else if (piece.getName().equals("相")) {
			int centerI = (startI + endI) / 2;
			int centerJ = (startJ + endJ) / 2;
			int xAxle = Math.abs(startI - endI);
			int yAxle = Math.abs(startJ - endJ);
			if (xAxle == 2 && yAxle == 2 && endJ >= 6) {
				if (point[centerI][centerJ].isPiece()) {
					moveornot = false;
				} else {
					moveornot = true;
				}
			} else {
				moveornot = false;
			}
		} else if (piece.getName().equals("包")) {
			int number = 0;
			if (startI == endI) {
				int j = 0;
				for (j = minJ + 1; j <= maxJ - 1; j++) {
					if (point[startI][j].isPiece()) {
						number++;
					}
				}
				if (number > 1) {
					moveornot = false;
				} else if (number == 1) {
					if (point[endI][endJ].isPiece()) {
						moveornot = true;
					}
				} else if (number == 0 && !point[endI][endJ].isPiece()) {
					moveornot = true;
				}
			} else if (startJ == endJ) {
				int i = 0;
				for (i = minI + 1; i <= maxI - 1; i++) {
					if (point[i][startJ].isPiece()) {
						number++;
					}
				}
				if (number > 1) {
					moveornot = false;
				} else if (number == 1) {
					if (point[endI][endJ].isPiece()) {
						moveornot = true;
					}
				} else if (number == 0 && !point[endI][endJ].isPiece()) {
					moveornot = true;
				}
			} else {
				moveornot = false;
			}
		} else if (piece.getName().equals("炮")) {
			int number = 0;
			if (startI == endI) {
				int j = 0;
				for (j = minJ + 1; j <= maxJ - 1; j++) {
					if (point[startI][j].isPiece()) {
						number++;
					}
				}
				if (number > 1) {
					moveornot = false;
				} else if (number == 1) {
					if (point[endI][endJ].isPiece()) {
						moveornot = true;
					}
				} else if (number == 0 && !point[endI][endJ].isPiece()) {
					moveornot = true;
				}
			} else if (startJ == endJ) {
				int i = 0;
				for (i = minI + 1; i <= maxI - 1; i++) {
					if (point[i][startJ].isPiece()) {
						number++;
					}
				}
				if (number > 1) {
					moveornot = false;
				} else if (number == 1) {
					if (point[endI][endJ].isPiece()) {
						moveornot = true;
					}
				} else if (number == 0 && !point[endI][endJ].isPiece()) {
					moveornot = true;
				}
			} else {
				moveornot = false;
			}
		}else if (piece.getName().equals("兵")) {
			int xAxle = Math.abs(startI - endI);
			int yAxle = Math.abs(startJ - endJ);

			if (endJ >= 6) {
				if (startJ - endJ == 1 && xAxle == 0) {
					moveornot = true;
				}

				else {
					moveornot = false;
				}
			} else if (endJ <= 5) {
				if ((startJ - endJ == 1) && (xAxle == 0)) {
					moveornot = true;
				} else if ((endJ - startJ == 0) && (xAxle == 1)) {
					moveornot = true;
				} else {
					moveornot = false;
				}
			}
		} else if (piece.getName().equals("卒")) {
			int xAxle = Math.abs(startI - endI);
			int yAxle = Math.abs(startJ - endJ);

			if (endJ <= 5) {
				if (endJ - startJ == 1 && xAxle == 0) {
					moveornot = true;
				} else {
					moveornot = false;
				}
			} else if (endJ >= 6) {
				if ((endJ - startJ == 1) && (xAxle == 0)) {
					moveornot = true;
				} else if ((endJ - startJ == 0) && (xAxle == 1)) {
					moveornot = true;
				} else {
					moveornot = false;
				}
			}
		}

		else if (piece.getName().equals("士")) {
			int xAxle = Math.abs(startI - endI);
			int yAxle = Math.abs(startJ - endJ);
			if (endI <= 6 && endI >= 4 && endJ <= 3 && xAxle == 1 && yAxle == 1) {
				moveornot = true;
			} else {
				moveornot = false;
			}
		} else if (piece.getName().equals("仕")) {
			int xAxle = Math.abs(startI - endI);
			int yAxle = Math.abs(startJ - endJ);
			if (endI <= 6 && endI >= 4 && endJ >= 8 && xAxle == 1 && yAxle == 1) {
				moveornot = true;
			} else {
				moveornot = false;
			}
		} else if (piece.getName().equals("將")) {
			int xAxle = Math.abs(startI - endI);
			int yAxle = Math.abs(startJ - endJ);
			if (endI <= 6 && endI >= 4 && endJ <= 3) {
				if ((xAxle == 1 && yAxle == 0) || (xAxle == 0 && yAxle == 1)) {
					moveornot = true;
				} else {
					moveornot = false;
				}
			} else if (startI == endI) {
				int j = 0;
				for (j = minJ + 1; j <= maxJ - 1; j++) {
					if (point[startI][j].isPiece()) {
						moveornot = false;
						break;
					}
				}
				if (j == maxJ) {
					if((point[endI][endJ].piece.getName()).equals("帥")){
					moveornot = true;
					}
				}
					
			} else 
				moveornot = false;
		}else if (piece.getName().equals("帥")) {
			int xAxle = Math.abs(startI - endI);
			int yAxle = Math.abs(startJ - endJ);
			if (endI <= 6 && endI >= 4 && endJ >= 8) {
				if ((xAxle == 1 && yAxle == 0) || (xAxle == 0 && yAxle == 1)) {
					moveornot = true;
				} else {
					moveornot = false;
				}
			} else if (startI == endI) {
						int j = 0;
						for (j = minJ + 1; j <= maxJ - 1; j++) {
							if (point[startI][j].isPiece()) {
								moveornot = false;
								break;
							}
						}
						if (j == maxJ) {
							if((point[endI][endJ].piece.getName()).equals("將")){
							moveornot = true;
							}
						}	
			
			} else 
				moveornot = false;
		}

		return moveornot;

	}
}

