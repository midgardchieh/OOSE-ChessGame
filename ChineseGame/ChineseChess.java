﻿package ChineseChess;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.LinkedList;

/**
 * 象棋主類別
 */
public class ChineseChess extends JFrame implements ActionListener {
	ChessBoard board = null;
	MakeChessManual record = null;
	Container con = null;
	JFileChooser fileChooser = null;
	LinkedList Manual = null;

	public ChineseChess() {
		setTitle("中國象棋");
		board = new ChessBoard(45, 45, 9, 10);

		con = getContentPane();
		JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true,
				board, record);
		split.setDividerSize(5);
		split.setDividerLocation(460);
		con.add(split, BorderLayout.CENTER);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		setVisible(true);
		setBounds(60, 20, 540, 540);
		fileChooser = new JFileChooser();
		con.validate();
		validate();
		JOptionPane.showMessageDialog(null, "開始遊戲，紅方先下!!!!!!!!!!");
	}

	public void actionPerformed(ActionEvent e) {
	}

	public static void main(String args[]) {
		new ChineseChess();
	}
}