package mineSweeper;

import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class MineSweeper implements MouseListener {
	JFrame frame;
	Btn[][] board = new Btn[10][10];
	private int openButton;
	
	public MineSweeper() {
		openButton = 0;
		frame = new JFrame("Mayın Tarlası");
		frame.setSize(800, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setLayout(new GridLayout(10, 10));
		
		for(int row = 0; row < board.length; row++) {
			for(int col = 0; col < board[0].length; col++) {
				Btn btn = new Btn(row, col);
				frame.add(btn);
				btn.addMouseListener(this);
				board[row][col] = btn;
			}
		}
		
		generateMine();
		updateCount();
//		printMine();
		frame.setVisible(true);
	}
	
	public void generateMine() {
		int i = 0;
		while(i < 10) {
			int randRow = (int) (Math.random() * board.length);
			int randCol = (int) (Math.random() * board[0].length);
			while(board[randRow][randCol].isMine()) {
				randRow = (int) (Math.random() * board.length);
				randCol = (int) (Math.random() * board[0].length);
			}
			board[randRow][randCol].setMine(true);
			i++;
		}
	}
	
	public void print() {
		for(int row = 0; row < board.length; row++) {
			for(int col = 0; col < board[0].length; col++) {
				if(board[row][col].isMine()) {
					board[row][col].setIcon(new ImageIcon(getClass().getResource("/images/mine.png")));
				} else {
					board[row][col].setText(board[row][col].getCount() + "");
					board[row][col].setEnabled(false);
				}
			}
		}
	}
	
	public void printMine() {
		for(int row = 0; row < board.length; row++) {
			for(int col = 0; col < board[0].length; col++) {
				if(board[row][col].isMine()) {
					board[row][col].setIcon(new ImageIcon(getClass().getResource("/images/mine.png")));
				}
			}
		}	
	}
	
	public void updateCount() {
		for(int row = 0; row < board.length; row++) {
			for(int col = 0; col < board[0].length; col++) {
				if(board[row][col].isMine()) {
					counting(row, col);
				}
			}
		}
	}
	
	public void counting(int row, int col) {
		for(int i = row - 1; i <= row + 1; i++) {
			for(int j = col - 1; j <= col + 1; j++) {
				try {
					int value = board[i][j].getCount();
					board[i][j].setCount(++value);
				} catch (Exception e) {
					
				}
			}
		}
	}
	
	public void open(int r, int c) {
		if(r < 0 || r >= board.length || c < 0 || c >= board[0].length 
				|| board[r][c].getText().length() > 0 
				|| board[r][c].isEnabled() == false) {
			return;
		} else if (board[r][c].getCount() != 0) {
			board[r][c].setText(board[r][c].getCount() + "");
			board[r][c].setEnabled(false);
			openButton++;
		} else {
			openButton++;
			board[r][c].setEnabled(false);
			open(r - 1, c);
			open(r + 1, c);
			open(r, c - 1);
			open(r, c + 1);
		}
	}
	
	public void revealSurrounding(Btn btn) {
		int row = btn.getRow();
		int col = btn.getCol();
		for(int i = row - 1; i <= row + 1; i++) {
			for(int j = col - 1; j <= col + 1; j++) {
				if(i >= 0 && i < board.length && j >= 0 && j < board[0].length && !board[i][j].isMine() && !board[i][j].isFlag()) {
					open(i, j);
				}
			}
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		Btn btn = (Btn) e.getComponent();
		if(e.getButton() == 1) {
			System.out.println("Sol tık çalıştı!");
			if(btn.isMine()) {
				JOptionPane.showMessageDialog(frame, "Mayına bastınız. Oyun bitti!");
				print();
			} else {
				if(e.getClickCount() == 2) { 
					System.out.println("Çift tık çalıştı!");
					revealSurrounding(btn);
				} else {
					open(btn.getRow(), btn.getCol());
					if(openButton == (board.length * board[0].length) - 10) {
						JOptionPane.showMessageDialog(frame, "Tebrikler! Oyunu kazandınız!");
						print();
					}
				}
			}
		} else if (e.getButton() == 3) {
			System.out.println("Sağ tık çalıştı!");
			if(!btn.isFlag()) {
				btn.setIcon(new ImageIcon(getClass().getResource("/images/flag.png")));
				btn.setFlag(true);
			} else {
				btn.setIcon(null);
				btn.setFlag(false);
			}
		}
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
