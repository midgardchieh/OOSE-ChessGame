package chessBoard;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

/**
 * 提供抽象方法供子類別繼承並實作
 */
public abstract class BWCGame extends JPanel {
	   public static final int MARGIN = 30;//邊距
	   public static final int GRID_SPAN = 35;//網格間距
	   public static final int ROWS = 15;//棋盤行數
	   public static final int COLS = 15;//棋盤列數
	   
	   Point[] chessList = new Point[ROWS * COLS];//初始化
	   boolean isBlack = true;//黑棋先開始
	   boolean gameOver = false;//遊戲是否结束
	   int chessCount;//目前棋盤旗子的個數
	   int xIndex, yIndex;//剛下棋子的索引
	   
	   Image img;
	   Image shadows;
	   Color colortemp;
	   Graphics g;
	   
	   public void paintComponent(){}
	   public void mousePressed(MouseEvent e){}
	   public void mouseClicked(MouseEvent e){} 
	
	   public void mouseEntered(MouseEvent e){}
	   public void mouseExited(MouseEvent e){}
	   public void mouseReleased(MouseEvent e){}
	
	   private boolean findChess(int x, int y){
		   return false;
		}
	   	private boolean isWin(){
	   		return false;
	   	}
	   	private Point getChess(int xIndex, int yIndex, Color color){
	   		return null;
	   	}
	   	public void restartGame(){}
	   	public void goback(){}
	   	public Dimension getPreferredSize(){
		return null;}
}
