package ku.opensrcsw.book_diary;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class FileManager {
	FileManager(){
	}
	
	public ArrayList<Book> load() {
		ArrayList<Book> books = new ArrayList<Book>();
		try {
			File file = new File("./book_information.txt");
			if(file.exists()) { 
				BufferedReader inFile = new BufferedReader(new FileReader(file)); 
				String sLine = null; 
				while( (sLine = inFile.readLine()) != null ) {
					String arg[] = sLine.split("\\\\");
					books.add(new Book(arg[0],arg[1],arg[2]));
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return books;
	}
	
	public Book read(String date, String title, String author) {
		String targetString = "date"+"\\"+title+"\\"+author;
		try {
			File file = new File("./book_information.txt");
			if(file.exists()) { 
				BufferedReader inFile = new BufferedReader(new FileReader(file)); 
				String sLine = null; 
				while( (sLine = inFile.readLine()) != null ) { 
					if(sLine.equals(targetString)) {
						String arg[] = targetString.split("\\");
						return new Book(arg[0],arg[1],arg[2]);
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public void write(String date, String title, String author) {
		File inputFile = new File("./book_information.txt");
		File tempFile = new File("./temp_file.txt");
		try {
	
			BufferedReader reader = new BufferedReader(new FileReader(inputFile));
			BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
	
			String lineToRemove = "date"+"\\"+title+"\\"+author;
//			String lineToRemove = "1";
			String currentLine;
	
			while((currentLine = reader.readLine()) != null) {
			    // trim newline when comparing with lineToRemove
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
		
		try {
			Path source = tempFile.toPath();
			inputFile.delete();
			try {
			     Files.move(source, source.resolveSibling(inputFile.toPath()));
			} catch (Exception e) {
			     e.printStackTrace();
			}
		}
		catch(Exception e) {
			System.out.println("file error2");
		};
	}
}
