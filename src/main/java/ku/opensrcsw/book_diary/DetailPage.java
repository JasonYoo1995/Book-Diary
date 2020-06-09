package ku.opensrcsw.book_diary;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;
import javax.swing.border.LineBorder;

public class DetailPage extends JPanel{
	Frame frame;
	JPanel titlePanel, bottomPanel;
	ContentPanel contentPanel;
	JLabel titleText;
	int startpointX = 60;
	int startpointY = 110;
	int width = 600;
	Color red, yellow, green, blue;
	DetailPage(final Frame frame){
		red = new Color(248,229,228);
		yellow = new Color(248,240,205);
		green = new Color(213,248,201);
		blue = new Color(226,249,255);

		this.frame = frame;
		this.setLayout(null);
		this.setBackground(green); // bright green
		
		titlePanel = new JPanel();
		titlePanel.setBounds(startpointX,40,width,50);
		this.add(titlePanel);
		titleText = new JLabel("도서 정보");
		titleText.setFont(new Font("Serif", Font.BOLD, 26));
		titlePanel.add(titleText);
		titlePanel.setBackground(red); // bright red
		
		contentPanel = new ContentPanel(this);
		int contentHeight = 450;
		contentPanel.setBounds(startpointX,startpointY,width,contentHeight);
		this.add(contentPanel);
		contentPanel.setBackground(blue);

		bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		bottomPanel.setBounds(startpointX,startpointY+contentHeight+5,width,40);
		this.add(bottomPanel);
		bottomPanel.setOpaque(false);

		JButton deleteButton = new JButton();
		JButton saveButton = new JButton();
		JButton backButton = new JButton();
		deleteButton.setText("삭제");
		saveButton.setText("저장");
		backButton.setText("목록");
		bottomPanel.add(deleteButton);
		bottomPanel.add(saveButton);
		bottomPanel.add(backButton);
		deleteButton.addMouseListener(new MouseAdapter(){
			final Frame f = frame;
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
			}
		});
		saveButton.addMouseListener(new MouseAdapter(){
			final Frame f = frame;
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
			}
		});
		backButton.addMouseListener(new MouseAdapter(){
			final Frame f = frame;
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				f.changePage("list");
			}
		});

	}
}