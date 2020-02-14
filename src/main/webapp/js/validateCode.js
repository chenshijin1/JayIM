$(document).ready(function(){
    var setup = function() {
        // tab click
        $('.zh-tab > a').on('click', function () {
            var self = $(this);
            console.log('click a')
            $('.active').removeClass('active');
            self.addClass('active');
        });

        // tab action
        var tabAction = function (position, showLogin) {
            console.log(position);
            $(".zh-block").animate({
                "left": position
            }, "fast");
            $('#zh-form-login').toggle(showLogin);
            $('#zh-form-signup').toggle(!showLogin);
        };

        $('#zh-signup').on('click', function() {
            var position = '66px';
            var showLogin = false;
            tabAction(position, showLogin);
        });
        $('#zh-login').on('click', function() {
            var position = '155px';
            var showLogin = true;
            tabAction(position, showLogin);
        });
    };

    var __main = function() {
        setup();
        // select signup
        $('#zh-signup').click();
    };

    __main();
});

//生成验证码
var arr = ['1', 'r', 'Q', '4', 'S', '6', 'w', 'u', 'D', 'I', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p',
    'q', '2', 's', 't', '8', 'v', '7', 'x', 'y', 'z', 'A', 'B', 'C', '9', 'E', 'F', 'G', 'H', '0', 'J', 'K', 'L', 'M', 'N', 'O', 'P', '3', 'R',
    '5', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'];
function create_code() {
    function shuffle() {
        return arr.sort(function () {
            return (Math.random() - .5);
        });
    };
    shuffle();
    function show_code() {
        var ar1 = '';
        var code = shuffle();
        for (var i = 0; i < 6; i++) {
            ar1 += code[i];
        }
        ;
        //var ar=ar1.join('');
        $(".phoKey").text(ar1);
    };
    show_code();
    $(".phoKey").click(function () {
        show_code();
    });
}
function password1() {
    var upassword = document.getElementById("password").value;
    var upassword1 = document.getElementById("password1").value;
    if (upassword1 != upassword) {
        alert("两次密码不同");
    }
}

//usereamil验证
function email() {
    var regemail = /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
    var uemail = document.getElementById("email").value;
    if (uemail.length <= 0 || !regemail.test(uemail)) {
        alert("邮箱地址填写错误！");
    }
}
//phone验证
function isValidMobile (value) {
    var isValid = !!value.match(/^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/);
    if (!isValid) {
        alert("电话号码填写错误!")
    }
    return isValid;
}

// images/animal-01.png
$(function() {
    //password1验证
    create_code();
    $("#register_bt").click(function() {
        var code = $("#code").val();
        var phoKey = $("#phoKey").text();
        var name = $("#name").val();
        var password = $("#password").val();
        var nickName = $("#nickName").val();

        // var avator = 'images/animal-01.png';
        var phone = $("#phone").val();
        var email = $("#email").val();
        if(code!=phoKey) {
            alert("验证码错误");
            console.log("验证码错误");
        }
        else{
            console.log(code+" "+phoKey);
            $.post("/index/register",
                {   userName:name,
                    password:password,
                    nickName:nickName,
                    email:email,
                    phone:phone,
                    // avator:avator,
                },
                function(result){
                    layui.use(['layer', 'form'], function () {
                        layer.msg("已经发送请求，请耐心等待回复",{time:1000}, function () {
                            var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
                            parent.layer.close(index);
                        });
                    })
                });
        }
    });
});
