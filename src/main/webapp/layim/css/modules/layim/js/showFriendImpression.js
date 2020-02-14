/*
1.获取json数据

2.json数据转echarts图表
echarts图表
html代码形式
3.显示html代码

*/
// 1.获取json数据


// var id = getId();
var url = "/userInfo/getAllInfo?id=" + parseInt(localStorage.getItem("selectid"));
var id = parseInt(localStorage.getItem("userId"));
var params = {"id": id};
// url += {"id": id};
// /userInfo/getAllInfo?id=2


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

/*
//获取用户id或者好友id
function getId() {
    var id = undefined;
    var id = localStorage.getItem("selectid");
    console.log(localStorage.getItem("userId"), "userId");
    console.log(localStorage.getItem("selectid"), "selectid");

    if (typeof id !== 'undefined') {
        console.log(localStorage.getItem("selectid"), "selectid");
        return id;

    } else {
        id = localStorage.getItem("userId");
        console.log(localStorage.getItem("userId"), "userId");

        return id;

    }

}
*/

var results = $.getValues(url, params);
console.log(results, " results");
console.log(typeof results.friendImpression, "results.friendImpression");


/*
2.json数据转echarts图表
echarts图表
html代码形式
*/

/*
2.1 json 转 array
*/

var impressionJson = results.friendImpression;


// 把原始json数据改成统计数据
/*
[['能侃',2],
['阿里巴巴', 1],
['无形之中装逼',3],
['首富',4],
['悔创阿里',4]],
https://blog.csdn.net/xyphf/article/details/50984778

* */
function JsonToArray(impressionJson) {
    var modifiedImpressionJson = modifiedJson(impressionJson)
    var json_data = modifiedImpressionJson;
    var result = [];

    for (var i in json_data)
        result.push([i, json_data [i]]);

    console.log(result, "result");
    return result;
}

/* 对json中key出现次数进行统计 */
function modifiedJson(impressionJson) {
    var str = extractContent(impressionJson);
    // console.log(str, "str");
    var arr = str.split(' ');
    // console.log(arr, "arr");

    var json = {};
    for (var i = 0; i < arr.length; i++) {
        var key = arr[i];
        if (json[key]) {
            //去重+统计
            //存在
            json[key]++;//取出属性值+1
        } else {
            //不存在
            json[key] = 1;//做一个属性赋值1
        }
    }
    console.log(json, "showImpression json");
    return json;
}

/*
提取content
*/
function extractContent(impressionJsosn) {
    var str = '';
    for (var i = 0; i < impressionJson.length; i++) {
        // console.log(impressionJson[i]['content']);
        var content = impressionJson[i]['content'] + " ";
        str += content;
    }
    return str;
}


var impressionArray = JsonToArray(impressionJson);
console.log(impressionArray, "showImpression  impressionArray");

/*
var wordFreqData = [['能侃', 2],
    ['阿里巴巴', 1],
    ['无形之中装逼', 3],
    ['首富', 4],
    ['悔创阿里', 4]]
*/

function showImprssion() {
    // var id = getId();
    // console.log(id, "id");
    var wordFreqData = impressionArray;
    var canvas = document.getElementById('canvas');
    var options = eval({
        "list": wordFreqData,//或者[['各位观众',45],['词云', 21],['来啦!!!',13]],只要格式满足这样都可以
        "gridSize": 6, // 密集程度 数字越小越密集
        "weightFactor": 20, // 字体大小=原始大小*weightFactor
        "maxFontSize": 60, //最大字号
        "minFontSize": 14, //最小字号
        "fontWeight": 'normal', //字体粗细
        "fontFamily": 'Times, serif', // 字体
        "color": 'random-light', // 字体颜色 'random-dark' 或者 'random-light'
        "backgroundColor": '#FFF', // 背景颜色
        // "backgroundColor": '#333', // 背景颜色
        "rotateRatio": 1 // 字体倾斜(旋转)概率，1代表总是倾斜(旋转)
    });
//生成
    WordCloud(canvas, options);


}


showImprssion();
