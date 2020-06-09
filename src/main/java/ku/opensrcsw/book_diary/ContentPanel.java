package ku.opensrcsw.book_diary;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ContentPanel extends JPanel {
    JLabel dateLabel, titleLabel, authorLabel;
    JTextField dateField, titleField, authorField;
    int posX=20, posY=20, sizeX=30, sizeY=25;
    DetailPage detailPage;
    ContentPanel(final DetailPage detailPage) {
        this.detailPage = detailPage;
        this.setLayout(null);
        dateLabel = new JLabel();
        titleLabel = new JLabel();
        authorLabel = new JLabel();
        this.dateLabel.setText("날짜");
        this.titleLabel.setText("제목");
        this.authorLabel.setText("저자");
        dateField = new JTextField();
        titleField = new JTextField();
        authorField = new JTextField();
        dateLabel.setBounds(posX,posY,sizeX,sizeY);
        titleLabel.setBounds(posX+130,posY,sizeX,sizeY);
        authorLabel.setBounds(posX+355,posY,sizeX,sizeY);
        dateField.setBounds(posX+35,posY,80,sizeY);
        titleField.setBounds(posX+165,posY,180,sizeY);
        authorField.setBounds(posX+390,posY,120,sizeY);
        this.add(dateLabel);
        this.add(titleLabel);
        this.add(authorLabel);
        this.add(dateField);
        this.add(titleField);
        this.add(authorField);
        JButton naver = new JButton();
        naver.addMouseListener(new MouseAdapter() {
            final Frame f = detailPage.frame;
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                f.changePage("naver");
            }
        });
        naver.setText("Web");
        Border border = BorderFactory.createLineBorder(Color.BLACK);
        naver.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(0, 0, 0, 0)));
        naver.setBounds(posX+520,posY,40,sizeY);
        this.add(naver);

    }
}
