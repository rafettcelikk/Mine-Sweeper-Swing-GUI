package mineSweeper;

import java.awt.Image;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;


public class Btn extends JButton {
	private int row, col, count;
	private boolean mine, flag;
	
	public Btn(int row, int col) {
		super();
		this.row = row;
		this.col = col;
		this.count = 0;
		this.mine = false;
		this.flag = false;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public boolean isMine() {
		return mine;
	}

	public void setMine(boolean mine) {
		this.mine = mine;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	
	public void setIcon(Icon icon) {
        if (icon instanceof ImageIcon) {
        	this.setSize(80, 80);
            ImageIcon imageIcon = (ImageIcon) icon;
            Image image = imageIcon.getImage();
            Image scaledImage = image.getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_SMOOTH);
            super.setIcon(new ImageIcon(scaledImage));
        } else {
            super.setIcon(icon);
        }
	}
}
