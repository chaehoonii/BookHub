$(document).ready(function () {
	
	//url 전송 변수
	let mall = $('#search_target option:selected').text();
	let searchTarget = $('#search_target').val();
	let categoryId = $('#category_id').val();
	let queryType = $('#query_type').val();
	let searchWord = $('#booklist_search_word').val();
	
	
	//카테고리 선택 태그
	//$(document).on("change", "#search_target", function() {
	$('#search_target').on('change', function (){
		
		mall = $('#search_target option:selected').text();
		searchTarget = $(this).val();
		
		queryType = $('#query_type').val();
		searchWord = $('#booklist_search_word').val();
		
		location.href = '/booksearch?mall=' + mall + '&searchWord=' + searchWord
		 + '&queryType=' + queryType + '&SearchTarget=' + searchTarget;
	});

	$('#category_id').on('change', function (){
		//$(document).on("change", "#category_id", function() {

		categoryId = $(this).val();
		
		queryType = $('#query_type').val();
		searchWord = $('#booklist_search_word').val();
		
		location.href = '/booksearch?mall=' + mall + '&searchWord=' + searchWord
		 + '&queryType=' + queryType + '&SearchTarget=' + searchTarget + '&CategoryId=' + categoryId;
		
	});
	
	//책 검색하기
	$('#booklist_search_btn').on('click', function(){

		queryType = $('#query_type').val();
		searchWord = $('#booklist_search_word').val();
		
		location.href = '/booksearch?mall=' + mall + '&searchWord=' + searchWord
		 + '&queryType=' + queryType + '&SearchTarget=' + searchTarget + '&CategoryId=' + categoryId;
	});
	
	//페이지 클릭 시
	$('.direction_first').on('click', function(){
		location.href = '/booksearch?mall=' + mall + '&searchWord=' + searchWord
		 + '&queryType=' + queryType + '&SearchTarget=' + searchTarget + '&start=1&CategoryId=' + categoryId;		
	});
	$('.direction_prev, .direction_num, .direction_next, .direction_last').on('click', function(){
		let page = $(this).attr("id");
		location.href = '/booksearch?mall=' + mall + '&searchWord=' + searchWord
		 + '&queryType=' + queryType + '&SearchTarget=' + searchTarget + '&start=' + page + '&CategoryId=' + categoryId;	
	});
	
});//ready end


