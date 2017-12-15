$(document).ready(function () {
    $("#find_cost_btn").click(function (e) {
        $.ajax({
            type: "POST",
            url: "http://localhost:8085/ez-travel/placeHire",
            data: {
                "vehicleType": $("#vehicleType").val(),
                "pickup-lat": $("#pickupLat").val(),
                "pickup-lng": $("#pickupLng").val(),
                "date": $("#pickupDate").val(),
                "time": $("#pickupTime").val()
            },
            success: function (result) {
                console.log("success");
            },
            error: function (result) {
                console.log(result);
            }
        });
    });
});