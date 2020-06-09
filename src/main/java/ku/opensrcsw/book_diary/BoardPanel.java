package ku.opensrcsw.book_diary;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class BoardPanel extends JPanel{
	JPanel datePanel, titlePanel, authorPanel;
	Color blue;
	ListPage listPage;
	
	public BoardPanel(int x, int y, int w, int h, ListPage listPage) {
		blue = new Color(226,249,255);
		
		this.setBounds(x,y,w,h);
		this.setLayout(null);

		this.listPage = listPage;

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
			final ArrayList<Book> bs = books;
			JLabel dateLabel = new JLabel(books.get(i).date);
			dateLabel.setHorizontalAlignment(JLabel.CENTER);
			datePanel.add(dateLabel);
			final Book book = books.get(i);
			final JLabel titleLabel = new JLabel(book.title);
			titleLabel.setHorizontalAlignment(JLabel.CENTER);
			titleLabel.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					super.mouseClicked(e);
					listPage.changePage("detail");
					listPage.setContent(book);
				}
			});
			titlePanel.add(titleLabel);
			JLabel authorLabel = new JLabel(books.get(i).author);
			authorLabel.setHorizontalAlignment(JLabel.CENTER);
			authorPanel.add(authorLabel);
		}
		this.revalidate();
	}

}
