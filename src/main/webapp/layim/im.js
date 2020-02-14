var userId=$("#userId").val();
localStorage.setItem("userId",userId);
var socket = null;  // 判断当前浏览器是否支持WebSocket
if ('WebSocket' in window) {
	socket = new WebSocket("ws://localhost:8080/LLWS/"+userId);
} else {
	alert('该浏览器不支持本系统即时通讯功能，推荐使用谷歌或火狐浏览器！');
}
layui.use('layim', function(layim){
  var autoReplay = [
    '您好，我现在有事不在，一会儿再和您联系。'
  ];
 layim.config({ 
    init: {
      url: '/index/getInitList?userId='+userId
      ,data: {}
    }
    ,brief: false
    //查看群员接口
    ,members: {
       url: '/group/getMemberByGroupId'
      ,data: {}
    }
    ,uploadImage: {
       url: '/sns/uploadFile?userId='+userId
      ,type: '' //默认post
    }
    ,uploadFile: {
       url: '/sns/uploadFile?userId='+userId
      ,type: '' //默认post
    }
    ,min:true
    ,title: 'JayIM'        //主面板最小化后显示的名称
    ,msgbox: layui.cache.dir + 'css/modules/layim/html/msgbox.html' //消息盒子页面地址，若不开启，剔除该项即可
    ,find: layui.cache.dir + 'css/modules/layim/html/find.html' //发现页面地址，若不开启，剔除该项即可
    ,chatLog: layui.cache.dir + 'css/modules/layim/html/chatlog.html' //聊天记录页面地址，若不开启，剔除该项即可
    ,copyright: true          //是否授权
    ,right: '30px'
    ,notice:true      //开启桌面消息提醒

  });
 
	// 连接发生错误的回调方法
	socket.onerror = function() {
		console.log("llws连接失败!");
	};
	// 连接成功建立的回调方法
	socket.onopen = function(event) {
		console.log("llws连接成功!");
	};
	
	// 接收到消息的回调方法
	socket.onmessage = function(event) {
		var i=0;
		console.log("llws收到消息啦:" +event.data);
		var obj=eval("("+event.data+")");
		switch(obj.type){
            case "friendRequest":
                console.log(obj.message);
                if(obj.status == 0){
                    i++;
                    $(".layim-tool-msgbox").append("<span class=\"layui-anim layui-anim-loop layer-anim-05\">"+i+"</span>")
                }else if(obj.status == 1){
                    i++;
                    $(".layim-tool-msgbox").append("<span class=\"layui-anim layui-anim-loop layer-anim-05\">"+i+"</span>")
                    //将好友追加到主面板
                    parent.layui.layim.addList({
                        type: 'friend'
                        ,avatar: obj.avatar //好友头像
                        ,username: obj.userName //好友昵称
                        ,groupid: obj.typeId //所在的分组id
                        ,id: obj.id //好友ID
                        ,sign: obj.sign //好友签名
                    });
                }else if(obj.status == 2){
                    i++;
                    $(".layim-tool-msgbox").append("<span class=\"layui-anim layui-anim-loop layer-anim-05\">"+i+"</span>")
                }
                break;
            case "onlineStatus":
                console.log("开始置亮置灰");
                //实时置亮置灰
                layim.setFriendStatus(obj.id,obj.content);
                break;
            case "delFriend":
                parent.layui.layim.removeList({
                    type: 'friend' //或者group
                    ,id: obj.id //好友或者群组ID
                });
                break;
            case "moveFriend":
                if(obj.status >= 0){
                    parent.layui.layim.removeList({
                        type: 'friend' //或者group
                        ,id: obj.id //好友或者群组ID
                    });
                    parent.layui.layim.addList({
                        type: 'friend'
                        ,avatar: obj.avatar //好友头像
                        ,username: obj.userName //好友昵称
                        ,groupid: obj.typeId //所在的分组id
                        ,id: obj.id //好友ID
                        ,sign: obj.sign //好友签名
                    });
                }
                break;
            default:
                layim.getMessage(obj);
                break;
        }
	};
	// 连接关闭的回调方法
	socket.onclose = function() {
		console.log("llws关闭连接!");
	};
	// 监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
	window.onbeforeunload = function() {
		socket.close();
	};

    // 监听发送消息
    layim.on('sendMessage', function(data){
	   var obj={
			    "mine":{
				   "avatar":data.mine.avatar,             
				   "content":data.mine.content,          
				   "id":data.mine.id,        
				   "mine":true,                       
				   "username":data.mine.username      
				 },
				 "to":{
					   "avatar":data.to.avatar,
					   "id":data.to.id,
					   "name":data.to.groupname,
					   "sign":data.to.sign,
					   "type":data.to.type,       
					   "username":data.to.username
				 }
			   }
	    console.log(JSON.stringify(obj));
		socket.send(JSON.stringify(obj));  	//发送消息倒Socket服务
   });
    
  //监听在线状态的切换事件
  layim.on('online', function(status){
      $.post("/index/updateStatus", {
          userId: localStorage.getItem("userId"),
          status: status
      }, function (result) {
          console.log(result);
      })
  });
 
  //layim建立就绪
  layim.on('ready',function(){
  	//获取离线消息
      $.post("/index/getOfflineMsgFromRedis?userId="+userId,function(res){
          console.log(res);
          $.each(res,function(k,v){
              var s = eval('(' + v + ')');
              layim.getMessage(s);
          });
      });
      /*layim.add({
          type: 'friend' //friend：申请加好友、group：申请加群
          ,username: 'testfriend' //好友昵称，若申请加群，参数为：groupname
          ,avatar: '/images/animal-01.png' //头像
          ,submit: function(group, remark, index){ //一般在此执行Ajax和WS，以通知对方
              group = [1, 2];
              remark = "加我哦哈"
              console.log(group); //获取选择的好友分组ID，若为添加群，则不返回值
              console.log(remark); //获取附加信息
              layer.close(index); //关闭改面板
          }
      });*/
  });

  //监听查看群员
  layim.on('members', function(data){
    console.log(data);
  });
  
  //监听聊天窗口的切换
  layim.on('chatChange', function(data){
    console.log(data);
  }); 
  
  function fankui(name,id,logo,sign){
	  var iid=Number(id);
	  layim.chat({
		   sign:sign
		  ,name: name
		  ,type: 'fankui'  
		  ,avatar: logo 
		  ,id:iid  
		});
   }
  
  layim.on('sign', function(value){
	  //console.log(value.length); //获得新的签名
	  if(value.length<200){
		  $.post("/index/updateSign",{"id":userId,"sign":value},function(result){
			  console.log(result); 
		  })  
	  }else{
		  layer.msg("签名不能超过200字符！")
	  }
	});    
  
});




