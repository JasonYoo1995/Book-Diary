package ku.opensrcsw.book_diary;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class ListPage extends JPanel{
	Frame frame;
	JPanel titlePanel, searchPanel;
	BoardPanel boardPanel;
	NumberPanel numberPanel;
	JLabel titleText, searchText, boardText;
	int startpointX = 60;
	int startpointY = 110;
	int width = 600;
	Color red, yellow, green;
	Calendar calendarFrom, calendarTo;
	BookInformation bookInformation;
	
	public void linkObject(BookInformation bookInformation) {
		this.bookInformation = bookInformation;
		numberPanel.linkObject(bookInformation);
	}
	
	ListPage(final Frame frame){
		red = new Color(248,229,228);
		yellow = new Color(248,240,205);
		green = new Color(213,248,201);
		
		this.frame = frame;
		this.setLayout(null);
		this.setBackground(green); // bright green
		
		titlePanel = new JPanel();
		titlePanel.setBounds(startpointX,40,width,50);
//		titlePanel.setBorder(new LineBorder(Color.black,1));
		this.add(titlePanel);
		titleText = new JLabel("도서 목록");
		titleText.setFont(new Font("Serif", Font.BOLD, 26));
		titlePanel.add(titleText);
		titlePanel.setBackground(red); // bright red
		
		searchPanel = new JPanel();
		searchPanel.setLayout(null);	
		int searchHeight = 80;
		searchPanel.setBounds(startpointX,startpointY,width,searchHeight);
		this.add(searchPanel);
		searchText = new JLabel("제목 : ");
		searchText.setBounds(30, 25, 40, 30);
		searchPanel.add(searchText);
		searchPanel.setBackground(yellow); // bright yellow
		
		// edit text
		final JTextField searchField = new JTextField();
		searchField.setBounds(70, 25, 160, 30);
		searchPanel.add(searchField);

		// search button
		JButton searchButton = new JButton("검색");
		searchButton.setBounds(250, 15, 60, 20);
				searchPanel.add(searchButton);
				searchButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						super.mouseClicked(e);
						SimpleDateFormat transFormatter = new SimpleDateFormat("yyyy-MM-dd");
						Date dateFrom = null, dateTo = null;
						dateFrom = (Date)calendarFrom.getModel().getValue();
						dateTo = (Date)calendarTo.getModel().getValue();
						String fromString = null, toString = null;
						if(dateFrom!=null)
							fromString = transFormatter.format(dateFrom);
						if(dateTo!=null)
							toString = transFormatter.format(dateTo);
						numberPanel.getBooks(1, true, searchField.getText(), fromString, toString);
					}
				});

				// search button
				JButton addButton = new JButton("등록");
				addButton.setBounds(250, 45, 60, 20);
				searchPanel.add(addButton);
				addButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						super.mouseClicked(e);
						frame.changePage("detail");
			}
		});
		
		Border border = BorderFactory.createLineBorder(Color.BLACK);
		
		// from label
		JPanel from = new JPanel(new FlowLayout());
//		from.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(0, 0, 0, 0)));
		from.setBounds(380-30, 2, 40, 40);
		from.add(new JLabel("from"));
		from.setBackground(yellow); // bright yellow
		searchPanel.add(from);

		// to label
		JPanel to = new JPanel(new FlowLayout());
		to.setBounds(380-30, 42, 40, 40);
		to.add(new JLabel("to"));
		to.setBackground(yellow); // bright yellow
		searchPanel.add(to);
		
		// from
		JPanel calendarFromPanel = new JPanel(new FlowLayout());
		calendarFromPanel.setBackground(yellow); // bright yellow
		calendarFromPanel.setBounds(380, 0, 220,40);
		this.calendarFrom = new Calendar();
		calendarFromPanel.add(calendarFrom);
		searchPanel.add(calendarFromPanel);
		
		// to
		JPanel calendarToPanel = new JPanel(new FlowLayout());
		calendarToPanel.setBackground(yellow); // bright yellow
		calendarToPanel.setBounds(380, 40, 220,40);
		this.calendarTo = new Calendar();
		calendarToPanel.add(calendarTo);
		searchPanel.add(calendarToPanel);

		// board
		int boardHeight = 360;
		boardPanel = new BoardPanel(startpointX,startpointY+searchHeight+20,width,boardHeight, this);
		this.add(boardPanel);
		
		// number
		numberPanel = new NumberPanel(boardPanel);
		numberPanel.setBounds(startpointX,startpointY+searchHeight+20+boardHeight+20,width,30);
		this.add(numberPanel);
		
	}
	public void setContent(Book book){
		ContentPanel contentPanel = frame.detailPage.contentPanel;
		contentPanel.dateField.setText(book.date);
		contentPanel.titleField.setText(book.title);
		contentPanel.authorField.setText(book.author);
	}
	public void changePage(String pageName){
		frame.changePage(pageName);
	}
}