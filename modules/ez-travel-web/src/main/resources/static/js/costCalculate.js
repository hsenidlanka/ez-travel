$(document).ready(function () {
    $("#find_cost_btn").click(function (e) {
        $.ajax({
            type: "POST",
            url: "http://localhost:8085/ez-travel/hire/findCost",
            data: {
                "length": $("#length").val(),
                "vehicleType": $("#vehicleType").val()
            },
            success: function (result) {
                $("#cost").html("LKR " + result);
                $("#cost_result").show();
                $("#find_cost_btn").hide();
            },
            error: function (result) {
                console.log(result);
            }
        });
    });

    $("#sign_in_for_booking").click(function (e) {
        $.ajax({
            type: "post",
            url: "http://localhost:8085/ez-travel/login",
            data: {
                "length": $("#length").val(),
                "vehicleType": $("#vehicleType").val(),
                "pickup": $("#pickup").val(),
                "drop": $("#drop").valueOf()
            },
            success: function (result) {
                alert("logged in");
            },
            error: function (result) {
                alert("fail");
                console.log(result);
            }
        });
    });
});