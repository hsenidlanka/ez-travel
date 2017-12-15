$(document).on("click", "#user", function () {
    $("#customerInfo").submit();
});

$(function () {
    $("#pickupDate").datepicker();
});

$("#pickupDate").datepicker({
    dateFormat: "dd-mm-yy"
});


