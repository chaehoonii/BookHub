package multi.dokgi.bookhub.booklist.dto;

import lombok.Data;

@Data
public class BookListDTO {
	
	private String bookIsbn; //책 ISBN(isbn13) - 알라딘 API 필드명
	private String bookName; //책 이름(title)
	private String bookImg; //책 표지(cover)
	private String bookAuthor; //책 저자(author)
	private String bookContent; //책 상품설명(description)
	private String bookPubdate; //책 출간일(pubDate)
	private String bookPublisher; //책 출판사(publisher)
	
	//부가정보
	private int bookEndpage; //책 페이지 수 (itemPage)
	
}
