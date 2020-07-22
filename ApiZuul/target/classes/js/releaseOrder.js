
function checkTel() {
    var Telephone = $.trim($("#releaseOrder-telephone").val());
    var telSpan = document.getElementsByClassName("releaseOrder-telSpan")[0];
    if (Telephone.length != 11) {
        telSpan.innerHTML = "请输入正确的账号!";
        return false;
    }
    else {
        telSpan.innerHTML = "";
        return true;
    }
}

function checkShipAdd() {
    var shipAddress = $.trim($("#releaseOrder-shipAddress").val());
    var SASpan = document.getElementsByClassName("releaseOrder-shipAddSpan")[0];
    if (shipAddress == "") {
        SASpan.innerHTML = "请输入取货地址";
        return false;
    } else {
        SASpan.innerHTML = "";
        return true;
    }
}

function checkSendAdd() {
    var sendAddress = $.trim($("#releaseOrder-sendAddress").val());
    var SASpan = document.getElementsByClassName("releaseOrder-sendAddSpan")[0];
    if (sendAddress == "") {
        SASpan.innerHTML = "请输入送货地址";
        return false;
    } else {
        SASpan.innerHTML = "";
        return true;
    }
}

function checkWage() {
    var wage = $.trim($("#releaseOrder-wage").val());
    var wageSpan = document.getElementsByClassName("releaseOrder-wageSpan")[0];
    if (wage == "") {
        wageSpan.innerHTML = "请输入金额";
        return false;
    } else {
        wageSpan.innerHTML = "";
        return true;
    }
}

function checkdateTime() {
    var datetime = $.trim($("#releaseOrder-datetime").val());
    var DTSpan = document.getElementsByClassName("releaseOrder-dtSpan")[0];
    if (datetime == "") {
        DTSpan.innerHTML = "请输入截止时间";
        return false;
    } else {
        DTSpan.innerHTML = "";
        return true;
    }
}

function checkGoodSize() {
    var goodSize = $.trim($("#releaseOrder-goodSize").val());
    var GSSpan = document.getElementsByClassName("releaseOrder-goodSizeSpan")[0];
    if (goodSize == "") {
        GSSpan.innerHTML = "请输入物品大小的描述";
        return false;
    } else {
        GSSpan.innerHTML = "";
        return true;
    }
}


//提交订单函数
function checkSubmit() {
    var tel = checkTel();
    var shipAdd = checkShipAdd();
    var sendAdd = checkSendAdd();
    var wage = checkWage();
    var dt = checkdateTime();
    var goodSize = checkGoodSize();
    if (tel && shipAdd && sendAdd && wage && dt && goodSize) {
        var telephone = $.trim($("#releaseOrder-telephone").val());
        var shipAddress = $.trim($("#releaseOrder-shipAddress").val());
        var sendAddress = $.trim($("#releaseOrder-sendAddress").val());
        var money = $("#releaseOrder-wage").val();
        var sex = $("input[name='Sex']:checked").val();
        var note = $("#releaseOrder-note").val();
        var deadline = $("#releaseOrder-datetime").val();
        var size = $("#releaseOrder-goodSize").val();
        var data = {
            "telephone": telephone, "shipAddress": shipAddress, "sendAddress": sendAddress, "money":
                money, "sex": sex, "note": note, "deadline": deadline, "size": size
        };
        if (checkTelephone) {
            $.ajax({
                url: protocol + domainName + prot + orderModule+"/expressInfo/writeInfo",
                async: false,
                type: "POST",
                data: JSON.stringify(data),
                dataType: "json",
                contentType: "application/json",
                success: function (result) {
                    if (result.isSuccss) {
                        if (result.code == 222) {
                            alert("订单发布成功");
                            window.location.href = "index.jsp";
                        }else{
                            alert("订单发布失败");
                        }
                    } else {
                        alert(result.message);
                    }
                }
            });
        }
    }
}