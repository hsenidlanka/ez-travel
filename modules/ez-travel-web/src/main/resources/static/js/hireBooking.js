$(document).ready(function () {
    $("#hirePlaced-dialog").dialog({
        autoOpen: false,
        modal: true,
        title: "Successful",
        buttons: {
            Close: function () {
                $(this).dialog('close');
            }
        }
    });

    $("#booking_hire_btn").click(function (e) {
        $.ajax({
            type: "POST",
            url: "http://localhost:8085/ez-travel/placeHire",
            data: {
                "vehicleType": $("#vehicleType").val(),
                "pickupLat": $("#pickupLat").val(),
                "pickupLng": $("#pickupLng").val(),
                "date": $("#date").val(),
                "time": $("#time").val()
            },
            success: function (result) {
                $("#hirePlaced-dialog").dialog("open");
                $("#hirePlaced-dialog").html(result);
                console.log("success");
            },
            error: function (result) {
                console.log(result);
            }
        });
    });
});

