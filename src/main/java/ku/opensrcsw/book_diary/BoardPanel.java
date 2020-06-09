package ku.opensrcsw.book_diary;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class BoardPanel extends JPanel{
	JPanel datePanel, titlePanel, authorPanel;
	Color blue;
	
	public BoardPanel(int x, int y, int w, int h) {
		blue = new Color(226,249,255);
		
		this.setBounds(x,y,w,h);
		this.setLayout(null);

		datePanel = new JPanel(new GridLayout(10,1));
		titlePanel = new JPanel(new GridLayout(10,1));
		authorPanel = new JPanel(new GridLayout(10,1));

		datePanel.setBounds(0,0,w/5,h);
		titlePanel.setBounds(w/5,0,w/5*3,h);
		authorPanel.setBounds(w/5*4,0,w/5,h);
		
		datePanel.setBackground(blue);
		titlePanel.setBackground(blue);
		authorPanel.setBackground(blue);
		
		this.add(datePanel);
		this.add(titlePanel);
		this.add(authorPanel);

		this.revalidate();
		this.repaint();
	}
	
	public void refreshPage(ArrayList<Book> books) {
		datePanel.removeAll();
		titlePanel.removeAll();
		authorPanel.removeAll();

		this.revalidate();
		this.repaint();
		
		if(books.isEmpty()) {
			return;
		}
		
		for (int i = 0; i < books.size(); i++) {
			JLabel label = new JLabel(books.get(i).date);
			label.setHorizontalAlignment(JLabel.CENTER);
			datePanel.add(label);
			JLabel label2 = new JLabel(books.get(i).title);
			label2.setHorizontalAlignment(JLabel.CENTER);
			titlePanel.add(label2);
			JLabel label3 = new JLabel(books.get(i).author);
			label3.setHorizontalAlignment(JLabel.CENTER);
			authorPanel.add(label3);
		}
		this.revalidate();
	}

}
