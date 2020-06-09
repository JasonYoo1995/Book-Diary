package ku.opensrcsw.book_diary;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class NaverPanel extends JPanel{
	JPanel resultPanel, selectPanel;
	WebServer webServer;
	ContentPanel contentPanel;
	NaverPanel(ContentPanel contentPanel){
		this.contentPanel = contentPanel;
		this.setLayout(null);
		this.setBounds(20,60,560,370);
		this.setBackground(new Color(248,240,205));

		resultPanel = new JPanel();
		resultPanel.setBounds(20,20, 450, 330);
		resultPanel.setLayout(new GridLayout(7,1));
		this.add(resultPanel);
		resultPanel.setOpaque(false);

		selectPanel = new JPanel();
		selectPanel.setBounds(490,20,50, 330);
		selectPanel.setLayout(new GridLayout(7,1));
		this.add(selectPanel);
		selectPanel.setOpaque(false);

		webServer = new WebServer();
	}

	public void search(String title){
		resultPanel.removeAll();
		selectPanel.removeAll();
		resultPanel.setLayout(new GridLayout(7,1));
		selectPanel.setLayout(new GridLayout(7,1));
		final ArrayList<Book> books = webServer.search(title);
		for (int i=0; i<books.size(); i++){
			resultPanel.add(new JLabel(books.get(i).title+" / "+books.get(i).author));
			JButton button = new JButton("선택");
			Border border = BorderFactory.createLineBorder(Color.BLACK);
			button.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(0, 0, 0, 0)));
			selectPanel.add(button);
			final int idx = i;
			button.addMouseListener(new MouseAdapter() {
				int index = idx;
				@Override
				public void mouseClicked(MouseEvent e) {
					super.mouseClicked(e);
					bookSelected(books.get(index));
				}
			});
		}
		revalidate();
	}
	public void bookSelected(Book book){
		contentPanel.setSelectedBook(book);
	}
}
