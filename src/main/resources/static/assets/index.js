function click(x, y) {
    $.ajax({
        type: "GET",
        url: "/update",
        data: { xAxis: x, yAxis: y },
        success: function (response) {
            console.log(response);
        }
    });
};