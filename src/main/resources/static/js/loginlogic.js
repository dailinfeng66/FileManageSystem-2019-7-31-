// $(function () {
$('#login_button').click(function () {    //登录逻辑

    var userid = $('#u').val();
    var userpassord = $('#p').val();
    if (userid != null && userid != "") {
        if (userpassord != null && userpassord != "") {
            judgecheckcode(userid, userpassord);
        }
    } else {//账号或密码为空
        alert1("账号密码不能为空!")
    }
});


$('#reg').click(function () {   //注册逻辑

    var userid = $.trim($('#user').val());
    var correctpass = true;
    var pass = $.trim($('#passwd').val());
    var email = $.trim($('#email').val());

    var myreg = /^([\.a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(\.[a-zA-Z0-9_-])+/;
    if (!myreg.test(email)) {
        alert1("请输入正确邮箱地址");
        correctpass = false;
    }
    if ($('#passwd').val().length < pwdmin) {
        $('#passwd').focus();
        $('#userCue').html("<font color='red'><b>×密码不能小于" + pwdmin + "位</b></font>");
        correctpass = false;
    }
    if ($('#passwd2').val() != $('#passwd').val()) {
        $('#passwd2').focus();
        $('#userCue').html("<font color='red'><b>×两次密码不一致！</b></font>");
        correctpass = false;
    }

    if (correctpass) {   //注册
        /**
         * websocket链接服务器
         */


        register(userid, pass, email);
    }
});


$('#user').blur(function () {   //注册的时候判断用户名是否已经存在

    var userid = $('#user').val();

    if (userid != null && userid != "") {

        $.ajax({
            url: 'judgeuserid',
            type: 'POST',
            dataType: "JSON",

            success: function (data) {
                console.log(data);
                if (!data.result) {
                    $('#userCue').html("<font color='red'><b>×此账号已被人注册!</b></font>");
                    $('#user').focus().css({
                        border: "1px solid red",
                        boxShadow: "0 0 2px red"
                    });
                } else {
                    $('#userCue').html("<font color='green'><b>此账号可用!</b></font>");
                }

            },
            error: function (data) {
                console.log(data);
                alert1("系统发生错误!")
            },

            data: {
                userid: userid
            }
        });
    }

})

// });


function ajax(userid, password) {
    $.ajax({
        url: 'login',
        type: 'POST',
        dataType: "JSON",

        success: function (data) {
            console.log(data);
            if (data.result) {
                window.location.href = 'success';
                // alert1("登陆成功")
            } else {
                alert1("账号或密码错误!")
            }

        },
        error: function (data) {
            console.log(data);
            alert1("系统发生错误!");
        },

        data: {
            userid: userid,
            password: password
        }
    });
}

function register(userid, password, email) {
    $.ajax({
        url: 'register',
        type: 'POST',
        dataType: "JSON",

        success: function (data) {
            console.log(data);
            if (data.result) {
                alert1("已经向您的邮箱发送激活链接,请前往邮箱激活");
                openWebsocket(userid);
                // window.location.href = '/';
            } else {
                // alert("系统错误注册失败!")
            }


        },
        error: function (data) {
            console.log(data);
            alert1("系统发生错误!")
        },

        data: {
            userid: userid,
            password: password,
            email: email
        }
    });
}


function alert1(data) {
    $.messager.alert('登录', data);
}


$(document).ready(function () {
    $('#checkcode').click(function () {

        $.ajax({
            url: 'getCheckCode',
            type: 'get',
            success: function (data, status, xhr) {
                $('#checkcode').attr('src', 'getCheckCode');
            },
        });
    });
});


function judgecheckcode(userid, userpassord) {
    var checkcode = document.getElementById("yzm").value;

    $.ajax({
        url: 'justCheckCode',
        type: 'POST',
        dataType: "JSON",

        success: function (data) {
            console.log(data);
            if (data == true || data == "true") {
                ajax(userid, userpassord);  //验证登录
            } else {
                alert1("验证码错误!");
                // $('#yzm').attr("value","");
                $.ajax({
                    url: 'getCheckCode',
                    type: 'get',
                    success: function (data, status, xhr) {
                        $('#checkcode').attr('src', 'getCheckCode');
                    },
                });

            }


        },
        error: function (data) {
            console.log(data);
            alert1("系统发生错误!")
        },

        data: {
            checkcode: checkcode
        }
    });


}


function openWebsocket(userid) {     //websocket  注册按钮点击之后链接上服务器

    var websocket = null;

    //判断当前浏览器是否支持WebSocket
    if ('WebSocket' in window) {

        websocket = new WebSocket("ws://localhost:9696/websocket/" + userid);

        // alert("当前浏览器支持");
    } else {
        alert('当前浏览器 Not support websocket');
    }
    //连接发生错误的回调方法
    websocket.onerror = function () {
        // alert("发生错误");
        console.log("链接发生错误")
        // setMessageInnerHTML("WebSocket连接发生错误");
    };
    //连接成功建立的回调方法
    websocket.onopen = function () {
        console.log("链接成功")
        // var msg = "{\"To\": \"tom\"}"
        // websocket.send(msg)
        // setMessageInnerHTML("WebSocket连接成功");

    };
    //接收到消息的回调方法
    websocket.onmessage = function (event) {
        console.log("激活邮件时候收到的反馈:" + event.data);
        if (event.data == "activatesuccess") {
            alert1("账号激活成功!");


            closeWebSocket();


        } else if (event.data == "false") {
            alert1("激活失败!")
        }
    };
    //连接关闭的回调方法
    websocket.onclose = function () {
        console.log("链接关闭")
        // setMessageInnerHTML("WebSocket连接关闭");
    };
    //监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
    window.onbeforeunload = function () {
        closeWebSocket();
    };

    //关闭WebSocket连接
    function closeWebSocket() {
        websocket.close();
        window.location.href = '/';
    }
}

var count = 0;

$('#phonebtn').click(function () {

    var userid = $('#phonenumber').val().trim();
    if (userid != null && userid != "") {
        count++;
        if (count == 1) {


            phonelogin(userid);
        }
        $(this).addClass("on");
        var time = 60;
        $(this).attr("disabled", true);
        var timer = setInterval(function () {
            if (time == 0) {
                clearInterval(timer);
                $('#phonebtn').attr("disabled", false);
                $('#phonebtn').val("获取邮箱登录密匙");
                $('#phonebtn').removeClass("on");
            } else {
                $('#phonebtn').val(time + "秒");
                time--;
            }
        }, 1000);


    } else {
        alert1("电话号码不能为空");
    }

});

function phonelogin(userid) {
    $.ajax({
        url: 'Sendsms',
        type: 'POST',
        success: function (data) {
            console.log(data);

            linkBysocket(userid);

            alert1(data);
        },
        error: function (data) {
            console.log(data);
            alert1("系统发生错误!")
        },

        data: {
            userid: userid
        }
    });
}


function linkBysocket(userid) {     //websocket  用于短信登录

    var websocket = null;

    //判断当前浏览器是否支持WebSocket
    if ('WebSocket' in window) {

        websocket = new WebSocket("ws://localhost:9696/websocket/" + userid);

        // alert("当前浏览器支持");
    } else {
        alert('当前浏览器 Not support websocket');
    }
    //连接发生错误的回调方法
    websocket.onerror = function () {
        // alert("发生错误");
        console.log("链接发生错误")
        // setMessageInnerHTML("WebSocket连接发生错误");
    };
    //连接成功建立的回调方法
    websocket.onopen = function () {
        console.log("链接成功")
        // var msg = "{\"To\": \"tom\"}"
        // websocket.send(msg)
        // setMessageInnerHTML("WebSocket连接成功");

    };
    //接收到消息的回调方法
    websocket.onmessage = function (event) {
        console.log(event.data);
        if (event.data == "loginsuccess") {


            closeWebSocket();


        } else if (event.data == "false") {
            alert1("登录失败!")
        }
    };
    //连接关闭的回调方法
    websocket.onclose = function () {
        console.log("链接关闭")
        // setMessageInnerHTML("WebSocket连接关闭");
    };
    //监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
    window.onbeforeunload = function () {
        closeWebSocket();
    };

    //关闭WebSocket连接
    function closeWebSocket() {
        websocket.close();
        window.location.href = 'success';
    }
}



