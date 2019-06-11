//jquery获得url的get参数
function GetQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return decodeURI(r[2]);
    return null;
}

//转日期格式
function formatDate(date, fmt) {
    date = new Date(getDateDiff(date));
    if (/(y+)/.test(fmt)) {
        fmt = fmt.replace(RegExp.$1, (date.getFullYear() + '').substr(4 - RegExp.$1.length));
    }
    var o = {
        'M+': date.getMonth() + 1,
        'd+': date.getDate(),
        'h+': date.getHours(),
        'm+': date.getMinutes(),
        's+': date.getSeconds()
    };
    for (var k in o) {
        if (new RegExp(`(${k})`).test(fmt)) {
            var str = o[k] + '';
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length === 1) ? str : padLeftZero(str));
        }
    }
    return fmt;
}


function getDateDiff(date) {
    //将xxxx-xx-xx的时间格式，转换为 xxxx/xx/xx的格式
    return date.replace(/\-/g, "/");
}

function padLeftZero(str) {
    return ('00' + str).substr(str.length);
}

//获取url后的请求参数
function getRequest() {
    var url = location.search; //获取url中"?"符后的字串
    var theRequest = new Object();
    if (url.indexOf("?") != -1) {
        var str = url.substr(1);
        strs = str.split("&;");
        for (var i = 0; i < strs.length; i++) {
            theRequest[strs[i].split("=")[0]] = unescape(strs[i].split("=")[1]);
        }
    }
    return theRequest;
}

//获取数组中最大的数
function getMaxNumber(array) {
    return Math.max.apply(null, array);
}

function gcd(a, b) {
    return (b == 0) ? a : gcd(b, a % b);
}

function shareImage(type, title, content) {
    var top = 200;
    var tableHeight = $(".main").height();
    var tableWidth = $(".main").width();
    var canvas = document.createElement("canvas");
    canvas.width = 640;
    tableHeight = tableHeight * 640 / tableWidth;
    canvas.height = tableHeight + top + 20 + 52;
    var context = canvas.getContext("2d");
    context.rect(0, 0, canvas.width, canvas.height);
    context.fillStyle = "#fff";
    context.fill();
    var topImage = new Image();
    topImage.src = "/static/img/share_top.jpg";
    topImage.onload = function () {
        context.drawImage(topImage, 0, 0, 640, 118);
        context.font = "18px bold 黑体";
        context.fillStyle = "#000";
        context.textAlign = "center";
        context.textBaseline = "top";
        context.fillText(title, 300, 140);
        if (content != "") {
            context.fillText(content, 300, 160);
        }
        html2canvas(document.querySelector(".main")).then(function (c) {
            var contentImage = new Image();
            contentImage.src = c.toDataURL();
            contentImage.onload = function () {
                context.drawImage(contentImage, 10, top, canvas.width - 20, tableHeight);
                var bottomImage = new Image();
                bottomImage.src = "/static/img/share_bottom.jpg";
                bottomImage.onload = function () {
                    context.drawImage(bottomImage, 0, top + tableHeight + 20, 640, 52);
                    $.ajax({
                        url: "/teacher101/table/uploadShareImage",
                        data: {
                            data: canvas.toDataURL('images/png')
                        },
                        type: "POST",
                        success: function (rs) {
                            if (rs != "") {
                                try {
                                    if (window.webkit) {
                                        // type  1 代表分享好友 2 代表分享朋友圈
                                        window.webkit.messageHandlers.createShareImage.postMessage({
                                            type: type,
                                            url: rs
                                        });
                                    } else {
                                        teacherindex.shareImage(type, rs);
                                    }
                                } catch (e) {
                                }
                            } else {
                                layer.msg("分享图片失败，请稍后再试");
                            }
                        }
                    });
                };
            };
        });
    };

}

