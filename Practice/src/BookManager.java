
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

public class BookManager {
    private ArrayList<Book> books;

    public BookManager() {
        books = new ArrayList<>();
    }

    // 책 추가
    public void addBook(Book book) {
        for (Book existingBook : books) {
            if (existingBook.getId().equals(book.getId())) {
                //System.out.println("해당 ID(" + book.getId() + ")는 이미 존재합니다.");
                return;
            }
        }
        books.add(book);
        //System.out.println(book + "도서가 추가되었습니다.");
    }

    // 모든 책 반환
    public ArrayList<Book> getAllBooks() {
        return new ArrayList<Book>(books); 
    }
    
    // Id로 책 삭제
    public void removeBookById(String id) {
        Iterator<Book> iterator = books.iterator();
        while (iterator.hasNext()) {
            Book book = iterator.next();
            if (book.getId().equals(id)) {
                iterator.remove();
                // System.out.println("Book(id: '" + id + "') 도서를 삭제하였습니다.");
                return;
            }
        }
        // System.out.println("해당 ID(" + id + ")의 도서를 찾을 수 없습니다.");
    } 



    // 저자로 책 찾기
    public ArrayList<Book> findBooksByAuthor(String author) {
    	ArrayList<Book> booksFound = new ArrayList<Book>();
        for (Book book : books) {
            if (book.getAuthor().contains(author)) {
            	booksFound.add(book);
            }
        }
        return booksFound;
    }

    // 제목으로 책 찾기
    public ArrayList<Book> findBooksByTitle(String titleSubstring) {
        ArrayList<Book> booksFound = new ArrayList<Book>(); 
        for (Book book : books) { 
            if (book.getTitle().contains(titleSubstring)) { 
                booksFound.add(book); 
            }
        }
        return booksFound; 
    }

    
    // id로 책 찾기
    public Book findBookById(String id) {
        for (Book book : this.books) { 
            if (book.getId().equals(id)) {
                return book;
            }
        }
        return null; 
    }

    // 출판년도로 책 찾기
    public ArrayList<Book> findBooksByYearOfPublication(int year) {
        ArrayList<Book> booksFound = new ArrayList<Book>(); 
        for (Book book : books) { 
            if (book.getYearOfPublication() == year) { 
                booksFound.add(book); 
            }
        }
        return booksFound;
    }
    
    /* 
     *	여기서부터 Binary Search를 위한 함수
     *  1. Binary Search를 하기 전에 Id로 sort
     *	2. 해당 Id를 바탕으로 Binary Search 진행
     *	3. 찾아진 아이템을 리턴
     */

    // Id 를 기반으로 books arrayList를 Sort하는 함수
    public void sortById() {
    	books.sort(new Comparator<Book>() {
			
			@Override
			public int compare(Book book1, Book book2) {
				String bookId1 = book1.getId();
				String bookId2 = book2.getId();
				
				int result = bookId1.compareTo(bookId2);
				return result;
			}
		});
    }
    
    
    // Id로 binary search를 이용해서 book 찾기
    public Book search_bs(String id) {
    	int minIndex = 0;
    	int maxIndex = books.size() - 1;
    	
    	// books 배열을 Id를 기준으로 Sort한다.
    	sortById();
    	
    	// binary search를 수행한다.
    	while (minIndex <= maxIndex) {
    		int middleIndex = (minIndex + maxIndex) / 2;
    		
    		int result = books.get(middleIndex).getId().compareTo(id);
    		
    		if (result < 0) {
    			minIndex = middleIndex + 1;
    		} else if (result > 0) {
    			maxIndex = middleIndex - 1;
    		} else {
    			return books.get(middleIndex);
    		}
    	}
    	
    	return null;
    }
}
