import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
//
public class BookTest {
    @BeforeEach
    public void setUp() {
        Book.addBook(new Book("1", "Java 기초", "John", 2021));
        Book.addBook(new Book("2", "Python 기초", "John", 2022));
        Book.addBook(new Book("3", "알고리즘 천재가 되는 방법", "Doe", 2023));
        Book.addBook(new Book("4", "데이터 구조 기초", "Smith", 2024));
    }

    @Test
    public void testAddBook() {
        Book.addBook(new Book("5", "네트워크 기초", "Alice", 2025));
        System.out.println("testAddBook 실행 완료");
    }

    @Test
    public void testPrintHello(){
        System.out.println("hello start");
        Book.HelloWorld();
        System.out.println("hello fin");
    }

    @Test
    public void testDuplicateAddBook() {
        Book.addBook(new Book("1", "Java 기초", "Alice", 2021)); // 중복 추가 시도
        System.out.println("testDuplicateAddBook 실행 완료");
    }

    @Test
    public void testFindBookById() {
        Book.findBookById("2");
        System.out.println("testFindBookById 실행 완료");
    }

    @Test
    public void testFindBookByTitle() {
        System.out.println("testFindBookByTitle 시작");
        Book.findBookByTitle("기초");  // "기초"가 제목에 포함된 도서 검색
        System.out.println("testFindBookByTitle 실행 완료");
    }

    @Test
    public void testDeleteBook() {
        Book.deleteBook("4");
        System.out.println("testDeleteBook 실행 완료");
    }

    @Test
    public void testFindBookByAuthor() {
        System.out.println("testFindBookByAuthor 시작");
        Book.findBookByAuthor("John");
        System.out.println("testFindBookByAuthor 실행 완료");
    }
}
