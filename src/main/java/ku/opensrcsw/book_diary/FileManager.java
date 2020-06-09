package ku.opensrcsw.book_diary;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class FileManager {
	String fileName1="./book_information1.txt", fileName2="./book_information2.txt";
	boolean toggle = true;
	public void toggle(){
		this.toggle = !toggle;
	}
	FileManager(){
	}
	
	public ArrayList<Book> loadAllBooks() {
		ArrayList<Book> books = new ArrayList<Book>();
		try {
			File file;
			if(toggle) file = new File(fileName1);
			else file = new File(fileName2);
			if(file.exists()) { 
				BufferedReader inFile = new BufferedReader(new FileReader(file)); 
				String sLine = null; 
				while( (sLine = inFile.readLine()) != null ) {
					String arg[] = sLine.split("/");
					books.add(new Book(arg[0],arg[1],arg[2]));
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return books;
	}
	
	public void addBook(Book book) {
		String date = book.date;
		String title = book.title;
		String author = book.author;
		Writer output;
		try {
			if(toggle) output = new BufferedWriter(new FileWriter(fileName1, true));
			else output = new BufferedWriter(new FileWriter(fileName2, true));
			String lineToAdd = date+"/"+title+"/"+author+"\n";
			output.append(lineToAdd);
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteBook(Book book) {
		String date = book.date;
		String title = book.title;
		String author = book.author;
		File inputFile, tempFile;
		if(!toggle) {
			inputFile = new File(fileName1);
			tempFile = new File(fileName2);
		}
		else {
			inputFile = new File(fileName2);
			tempFile = new File(fileName1);
		}
		toggle();
		try {
	
			BufferedReader reader = new BufferedReader(new FileReader(inputFile));
			BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
	
			String lineToRemove = date+"/"+title+"/"+author+"\n";
			String currentLine;
	
			while((currentLine = reader.readLine()) != null) {
			    String trimmedLine = currentLine.trim();
			    if(trimmedLine.equals(lineToRemove)) continue;
			    writer.write(currentLine + System.getProperty("line.separator"));
			}
			writer.close(); 
			reader.close();
		}
		catch(Exception e) {
			System.out.println("file error");
		};
		
//		try {
//			Path source = tempFile.toPath();
//			inputFile.delete();
//			try {
//			     Files.move(source, source.resolveSibling(inputFile.toPath()));
//			} catch (Exception e) {
//			     e.printStackTrace();
//			}
//		}
//		catch(Exception e) {
//			System.out.println("file error2");
//		};
	}
}
