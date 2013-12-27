package ChineseChess;

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
	Demon demon = null;
	MakeChessManual record = null;
	Container con = null;
	JMenuBar bar;
	JMenu fileMenu;
	JMenuItem makeManual, saveManual, playManual;
	JFileChooser fileChooser = null;
	LinkedList Manual = null;

	public ChineseChess() {		
		bar = new JMenuBar();
		fileMenu = new JMenu("!!!!!!!!!!!按我製作棋譜!!!!!!!!!!");
		setTitle("中國象棋");
		makeManual = new JMenuItem("製作棋譜");
		saveManual = new JMenuItem("保存棋譜");
		saveManual.setEnabled(false);
		playManual = new JMenuItem("演示棋譜");
		fileMenu.add(makeManual);
		fileMenu.add(saveManual);
		fileMenu.add(playManual);
		bar.add(fileMenu);
		setJMenuBar(bar);
		makeManual.addActionListener(this);
		saveManual.addActionListener(this);
		playManual.addActionListener(this);
		board = new ChessBoard(45, 45, 9, 10);
		
		record = board.record;
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
		setBounds(60, 20, 690, 540);
		fileChooser = new JFileChooser();
		con.validate();
		validate();
		JOptionPane.showMessageDialog(null, "開始遊戲，紅方先下!!!!!!!!!!");
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == makeManual) {
			con.removeAll();
			saveManual.setEnabled(true);
			this.setTitle(makeManual.getText());
			board = new ChessBoard(45, 45, 9, 10);
			record = board.record;
			JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
					true, board, record);
			split.setDividerSize(5);
			split.setDividerLocation(460);
			con.add(split, BorderLayout.CENTER);
			validate();
		}
		if (e.getSource() == saveManual) {
			int state = fileChooser.showSaveDialog(null);
			File saveFile = fileChooser.getSelectedFile();
			if (saveFile != null && state == JFileChooser.APPROVE_OPTION) {
				try {
					FileOutputStream outOne = new FileOutputStream(saveFile);
					ObjectOutputStream outTwo = new ObjectOutputStream(outOne);
					outTwo.writeObject(record.getManual());
					outOne.close();
					outTwo.close();
				} catch (IOException event) {
				}
			}
		}
		if (e.getSource() == playManual) {
			con.removeAll();
			con.repaint();
			con.validate();
			validate();
			saveManual.setEnabled(false);

			int state = fileChooser.showOpenDialog(null);
			File openFile = fileChooser.getSelectedFile();
			if (openFile != null && state == JFileChooser.APPROVE_OPTION) {
				try {
					FileInputStream inOne = new FileInputStream(openFile);
					ObjectInputStream inTwo = new ObjectInputStream(inOne);
					Manual = (LinkedList) inTwo.readObject();
					inOne.close();
					inTwo.close();
					ChessBoard board = new ChessBoard(45, 45, 9, 10);
					demon = new Demon(board);
					demon.setManual(Manual);
					con.add(demon, BorderLayout.CENTER);
					con.validate();
					validate();
					this.setTitle(playManual.getText() + ":" + openFile);
				} catch (Exception event) {
					JLabel label = new JLabel("不是棋譜文件");
					label.setFont(new Font("標楷體", Font.BOLD, 60));
					label.setForeground(Color.red);
					label.setHorizontalAlignment(SwingConstants.CENTER);
					con.add(label, BorderLayout.CENTER);
					con.validate();
					this.setTitle("沒有打開棋譜");
					validate();
				}
			} else {
				JLabel label = new JLabel("沒有打開棋譜文件");
				label.setFont(new Font("標楷體", Font.BOLD, 50));
				label.setForeground(Color.pink);
				label.setHorizontalAlignment(SwingConstants.CENTER);
				con.add(label, BorderLayout.CENTER);
				con.validate();
				this.setTitle("沒有打開棋譜文件");
				validate();
			}
		}
	}

	public static void main(String args[]) {
		new ChineseChess();
	}
}