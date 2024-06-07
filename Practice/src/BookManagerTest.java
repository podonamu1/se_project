import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BookManagerTest {
	
	private BookManager bookManager;
	
	private Book book1 = new Book("1", "자바 기초", "Jane", 2021);
	private Book book2 = new Book("2", "소프트웨어 기초", "Tom", 2024);
	private Book book3 = new Book("3", "분산 컴퓨팅", "Yoon", 2020);
	private Book book4 = new Book("4", "가나다부터 시작하는 코딩", "Yoon", 2019);
	private Book book5 = new Book("5", "응용 데이터베이스", "Aba", 2024);
	
	@BeforeEach
	void setUp() {
		bookManager = new BookManager();
		bookManager.addBook(book1);
	    bookManager.addBook(book2);
	    bookManager.addBook(book3);
	    bookManager.addBook(book4);
	    bookManager.addBook(book5);
	}

	// 책을 추가하는 것이 잘 수행되는지 테스트
	@Test
	void testAddBook() {
		System.out.println("testAddBook 시작");
		
		int bookNum = bookManager.getAllBooks().size();
        Book book4 = new Book("6", "알고리즘", "Kim", 2023);
        
        bookManager.addBook(book4);
        System.out.println(book4 + " 도서가 추가되었습니다.");
        
	    assertEquals(bookNum + 1, bookManager.getAllBooks().size());
	    assertTrue(bookManager.getAllBooks().contains(book4));
	    
        System.out.println("testAddBook 통과");
	}

	// 중복 책 추가시 오류 메시지 테스트
	@Test
	void testAddDuplicatedBook() {
		System.out.println("testAddDuplicatedBook 시작");
		
		int bookNum = bookManager.getAllBooks().size();
		Book bookDuplicate = book1;
		
	    bookManager.addBook(bookDuplicate);
	    System.out.println("해당 ID(" + bookDuplicate.getId() + ")는 이미 존재합니다.");
	    
	    assertEquals(bookNum, bookManager.getAllBooks().size());
        System.out.println("testAddDuplicatedBook 통과");
	}
	
	// 책을 포함하고 있는지 여부를 테스트
	@Test
	void testGetAllBooks() {
		System.out.println("testGetAllBooks 시작");
		
		ArrayList<Book> books = bookManager.getAllBooks();
		System.out.println("All Books : " + books);
		
		assertTrue(books.contains(book1));
		assertTrue(books.contains(book2));
		assertTrue(books.contains(book3));
		
		System.out.println("testGetAllBooks 통과");
		
	}

	// 아이디에 의한 책 제거 기능 테스트
	@Test
	void testRemoveBookById() {
		System.out.println("testRemoveBookById 시작");
		
		int bookNum = bookManager.getAllBooks().size();
		String removedBookId = book3.getId();
        
        bookManager.removeBookById(removedBookId);
        System.out.println("Book(id: '" + removedBookId + "') 도서를 삭제하였습니다.");
	    
	    assertEquals(bookNum - 1, bookManager.getAllBooks().size());
	    assertFalse(bookManager.getAllBooks().contains(book3));
	    
        System.out.println("testRemoveBookById 통과");
	}
	
	// 존재하지 않는 책 제거시 오류 메시지 띄우기 테스트
	@Test
	void testRemoveNonExistentBook() {
		System.out.println("testRemoveNonExistentBook 시작");
		
		int bookNum = bookManager.getAllBooks().size();
		String removedBookId = "-1";
        
		assertFalse(bookManager.
				getAllBooks().
				stream().
				anyMatch(book -> book.getId().equals(removedBookId)));
        
		bookManager.removeBookById(removedBookId);
        System.out.println("Book(id: '" + removedBookId + "')의 도서를 찾을 수 없습니다.");
        
	    assertEquals(bookNum, bookManager.getAllBooks().size());
	    
        System.out.println("testRemoveNonExistentBook 통과");
	}

	// 작가 이름으로 책 검색 테스트
	@Test
	void testFindBooksByAuthor() {
		System.out.println("testFindBooksByAuthor 시작");
		
        ArrayList<Book> searchedBooks = bookManager.findBooksByAuthor("Yoon");
        System.out.println("검색결과: " + searchedBooks);
        
        assertNotNull(searchedBooks);
        assertEquals(2, searchedBooks.size());
        
        for (Book b: searchedBooks) {
        	assertTrue(b.getAuthor().contains("Yoon"));
        	assertTrue(bookManager.getAllBooks().contains(b));
        }
        
        System.out.println("testFindBooksByAuthor 통과");
    }
	
	// 작가 이름을 책 찾기시 작가 이름이 없을 때 오류 메시지 테스트
	@Test
	void testFindUnknownBooksByAuthor() {
		System.out.println("testFindUnknownBooksByAuthor 시작");
		
        ArrayList<Book> searchUnknownBooks = bookManager.findBooksByAuthor("Julie");
        assertEquals(0, searchUnknownBooks.size());
        System.out.println("검색된 도서가 없습니다.");
        
        System.out.println("testFindUnknownBooksByAuthor 통과");
    }

	// 책 제목으로 책 검색 테스트
	@Test
	void testFindBooksByTitle() {
		System.out.println("testFindBooksByTitle 시작");
		
        ArrayList<Book> searchedBooks = bookManager.findBooksByTitle("기초");
        System.out.println("검색결과: " + searchedBooks);
        assertNotNull(searchedBooks);
        assertEquals(2, searchedBooks.size());
        
        for (Book b: searchedBooks) {
        	assertTrue(b.getTitle().contains("기초"));
        	assertTrue(bookManager.getAllBooks().contains(b));
        }
        
        System.out.println("testFindBooksByTitle 통과");
	}

	// 책 제목으로 책 검색시 해당하는 책이 없을 때 오류 메시지 테스트
	@Test
	void testFindUnknownBooksByTitle() {
		System.out.println("testFindUnknownBooksByTitle 시작");
		
        ArrayList<Book> searchUnknownBooks = bookManager.findBooksByTitle("정의란 무엇인가");
        assertEquals(0, searchUnknownBooks.size());
        System.out.println("검색된 도서가 없습니다.");
        
        System.out.println("testFindUnknownBooksByTitle 통과");
    }
	
	// Id로 책 검색 성공한 경우 테스트
	@Test
	void testFindBookById() {
		System.out.println("testFindBooksById 시작");
		
		String searchId = "1";
        Book searchedBook = bookManager.findBookById(searchId);
        System.out.println("검색결과: " + searchedBook);
        
        assertNotNull(searchedBook);
        assertEquals(searchId, searchedBook.getId());
        assertTrue(bookManager.getAllBooks().contains(searchedBook));
        
        System.out.println("testFindBooksById 통과");
	}
	
	// 아이디에 해당하는 책을 보유하고 있지 않는 경우 오류 메시지 테스트
	@Test
	void testFindUnknownBookById() {
		System.out.println("testFindUnknownBookById 시작");
		
        String searchId = "-1";
        assertNull(bookManager.findBookById(searchId));
        System.out.println("검색된 도서가 없습니다.");
        
        System.out.println("testFindUnknownBookById 통과");
    }

	// 출판 년도로 책 찾기 테스트
	@Test
	void testFindBooksByYearOfPublication() {
		System.out.println("testFindBooksByYOP 시작");
		
		int searchYOP = 2024;
        ArrayList<Book> searchedBooks = bookManager.findBooksByYearOfPublication(searchYOP);
        System.out.println("검색결과: " + searchedBooks);
        
        assertNotNull(searchedBooks);
        assertEquals(2, searchedBooks.size());
        
        for (Book b: searchedBooks) {
        	assertEquals(searchYOP, b.getYearOfPublication());
        	assertTrue(bookManager.getAllBooks().contains(b));
        }
        
        System.out.println("testFindBooksByYOP 통과");
	}
	
	// 아이디를 기준으로 책 정렬하기 테스트
	@Test
	void testSortById() {
		System.out.println("testSortById 시작");

        bookManager.sortById();
        System.out.println("sortById: " + bookManager.getAllBooks());
        
        String prevId = "";
        
       for (Book b : bookManager.getAllBooks()) {
    	   String currentId = b.getId();
    	   assertTrue(currentId.compareToIgnoreCase(prevId) >= 0);
    	   prevId = currentId;
       }
        
        System.out.println("testSortById 통과");
	}
	
	// 보유중인 책에 대한 이진 탐색을 수행하는 테스트
	@Test
	void testSearch_bs() {
		System.out.println("testSearch_bs 시작");
		
		String searchKey = "1";
		Book searchedBook = bookManager.search_bs(searchKey);
		System.out.println("binary search 검색결과: " + searchedBook);
		
		assertNotNull(searchedBook);
        assertEquals(searchKey, searchedBook.getId());
        assertTrue(bookManager.getAllBooks().contains(searchedBook));
        
        System.out.println("testSearch_bs 통과");
	}
	
	// 보유하고 있지 않은 책에 대한 이진 탐색 수행시 오류 메시지 발생 테스트
	@Test
	void testUnknownSearch_bs() {
		System.out.println("testFindUnknownBookById 시작");
		
        String searchKey = "-1";
        assertNull(bookManager.search_bs(searchKey));
        System.out.println("검색된 도서가 없습니다.");
        
        System.out.println("testUnknownSearch_bs 통과");
	}

}
