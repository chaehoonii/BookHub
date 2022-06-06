package multi.dokgi.bookhub.booklist.dto;

import lombok.Data;

@Data
public class BookListPageDTO {
	
    private int currentPage; // 현재페이지
    private int cntPerPage; // 페이지당 출력할 갯수
    private int pageSize; // 화면 하단 페이지 사이즈
    private int totalRecordCount; // 전체 데이터 개수
    private int totalPageCount; // 전체 페이지 개수 
    private int firstPage; // 페이지 리스트의 첫 페이지 번호
    private int lastPage; // 페이지 리스트의 마지막 페이지 번호
    private boolean hasPreviousPage; // 이전 페이지 존재 여부
    private boolean hasNextPage; // 다음 페이지 존재 여부
    
    public BookListPageDTO(int currentPage, int cntPerpage, int pageSize, int totalRecordCount) {
        //강제입력방지
        if (currentPage < 1) {
            currentPage = 1;
        }

        this.currentPage = currentPage;
        this.cntPerPage = cntPerpage;
        this.pageSize = pageSize;        
        this.totalRecordCount = totalRecordCount;
        
        if (totalRecordCount > 0) {
            calculation();
        }
    }

    private void calculation() {
 
        // 전체 페이지 수 (현재 페이지 번호가 전체 페이지 수보다 크면 현재 페이지 번호에 전체 페이지 수를 저장)
        totalPageCount = ((totalRecordCount - 1) / this.getCntPerPage()) + 1;
        if (this.getCurrentPage() > totalPageCount) {
            this.setCurrentPage(totalPageCount);
        }
 
        // 페이지 리스트의 첫 페이지 번호
        firstPage = ((this.getCurrentPage() - 1) / this.getPageSize()) * this.getPageSize() + 1;
 
        // 페이지 리스트의 마지막 페이지 번호 (마지막 페이지가 전체 페이지 수보다 크면 마지막 페이지에 전체 페이지 수를 저장)
        lastPage = firstPage + this.getPageSize() - 1;
        if (lastPage > totalPageCount) {
            lastPage = totalPageCount;
        }

        // 이전 페이지 존재 여부
        hasPreviousPage = firstPage == 1 ? false : true;
        if(hasPreviousPage == false) {
            if(currentPage != firstPage) {
                hasPreviousPage = true;
            }else {
                hasPreviousPage = false;
            }
        }
 
        // 다음 페이지 존재 여부
        hasNextPage = (lastPage * this.getCntPerPage()) >= totalRecordCount ? false : true;
        if(hasNextPage == false) {
            //마지막 페이지에서 현재페이지가 마지막 페이지가 아닌경우 next처리
            if(currentPage != lastPage) {
                hasNextPage = true;
            }else {
                hasNextPage = false;
            }
        }
    }
}
