$(function () {
    $.get("header.html",function (data) {
        $("#header").html(data);
    });
    $.get("footer.html",function (data) {
        $("#footer").html(data);
    });

    $.get("adminheader.html",function (data) {
        $("#adminheader").html(data);
    });

    $.get("mangement.html",function (data) {
        $("#mangement").html(data);
    });


});