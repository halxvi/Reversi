function fieldClick(value) {
    const data = value.split(":");
    const x = data[0];
    const y = data[1];
    $.ajax({
        type: "GET",
        url: "/update",
        data: { xAxis: x, yAxis: y },
        success: function (response) {
            console.log(response);
        }
        , error: function (res) {
            console.log(res);
        }
    });
};