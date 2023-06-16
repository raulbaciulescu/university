    const submitBtn = document.getElementById("button");

submitBtn.onclick = () => {
    const nameInput = document.getElementById("name");
    const birthdayInput = document.getElementById("birthday");
    const ageInput = document.getElementById("age");
    const emailInput = document.getElementById("email");

    Array.from(document.querySelectorAll('input')).forEach((el) => el.classList.remove('error'));
    text = ""
    if (!validateName(nameInput)) {
        nameInput.classList.add('error');
        setTimeout(function() {
            nameInput.classList.remove('error');
        }, 4000);
        text = "Incorrect data";
    }

    if (!validateBirthday(birthdayInput)) {
        birthdayInput.classList.add('error');
        setTimeout(function() {
            birthdayInput.classList.remove('error');
        }, 4000);
        text = "Incorrect data";
    }

    if (!validateAge(ageInput)) {
        ageInput.classList.add('error');
        setTimeout(function() {
            ageInput.classList.remove('error');
        }, 4000);
        text = "Incorrect data";
    }
    if (!validateEmail(emailInput)) {
        emailInput.classList.add('error');
        setTimeout(function() {
            emailInput.classList.remove('error');
        }, 4000);
        text = "Incorrect data";
    }
    document.getElementById("text").innerHTML = text;
}

function validateName(name) {
    console.log(name.value.length)
    return name.value.length > 0;
}


function validateAge(age) {
    if (!age.value)
        return false;
    if (isNaN(age.value) || age.value < 1 || age.value > 100) {
        return false;
    }
    console.log(age.value)
    return age.value.length > 0;
}

function validateBirthday(birthdayInput) {
    console.log(birthdayInput.value)
    if (!birthdayInput.value)
        return false;
    return birthdayInput.value;
}

function validateEmail(emailInput) {
    if (!emailInput.value)
        return false;
    if (!(/^\w+([-]?\w+)*@\w+([-]?\w+)*(\.\w{2,3})+$/.test(emailInput.value)))
        return false;
    return true;
}