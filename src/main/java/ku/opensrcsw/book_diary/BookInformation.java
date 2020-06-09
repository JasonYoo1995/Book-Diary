package ku.opensrcsw.book_diary;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

public class BookInformation {
	ArrayList<Book> books = new ArrayList<Book>();
	ArrayList<Book> filteredBooks = new ArrayList<Book>();
	ListPage listPage;
	NumberPanel numberPanel;
	FileManager fileManager;
	boolean random;
	
	public void linkObject(ListPage listPage) {
		this.listPage = listPage;
		this.numberPanel = listPage.numberPanel;
	}
	
	BookInformation() {
		this.fileManager = new FileManager();
//		random = true;
		initializeBooks();
	}
	
	public void sortByAlphbet() {
		Collections.sort(books, new Comparator<Book>() {
		    @Override
		    public int compare(Book book1, Book book2) {
		        return book1.date.compareToIgnoreCase(book2.date);
		    }
		});
	}
	
	public void generateBooksRandomly() {
		for (int i = 0; i < 500; i++) {
			Random randomMonth = new Random();
			Random randomDay = new Random();
			String month = String.valueOf(4+randomMonth.nextInt(4));
			int dayInt = 1+randomMonth.nextInt(30);
			String day;
			if(dayInt<10) day = "0"+String.valueOf(dayInt);
			else day = String.valueOf(dayInt);
			String date = "2020-0"+month+"-"+day;
			Random randomName = new Random();
			books.add(new Book(date, "자동 생성된 책 이름 "+randomName.nextInt(1000), "랜덤 저자"));
		}
	}
	
	public void initializeBooks() {
		// load real books
		books = fileManager.load();
		
		//generate random books
		if(random) generateBooksRandomly();

		sortByAlphbet();
	}
	
	public Book readBook(String date, String title, String author) {
		return this.fileManager.read(date, title, author);
	}
	
	public Book addBook(String date, String title, String author) {
		this.fileManager.write(date, title, author);
		return new Book(date, title, author);
	}
	
	public void deleteBook() {
		
	}
	
	// if search is false, the event is due to clicking the number below.	
	public ArrayList<Book> getBooks(int pageNum, boolean search, String keyword, String fromString, String toString) { 
		ArrayList<Book> bundle = new ArrayList<Book>();
		if(search) { // search
			numberPanel.setNumber(1);
			filteredBooks.clear();
			for (int i = 0; i < books.size(); i++) { // filtering
				if(fromString!=null) {
					if(books.get(i).date.compareTo(fromString)<0) continue;
				}
				if(toString!=null) {
					if(books.get(i).date.compareTo(toString)>0) continue;
				}
				if(books.get(i).title.contains(keyword)) {
					filteredBooks.add(books.get(i));
				}
			}
			int to = 10;
			if(pageNum*10 > filteredBooks.size()) to = filteredBooks.size()%10;
 			for (int i = 0; i < to; i++) { // get 1~10 items
				bundle.add(filteredBooks.get((pageNum-1)*10+i));
			}
		}
		else { // number click
			int to = 10;
			if(pageNum*10 > filteredBooks.size()) to = filteredBooks.size()%10;
			for (int i = 0; i < to; i++) { // get 1~10 items
				bundle.add(filteredBooks.get((pageNum-1)*10+i));
			}
		}
		return bundle;
	}
	
}
