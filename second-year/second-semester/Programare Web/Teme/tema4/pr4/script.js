

$('th').click(function(){
    let table = $(this).parents('table').eq(0)
    let rows = table.find('tr:gt(0)').toArray().sort(comparer($(this).index()))
    this.asc = !this.asc
    if (!this.asc) {
        rows = rows.reverse()
    }
    for (let i = 0; i < rows.length; i++){
        table.append(rows[i])
    }
})
function comparer(index) {
    return function(a, b) {
        let valA = getCellValue(a, index);
        let valB = getCellValue(b, index);
        return $.isNumeric(valA) && $.isNumeric(valB) ? valA - valB : valA.toString().localeCompare(valB)
    }
}
function getCellValue(row, index) {
    return $(row).children('td').eq(index).text()
}

// invers
$('th').click(function(){
    let swapped = true;
    do {
        swapped = false;
        // console.log($(this).parent().children())
        for (let c = 2; c <= $(this).parent().children().length - 1; c++) {
            let cellCurrValue = $(this).parent().children("td:nth-child(" + c + ")").text();
            let cellNextValue = $(this).parent().children("td:nth-child(" + (c + 1) + ")").text();

            if ($.isNumeric(cellCurrValue))
                cellCurrValue = parseInt(cellCurrValue);

            if ($.isNumeric(cellNextValue))
                cellNextValue = parseInt(cellNextValue);

            if (!$(this).hasClass("sorted-asc") && cellCurrValue > cellNextValue) {
                for (let r = 1; r <= $(this).parent().parent().children().length; r++) {
                    let temp = $(this).parent().parent().children("tr:nth-child(" + r + ")").children("td:nth-child(" + c + ")").text();
                    $(this).parent().parent().children("tr:nth-child(" + r + ")").children("td:nth-child(" + c + ")").text(
                        $(this).parent().parent().children("tr:nth-child(" + r + ")").children("td:nth-child(" + (c + 1) + ")").text());
                    $(this).parent().parent().children("tr:nth-child(" + r + ")").children("td:nth-child(" + (c + 1) + ")").text(temp);
                }

                swapped = true;
            }
            else if (!$(this).hasClass("sorted-desc") && $(this).hasClass("sorted-asc") && cellCurrValue < cellNextValue) {
                for (let r = 1; r <= $(this).parent().parent().children().length; r++) {
                    let temp = $(this).parent().parent().children("tr:nth-child(" + r + ")").children("td:nth-child(" + (c + 1) + ")").text();
                    $(this).parent().parent().children("tr:nth-child(" + r + ")").children("td:nth-child(" + (c + 1) + ")").text(
                        $(this).parent().parent().children("tr:nth-child(" + r + ")").children("td:nth-child(" + c + ")").text());
                    $(this).parent().parent().children("tr:nth-child(" + r + ")").children("td:nth-child(" + c + ")").text(temp);
                }
                swapped = true;
            }
        }
    } while (swapped);

    if ($(this).hasClass("sorted-asc"))
        $(this).removeClass("sorted-asc").addClass("sorted-desc");
    else
        $(this).removeClass("sorted-desc").addClass("sorted-asc");
})

