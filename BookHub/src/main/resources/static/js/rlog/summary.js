calendarInitializer();

function calendarInitializer() {
    // 날짜 조회
    const endDateRight = new Date();
    const endDateCenter = new Date(
        endDateRight.getFullYear(),
        endDateRight.getMonth(),
        0
    );
    const endDateleft = new Date(
        endDateCenter.getFullYear(),
        endDateCenter.getMonth(),
        0
    );

    // 달력 DOM 조회
    const calendarLeft = document.querySelector("#graph-content-left");
    const calendarCenter = document.querySelector("#graph-content-center");
    const calendarRight = document.querySelector("#graph-content-right");

    makeCalendar(calendarLeft, endDateleft);
    makeCalendar(calendarCenter, endDateCenter);
    makeCalendar(calendarRight, endDateRight);

    function makeCalendar(calendar, endDate) {
        const calendarMain = calendar.querySelector(".graph-main");

        // 제목(연월) 설정
        calendar.querySelector(".graph-title").innerText =
            endDate.getFullYear() +
            "년 " +
            (endDate.getMonth() + 1) +
            "월";

        // 1일의 요일 지정
        calendarMain.innerHTML +=
            `<div class="graph-day" style="grid-column: ` +
            (new Date(
                endDate.getFullYear(),
                endDate.getMonth(),
                1
            ).getDay() +
                1) +
            `">1</div>`;

        // 마지막날까지의 항목(일) 생성
        for (let i = 2; i <= endDate.getDate(); i++) {
            calendarMain.innerHTML += `<div class="graph-day">` + i + `</div>`;
        }
    }
}
