$(document).ready(function () {
    $('.header').height($(window).height());
    $('.error-block').height($(window).height());
    $("#phone").mask("+375(99)999-99-99");
    if ($(".time") != null) {
        $(".time").mask("99:99");
    }
});

$(window).scroll(function () {
    if ($("#navbar-main") != null) {
        var top = $(window).scrollTop(),
            opacity = top > 500 ? 1 : top * 2 / 1000;
        $("#navbar-main").css("background-color", "rgba(0,0,0," + opacity + ")");
    }
});
if (document.getElementById("add-skill") != null) {
    var i = 2;
    document.getElementById("add-skill").onclick = function () {
        var elem = $("#require-element").clone().appendTo(".skills-require");
        elem.children(":first").attr("name", "skill" + i);
        elem.children(":last").attr("name", "level" + i);
        ++i;
    }
}
if (document.getElementById("add-timetable") != null) {
    var i = 2;
    document.getElementById("add-timetable").onclick = function () {
        var i = 2;
        document.getElementById("add-timetable").onclick = function () {
            var elem = $("#timetable-element").clone().appendTo(".timetable");
            elem.children(":first").attr("name", "day" + i);
            elem.children(":last").attr("name", "time" + i);
            ++i;
            $(".time").mask("99:99");
        }
    }
}