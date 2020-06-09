package ku.opensrcsw.book_diary;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class NumberPanel extends JPanel implements MouseListener{
	BoardPanel boardPanel;
	BookInformation bookInformation;
	JLabel[] button = new JLabel[10];
	int buttonNum = 0;
	String fromString = null, toString = null;
	
	public void linkObject(BookInformation bookInformation) {
		this.bookInformation = bookInformation;
	}
	
	NumberPanel(BoardPanel boardPanel){
		this.boardPanel = boardPanel;
		this.setLayout(new FlowLayout());
		this.bookInformation = bookInformation;
		for (int i = 0; i < button.length; i++) {
			button[i] = new JLabel(String.valueOf(i+1));
			button[i].addMouseListener(this);
		}
	}
	
	public void setNumber(int pageNum) {
		this.removeAll();
		this.revalidate();
		this.repaint();
		
		this.buttonNum = (bookInformation.filteredBooks.size()+9)/10;
		if(buttonNum<10) {
			for (int i = 0; i < buttonNum; i++) {
				button[i].setText(String.valueOf(i+1));
				this.add(button[i]);
			}
		}
		else {
			for (int i = 0; i < 10; i++) {
				button[i].setText(String.valueOf(pageNum-5+i));
				button[i].setText(String.valueOf(i+1));
				this.add(button[i]);
			}
		}
		
		if(pageNum<=5) {
			for (int i = 0; i < button.length; i++) {
				button[i].setForeground(Color.black);
				button[pageNum-1].setForeground(Color.red);
			}
		}
		else{
			for (int i = 0; i < button.length; i++) {
				button[i].setText(String.valueOf(pageNum-5+i));
				button[i].setForeground(Color.black);
				button[5].setForeground(Color.red);
			}
		}
		this.revalidate();
	}
	
	public void getBooks(int pageNum, boolean search, String keyword, String fromString, String toString) {
		this.fromString = fromString;
		this.toString = toString;
		ArrayList<Book> books = bookInformation.getBooks(pageNum, search, keyword, fromString, toString);
		boardPanel.refreshPage(books);
		this.setNumber(pageNum);
	}
	
	public void mouseClicked(MouseEvent e) { // number clicked
		JLabel button = (JLabel)e.getSource();
		int num = Integer.parseInt(button.getText());
		getBooks(num, false, "", fromString, toString);
	}
	
	public void mousePressed(MouseEvent e) {	}
	public void mouseReleased(MouseEvent e) {	}
	public void mouseEntered(MouseEvent e) {	}
	public void mouseExited(MouseEvent e) {	}
}
