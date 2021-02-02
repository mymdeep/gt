var username = null, password = null, data = null;
var usr_is_err = true, pwd_is_err = true;

function checkAll() {
    if (!usr_is_err && !pwd_is_err) {	//账号密码都正确时，启用提交
        document.getElementById("p-submit").style.pointerEvents = "auto";
        document.getElementById("button-submit").style.background = "rgb(60, 113, 255)";
    } else {	//否则，禁用提交
        document.getElementById("p-submit").style.pointerEvents = "none";
        document.getElementById("button-submit").style.background = "rgb(189, 206, 252)";
    }
}

function check(obj) {
    var val = obj.valueOf().value;
    var id = obj.id;
    if (val !== "" && val !== undefined && val != null) {
        if (id === "username") {
            //一旦账号修改，自动清除密码
            document.getElementById("password").value = "";
            document.getElementById("err-password").style.visibility = "hidden";
            pwd_is_err = true;
            username = val;
            data = {username: username};
            //ajax去服务器端校验
            $.ajax({
                type: "POST",
                url: "hasaccount",
                contentType: "application/json; charset=utf-8",
                dataType: 'json',
                data: JSON.stringify(data),
                async: false,
                success: function (msg) {
                    if (msg === true) {
                        //账号存在
                        document.getElementById("err-username").style.visibility = "hidden";
                        usr_is_err = false;
                    } else if (msg === false) {
                        //账号不存在
                        document.getElementById("err-username").style.visibility = "visible";
                        usr_is_err = true;
                    }
                }
            });
        } else if (id === "password") {
            password = val;
            if (username == null) {     //刚打开页面的时候，如果只输入密码，会走这条分支
                document.getElementById("err-username").style.visibility = "visible";
            } else if (!usr_is_err) {    //只有账号正确才会走以下分支
                data = {username: username, password: password};
                $.ajax({
                    type: "POST",
                    url: "checkaccount",
                    contentType: "application/json; charset=utf-8",
                    dataType: 'json',
                    data: JSON.stringify(data),
                    async: false,
                    success: function (msg) {
                        if (msg === 2) {	//密码错误
                            document.getElementById("err-password").style.visibility = "visible";
                            pwd_is_err = true;
                        } else if (msg === 0) {	 //账号密码正确，但账号被禁用
                            //禁用提交
                            document.getElementById("err-password").style.visibility = "hidden";
                            document.getElementById("p-submit").style.pointerEvents = "none";
                            document.getElementById("button-submit").style.background = "rgb(189, 206, 252)";
                            alert("账号被禁用，请联系管理员!");
                            document.getElementById("password").value = "";
                            document.getElementById("err-password").style.visibility = "hidden";
                            pwd_is_err = true;
                        } else if (msg === 1) {	//账号密码正确
                            document.getElementById("err-password").style.visibility = "hidden";
                            usr_is_err = false;
                            pwd_is_err = false;
                        }
                    }
                });
            }
        }
    } else {
        if (id === "username") {
            username = null;
            usr_is_err = true;
            document.getElementById("err-username").style.visibility = "hidden";
        } else if (id === "password") {
            password = null;
            pwd_is_err = true;
            document.getElementById("err-password").style.visibility = "hidden";
        }
    }
    checkAll();
}

function login() {
    if (!usr_is_err && !pwd_is_err) {
        data = {username: username, password: password};
        $.ajax({
            type: "POST",
            url: "login",
            contentType: "application/json; charset=utf-8",
            dataType: 'json',
            data: JSON.stringify(data),
            async: false,
            success: function (msg) {   //账号密码正确，并且账号状态启用
                if (msg === 1) {
                    window.location.href = "index.html";
                } else if (msg === 0) {   //账号密码正确，但是账号状态禁用
                    alert("账号被禁用，请联系管理员!");
                    document.getElementById("password").value = "";
                    document.getElementById("err-password").style.visibility = "hidden";
                    pwd_is_err = true;
                    checkAll();
                }
            }
        });
    } else {
        alert("请检查账号密码");
    }
}

$(document).ready(function () {
    /*登录按钮动态更改样式 begin*/
    $(".button-submit").hover(function () {
        if (!usr_is_err && !pwd_is_err) {
            $(".button-submit").css("background", "rgb(72,85,255)");
        }
    }, function () {
        $(".button-submit").css("background", "rgb(60, 113, 255)");
    });
    /*登录按钮动态更改样式 end*/
});

/*光标处于输入密码框，按回车登录 begin*/
$("#password").bind("keyup", function (e) {
    //event.preventDefault() 方法阻止元素发生默认的行为。
    if (e.key === "Enter") {
        e.preventDefault();
        //按下回车后需要执行的函数，可以直接调用login函数
        login();
    }
});
/*光标处于输入密码框，按回车登录 end*/