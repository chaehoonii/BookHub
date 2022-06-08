async function deleteReadingLog(bookISBN, num) {
    await fetch("/rlog/book/delete", {
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

async function deleteAllReadingLog(bookISBN) {
    await fetch("/rlog/book/alldelete", {
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
