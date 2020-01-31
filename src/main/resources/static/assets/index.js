function fieldClick(value) {
    const data = value.split(":");
    const x = data[1];
    const y = data[0];
    $.ajax({
        type: "GET",
        url: "/update",
        data: { xAxis: x, yAxis: y },
        success: function (res) {
            $(".field").html(res);
        }
        , error: function (res) {
            console.log(res);
        }
    });
};

function checkMessage() {
    $.ajax({
        type: "GET",
        url: "/message",
        success: function (res) {
            $(".message").html(res);
        }, error: function (res) {
            console.log(res);
        }
    });
};

let continuousTimer = setInterval(checkMessage, 1000);
