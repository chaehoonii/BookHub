	$('#search_btn').on('click', function(){

		searchWord = $('#main_search_word').val();
		
		location.href = '/booksearch?searchWord=' + searchWord;
	});
	
	function enterkey() {
	if (window.event.keyCode == 13) {
    	// 엔터키가 눌렸을 때
    	searchWord = $('#main_search_word').val();
		
		location.href = '/booksearch?searchWord=' + searchWord;
    }
}