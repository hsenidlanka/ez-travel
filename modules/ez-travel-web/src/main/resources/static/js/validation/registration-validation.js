/**
 * Created by hsenid on 18/10/17.
 */


function indicateError(para1, para2, para3) {
    document.getElementById(para2).style.border = "2px solid #f9b43f";
    document.getElementById(para1).style.color = "#f9b43f";
    document.getElementById(para1).innerHTML = para3;
}

function clearErrors(para1, para2, para3) {
    document.getElementById(para2).style.border = "";
    document.getElementById(para1).style.color = "";
    document.getElementById(para1).innerHTML = "";
}

function validateName() {
    var name = document.getElementById("fName");
    var regex = /^[a-zA-Z ]{2,30}$/;

    if (!name.value.match(regex)){
        indicateError("fName-error", "fName", "First name can not be empty.");

    } else {
        clearErrors("fName-error", "fName", "");
        return true;
    }
}

function validateLastName() {
    var name = document.getElementById("lName");
    var regex = /^[a-zA-Z ]{2,30}$/;

    if (!name.value.match(regex)){
        indicateError("lName-error", "lName", "Last name can not be empty.");
        return false;
    } else {
        clearErrors("lName-error", "lName", "");
        return true;
    }
}

function validatePNumber() {
    var pattern = /^07[125678][0-9]{7}$/;
    var mobileNumber = document.getElementById("pNumber");

    if (!mobileNumber.value.match(pattern)){
        indicateError("pNumber-error", "pNumber", "Invalid phone number.");
        return false;
    } else {
        clearErrors("pNumber-error", "pNumber", "");
        return true;
    }
}

function validateYear() {
    var year = document.getElementById("year");
    var date = new Date();
    var currentYear = date.getFullYear();

    if (!(year.value>currentYear-100 && year.value<currentYear)){
        indicateError("year-error", "year", "Invalid year.");
        return false;
    } else {
        clearErrors("year-error", "year", "");
        return true;
    }
}

function validateMonth() {
    var month = document.getElementById("month");

    if (month.value == null || month.value == '') {
        indicateError("month-error", "moth", "Select a month.")
        return true;
    } else {
        clearErrors("month-error", "moth", "");
        return false;
    }
}

function validateDay() {
    var day = document.getElementById("day");

    if (!(day.value>1 && day.value<31)){
        indicateError("day-error", "day", "Invalid day.");
        return false;
    } else {
        clearErrors("day-error", "day", "");
        return true;
    }
}

function validateNIC() {
    var oldIdPattern = /^[0-9]{9}[V]$/;
    var newIdPattern = /^[0-9]{12}$/;
    var nic = document.getElementById("nic");

    if ((!nic.value.match(oldIdPattern)) && (!nic.value.match(newIdPattern))){
        indicateError("nic-error", "nic", "Incorrect NIC.");
        return false;
    } else {
        clearErrors("nic-error", "nic", "");
        return true;
    }
}

function validateEmail(){

    var mail = document.getElementById("email");
    var pattern = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;

    if(!mail.value.match(pattern)){
        indicateError("email-error", "email", "Incorrect email");
        return false;
    } else {
        clearErrors("email-error", "email", "");
        return true;
    }
}

function validatePassword(){
    var password = document.getElementById("password");
    if (password.value.length < 6){
        indicateError("password-error", "password", "Too short. Minimum length is 6")
        return false;
    } else {
        clearErrors("password-error", "password", "");
        return true;
    }
}

function checkRePassword() {
    var password = document.getElementById("password");
    var rePaassword = document.getElementById("rePassword");

    if (!(password.value == rePaassword.value) || rePaassword.value == null || rePaassword.value ===''){
        indicateError("rePassword-error", "rePassword", "These passwords don't match. Try again?");
        return false;
    } else {
        clearErrors("rePassword-error", "rePassword", "");
        return true;
    }
}

function validateForm() {
    if (validateName() && validateLastName() && validatePNumber() && validateNIC() && validateYear()
        && validateMonth() && validateDay() && validateEmail() && validatePassword() && checkRePassword()){
        return true;
    } else {
        return false;
    }
}

