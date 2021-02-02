var map = null;
$(document).ready(function () {
    $.ajax({
        type: "POST",
        url: "getloginuser",
        contentType: "application/json; charset=utf-8",
        dataType: 'json',
        data: null,
        async: false,
        success: function (msg) {
            map = msg;
            $('#login_username_top')[0].innerHTML = map["login_username"];
            $('#login_username_left')[0].innerHTML = map["login_username"];
        }
    });
});

function logout() {
    $.ajax({
        type: "POST",
        url: "logout",
        contentType: "application/json; charset=utf-8",
        dataType: 'json',
        data: null,
        async: false,
        success: function (msg) {
            if (msg === 0) {
                window.location.href = "login.html";
            } else {
                alert("退出时发生了错误，请查看后台");
            }
        }
    });
}