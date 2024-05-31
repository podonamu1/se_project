
import java.util.ArrayList;
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

}
