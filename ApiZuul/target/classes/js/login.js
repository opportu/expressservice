function getRegisterhtml() {
    window.location.href = "register.html";
}

//密码登录切换至验证码登录函数
function loginChangeForMsg() {
    var msgForm = document.getElementById("login-Msg");
    var telForm = document.getElementById("login-Tel");
    var forTel = document.getElementsByClassName("login-forTel")[0];
    var forMsg = document.getElementsByClassName("login-forMsg")[0];
    msgForm.style.setProperty("display", "block", "important");
    telForm.style.setProperty("display", "none", "important");
    forTel.style.setProperty("display", "inline-block", "important");
    forMsg.style.setProperty("display", "none", "important");
    document.getElementById("login-securityCode").value = "";
}

//验证码登录切换至密码登录函数
function loginChangeForTel() {
    console.log("切换至账号登录");
    var msgForm = document.getElementById("login-Msg");
    var telForm = document.getElementById("login-Tel");
    var forTel = document.getElementsByClassName("login-forTel")[0];
    var forMsg = document.getElementsByClassName("login-forMsg")[0];
    msgForm.style.setProperty("display", "none", "important");
    telForm.style.setProperty("display", "block", "important");
    forTel.style.setProperty("display", "none", "important");
    forMsg.style.setProperty("display", "inline-block", "important");
    document.getElementById("login-pwdForTel").value = "";
}

//密码登录，约束账号函数
function checkTelForTel() {
    var Telephone = $.trim($("#login-telForTel").val());
    if (Telephone.length != 11) {
        document.getElementsByClassName("login-errorInfo")[0].innerHTML = "请输入正确的账号!";
        return false;
    } else if (Telephone == "") {
        document.getElementsByClassName("login-errorInfo")[0].innerHTML = "请输入账号！"
        return false;
    }
    else {
        document.getElementsByClassName("login-errorInfo")[0].innerHTML = "&nbsp"
        return true;
    }
    //密码登录，约束账号不为空，长度为11位，input约束最大长度为11位
}

//验证码登录，约束账号函数
function checkTelForMsg() {
    var Telephone = $.trim($("#login-telForMsg").val());
    if (Telephone.length != 11) {
        document.getElementsByClassName("login-errorInfo")[0].innerHTML = "请输入正确的账号!";
        return false;
    } else if (Telephone == "") {
        document.getElementsByClassName("login-errorInfo")[0].innerHTML = "请输入账号！"
        return false;
    }
    else {
        document.getElementsByClassName("login-errorInfo")[0].innerHTML = "&nbsp"
        return true;
    }
    //验证码登录，约束账号不为空，长度为11位，input约束最大长度为11位
}

//密码登录的约束密码函数
function checkPwd() {
    var Password = $.trim($("#login-pwdForTel").val());
    if (Password.length < 9) {
        document.getElementsByClassName("login-errorInfo")[0].innerHTML = "密码过短!";
        return false;
    } else {
        document.getElementsByClassName("login-errorInfo")[0].innerHTML = "&nbsp";
        return true;
    }
    //约束密码最小长度位9，input约束最大长度为20
}

//验证码登录的约束验证码的函数
function checkSecurityCode() {
    var Code = $.trim($("#login-securityCode").val());
    if (Code == "") {
        document.getElementsByClassName("login-errorInfo")[0].innerHTML = "验证码为空！";
        return false;
    } else {
        document.getElementsByClassName("login-errorInfo")[0].innerHTML = "&nbsp";
        return true;
    }
    //约束验证码不能为空，input约束最大长度为6
}

//获取验证码函数
function getSecurityCode(obj) {
    var telephone = $.trim($("#login-telForMsg").val());
    var getCode = true;
    console.log(telephone);
    console.log(getCode);
    var data = { "telephone": telephone, "getCode": getCode };
    countdown = 60;
    $.ajax({
        url: protocol + domainName + prot + userModule + "/userInfo/getCode",
        dataType: "json",
        type: "POST",
        async: true,
        data: JSON.stringify(data),
        contentType: "application/json",
        success: function (result) {
            if (result.isSuccess) {
                if (result.code == 222) {
                    function setTime(obj) {
                        if (countdown == 0) {
                            obj.removeAttribut("disabled");
                            obj.value = "获取验证码";
                            countdown == 60;
                            return;
                        } else {
                            obj.setAttribute("disabled", true);
                            obj.value = "重新获取(" + countdown + "s)";
                            countdown--;
                        }
                        setTimeout(() => { setTime(obj) }, 1000);
                    }
                    setTime(obj);
                } else {
                    alert("验证码获取失败");
                }
            } else {
                alert("服务器开小差了...");
            }
        }
    })

}

//密码登录，提交表单函数
function userLoginForTel() {
    var tel = checkTelForTel();
    var pwd = checkPwd();
    if (tel && pwd) {
        var telephone = $.trim($("#login-telForTel").val());
        var Password = $.trim($("#login-pwdForTel").val());
        var data = { "telephone": telephone, "Password": Password };
        $.ajax({
            url: protocol + domainName + prot + userModule + "/userInfo/loginUserWithPwd",
            type: "POST",
            dataType: "json",
            async: false,
            data: JSON.stringify(data),
            contentType: "application/json",
            success: function (result) {
                if (result.isSuccess) {
                    if (result.code == 200) {
                        alert("用户已登录");
                    } else if (result.code == 300) {
                        alert("账号或密码有误！");
                    } else if (result.code == 222) {
                        alert("登陆成功");
                        user.userID = result.data.id;
                        user.telephone = result.data.telephone;
                        user.nickname = result.data.nickname;
                        user.password = result.data.password;
                        window.location.href = "index.jsp";
                    } else if (result.code == 100) {
                        alert("用户未注册");
                        window.location.href = "register.jsp";
                    }
                    else {
                        alert("登录失败");
                    }
                } else {
                    alert("服务器开小差了...");
                }
            }
        });
    }
}

//验证码登录，提交表单函数
function userLoginForMsg() {
    var tel = checkTelForMsg();
    var code = checkSecurityCode();
    if (tel && code) {
        var telephone = $.trim($("#login-telForMsg").val());
        var code = $.trim($("#login-securityCode").val());
        var data = { "telephone": telephone, "code": code };
        $.ajax({
            url: protocol + domainName + prot + userModule + "/userInfo/loginUserWithCode",
            type: "POST",
            dataType: "json",
            async: false,
            data: JSON.stringify(data),
            contentType: "application/json",
            success: function (result) {
                if (result.isSuccess) {
                    if (result.code == 200) {
                        alert("用户已登录");
                    } else if (result.code == 300) {
                        alert("账号或密码有误！");
                    } else if (result.code == 222) {
                        alert("登陆成功");
                        user.userID = result.data.id;
                        user.telephone = result.data.telephone;
                        user.nickname = result.data.nickname;
                        user.password = result.data.password;
                        window.location.href = "index.jsp";
                    } else if (result.code == 100) {
                        alert("用户未注册");
                        window.location.href = "register.jsp";
                    }
                    else {
                        alert("登录失败");
                    }
                } else {
                    alert("请求失败");
                }
            }
        });
    }
}

//密码登录，取消登录函数
function concelLoginForTel() {
    window.location.href = "http:localhost:8080/index.jsp";
}

//验证码登录，取消登录函数
function concelLoginForMsg() {
    window.location.href = "http:localhost:8080/index.jsp";
}

//忘记密码函数
function loginForget() {
    window.location.href = "forgetPassword.html";
}