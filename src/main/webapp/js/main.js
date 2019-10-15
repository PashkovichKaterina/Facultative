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
if (document.getElementById("add-skill")!= null) {
    document.getElementById("add-skill").onclick = function () {
        var newSelect = document.createElement("select");
        newSelect.className = "selected-column";
        for (var i = 0; i < 5; i++) {
            var newOptional = document.createElement("option");
            newOptional.innerHTML = "навык";
            newSelect.appendChild(newOptional);
        }
        document.getElementById("skills-require").appendChild(newSelect);
        var newSelect = document.createElement("select")
        newSelect.className = "selected-column";
        for (var i = 0; i < 5; i++) {
            var newOptional = document.createElement("option");
            newOptional.innerHTML = "уровень";
            newSelect.appendChild(newOptional);
        }
        document.getElementById("skills-require").appendChild(newSelect);
    }
}
if (document.getElementById("add-timetable") != null) {
    document.getElementById("add-timetable").onclick = function () {
        var newSelect = document.createElement("select")
        newSelect.className = "selected-column";
        for (var i = 0; i < 5; i++) {
            var newOptional = document.createElement("option");
            newOptional.innerHTML = "день недели";
            newSelect.appendChild(newOptional);
        }
        document.getElementById("timetable").appendChild(newSelect);
        var newSelect = document.createElement("select")
        newSelect.className = "selected-column";
        for (var i = 0; i < 5; i++) {
            var newOptional = document.createElement("option");
            newOptional.innerHTML = "уровень";
            newSelect.appendChild(newOptional);
        }
        var newInput = document.createElement("input");
        newInput.className = "selected-column time";
        newInput.setAttribute("type", "text");
        document.getElementById("timetable").appendChild(newInput);
        $(".time").mask("99:99");
    }
}