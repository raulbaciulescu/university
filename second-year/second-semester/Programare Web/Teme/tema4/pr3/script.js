let cell = null;
let nr = 0;
let selected1 = -1;
let selected2 = -1;
let n = 0;

function validateInput(rowInput, colInput) {
    if (!rowInput.value || !colInput)
        return false;
    return rowInput.value % 2 === 0 && colInput.value % 2 === 0;
}

function getRandomNumber(array, n) {
    let number = -1;
    while (number === -1) {
        number = Math.floor(Math.random() * n);
        let count = 0;
        for (let i = 0; i < array.length; i++) {
            if (array[i] === number)
                count++;
        }
        if (count >= 2)
            number = -1;
    }
    return number;
}

function createTable(rows, cols) {
    let array = [];
    n = cols * rows / 2;
    let table = $("<table></table>");
    for(let i = 0; i < rows; i++) {
        let tr = $("<tr></tr>");
        for(let j = 0; j < cols; j++) {
            let number = getRandomNumber(array, n);
            array.push(number);
            console.log(number)
            let td = $("<td></td>").text(number.toString());
            td.addClass("hidden")
            tr.append(td);
        }
        table.append(tr);
    }
    $("#div-table").append(table);
}
function memoryGame() {
    let td = $(this);
    if (selected1 === -1) {
        selected1 = td.text();
        cell = td;
        td.removeClass("hidden");
        td.addClass("visible");
    }
    else if (selected2 === -1) {
        selected2 = td.text();
        td.removeClass("hidden");
        td.addClass("visible");
        if (selected1 !== selected2) {
            setTimeout(() => {
                td.removeClass("visible");
                td.addClass("hidden");
                cell.removeClass("visible");
                cell.addClass("hidden");
                selected1 = -1;
                selected2 = -1;
            }, 1000);
        }
        else {
            nr++;
            selected1 = -1;
            selected2 = -1;
        }
    }
    if (nr === n) {
        alert("You won!");
    }
}
$("#button").click(() => {
    $("#text").innerHTML = ""
    if (!validateInput($("#rows")[0], $("#cols")[0])) {
        $("#text").html("Invalid numbers!");
        return;
    }
    createTable($("#rows").val(), $("#cols").val());
    $("#inputs").remove();
    $("table tr td").click(memoryGame);
})