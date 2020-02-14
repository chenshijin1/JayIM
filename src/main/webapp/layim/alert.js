adduser_card=function(){
    layer.open({
        type: 2,
        title: '迷你资料卡',
        shadeClose: true,
        shade: false,
        skin: 'layui-layer-rim',//加上边框
        maxmin: true, //开启最大化最小化按钮
        area: ['300px', '450px'], //宽， 高
        content: 'user_card.html',


    });
};
adduser=function(){
    layer.open({
        type: 2,
        title: '添加好友',
        shadeClose: true,
        shade: false,
        skin: 'layui-layer-rim',//加上边框
        maxmin: true, //开启最大化最小化按钮
        area: ['200px', '200px'],
        content: ['user_card.html', 'no']
    });
};

function strlen(str){
    var len = 0;
    for (var i=0; i<str.length; i++) {
        var c = str.charCodeAt(i);
        //单字节加1
        if ((c >= 0x0001 && c <= 0x007e) || (0xff60<=c && c<=0xff9f)) {
            len++;
        }
        else {
            len+=2;
        }
    }
    return len;
}

layui.use('layer', function(){ //独立版的layer无需执行这一句
    var $ = layui.jquery, layer = layui.layer; //独立版的layer无需执行这一句
    //触发事件
    var active = {
        showtable:function(othis){
            layer.open({
                type: 2,
                title: '好友信息统计',
                shadeClose: true,
                shade: false,
                skin: 'layui-layer-rim',//加上边框
                maxmin: true, //开启最大化最小化按钮
                area: ['100%', '100%'],
                content: ['showtables.html', 'no']
            });
        },

    deletetype_name: function(othis){
            var type = othis.data('type')
                ,text = othis.text();

            layer.prompt({title: '请输入你想删除组名', formType: 0}, function(text, index){
                var flag=true;
                localStorage.setItem("type_name",text);
                for(var i=0;i<parseInt(localStorage.getItem("item_typelength"));i++){
                    // console.log(parseInt(localStorage.getItem("item_typecount"+i)));
                   if(localStorage.getItem("item_typename"+i)==text){
                        var temp=parseInt(localStorage.getItem("item_typeid"+i));
                    // console.log(temp)
                       if(parseInt(localStorage.getItem("item_typecount"+i))>0){
                           flag=false;
                           layer.msg("当前分组已有好友，请删除好友后再删除相关组",function(){

                           });
                       }
                   }
                }
                // console.log(localStorage.getItem("item_typename"+i))
                // var item=localStorage.getItem("item_type0");
                // console.log(item);
                var user = parseInt(localStorage.getItem("userId"));
                if(flag==true){
                    $.post("/friendType/delete",
                        {   id:temp,
                            user_id:user
                        },
                        function(data,status){
                            layer.msg('成功删除组名'+ text );
                            window.location.href="find.html"
                        });
                }

                layer.close(index);
            });
            // });
        },
        addtype_name: function(othis){
            var type = othis.data('type')
                 ,text = othis.text();

            layer.prompt({title: '请输入组名', formType: 0}, function(text, index){
                var temp=0;
                var flag=true;
                localStorage.setItem("type_name",text);
                // console.log("123");
                // console.log(parseInt(text));
                if(strlen(text)>20){
                    flag=false;
                    layer.msg("输入字符不能超过限制，请重新输入")
                }
                if(flag==true) {
                    var user = parseInt(localStorage.getItem("userId"));
                    // console.log(user);
                    // console.log(localStorage.getItem("type_name"));
                    // console.log(temp);
                    for (var i = 0; i < parseInt(localStorage.getItem("item_typelength")); i++) {
                        if (localStorage.getItem("item_typename" + i) == text) {
                            alert("不能输入相同的组名，请重新输入");
                            window.location.href = "find.html";
                            return;
                        }
                    }
                    $.post("/friendType/add",
                        {
                            type_name: localStorage.getItem("type_name"),
                            user_id: user,
                            is_default: temp
                        },
                        function (data, status) {
                            layer.msg('成功添加组名' + text);
                            window.location.href = "find.html"
                        });
                }
                    layer.close(index);

            });
            // });
        }
    };

    $('#layerDemo .layui-btn').on('click', function(){
        var othis = $(this), method = othis.data('method');
        active[method] ? active[method].call(this, othis) : '';
    });
});