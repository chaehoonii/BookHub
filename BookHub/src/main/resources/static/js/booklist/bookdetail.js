$(document).ready(function () {
	//카테고리 선택 태그
	//$(document).on("change", "#search_target", function() {
	$('#search_target').on('change', function (){
		
		const mall = $('#search_target option:selected').text();
		const searchTarget = $(this).val();
		
		location.href = '/booklist?mall=' + mall + '&SearchTarget=' + searchTarget;
		
	});
	$('#category_id').on('change', function (){
		//$(document).on("change", "#category_id", function() {

		const mall = $('#search_target option:selected').text();
		const searchTarget = $('#search_target').val();
		const categoryId = $(this).val();
		
		location.href = '/booklist?mall=' + mall + '&SearchTarget=' + searchTarget + '&CategoryId=' + categoryId;
		
	});
	
	//책 검색하기
	$('#booklist_search_btn').on('click', function(){
		const mall = $('#search_target option:selected').text();
		const searchTarget = $('#search_target').val();
		const categoryId = $('#category_id').val();
		const queryType = $('#query_type').val();
		const searchWord = $('#booklist_search_word').val();
		
		console.log('queryType == ' + queryType + '///searchword===' + searchWord);
		//String searchWord, String queryType, String SearchTarget, String start, String CategoryId
		location.href = '/booksearch?mall=' + mall + '&searchWord=' + searchWord
		 + '&queryType=' + queryType + '&SearchTarget=' + searchTarget + '&CategoryId=' + categoryId;		
	});
	
});//ready end


