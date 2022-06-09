evnentHandler();

function evnentHandler() {
    // 진행도(전체/읽고 있는 책/읽은 책) 조건부 필터 이벤트 등록
    document
        .querySelector("#searchmenu-readoption")
        .addEventListener("change", function () {
            document.querySelector("#searchmenu-searchform").submit();
        });
}
