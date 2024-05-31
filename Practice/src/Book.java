import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
//
public class Book {
    private static Map<String, Book> bookCollection = new HashMap<>();

    private String id;
    private String title;
    private String author;
    private int yearOfPublication;

    public static void HelloWorld(){
        System.out.println("Hello World");
    }

    public Book(String id, String title, String author, int yearOfPublication) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.yearOfPublication = yearOfPublication;
    }

    public static void addBook(Book book) {
        if(bookCollection.containsKey(book.getId())) {
            System.out.println("해당 ID(" + book.getId() + ")은 이미 존재합니다.");
        } else {
            bookCollection.put(book.getId(), book);
            System.out.println(book + " 도서가 추가되었습니다.");
        }
    }

    public static void deleteBook(String id) {
        if(bookCollection.containsKey(id)) {
            Book removedBook = bookCollection.remove(id);
            System.out.println(removedBook + " 도서를 삭제하였습니다.");
        } else {
            System.out.println("해당 ID(" + id + ")의 도서를 찾을 수 없습니다.");
        }
    }

    public static void findBookById(String id) {
        Book foundBook = bookCollection.get(id);
        if(foundBook != null) {
            System.out.println(foundBook);
        } else {
            System.out.println("해당 ID(" + id + ")의 도서를 찾을 수 없습니다.");
        }
    }

    public static void findBookByTitle(String title) {
        String result = bookCollection.values().stream()
                .filter(book -> book.getTitle().contains(title))
                .map(Book::toString)
                .collect(Collectors.joining("\n"));

        if(!result.isEmpty()) {
            System.out.println(result);
        } else {
            System.out.println("검색된 도서가 없습니다.");
        }
    }

    public static void findBookByAuthor(String author) {
        String result = bookCollection.values().stream()
                .filter(book -> book.getAuthor().contains(author))
                .map(Book::toString)
                .collect(Collectors.joining("\n"));

        if(!result.isEmpty()) {
            System.out.println(result);
        } else {
            System.out.println("작가 이름으로 검색된 도서가 없습니다.");
        }
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getYearOfPublication() {
        return yearOfPublication;
    }

    @Override
    public String toString() {
        return "Book {id: '" + id + "', 제목: '" + title + "', 저자: '" + author + "', 출판년도: " + yearOfPublication + "}";
    }
}
