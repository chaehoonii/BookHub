$(document).ready(function () {
	$('#search_target').on('change', function (){
		console.log("search_target change === ")
		console.log("val()==" + $(this).val() + "text()===" + $('#search_target option:selected').text());
			
		const mall = $('#search_target option:selected').text();
		
		//카테고리초기화
		$('#category_id').html('<option value="0" selected>전체</option>');
	
		$.ajax({
			url: "/getcategory",
			data: { 'mall': mall },
			type: 'post',
			async: false,
			dataType: 'json',
			success: function (result) {
				for(var i in result){
					console.log(" i " + result[i].cid + "result[i]" + result[i].categoryName);
					$('#category_id').append('<option value="' + result[i].cid + '">' + result[i].categoryName +'</option>')
				}			
			}
		});	
		
		
	});
});//ready end

$(document).on("change", "#category_id", function() {
	console.log("category_td change === ")
	console.log($(this).val());
});

