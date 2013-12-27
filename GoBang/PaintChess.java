package chessBoard;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RadialGradientPaint;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
    
	/**
	 * 實作畫棋子的部分
	 */
	public class PaintChess extends BWCGame {
		Graphics g;
		
		public PaintChess(Graphics g) {
			PaintChess p = new PaintChess(g);
			p.PaintComponent(g);
		}
		public void PaintComponent(Graphics g){
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
		               g.drawRect(xPos - Point.DIAMETER / 2, yPos-Point.DIAMETER / 2, 34, 35);			           }
			       }
			
	}
		
	
}