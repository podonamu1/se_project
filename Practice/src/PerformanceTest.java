import java.util.*;

public class PerformanceTest {
	
	BookManager bookManager;
	public static final int BOOKS_NUMBER = 10000; // 책 개수
	public PerformanceTest() {
		bookManager = new BookManager();
		
		// 책 추가
		for (int i = 1; i <= BOOKS_NUMBER; i++) {
			bookManager.addBook(new Book(
				Integer.toString(i),
				"그리스 로마 신화 " + i + "권",
				"그리스인들",
				100 + 3 * i
			));
		}
	}
	
	// 기본 Search에 대한 성능 테스트를 수행하는 메서드
	// 기본 Search로 100개의 모든 책에 대한 검색 시작
	public long testBasicSearch() {
		// 시작 시간 입력
		System.out.println("모든 책에 대한 기본 Search 시작");
		long startTime = System.nanoTime();
		
		// Search 수행
		for (int i = 1; i <= BOOKS_NUMBER; i++) {
			bookManager.findBookById(Integer.toString(i));
		}
		
		// 끝 시간 입력 및 수행 시간 검사
		long endTime = System.nanoTime();
		System.out.println("모든 책에 대한 기본 Search 종료");
		
		long elapsedTime = endTime - startTime;
		System.out.println("소요 시간(ns) : " + elapsedTime + " ns");
		System.out.println("소요 시간(초) : " + elapsedTime / 1000000000.0 + " 초");
		
		return elapsedTime;
	}
	
	// 이진 Search에 대한 성능 테스트를 수행하는 메서드
	// 이진 Search로 100개의 모든 책들에 대한 검색 시작
	public long testBinarySearch() {
		// binarySearch 이전에 Sort 한다
		bookManager.sortById();
		
		// 시작 시간 입력
		System.out.println("모든 책에 대한 Binary Search 시작");
		long startTime = System.nanoTime();
		
		// Search 수행
		for (int i = 1; i < BOOKS_NUMBER; i++) {
			bookManager.search_bs(Integer.toString(i));
		}
		
		// 끝 시간 입력 및 수행 시간 검사
		long endTime = System.nanoTime();
		System.out.println("모든 책에 대한 Binary Search 종료");
		
		long elapsedTime = endTime - startTime;
		System.out.println("소요 시간(ns) : " + elapsedTime + " ns");
		System.out.println("소요 시간(초) : " + elapsedTime / 1000000000.0 + " 초");
		
		return elapsedTime;
	}
	
	// basic 과 binary search에 대한 성능을 비교하는 메인 함수
	public static void main(String[] args) {
		PerformanceTest test = new PerformanceTest();
		long basicSearchTime = test.testBasicSearch();
		long binarySearchTime = test.testBinarySearch();
		
		if (basicSearchTime < binarySearchTime) {
			double elapsedSeconds = (binarySearchTime - basicSearchTime) / 1000000000.0;
			System.out.println("기본 Search가 Binary Search보다 " + elapsedSeconds + " 초만큼 빠르다.");
		} else {
			double elapsedSeconds = (basicSearchTime - binarySearchTime);
			System.out.println("Binary Search가 기본 Search보다 " + elapsedSeconds + " 초만큼 빠르다.");
		}
	}
}
