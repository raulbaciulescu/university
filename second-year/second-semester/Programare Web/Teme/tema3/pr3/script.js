const submitBtn = document.getElementById("button");


let selected1 = -1;
let selected2 = -1;
let n = 0;
submitBtn.onclick = () => {
    document.getElementById("text").innerHTML = ""
    const rowInput = document.getElementById("rows");
    const colInput = document.getElementById("cols");
    if (!validateInput(rowInput, colInput)) {
        document.getElementById("text").innerHTML = "Invalid data!"
        return;
    }
    document.body.innerHTML = "";
    let table = document.createElement("table");
    let tbody = document.createElement("tbody");
    table.appendChild(tbody);

    document.getElementById("body").appendChild(table);
    let array = [];
    n = colInput.value * rowInput.value / 2;
    for (let i = 0; i < rowInput.value; i++) {
        let row = document.createElement("tr");
        for (let j = 0; j < colInput.value; j++) {
            let elem = document.createElement("td");
            let number = getNumber(array, n);
            array.push(number);
            elem.innerHTML = number;
            elem.addEventListener("click", onCellClicked)
            elem.classList.add("hidden");
            row.appendChild(elem);
        }
        tbody.appendChild(row);
    }


}
let cell = null;
let nr = 0;
function onCellClicked() {
    if (selected1 === -1) {
        selected1 = this.innerHTML;
        cell = this;
        this.classList.remove("hidden");
        this.classList.add("visible");
    }
    else if (selected2 === -1) {
        selected2 = this.innerHTML;
        this.classList.remove("hidden");
        this.classList.add("visible");
        if (selected1 !== selected2) {
            setTimeout(() => {
                this.classList.remove("visible");
                this.classList.add("hidden");
                cell.classList.remove("visible");
                cell.classList.add("hidden");
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
    console.log(selected1 + " " + selected2)
    if (nr === n) {
        alert("You won!");
    }
}


function getNumber(array, n) {
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
function validateInput(rowInput, colInput) {
    if (!rowInput.value || !colInput)
        return false;
    return rowInput.value % 2 === 0 && colInput.value % 2 === 0;
}