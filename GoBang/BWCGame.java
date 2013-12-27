package chessBoard;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

/**
 * 提供抽象方法供子類別繼承並實作
 */
public abstract class BWCGame extends JPanel {
	
	public void paintComponent(){}
	public void mousePressed(MouseEvent e){}
	public void mouseClicked(MouseEvent e){} 
	
	public void mouseEntered(MouseEvent e){}
	public void mouseExited(MouseEvent e){}
	public void mouseReleased(MouseEvent e){}
	
	private boolean findChess(int x, int y){
		return false;}
	private boolean isWin(){
		return false;}
	 private Point getChess(int xIndex, int yIndex, Color color){
		return null;}
	 public void restartGame(){}
	 public void goback(){}
	 public Dimension getPreferredSize(){
		return null;}
}
