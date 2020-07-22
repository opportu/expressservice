function checkNewPwd() {
    var pwd = $.trim($("#resetPwd-newPwd").val());
    var reg = /^([a-zA-Z0-9]){9,20}$/i;
    if (!reg.test(pwd)) {
        document.getElementsByClassName("resetPwd-errorInfo")[0].innerHTML = "密码格式不正确";
        return false;
    } else {
        document.getElementsByClassName("resetPwd-errorInfo")[0].innerHTML = "&nbsp";
        return true;
    }
}

function checkConfirmPwd() {
    var newPwd = checkNewPwd();
    if (newPwd) {
        var pwd = $.trim($("#resetPwd-newPwd").val());
        var confirmPwd = $.trim($("#resetPwd-confirmPwd").val());
        var reg = /^([a-zA-Z0-9]){9,20}$/i;
        if (!reg.test(pwd)) {
            document.getElementsByClassName("resetPwd-errorInfo")[0].innerHTML = "密码格式不正确";
            return false;
        } else {
            if (pwd == confirmPwd) {
                document.getElementsByClassName("resetPwd-errorInfo")[0].innerHTML = "&nbsp";
                return true;
            } else {
                document.getElementsByClassName("resetPwd-errorInfo")[0].innerHTML = "与新密码不一致";
                return false;
            }
        }
    } else {
        document.getElementsByClassName("resetPwd-errorInfo")[0].innerHTML = "新密码格式不正确";
        return false;
    }

    function submitForm() {
        var newPwd = checkNewPwd();
        var confirmPwd = checkConfirmPwd();
        if (newPwd && confirmPwd) {
            var confirmpassword = $.trim($("#resetPwd-confirmPwd").val());
            var data = { "password": confirmpassword};
            $.ajax({
                url:protocol + domainName + prot + userModule+ "/userInfo/resetPwd",
                type: "POST",
                dataType: "json",
                async: false,
                data: JSON.stringify(data),
                contentType: "application/json",
                success: function (result) {
                    if (result.isSuccess) {
                        if(result.code==222){
                        alert("修改成功");
                        window.location.href = "login.html";
                        }else{
                            alert("修改失败");
                            $("#resetPwd-newPwd").val("");
                            $("#resetPwd-confirmPwd").val("");
                        }
                    } else {
                        alert("服务器开小差了...");
                        $("#resetPwd-newPwd").val("");
                        $("#resetPwd-confirmPwd").val("");
                    }
                }
            });
        }
    }
}