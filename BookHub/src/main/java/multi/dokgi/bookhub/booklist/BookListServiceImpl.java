package multi.dokgi.bookhub.booklist;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.springframework.stereotype.Service;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.ParserAdapter;

@Service("booklistservice")
public class BookListServiceImpl implements BookListService{
	
	//상품 리스트 API
	@Override
	public Map<String, Object> itemList(String start) {
		List<BookListDTO> booklist = new ArrayList<BookListDTO>();
		int totalResults = 0; //총 결과 수
		Map<String,Object> result = new HashMap<String, Object>();
		
		//알라딘API
		String BASE_URL = "http://www.aladin.co.kr/ttb/api/ItemList.aspx?";
		Map<String,String> hm = new HashMap<String,String>();
		
		try {
			hm.put("ttbkey", "ttbkjn92051341001");		
			hm.put("QueryType", "Bestseller");
			hm.put("MaxResults", "10");
			hm.put("start", start);
			hm.put("SearchTarget", "Book");
			hm.put("output", "xml");
			hm.put("Version", "20131101");
			
			StringBuffer sb = new StringBuffer();
			Iterator<String> iter = hm.keySet().iterator();
			while (iter.hasNext()) {
				String key = iter.next();
				String val  = hm.get(key);
				sb.append(key).append("=").append(val).append("&");
			}

			String url = BASE_URL + sb.toString();			
			AladdinOpenAPIHandler api = new AladdinOpenAPIHandler();
			api.parseXml(url);
			
			for(BookListDTO item : api.Items){
				booklist.add(item);
				System.out.println(item.bookIsbn + " : " + item.bookName);
			}
			totalResults = api.totalResults;
			
			result.put("booklist", booklist);
			result.put("totalResults", totalResults);
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
		}		
		
		return result;
	}

	//상품 검색 API
	@Override
	public Map<String,Object> itemSearch(String searchType, String searchWord, String start){

		List<BookListDTO> booklist = new ArrayList<BookListDTO>();
		int totalResults = 0; //총 결과 수
		Map<String,Object> result = new HashMap<String, Object>();
		
		//알라딘API
		String BASE_URL = "http://www.aladdin.co.kr/ttb/api/ItemSearch.aspx?";
		Map<String,String> hm = new HashMap<String,String>();
		
		try {
			hm.put("ttbkey", "ttbkjn92051341001");		
			hm.put("Query", URLEncoder.encode(searchWord, "UTF-8"));
			hm.put("QueryType", searchType);
			hm.put("MaxResults", "25");
			hm.put("start", start);
			hm.put("SearchTarget", "Book");
			hm.put("Sort", "Accuracy");
			hm.put("output", "xml");
			
			StringBuffer sb = new StringBuffer();
			Iterator<String> iter = hm.keySet().iterator();
			while (iter.hasNext()) {
				String key = iter.next();
				String val  = hm.get(key);
				sb.append(key).append("=").append(val).append("&");
			}

			String url = BASE_URL + sb.toString();			
			AladdinOpenAPIHandler api = new AladdinOpenAPIHandler();
			api.parseXml(url);
			
			for(BookListDTO item : api.Items){
				booklist.add(item);
				System.out.println(item.bookIsbn + " : " + item.bookName);
			}
			totalResults = api.totalResults;
			
			result.put("booklist", booklist);
			result.put("totalResults", totalResults);
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
		}		
		
		return result;
	}

	//상품 조회 API
	@Override
	public BookListDTO itemLookUp(String isbn) {
		BookListDTO bookdetail = null;
		
		//알라딘API
		String BASE_URL = "http://www.aladdin.co.kr/ttb/api/ItemLookUp.aspx?";
		Map<String,String> hm = new HashMap<String,String>();
		
		try {
			hm.put("ttbkey", "ttbkjn92051341001");		
			hm.put("ItemIdType", "ISBN13");
			hm.put("ItemId", isbn);
			hm.put("Cover", "Big");
			hm.put("output", "xml");
			
			StringBuffer sb = new StringBuffer();
			Iterator<String> iter = hm.keySet().iterator();
			while (iter.hasNext()) {
				String key = iter.next();
				String val  = hm.get(key);
				sb.append(key).append("=").append(val).append("&");
			}

			String url = BASE_URL + sb.toString();			
			AladdinOpenAPIHandler api = new AladdinOpenAPIHandler();
			api.parseXml(url);
			
			List<BookListDTO> booklist = new ArrayList<BookListDTO>();
			
			for(BookListDTO item : api.Items){
				booklist.add(item);
				System.out.println(item.bookIsbn + " : " + item.bookName);
			}
			bookdetail = booklist.get(0);
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
		}		
		
		return bookdetail;
	}

}

class AladdinOpenAPIHandler extends DefaultHandler {
	public List<BookListDTO> Items;
	private BookListDTO currentItem;
	private boolean inItemElement = false;
	private String tempValue;
	public int totalResults;

	public AladdinOpenAPIHandler( ){
		Items = new ArrayList<BookListDTO>();
		totalResults = 0;
	}

	//시작 태그를 만났을 때
	public void startElement(String namespace, String localName, String qName, Attributes atts) {
		if(localName.equals("totalResults")) {
			tempValue = "";
		}		
		if (localName.equals("item")) {
			currentItem = new BookListDTO();
			inItemElement = true;
		} else if (localName.equals("isbn13")) {
			tempValue = "";
		} else if (localName.equals("title")) {
			tempValue = "";
		} else if (localName.equals("cover")) {
			tempValue = "";
		} else if (localName.equals("author")) {
			tempValue = "";
		} else if (localName.equals("description")) {
			tempValue = "";
		} else if (localName.equals("pubdate")) {
			tempValue = "";
		} else if (localName.equals("publisher")) {
			tempValue = "";
		} else if (localName.equals("itemPage")) {
			tempValue = "";
		}
	}
	
	//태그 사이의 내용을 처리
	public void characters(char[] ch, int start, int length) throws SAXException{
		tempValue = tempValue + new String(ch,start,length);
	}

	//끝 태그를 만났을 때
	public void endElement(String namespaceURI, String localName,String qName) {
		if(!inItemElement){
			if(localName.equals("totalResults")) {
				totalResults = Integer.parseInt(tempValue);
			}
		}
		else{
			if (localName.equals("item")) {
				Items.add(currentItem);
				currentItem = null;
				inItemElement = false;
			} else if (localName.equals("isbn13")) {
				currentItem.bookIsbn = tempValue;
			} else if (localName.equals("title")) {
				currentItem.bookName = tempValue;
			} else if (localName.equals("cover")) {
				currentItem.bookImg = tempValue;
			} else if (localName.equals("author")) {
				currentItem.bookAuthor = tempValue;
			} else if (localName.equals("description")) {
				currentItem.bookContent = tempValue;
			} else if (localName.equals("pubdate")) {
				currentItem.bookPubdate = tempValue;
			} else if (localName.equals("publisher")) {
				currentItem.bookPublisher = tempValue;
			} else if (localName.equals("itemPage")) {
				currentItem.bookEndpage = Integer.parseInt(tempValue);
			}
		}
	}

	public void parseXml(String xmlUrl) throws Exception {
            SAXParserFactory spf = SAXParserFactory.newInstance();
            SAXParser sp = spf.newSAXParser();
            ParserAdapter pa = new ParserAdapter(sp.getParser());
            pa.setContentHandler(this);
			pa.parse(xmlUrl);
	}
}
