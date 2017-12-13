/**
 * Check all the validations before submit the form
 *
 * @returns {boolean}
 */
function validateUserInfoForm() {
    if (validateFirstName() && validateLastName() && validatePNumber()) {
        document.getElementById("userInfoUpdate-alert").innerHTML = "Successfully updated!";
        return true;
    } else {
        document.getElementById("userInfoUpdate-alert").innerHTML = "Please complete all fields!";
        return false;
    }
}

/**
 * When a validation error caught this function will indicate the error on the jsp page
 *
 * @param para1 id of the div which use to show the error message
 * @param para2 id of the form feild which has the error
 * @param para3 error message
 */
function indicateError(para1, para2, para3) {
    document.getElementById(para2).style.border = "2px solid #f9b43f";
    document.getElementById(para1).style.color = "#f9b43f";
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

function validateFirstName() {
    var firstName = document.getElementById("firstName");
    var regex = /^[a-zA-Z ]{2,30}$/;

    if (!firstName.value.match(regex)) {
        indicateError("firstName-error", "firstName", "First name can not be empty.");
        return false;
    } else {
        clearErrors("firstName-error", "firstName", "");
        return true;
    }
}

function validateLastName() {
    var lastName = document.getElementById("lastName");
    var regex = /^[a-zA-Z ]{2,30}$/;

    if (!lastName.value.match(regex)) {
        indicateError("lastName-error", "lastName", "Last name can not be empty.");
        return false;
    } else {
        clearErrors("lastName-error", "lastName", "");
        return true;
    }
}

function validatePNumber() {
    var pattern = /^07[125678][0-9]{7}$/;
    var mobileNumber = document.getElementById("contactNumber");

    if (!mobileNumber.value.match(pattern)) {
        indicateError("contactNumber-error", "contactNumber", "Invalid phone number.");
        return false;
    } else {
        clearErrors("contactNumber-error", "contactNumber", "");
        return true;
    }
}