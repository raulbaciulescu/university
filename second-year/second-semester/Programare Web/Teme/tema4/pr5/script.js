
let selectedIndex = 0;
let timer;
function initializeCarouselNav() {
    let items = $(".carousel__item");
    let carouselNav = $("<div></div>").addClass("carousel__nav");
    length = $(".carousel__item").length
    for (let i = 0; i < items.length; i++) {
        let carouselButton = $("<span></span>").addClass("carousel__button");
        carouselNav.append(carouselButton);
    }
    $("body").append(carouselNav);
}

function slide() {
    $(".carousel__item").removeClass("carousel__item--selected");
    $(".carousel__button").removeClass("carousel__button--selected");
    if (selectedIndex < length - 1) {
        $(".carousel__item").eq(selectedIndex + 1).addClass("carousel__item--selected");
        $(".carousel__button").eq(selectedIndex + 1).addClass("carousel__button--selected");
        selectedIndex++;
    }
    else {
        $(".carousel__item").eq(0).addClass("carousel__item--selected");
        $(".carousel__button").eq(0).addClass("carousel__button--selected");
        selectedIndex = 0;
    }
}

function onLeftButtonClick() {
    $(".carousel__item").removeClass("carousel__item--selected");
    $(".carousel__button").removeClass("carousel__button--selected");
    clearInterval(timer);
    if (selectedIndex > 0) {
        $(".carousel__item").eq(selectedIndex - 1).addClass("carousel__item--selected");
        $(".carousel__button").eq(selectedIndex - 1).addClass("carousel__button--selected");
        selectedIndex++;
    }
    else {
        $(".carousel__item").eq(length - 1).addClass("carousel__item--selected");
        $(".carousel__button").eq(length - 1).addClass("carousel__button--selected");
        selectedIndex = length - 1;
    }

    timer = setInterval(slide, 3000);
}

function onRightButtonClick() {
    $(".carousel__item").removeClass("carousel__item--selected");
    $(".carousel__button").removeClass("carousel__button--selected");
    clearInterval(timer);
    if (selectedIndex < length - 1) {
        $(".carousel__item").eq(selectedIndex + 1).addClass("carousel__item--selected");
        $(".carousel__button").eq(selectedIndex + 1).addClass("carousel__button--selected");
        selectedIndex++;
    }
    else {
        $(".carousel__item").eq(0).addClass("carousel__item--selected");
        $(".carousel__button").eq(0).addClass("carousel__button--selected");
        selectedIndex = 0;
    }

    timer = setInterval(slide, 3000);
}

$(function() {
    initializeCarouselNav();
    $(".carousel__button").click(function() {
        clearInterval(timer);
        const index = $(this).index();
        $(".carousel__item").removeClass("carousel__item--selected");
        $(".carousel__button").removeClass("carousel__button--selected");

        $(".carousel__item").eq(index).addClass("carousel__item--selected");
        $(".carousel__button").eq(index).addClass("carousel__button--selected");
        selectedIndex = index;
        timer = setInterval(slide, 3000);
    })
    $(".carousel__item").eq(0).addClass("carousel__item--selected");
    $(".carousel__button").eq(0).addClass("carousel__button--selected");
    timer = setInterval(slide, 3000);

    $("#carousel__button__left").click(onLeftButtonClick);
    $("#carousel__button__right").click(onRightButtonClick);
});
