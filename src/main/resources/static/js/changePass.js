$('#userid').blur(function () {
    var userid = $('#userid').val();
    if (userid != null && userid != "") {
        judgeUserid();
    }
});


$('#ok').click(function () {
    var oldpass = $('#oldpass').val();
    var newpass1 = $('#newpass1').val();
    var newpass2 = $('#newpass2').val();
    var userid = $('#userid').val();

    var correctpass = true;
    var canChange = false;

    if ($('#newpass1').val().length < 6) {
        $('#newpass1').focus();
         alert1("密码不能小于六位");
        correctpass = false;
    }
    if ($('#newpass1').val() != $('#newpass2').val()) {
        $('#newpass1').focus();
        alert1("两次密码输入不一致");
        correctpass = false;
    }
    if (correctpass){
        $.ajax({
            url: 'login',
            type: 'POST',
            dataType : "JSON",

            success: function (data) {
                console.log(data);
                if(data.result){
                    changepassword(userid,newpass1);
                }
                else{
                    alert1("原密码错误!不能修改密码")
                }

            },
            error: function (data) {
                console.log(data);
                alert1("系统发生错误!");
            },

            data: {
                userid: userid,
                password: oldpass
            }
        });
    }

});

function alert1(mess) {
    $.messager.alert('修改密码', mess);
}

function judgeUserid() {
    $.ajax({
        url: 'judgeuserid',
        type: 'POST',
        dataType: "JSON",

        success: function (data) {
            console.log(data);
            if (!data.result) {
                alert1("无此账号");
            } else {

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


function changepassword(userid,password) {
    $.ajax({
        url: 'changepass',
        type: 'POST',
        dataType: "JSON",

        success: function (data) {
            console.log(data);
            changeSuccess();

        },
        error: function (data) {
            console.log(data);
            alert1("系统发生错误!")
        },

        data: {
            userid: userid,
            password: password
        }
    });
}


function changeSuccess() {
    $.messager.confirm('修改密码', '修改密码成功!', function(r){
        if (r){
            window.location.href = 'index';
        }
    });
}
