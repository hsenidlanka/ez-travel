
function emailValidate(){

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

function passwordValidate(){
    var password = document.getElementById("password");
    if (password.value.length < 6){
        indicateError("password-error", "password", "Too short. Minimum length is 6");
        return false;
    } else {
        clearErrors("password-error", "password", "");
        return true;
    }
}

function indicateError(para1, para2, para3) {
        document.getElementById(para2).style.border = "1px solid #dd4b39";
        document.getElementById(para1).style.color = "#dd4b39";
        document.getElementById(para1).innerHTML = para3;
}

/**
 * Remove the error message and warnings when a user correct the error
 *
 * @param para1 id of the div which use to show the error message
 * @param para2 id of the form feild which has the error
 * @param para3 empty string
 */
function clearErrors(para1, para2, para3) {
    document.getElementById(para2).style.border = "";
    document.getElementById(para1).style.color = "";
    document.getElementById(para1).innerHTML = "";
}

/**
 * Check all the validations before submit the form
 *
 * @returns {boolean}
 */
function loginFormValidate() {
    if (emailValidate() && passwordValidate()) {
        return true;
    } else {
        document.getElementById("login-alert").innerHTML = "Please complete all fields!";
        return false;
    }
}