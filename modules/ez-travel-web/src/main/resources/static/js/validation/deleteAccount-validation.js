function validatePasswordBeforeDelete() {
    var password = document.getElementById("currentPassword");
    if (password.value.length < 6) {
        document.getElementById("deleteAccount-error").innerHTML = "Invalid password!";
        return false;
    } else {
        return true;
    }
}