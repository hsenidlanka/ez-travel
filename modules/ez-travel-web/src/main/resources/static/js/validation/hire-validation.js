/**
 * Check all the validations before submit the form
 *
 * @returns {boolean}
 */
function validateForm() {
    if (pickupValidation() && dropValidation() && pickupDateValidation() && pickupTimeValidation() && vehicleTypeValidation()) {
        return true;
    } else {
        document.getElementById("form-errors").innerHTML = "Complete all the field correctly!";
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
    document.getElementById(para1).innerHTML = para3;
}


function pickupValidation() {
    var pickup = document.getElementById("pickup");

    if (!pickup.value) {
        indicateError("pickup-error", "pickup", "Select a correct location.");
        return false;
    } else {
        clearErrors("pickup-error", "pickup", "");
        return true;
    }
}

function dropValidation() {
    var drop = document.getElementById("drop");

    if (!drop.value) {
        indicateError("drop-error", "drop", "Select a correct location.");
        return false;
    } else {
        clearErrors("drop-error", "drop", "");
        return true;
    }
}

function pickupDateValidation() {
    var pickupDate = document.getElementById("pickupDate");
    var pattern = /^\d{1,2}\/\d{1,2}\/\d{4}$/;

    if (!pickupDate.value.match(pattern)) {
        indicateError("pickupDate-error", "pickupDate", "invalid date.");
        return false;

    } else {
        clearErrors("pickupDate-error", "pickupDate", "");
        return true;
    }
}

function pickupTimeValidation() {
    var pickupTime = document.getElementById("time");
    var pattern = /^\d{1,2}:\d{2}([ap]m)?$/;

    if (!pickupTime.value.match(pattern)) {
        indicateError("time-error", "time", "invalid time.");
        return false;
    } else {
        clearErrors("time-error", "time", "");
        return true;
    }
}

function vehicleTypeValidation() {
    var vehicleType = document.getElementById("vehicleType");

    if (!vehicleType.value) {
        indicateError("vehicleType-error", "vehicleType", "Please select a type.");
        return false;
    } else {
        clearErrors("vehicleType-error", "vehicleType", "");
        return true;
    }
}

