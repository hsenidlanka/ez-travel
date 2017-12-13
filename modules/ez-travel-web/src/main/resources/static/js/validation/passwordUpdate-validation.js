/**
 * Check all the validations before submit the form
 *
 * @returns {boolean}
 */
function validatePasswordUpdateForm() {
    if (validateCurrentPassword() && validateNewPassword() && confirmPassword()) {
        return true;
    } else {
        document.getElementById("passwordUpdate-alert").innerHTML = "Please complete all fields!";
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

function validateCurrentPassword() {
    var currentPassword = document.getElementById("currentPassword");
    if (currentPassword.value.length < 6) {
        indicateError("currentPassword-error", "currentPassword", "Incorrect password");
        return false;
    } else {
        clearErrors("currentPassword-error", "currentPassword", "");
        return true;
    }
}

function validateNewPassword() {
    var newPassword = document.getElementById("newPassword");
    if (newPassword.value.length < 6) {
        indicateError("newPassword-error", "newPassword", "Too short. Minimum length is 6");
        return false;
    } else {
        clearErrors("newPassword-error", "newPassword", "");
        return true;
    }
}

function confirmPassword() {
    var newPassword = document.getElementById("newPassword");
    var confirmPassword = document.getElementById("confirmPassword");

    if (!(newPassword.value == confirmPassword.value) || confirmPassword.value == null || confirmPassword.value === '') {
        indicateError("confirmPassword-error", "confirmPassword", "These passwords don't match. Try again?");
        return false;
    } else {
        clearErrors("confirmPassword-error", "confirmPassword", "");
        return true;
    }
}