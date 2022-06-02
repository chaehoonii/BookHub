initSlider();

function initSlider(){
	const paginationContainer=document.getElementById('pagination');
/*	const sliderContainer=document.getElementById('slider');
	const paginationContainer=document.createElement('div');

	//페이지네이션 생성
	paginationContainer.className='pagination';
	sliderContainer.prepend(paginationContainer);

	//슬라이드 갯 수 파악
	const slideCount = document.querySelectorAll('.slide').length;
	for(let i = slideCount;i>0;i--){
		let radioBtn = document.createElement('input');
		radioBtn.type='radio';
		radioBtn.name='slide-radios';
		radioBtn.className='slide-radio';
		radioBtn.id='slide-radio-${i}'

		if(i==1) radioBtn.checked=true;
		sliderContainer.prepend(radioBtn);
	
		//label 생성
		let label = document.createElement('label');
		//<label for="slide-radio-1">1</label>
		label.setAttribute('for', 'slide-radio-${i}');
		//label.innerHTML=i;
		paginationContainer.prepend(label);
	}*/
	//자동 슬라이드
	let autoRun=setInterval(changeSlide, 8000);

	function changeSlide(){
		const radioButtons = [...document.querySelectorAll('.slide-radio')];
		const currentIndex = radioButtons.findIndex(rb => rb.checked)
		// cI1 % 3 = 1, cI2 % 3 =2, cI3 % 3 = 0
		//console.log(currentIndex);
		radioButtons[(currentIndex+1) % radioButtons.length].checked = true;
	}
	
	//function(){a} -> ()=>a
	paginationContainer.addEventListener('mouseenter',()=> clearInterval(autoRun));
	paginationContainer.addEventListener('mouseleave',()=> autoRun=setInterval(changeSlide, 8000));
}

