package chessBoard;

import java.awt.Color;
	/**
	 * �Ѥl
	 */
	public class Point {
	  private int x;//�ѽL����x����
	  private int y;//�ѽL����y����
	  private Color color;//�C��
	  public static final int DIAMETER=30;//���|
	  
	  public Point(int x, int y, Color color){
	      this.x = x;
	      this.y = y;
	      this.color = color;
	  } 
	  
	  public int getX(){
	      return x;
	  }
	  public int getY(){
	      return y;
	  }
	  public Color getColor(){
	      return color;
	  }
	}