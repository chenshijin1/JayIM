

/*
/friendImpression/add
参数from_user_id to_user_id content
*/

var id = parseInt(localStorage.getItem("userId"));
var params = {"id": id};
// url += {"id": id};
// /userInfo/getAllInfo?id=2



/*
jQuery.extend({
    getValues: function (url) {
        var result = null;
        $.ajax({
            url: url,
            // param: params,
            type: 'get',
            dataType: 'json',
            async: false,
            success: function (data) {
                result = data;
            }
        });
        return result;
    }
});
// Then to access it, create the variable like so:
*/
/*
1.button 加onclick事件

2.laiyui弹出层
获取content

3.组合url
ajax发送
*/

function addImpression() {


    // var url = getUrl();
    getContent();
    // console.log(url, "addImpression url");

}

function getContent() {
    var content = "";
    //prompt层
    layer.prompt({title: '随便写点啥，并确认', formType: 2}, function(text, index){
        layer.close(index);
        console.log(text , "text");
        content = text;
        /*
         getContent(){ getUrl()}
         这样的顺序才能顺利获得url
        **/
        getUrl(content);
        layer.msg('<br>您最后写下了：'+text);
    });
    console.log(content , "before content");

    return content;
}


function getUrl(content) {
    // var content = " ";
    // var content=getContent();
    console.log(content , "content");

    var url = "/friendImpression/add?" +
        "from_user_id=" + parseInt(localStorage.getItem("userId")) +
        "&to_user_id=" + parseInt(localStorage.getItem("selectid")) +
        "&content=" + content
    ;
    console.log(url, "before addImpression url");
    $.sendUrl(url);
    return url;
}

jQuery.extend({
    sendUrl: function (url) {
        var result = null;
        $.ajax({
            url: url,
            // param: params,
            type: 'get',
            dataType: 'json',
            async: false,
            success: function (data) {
                result = data;
                console.log(data, "data ajax");
            }
        });
        return result;
    }
});
// Then to access it, create the variable like so:

// var results = $.getValues(url, params);
