//检查昵称函数
function checkNM() {
    var nickname = $.trim($("#register-nickname").val());
    if (nickname == "") {
        document.getElementsByClassName("register-nicknameSpan")[0].innerHTML = "输入为空!";
        return false;
        //约束昵称不能为空
    } else {
        document.getElementsByClassName("register-nicknameSpan")[0].innerHTML = "";
        return true;
    }
}

//检查手机号码函数
function checkTel() {
    var telephone = $.trim($("#register-tel").val());
    var reg = /^[0-9]*$/;
    if (telephone.length != 11) {
        document.getElementsByClassName("register-telSpan")[0].innerHTML = "请输入正确的格式!";
        return false;
    } else if (!reg.test(telephone)) {
        document.getElementsByClassName("register-telSpan")[0].innerHTML = "包含非法字符!";
        return false;
    } else {
        document.getElementsByClassName("register-telSpan")[0].innerHTML = "";
        return true;
    }
    //约束电话号码（账号）得长度不能不是11位,且为纯数字
}

//检查验证码格式函数
function checkVerification() {
    var verificationCode = $.trim($("#register-verification").val());
    if (verificationCode == '') {
        document.getElementsByClassName("register-verificationSpan")[0].innerHTML = "输入为空！";
        return false;
    } else {
        document.getElementsByClassName("register-verificationSpan")[0].innerHTML = "";
        return true;
    }
    //此函数仅约束验证码不为空
    //input中约束验证码的长度
}

//检查密码格式函数
function checkPwd() {
    var password = $.trim($("#register-pwd").val());
    var reg = /^([a-zA-Z0-9]){9,20}$/i;
    //正则表达式检查密码9-20位，包含任意的字母、数字，不可使用符号
    if (!reg.test(password)) {
        document.getElementsByClassName("register-pwdSpan")[0].innerHTML = "请输入正确的密码格式";
        return false;
        //约束密码长度为9-20位且不能为空、不包含符号
    } else {
        document.getElementsByClassName("register-pwdSpan")[0].innerHTML = "";
        return true;
    }
}

//检查再次确认密码函数
function checkConfirmPwd() {
    var confirmPassword = $.trim($("#register-confirmPwd").val());
    var password = $.trim($("#register-pwd").val());
    var reg = /^([a-zA-Z0-9]){9,20}$/i;
    if (confirmPassword != password) {
        document.getElementsByClassName("register-confirmPwdSpan")[0].innerHTML = "两次密码不一致!";
        return false;
    } else if (!reg.test(confirmPassword)) {
        document.getElementsByClassName("register-confirmPwdSpan")[0].innerHTML = "请输入正确的密码格式!";
        return false;
    } else {
        document.getElementsByClassName("register-confirmPwdSpan")[0].innerHTML = "";
        return true;
    }
    //约束两次输入得密码要一致，且格式整正确。
}

//获得验证码函数
function getSecurityCode(obj) {
    var tel = checkTel();
    var getCode = true;
    if (tel) {
        var telephone = $.trim($("#register-tel").val());
        var data = { "telephone": telephone, "getCode": getCode };
        var countdown = 60;
        $.ajax({
            url: protocol + domainName + prot + userModule+"/userInfo/getCode",
            type: "POST",
            data: JSON.stringify(data),
            dataType: "json",
            async: true,
            contentType: "application/json",
            success: function (result) {
                if (result.isSuccess) {
                    if (result.code == 222) {
                        function setTime(obj) {
                            if (countdown == 0) {
                                obj.removeAttribute("disabled");
                                obj.value = "发送验证码";
                                countdown = 60;
                                return;
                            } else {
                                obj.setAttribute("disabled", true);
                                obj.value = "重新发送(" + countdown + "s)";
                                countdown--;
                            }
                            setTimeout(() => { setTime(obj) }, 1000);
                        }
                        setTime(obj);
                    }else{
                        alert("验证码获取失败");
                    }
                } else {
                    alert("服务器开小差了...");
                }
            }
        })
    }
}

//提交表单约束及传值函数
function submitForm() {
    var nm = checkNM();
    var tel = checkTel();
    var pwd = checkPwd();
    var pwd2 = checkConfirmPwd();
    var vc = checkVerification();

    var nickname = $.trim($("#register-nickname").val());
    var telephone = $.trim($("#register-tel").val());
    var password = $.trim($("#register-confirmPwd").val());
    var sex = $.trim($("input[name='sex']:checked").val());
    var data = { "nickname": nickname, "telephone": telephone, "passowrd": password, "sex": sex };
    if (nm && tel && pwd && pwd2 && vc) {
        $.ajax({
            url: protocol + domainName + prot + userModule+"/userInfo/addUser",
            data: JSON.stringify(data),
            dataType: "json",
            async: false,
            type: "POST",
            contentType: "application/json",
            success: function (result) {
                if (result.isSuccess) {
                    const code = result.code;
                    if (code == 200) {
                        alert("用户已注册");
                    } else if (code == 400) {
                        alert("注册失败,验证码失效");
                    } else if (code == 500) {
                        alert("注册失败,验证码错误");
                    } else if (code == 300) {
                        alert("提供的号码有误");
                    } else if (code == 222) {
                        alert("注册成功");
                        window.location.href = "index.jsp";
                    } else {
                        alert("注册失败");
                    }
                } else {
                    alert("服务器开小差了...");
                }
            }
        });
    }
}
