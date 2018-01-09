$(document).ready(function () {
    $(function (e) {
        $("#feedback_status").html("");
        $("#feedback_status").hide();
    })

    $("#btnBanNow").click(function (e) {
        $.ajax({
            type: "POST",
            url: "http://localhost:8085/ez-travel/admin/banDriver",
            data: {
                "driver_email": $("#driver_email").val()
            },
            success: function (result) {
                $("#feedback_status").html(result);
                $("#feedback_status").show(0).delay(5000).hide(0);
                console.log(result);
            },
            error: function (result) {
                $("#feedback_status").html(result);
                $("#feedback_status").show(0).delay(5000).hide(0);
                console.log(result);
            }
        });
    });

    $("#btnReview").click(function (e) {
        $.ajax({
            type: "POST",
            url: "http://localhost:8085/ez-travel/admin/reviewCustomerFeedback",
            data: {
                "feedback_id": $("#feedback_id").val()
            },
            success: function (result) {
                $("#feedback_status").html(result);
                $("#feedback_status").show(0).delay(3000).hide(0);
                console.log(result);
            },
            error: function (result) {
                $("#feedback_status").html(result);
                $("#feedback_status").show(0).delay(3000).hide(0);
                console.log(result);
            }
        });
    });
});