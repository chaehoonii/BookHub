	$('#search_btn').on('click', function(){

		
		searchWord = $('#main_search_word').val();
		
		location.href = '/booksearch?searchWord=' + searchWord;
	});