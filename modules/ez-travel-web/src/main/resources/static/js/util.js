$(document).on("click", "#user", function () {
    $("#customerInfo").submit();
});

$(function () {
    $("#pickupDate").datepicker();
});

$("#pickupDate").datepicker({
    dateFormat: "dd-mm-yy"
});

var dateFormat = $("#pickupDate").datepicker("option", "dateFormat");

$("#pickupDate").datepicker("option", "dateFormat", "dd-mm-yy");

$(".selector").datepicker({
    minDate: new Date(2017, 1 - 1, 0)
});

var minDate = $(".selector").datepicker("option", "minDate");

$(".selector").datepicker("option", "minDate", new Date(2017, 1 - 1, 0));
