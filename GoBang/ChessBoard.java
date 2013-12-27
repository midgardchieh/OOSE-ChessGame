package chessBoard;

import java.awt.Color;
	import java.awt.Cursor;
	import java.awt.Dimension;
	import java.awt.Graphics;
	import java.awt.Graphics2D;
	import java.awt.Image;
	import java.awt.RadialGradientPaint;
	import java.awt.RenderingHints;
	import java.awt.Toolkit;
	import java.awt.event.MouseEvent;
	import java.awt.event.MouseListener;
	import java.awt.event.MouseMotionListener;
	import java.awt.geom.Ellipse2D;
	
	import javax.swing.*;
	/**
	 * 五子棋棋盤
	 */
	
	public class ChessBoard extends BWCGame implements MouseListener {
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
	   
	   public ChessBoard(){
	      
	       //setBackground(Color.CYAN);//設置背景顏色
	       img = Toolkit.getDefaultToolkit().getImage("Red_Monster.jpg");
	       shadows = Toolkit.getDefaultToolkit().getImage("shadows.jpg");
	       addMouseListener(this);
	       addMouseMotionListener(new MouseMotionListener(){
	           public void mouseDragged(MouseEvent e){
	               
	           }
	           
	           public void mouseMoved(MouseEvent e){
	             int x1 = (e.getX() - MARGIN + GRID_SPAN / 2) / GRID_SPAN;
	             //將滑鼠座標轉成網格索引
	             int y1 = (e.getY() - MARGIN + GRID_SPAN / 2) / GRID_SPAN;
	             //遊戲結束不能下棋子
	             //棋盤外面不能下棋子
	             //x，y位置已經有棋子存在，不能下棋
	             if(x1 < 0 || x1 > ROWS || y1 < 0 || y1 > COLS || gameOver || findChess(x1,y1))
	                 setCursor(new Cursor(Cursor.TEXT_CURSOR));
	             //設置成預設狀態
	             else setCursor(new Cursor(Cursor.HAND_CURSOR));
	             
	           }
	       });
	   } 
	   
	  
	
	//繪製
	   public void paintComponent(Graphics g){
	     
	       super.paintComponent(g);//畫棋盤
	     
	       int imgWidth = img.getWidth(this);
	       int imgHeight = img.getHeight(this);//獲得圖片寬度與高度
	       int FWidth = getWidth();
	       int FHeight = getHeight();//獲得視窗的高度與寬度
	       int x = (FWidth - imgWidth) / 2;
	       int y = (FHeight - imgHeight) / 2;
	       g.drawImage(img, x, y, null);
	    
	       
	       for(int i = 0 ; i <= ROWS ; i++){//畫橫線
	           g.drawLine(MARGIN, MARGIN + i * GRID_SPAN, MARGIN + COLS * GRID_SPAN, MARGIN + i * GRID_SPAN);
	       }
	       for(int i = 0 ; i <= COLS ; i++){//畫縱線
	           g.drawLine(MARGIN + i * GRID_SPAN, MARGIN, MARGIN + i * GRID_SPAN, MARGIN + ROWS * GRID_SPAN);
	           
	       }
	       
	       //畫棋子
	       for(int i = 0 ; i < chessCount ; i++){
	           //兩格交叉點X,Y座標
	           int xPos = chessList[i].getX() * GRID_SPAN + MARGIN;
	           int yPos = chessList[i].getY() * GRID_SPAN + MARGIN;
	           g.setColor(chessList[i].getColor());//設置顏色
	          // g.fillOval(xPos-Point.DIAMETER/2, yPos-Point.DIAMETER/2,
	                           //Point.DIAMETER, Point.DIAMETER);
	           //g.drawImage(shadows, xPos-Point.DIAMETER/2, yPos-Point.DIAMETER/2, Point.DIAMETER, Point.DIAMETER, null);
	           colortemp = chessList[i].getColor();
	           if(colortemp == Color.black){
	               RadialGradientPaint paint = new RadialGradientPaint(xPos - Point.DIAMETER / 2 + 25, yPos-Point.DIAMETER / 2 + 10, 20, new float[]{0f, 1f}
	               , new Color[]{Color.WHITE, Color.BLACK});
	               ((Graphics2D) g).setPaint(paint);
	               ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	               ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_DEFAULT);
	
	           }
	           else if(colortemp == Color.white){
	               RadialGradientPaint paint = new RadialGradientPaint(xPos-Point.DIAMETER/2+25, yPos-Point.DIAMETER/2+10, 70, new float[]{0f, 1f}
	               , new Color[]{Color.WHITE, Color.BLACK});
	               ((Graphics2D) g).setPaint(paint);
	               ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	               ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_DEFAULT);
	
	           }
	         
	           Ellipse2D e = new Ellipse2D.Float(xPos - Point.DIAMETER / 2, yPos - Point.DIAMETER / 2, 34, 35);
	           ((Graphics2D) g).fill(e);
	           //標示最後一個棋子的邊框
	           
	           if(i == chessCount - 1){
	               g.setColor(Color.BLUE);
	               g.drawRect(xPos - Point.DIAMETER / 2, yPos-Point.DIAMETER / 2, 34, 35);
	           }
	       }
	   }
	   
	   public void mousePressed(MouseEvent e){//滑鼠按下時
	       
	       //遊戲結束時，不能再下棋
	       if(gameOver) return;
	       
	       String colorName = isBlack ? "黑棋" : "白棋";
	       
	       //將滑鼠點擊的座標位置轉換成網格索引
	       
	       xIndex = (e.getX() - MARGIN + GRID_SPAN / 2) / GRID_SPAN;
	       yIndex = (e.getY() - MARGIN + GRID_SPAN / 2) / GRID_SPAN;
	       
	       //棋盤外不能按
	       if(xIndex < 0 || xIndex > ROWS || yIndex < 0 || yIndex > COLS)
	           return;
	       
	       //如果x，y位置已經有棋子存在，不能下
	       if(findChess(xIndex, yIndex))return;
	       
	       //可以下棋時的處理
	       Point ch = new Point(xIndex, yIndex, isBlack ? Color.black : Color.white);
	       chessList[chessCount++] = ch;
	       repaint();//通知系統重新繪製
	      
	       
	       //如果獲勝就提示訊息，不能再下棋
	       if(isWin()){
	           String msg = String.format("Congratulation，%s win！", colorName);
	           JOptionPane.showMessageDialog(this, msg);
	           gameOver = true;
	       }
	       isBlack =! isBlack;
	     }
	   //覆蓋mouseListener的方法
	  /* public void mouseClicked(MouseEvent e){}
	   
	   public void mouseEntered(MouseEvent e){}
	   public void mouseExited(MouseEvent e){}
	   public void mouseReleased(MouseEvent e){}*/

	   //找x，y的地方是否有棋子存在
	   private boolean findChess(int x, int y){
	       for(Point c:chessList){
	           if(c != null && c.getX() == x && c.getY() == y)
	               return true;
	       }
	       return false;
	   }
	   
	   private boolean isWin(){
	       int continueCount = 1;//計算連續的棋子個數
	      
	       //横向向左尋找
	       for(int x = xIndex - 1 ; x >= 0 ; x--){
	           Color c = isBlack ? Color.black : Color.white;
	           if(getChess(x, yIndex, c) != null){
	               continueCount++;
	           }else
	               break;
	       }
	      //横向向右尋找
	       for(int x = xIndex + 1 ; x <= COLS ; x++){
	          Color c = isBlack ? Color.black : Color.white;
	          if(getChess(x, yIndex, c) != null){
	             continueCount++;
	          }else
	             break;
	       }
	       if(continueCount >= 5){
	             return true;
	       }else 
	       continueCount = 1;
	       
	       //繼續向另一個方向搜尋
	       //向上搜尋
	       for(int y = yIndex - 1 ; y >= 0 ; y--){
	           Color c = isBlack ? Color.black : Color.white;
           if(getChess(xIndex, y, c) != null){
	               continueCount++;
	           }else
	               break;
	       }
	       //向下搜尋
	       for(int y = yIndex + 1 ; y <= ROWS ; y++){
	           Color c = isBlack ? Color.black : Color.white;
	           if(getChess(xIndex, y, c) != null)
	               continueCount++;
	           else
	              break;
	       
	       }
	       if(continueCount >= 5)
	           return true;
	       else
	           continueCount = 1;
	       
	       
	       //繼續向另一個方向搜尋
	       //右上尋找
	       for(int x = xIndex + 1, y = yIndex - 1 ; y >= 0 && x <= COLS ; x++, y--){
	           Color c = isBlack ? Color.black : Color.white;
	           if(getChess(x, y, c) != null){
	               continueCount++;
	           }
	           else break;
	       }
	       //左下尋找
	       for(int x = xIndex - 1, y = yIndex + 1 ; x >= 0 && y <= ROWS ; x--,y++){
	           Color c = isBlack ? Color.black : Color.white;
	           if(getChess(x, y, c) != null){
	               continueCount++;
	           }
	           else break;
	       }
	       if(continueCount >= 5)
	           return true;
	       else continueCount = 1;
	       
	       
	       //繼續向另一個方向搜尋
	       //左上尋找
	       for(int x = xIndex - 1, y = yIndex - 1 ; x >= 0 && y >= 0 ; x--, y--){
	           Color c = isBlack ? Color.black : Color.white;
	           if(getChess(x, y, c) != null)
	               continueCount++;
	           else break;
	       }
	       //右下尋找
	       for(int x = xIndex + 1, y = yIndex + 1 ; x <= COLS && y <= ROWS ; x++, y++){
	           Color c = isBlack ? Color.black : Color.white;
	           if(getChess(x, y, c) != null)
	               continueCount++;
	           else break;
	       }
	       if(continueCount >= 5)
	           return true;
	       else continueCount = 1;
	       
	       return false;
	     }
	   
	   
	   private Point getChess(int xIndex, int yIndex, Color color){
	       for(Point p:chessList){
	           if(p != null && p.getX() == xIndex&&p.getY() == yIndex
	                   && p.getColor() == color)
	               return p;
	       }
	       return null;
	   }
	   
	   
	   public void restartGame(){
	       //清除棋子
	       for(int i = 0 ; i < chessList.length ; i++){
	           chessList[i] = null;
	       }
	       //恢復初始值
	       isBlack = true;
	       gameOver = false; //遊戲是否结束
	       chessCount = 0; //當前棋盤棋子數量
	       repaint();
	   }
	   
	   //悔棋
	   public void goback(){
	       if(chessCount==0)
	           return ;
	       chessList[chessCount-1]=null;
	       chessCount--;
	       if(chessCount>0){
	           xIndex=chessList[chessCount-1].getX();
	           yIndex=chessList[chessCount-1].getY();
	       }
	       isBlack=!isBlack;
	       repaint();
	   }
	   
	   //矩形Dimension
	
	   public Dimension getPreferredSize(){
	       return new Dimension(MARGIN*2+GRID_SPAN*COLS,MARGIN*2
	                            +GRID_SPAN*ROWS);
	   }         
	}