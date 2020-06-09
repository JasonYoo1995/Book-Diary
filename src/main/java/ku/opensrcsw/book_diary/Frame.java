package ku.opensrcsw.book_diary;

import java.awt.CardLayout;
import java.awt.Container;

import javax.swing.JFrame;

public class Frame extends JFrame{
	Container contentPane;
	CardLayout cardLayout;
	ListPage listPage;
	BookInformation bookInformation;
	public Frame() {
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("My Own Book Diary");
        this.setSize(735,700);
        this.setLocation(350, 100);
        this.setVisible(true);
        this.setResizable(false);
		this.contentPane = this.getContentPane();
		
        cardLayout = new CardLayout(0,0);
        contentPane.setLayout(cardLayout);
        
        this.bookInformation = new BookInformation();
        this.listPage = new ListPage(this);
        listPage.linkObject(bookInformation);
        bookInformation.linkObject(listPage);
        
        contentPane.add(listPage,"list");
        contentPane.add(new WritePage(this), "write");
        contentPane.add(new ReadPage(this), "read");
        
        this.changePage("list");
	}
	
	public void changePage(String pageName) {
        cardLayout.show(contentPane, pageName);	
	}
}
