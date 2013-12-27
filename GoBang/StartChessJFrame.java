package chessBoard;

import java.awt.event.*;
import java.awt.*;
	
import javax.swing.*;
	/**
	 * 啟動五子棋
	 */
	public class StartChessJFrame extends JFrame {
	  private ChessBoard chessBoard;
	  private JPanel toolbar;
	  private JButton startButton,backButton,exitButton;
	  
	  private JMenuBar menuBar;
	  private JMenu sysMenu;
	  private JMenuItem startMenuItem,exitMenuItem,backMenuItem;
	  //重新開始，退出，和悔棋選單
	  public StartChessJFrame(){
	      setTitle("Gobang");//設置標題
	      chessBoard=new ChessBoard();
	    
	      
	      Container contentPane=getContentPane();
	      contentPane.add(chessBoard);
	      chessBoard.setOpaque(true);
	      
	      
	      //建立和增加選單
	      menuBar =new JMenuBar();//初始化選單欄
	      sysMenu=new JMenu("系统");//初始化選單
	      //初始化選單項目
	      startMenuItem=new JMenuItem("重新開始");
	      exitMenuItem =new JMenuItem("退出");
	      backMenuItem =new JMenuItem("悔棋");
	      //將三個選單項目增加到選單上
	      sysMenu.add(startMenuItem);
	      sysMenu.add(exitMenuItem);
	      sysMenu.add(backMenuItem);
	      //初始化按鈕事件
	      MyItemListener lis=new MyItemListener();
	      //將三個選單創建到ActionListener
	      this.startMenuItem.addActionListener(lis);
	      backMenuItem.addActionListener(lis);
	      exitMenuItem.addActionListener(lis);
	      menuBar.add(sysMenu);//將系統選單增加到選單欄
	      setJMenuBar(menuBar);//將menuBar設置為選單欄
	      
	      toolbar=new JPanel();//工具面板實體化
	      //三個按鈕初始化
	      startButton=new JButton("重新開始");
	      exitButton=new JButton("退出");
	      backButton=new JButton("悔棋");
	      //將工具面板按鈕用FlowLayout佈局
	      toolbar.setLayout(new FlowLayout(FlowLayout.LEFT));
	      //將三個按鈕添加到工具面板
	      toolbar.add(startButton);
	      toolbar.add(exitButton);
	      toolbar.add(backButton);
	      //將三個按鈕創建到ActionListener
	      startButton.addActionListener(lis);
	      exitButton.addActionListener(lis);
	      backButton.addActionListener(lis);
	      //將工具面板佈局到介面”南方“也就是下方
	      add(toolbar,BorderLayout.SOUTH);
	      //將面板對象添加到視窗上
	      add(chessBoard);
	      //設置介面關閉事件
	      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	      //setSize(800,800);
	      pack();//調適大小
	      
	  }
	  
	  private class MyItemListener implements ActionListener{
	      public void actionPerformed(ActionEvent e){
	          Object obj=e.getSource();
	          if(obj==StartChessJFrame.this.startMenuItem||obj==startButton){
	              //重新開始
	              //JFiveFrame.this内部類別引用外部類別
	              System.out.println("重新開始");
	              chessBoard.restartGame();
	          }
	          else if (obj==exitMenuItem||obj==exitButton)
	              System.exit(0);
	          else if (obj==backMenuItem||obj==backButton){
	              System.out.println("悔棋...");
	              chessBoard.goback();
	          }
	      }
	  }
	  
	  public static void main(String[] args){
	      StartChessJFrame f=new StartChessJFrame();//創建主框架
	      f.setVisible(true);//顯示主框架
	      
	  }
	}