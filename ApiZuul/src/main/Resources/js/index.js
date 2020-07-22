//传入的页数
var pageCount = 1;
var flag = 1;
//总数据
var orderSize;
//总页数
var countPage;
//每一页需要的订单数,也是最后一页
var countPerPage = 20;
//当前页数
var currentPage = 1;
//登陆状态
var loginStatus;

//当浏览器加载页面就触发时间，ajax与后台请求数据接收订单信息，修改当前用户的nickname，修改订单模块的父元素高度
//计算一共有多少页的数据（最后一页请求的是第几个数据）
$(document).ready(function () {
    //加载第一页
    changePage(1);

    //index-functionModule的nickname在页面一加载就进行导入
    if (JSON.stringify(user) == "{}") {
        loginStatus = 0;
    } else {
        $("#nicknameSpan").html(user.nickname);
        loginStatus = 1;
    }

    //计算总页数countPage,也是最后一页的页数
    countPage = Math.ceil(orderSize / 20);

    //当才进入主页，页数为第一页 分页的‘<<’按钮无效
    if (currentPage == 1) {
        $("#prePageBtn").attr("disabled", true);
    } else {
        $("#prePageBtn").attr("disabled", false);
    }
})

// 查看详情函数
function getMore(OID, obj) {
    var tr1 = document.getElementById(OID);
    if (flag == 1) {
        tr1.style.setProperty("display", "table-row", "important");
        obj.innerHTML = "折叠";
        flag = 0;
    } else {
        tr1.style.setProperty("display", "none", "important");
        obj.innerHTML = "展开";
        flag = 1;
    }
}

//修改订单状态函数
function changeStatus(OID, obj) {
    if (loginStatus == 0) {
        alert("请先去登录");
    } else {
        var orderInfoID = OID;
        var userID = userID;
        var data = { "orderInfoID": orderInfoID, "userID": userID };
        $.ajax({
            url: protocol + domainName + prot + orderModule+"/orderInfo/handleOrder",
            type: "GET",
            dataType: "json",
            async: true,
            data: JSON.stringify(data),
            contentType: "application/json",
            success: function (result) {
                if (result.isSuccess) {
                    if (result.code == 222) {
                        obj.innerHTML = "已领取";
                        obj.setAttribute("disabled", true);
                    } else {
                        alert("领取失败");
                    }
                } else {
                    alert("服务器开小差了...");
                }
            }
        });
    }
}

//分页模块的获取对应订单函数
function changePage(page) {
    if (page == 1) {
        $("#prePageBtn").attr("disabled", true);
    } else {
        $("#prePageBtn").attr("disabled", false);
        currentPage = page;
        pageCount = page;
    }
    if (page == countPage) {
        $("#nextPageBtn").attr("disabled", true);
    } else {
        $("#nextPageBtn").attr("disabled", false);
        currentPage = page;
        pageCount = page;
    }
    var data = { "pageCount": pageCount, "countPerPage": countPerPage };
    // 清空包含表格的div中的内容
    $(".index-orderFormModule-content:first").empty();
    var bodyContent = "<table id=\"tb\"><tr><th class=\"tb-sequence\">序号</th><th class=\"tb-goodSize\">商品规格</th><th class=\"tb-sex\">性别要求</th><th class=\"tb-wage\">金额</th><th class=\"tb-deadline\">配送截止时间</th><th class=\"tb-more\">详情</th><th class=\"tb-getBtn\">点击领取</th></tr>";
    $.ajax({
        url: protocol + domainName + prot + orderModule+"/orderInfo/showAllOrderInfoList",
        async: false,
        data: JSON.stringify(data),
        dataType: "json",
        type: "GET",
        contentType: "application/json",
        success: function (result) {
            if (result.isSuccess) {
                if (result.code == 200) {
                    var pageInfoList = result.data.list;
                    orderSize = pageInfoList.length;
                    pageInfoList.forEach((list) => {
                        bodyContent += "<tr><td>" + list.orderID + "</td>";
                        bodyContent += "<td>" + list.size + "</td>";
                        bodyContent += "<td>" + list.sex + "</td>";
                        bodyContent += "<td>" + list.money + "元</td>";
                        bodyContent += "<td>" + list.deadline + "</td>";
                        bodyContent += "<td>" + status + "</td>";
                        bodyContent += "<td><button onclick=\"changeStatus(" + list.orderID + ",this)\">领取</button></td>";
                        bodyContent += "<td class=\"unfoldTD\" onclick=\"getMore(" + list.orderID + ",this)\">展开</td></tr>";

                        bodyContent += "<tr class=\"hidden\" id=" + list.orderID + "><div>";
                        bodyContent += "<span class=\"hidden-title\">联系方式</span>:<span class=\"hidden-info\">" + list.telephone + "</span>";
                        bodyContent += "<span class=\"hidden-title\">取货地点</span>:<span class=\"hidden-info\">" + list.shipAddress + "</span>";
                        bodyContent += "<span class=\"hidden-title\">送货地点</span>:<span class=\"hidden-info\">" + list.sendAddress + "</span>";
                        bodyContent += "<span class=\"hidden-title\">备注</span>:<span class=\"hidden-info\">" + list.note + "</span></div></tr>";
                    });
                    bodyContent += "</table>";
                    $(".index-orderFormModule-content:first").append(bodyContent);
                }else{
                    alert("页面加载失败，请刷新重试");
                }
            } else {
                alert("服务器开小差了...");
            }
        }
    });
}

//进入我的发单界面
function enterMyPlacedOrder() {
    if (loginStatus == 0) {
        alert("请先去登录");
    } else if (loginStatus == 1) {
        window.location.href = "myPlacedOrder.html";
    }
}



//查询用户信息(未完成)
function showUser() {
    if (loginStatus == 0) {
        window.location.href = "login.html";
        $("#nicknameSpan").html("登录");
    } else if (loginStatus == 1) {
        $(".index-userInfo:first").toggle();
        $("#index-userInfo-nickname").text(user.nickname);
        $("#index-userInfo-tel").text(user.userID);
        $("#index-userInfo-sex").text(user.sex);
        $.ajax({
            url: "",
            async: true,
            data: JSON.stringify(data),
            type: "GET",
            dataType: "json",
            contentType: "application/json",
            success: function (result) {

            }
        });
    }
}



//注销函数(未完成)
function logoutFunc() {
    // const userID = user.userID;
    // const data = { "userID": userID };
    console.log(user);
    const isSuccess = true;
    if (isSuccess) {
        alert("注销成功");
        loginStatus = 0;
        user = {};
        console.log(loginStatus);
        console.log(user);
        console.log(user.userID);
    } else {
        alert("注销失败");
    }
    $.ajax({
        url: "/userInfo/deleteUser",
        type: "POST",
        async: true,
        data: JSON.stringify(data),
        dataType: "json",
        contentType: "application/json",
        success: function (result) {
            if (result.isSuccess) {
                alert("注销成功");
                loginStatus = 0;
                user = {};
                console.log(user);
                console.log(user.userID);
            } else {
                alert("注销失败");
            }
        }
    });
}