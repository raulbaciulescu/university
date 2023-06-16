function validateName(name) {
    return name.value.length > 0;
}


function validateAge(age) {
    if (!age.value)
        return false;
    if (isNaN(age.value) || age.value < 1 || age.value > 100) {
        return false;
    }
    return age.value.length > 0;
}

function validateBirthday(birthdayInput) {
    if (!birthdayInput.value)
        return false;
    return birthdayInput.value;
}

function validateEmail(emailInput) {
    if (!emailInput.value)
        return false;
    return /^\w+([-]?\w+)*@\w+([-]?\w+)*(\.\w{2,3})+$/.test(emailInput.value);
}

$("#button").click(() => {
    $("input").removeClass("error");
    let text = ""
    if (!validateName($("#name")[0])) {
        $("#name").addClass("error");
        setTimeout(function() {
            $("#name").removeClass("error");
        }, 4000);
        text = "Incorrect data";
    }

    if (!validateBirthday($("#birthday")[0])) {
        $("#birthday").addClass("error");
        setTimeout(function() {
            $("#birthday").removeClass("error");
        }, 4000);
        text = "Incorrect data";
    }

    if (!validateAge($("#age")[0])) {
        $("#age").addClass("error");
        setTimeout(function() {
            $("#age").removeClass("error");
        }, 4000);
        text = "Incorrect data";
    }

    if (!validateEmail($("#email")[0])) {
        $("#email").addClass("error");
        setTimeout(function() {
            $("#email").removeClass("error");
        }, 4000);
        text = "Incorrect data";
    }
    $("#text").html(text);
})