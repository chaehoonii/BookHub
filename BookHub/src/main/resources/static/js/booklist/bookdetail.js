$(document).ready(function () {
	//내 책으로 등록하기 버튼 클릭
	$('#userbook_reg').on('click', function(){
		//console.log($('#bookisbn').val());
		location.href = '/rlog/edit?isbn=' + $('#bookisbn').val();
	});
	//비로그인 내 책으로 등록하기 버튼 클릭 시 로그인 페이지로 이동
	$('#userbook_none').on('click', function(){
		location.replace('/oauth2/authorization/google');
	});
	
	//리뷰 쓰기 클릭시 리뷰 작성 폼 띄우기
	$('#review_btn').on('click', function(){
		$('#reviewItem_reg_display').show();	
	});
	
	//리뷰작성 textarea 글자 수 제한
 	$('#review_txt').on('keyup', function() {
        $('#review_txt_count').html("("+$(this).val().length+" / 300)");
 
        if($(this).val().length > 300) {
            $(this).val($(this).val().substring(0, 300));
            $('#review_txt_count').html("(300 / 300)");
        }
    });
    
    //리뷰 등록하기
    $('#review_reg_btn').on('click', function(){
		const reviewtxt = $('#review_txt').val();
		const userId = $('#userId').val();
		const bookisbn = $('#bookisbn').val();
	/*	
		$.ajax({
			url: "/reviewinsert",
			data: { 'reviewContent': reviewtxt,
					'userId': userId,
					'bookIsbn' : bookisbn},
			type: 'post',
			dataType: 'json',
			success: function (result) {
				$('#reviewItem_reg_display').hide();	

				if(result == 0){
					alert("리뷰 등록 실패 다시 시도해주세요");
				}
				
				//textarea 초기화
				$('#review_txt').val("");
				$('#review_txt_count').html("(0 / 300)");
			}
		});	
		*/
	});
    
});//ready end


