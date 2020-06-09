package ku.opensrcsw.book_diary;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class WritePage extends JPanel{
	Frame frame;
	JPanel titlePanel, contentPanel;
	JLabel titleText;
	int startpointX = 60;
	int startpointY = 110;
	int width = 600;
	WritePage(Frame frame){
		this.frame = frame;
		this.setLayout(null);
		this.setBackground(new Color(214, 245, 214));
		this.setOpaque(false);
		
		titlePanel = new JPanel();
		titlePanel.setBounds(startpointX,40,width,50);
		titlePanel.setBorder(new LineBorder(Color.black,1));
		this.add(titlePanel);
		titleText = new JLabel("감상평 작성");
		titlePanel.add(titleText);
		
		contentPanel = new JPanel();
		int contentHeight = 450;
		contentPanel.setBounds(startpointX,startpointY,width,contentHeight);
		contentPanel.setBorder(new LineBorder(Color.black,1));
		this.add(contentPanel);
		
	}
}
