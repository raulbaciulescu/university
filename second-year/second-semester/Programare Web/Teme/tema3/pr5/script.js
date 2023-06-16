

document.querySelectorAll(".carousel").forEach(carousel => {
    const items = carousel.querySelectorAll(".carousel__item");
    const buttonsHtml = Array.from(items, () => {
       return `<span class="carousel__button"></span>`;
    });
    carousel.insertAdjacentHTML("beforeend", `
    <div class="carousel__nav">${buttonsHtml.join("")}
    </div>
    `);
    let selectedIndex = 0;
    let length = items.length;
    const buttons = carousel.querySelectorAll(".carousel__button");
    buttons.forEach((button, i) => {
        button.addEventListener("click", () => {
            // unselect all the items
            items.forEach(item => item.classList.remove("carousel__item--selected"));
            buttons.forEach(button => button.classList.remove("carousel__button--selected"));

            items[i].classList.add("carousel__item--selected");
            buttons[i].classList.add("carousel__button--selected");
            selectedIndex = i;
        });
    });
    //select the first image
    items[0].classList.add("carousel__item--selected");
    buttons[0].classList.add("carousel__button--selected");
    let timer = setInterval(slide, 3000);

    function moveRight() {
        const rightBtn = document.getElementById("carousel__button__right");
        rightBtn.addEventListener("click", () => {
            clearInterval(timer);
            items.forEach(item => item.classList.remove("carousel__item--selected"));
            buttons.forEach(button => button.classList.remove("carousel__button--selected"));
            if (selectedIndex < length - 1) {
                items[selectedIndex + 1].classList.add("carousel__item--selected");
                buttons[selectedIndex + 1].classList.add("carousel__button--selected");
                selectedIndex++;
            }
            else {
                items[0].classList.add("carousel__item--selected");
                buttons[0].classList.add("carousel__button--selected");
                selectedIndex = 0;
            }
            timer = setInterval(slide, 3000);
        });
    }
    function slide() {
        items.forEach(item => item.classList.remove("carousel__item--selected"));
        buttons.forEach(button => button.classList.remove("carousel__button--selected"));
        if (selectedIndex < length - 1) {
            items[selectedIndex + 1].classList.add("carousel__item--selected");
            buttons[selectedIndex + 1].classList.add("carousel__button--selected");
            selectedIndex++;
        }
        else {
            items[0].classList.add("carousel__item--selected");
            buttons[0].classList.add("carousel__button--selected");
            selectedIndex = 0;
        }
        //setTimeout(slide, 3000);
    }
    function moveLeft() {
        const leftBtn = document.getElementById("carousel__button__left");
        leftBtn.addEventListener("click", () => {
            clearInterval(timer);
            items.forEach(item => item.classList.remove("carousel__item--selected"));
            buttons.forEach(button => button.classList.remove("carousel__button--selected"));
            if (selectedIndex > 0) {
                items[selectedIndex - 1].classList.add("carousel__item--selected");
                buttons[selectedIndex - 1].classList.add("carousel__button--selected");
                selectedIndex--;
            }
            else {
                items[length - 1].classList.add("carousel__item--selected");
                buttons[length - 1].classList.add("carousel__button--selected");
                selectedIndex = length - 1;
            }
            timer = setInterval(slide, 3000);
        })
    }
    moveRight();
    moveLeft();
});

