
function emailValidate(){

    var mail = document.getElementById("email");
    var pattern = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;

    if(!mail.value.match(pattern)){
        indicateError("email-error", "email", "Incorrect email");
    }
}

function passwordValidate(){
    var password = document.getElementById("password");
    if (password.value.length < 6){
        indicateError("password-error", "password", "Too short. Minimum length is 6")
    }
}

function indicateError(para1, para2, para3) {
        document.getElementById(para2).style.border = "1px solid #dd4b39";
        document.getElementById(para1).style.color = "#dd4b39";
        document.getElementById(para1).innerHTML = para3;
}
