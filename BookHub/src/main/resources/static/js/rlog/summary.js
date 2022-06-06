calendarInitializer();

function calendarInitializer() {
    // 날짜 조회
    const endDateRight = new Date();
    const endDateCenter = new Date(endDateRight);
    endDateCenter.setDate(0);
    const endDateLeft = new Date(endDateCenter);
    endDateLeft.setDate(0);

    // 달력 DOM 조회
    const calendarLeft = document.querySelector("#graph-content-left");
    const calendarCenter = document.querySelector("#graph-content-center");
    const calendarRight = document.querySelector("#graph-content-right");

    makeCalendar(calendarLeft, endDateLeft);
    makeCalendar(calendarCenter, endDateCenter);
    makeCalendar(calendarRight, endDateRight);

    setCalendar();

    function makeCalendar(calendar, endDate) {
        const lastDay = endDate.getDate();
        const calendarMain = calendar.querySelector(".graph-main");

        // 제목(연월) 설정
        calendar.querySelector(".graph-title").innerText =
            endDate.getFullYear() + "년 " + (endDate.getMonth() + 1) + "월";

        // 1일의 요일 지정
        endDate.setDate(1);
        calendarMain.innerHTML +=
            `<div class="graph-day" style="grid-column: ` +
            (endDate.getDay() + 1) +
            `"><time datetime="` +
            endDate.toISOString().split("T")[0] +
            `">1</time></div>`;

        // 마지막날까지의 항목(일) 생성
        for (let i = 2; i <= lastDay; i++) {
            endDate.setDate(i);
            calendarMain.innerHTML +=
                `<div class="graph-day"><time datetime="` +
                endDate.toISOString().split("T")[0] +
                `">` +
                i +
                `</time></div>`;
        }
    }

    async function setCalendar() {
        const colorSet = ["#add3f7", "#7ea9d4", "#43689f", "#374760"];

        // 최근 활동 기록 조회
        const recentCalendar = await fetch("/rlog/recentcalendar")
            .then((response) => response.json())
            .then((data) => data.recentCalendar);

        // 활동 기록 반영
        for (let i = 0; i < recentCalendar.length; i++) {
            const calendarDay = document.querySelector(
                `time[datetime="` +
                    recentCalendar[i].readDate.split("T")[0] +
                    `"]`
            );

            if (recentCalendar[i].readPage <= 10) {
                calendarDay.parentElement.style.backgroundColor = colorSet[0];
            } else if (recentCalendar[i].readPage <= 20) {
                calendarDay.parentElement.style.backgroundColor = colorSet[1];
            } else if (recentCalendar[i].readPage <= 50) {
                calendarDay.parentElement.style.backgroundColor = colorSet[2];
            } else {
                calendarDay.parentElement.style.backgroundColor = colorSet[3];
            }
            calendarDay.style.color = "white";
        }
    }
}
