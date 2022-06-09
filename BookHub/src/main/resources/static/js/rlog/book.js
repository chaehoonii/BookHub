function deleteReadingLog(bookISBN, num) {
    swal({
        title: "독서기록 삭제",
        text: "마지막이 아닌 독서기록을 삭제하면\n다른 독서기록들의 페이지가 앞으로 당겨집니다.\n\n정말로 삭제하시겠습니까?",
        icon: "warning",
        buttons: ["취소", "예, 삭제하겠습니다."],
        dangerMode: true,
    }).then((willDelete) => {
        if (willDelete) {
            fetch("/rlog/book/delete", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify({ bookISBN: bookISBN, num: num }),
            }).then((response) => {
                if (response) {
                    location.reload();
                }
            });
        }
    });
}

function deleteAllReadingLog(bookISBN) {
    swal({
        title: "모든 독서기록 삭제",
        text: "이 책의 모든 독서기록을 삭제하고 내 서재에서 제외합니다.\n삭제한 데이터는 복구할 수 없습니다.\n\n정말로 삭제하시겠습니까?",
        icon: "warning",
        buttons: ["취소", "예, 삭제하겠습니다."],
        dangerMode: true,
    }).then((willDelete) => {
        if (willDelete) {
            fetch("/rlog/book/alldelete", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify({ bookISBN: bookISBN }),
            }).then((response) => {
                if (response) {
                    location.href = "/rlog/library";
                }
            });
        }
    });
}
