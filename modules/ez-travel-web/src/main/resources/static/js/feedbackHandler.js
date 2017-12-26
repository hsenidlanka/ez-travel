$(document).ready(function () {
    $("#feedback_submit").click(function (e) {
        $.ajax({
            type: "POST",
            url: "http://localhost:8085/ez-travel/customer/feedback",
            data: {
                "feedbackDescription": $("#feedbackDescription").val(),
                "hire_id": $("#hire_id").val(),
                "driver_id": $("#driver_id").val()
            },
            success: function (result) {
                $("#feedback_success").html("Feedback adding " + result);
                $("#feedback_success").css("background-color", "red");
            },
            error: function (result) {
                $("#feedback_success").html("Feedback adding " + result);
                console.log(result);
            }
        });
    });
});