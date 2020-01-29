function fieldClick(value) {
    const data = value.split(":");
    const x = data[0];
    const y = data[1];
    $.ajax({
        type: "GET",
        url: "/update",
        data: { xAxis: x, yAxis: y },
        success: function (res) {
            console.log(res);
        }
        , error: function (res) {
            console.log(res);
        }
    });
};

function checkMessage() {
    $.ajax({
        type: "method",
        url: "url",
        data: "data",
        success: function (res) {
            $("#message").html(res);
        }, error: function (res) {
            console.log(res);
        }
    });
};

let continuousTimer = setInterval(checkMessage, 1000);
