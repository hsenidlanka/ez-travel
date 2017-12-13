$("button").click(function (e) {
    e.preventDefault();
    $.ajax({
        type: "POST",
        url: "http:'8085/ez-travel/findCost",
        data: {
            "distence": "abc",
            "vehicleType": "cde"
        },
        success: function (result) {
            alert('ok');
        },
        error: function (result) {
            alert('error');
        }
    });
});