package multi.dokgi.bookhub.booklist;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class BookListDTO {
	
	String bookIsbn; //책 ISBN(isbn13) - 알라딘 API 필드명
	String bookName; //책 이름(title)
	String bookImg; //책 표지(cover)
	String bookAuthor; //책 저자(author)
	String bookContent; //책 상품설명(description)
	String bookPubdate; //책 출간일(pubdate)
	String bookPublisher; //책 출판사(publisher)
	
	//부가정보
	int bookEndpage; //책 페이지 수 (itemPage)
	
}
