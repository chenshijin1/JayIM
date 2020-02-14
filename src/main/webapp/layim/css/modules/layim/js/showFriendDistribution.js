/*
/friend/getFriendCityCountByUserId?userId=2
{"data":[{"北京市":1},{"杭州":1},{"武汉":1},{"青岛":1}]}
to
[
    {name: "海门", value: 9},
    {name: "鄂尔多斯", value: 12},
    {name: "招远", value: 12},
    {name: "舟山", value: 12},
    {name: "齐齐哈尔", value: 14},

];

*/
//获取 data
var id = parseInt(localStorage.getItem("userId"));
var url = "/friend/getFriendCityCountByUserId?user_id=" + id;
// var url = "http://localhost:8080/friend/getFriendCityCountByUserId?user_id=" + parseInt(localStorage.getItem("userId"));
// var id = parseInt(localStorage.getItem("userId"));
// var params = {"id": id};
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


var results = $.getValues(url);
// console.log(results, " results");


//处理data
/*
for (var result in results) {
    for (var key in result) {
        alert('key : ' + key + ':: value : ' + result[key])
    }
    // if (key.match(/name|value/)) {
    // }
}
*/
var results=results.data;
// var results = [{"北京": 1}, {"杭州": 1}, {"武汉": 1}, {"青岛": 1}];

function processeJSON(results) {
    var processedResults =     [
        // {name: "海门", value: 9},
        // {name: "鄂尔多斯", value: 12},
        // {name: "招远", value: 12},
        // {name: "舟山", value: 12},
        // {name: "齐齐哈尔", value: 14},

    ];
//初始化 processedResults
//             console.log(results.length, "results.length");
//             console.log(results[0], "results[0]");
//     var length = 4;
    var length = results.length;
    for(var i=0; i<length; i++) {
        processedResults[i] = {name: " ", value: 0};
    }
    // console.log(processedResults, "after 初始化");
//赋值 processedResults
    for(var i=0; i<length; i++)
    {
        // console.log(results[i].text + " " + results[i].value);
        // alert(results[i].text + " " + results[i].value);
        // alert(results[i]);
        var result = results[i];
        // console.log(results[i], "results[i]");
        for(var key in result){
            // alert('key : ' + key + ':: value : ' + result[key]);
            processedResults[i].name = key;
            processedResults[i].value = result[key];

        }
    }
    // console.log(processedResults, "after 赋值");

    return processedResults;
}

var processedResults = processeJSON(results);

//整合

function getFriendDistribution() {
    // console.log(url, "url") ;
    // var results = $.getValues(url);
    // console.log(results, "results");
    var processedResults = processeJSON(results);

    // console.log(processedResults,"processedResults");
    /*
                processedResults=     [
                    {name: "海门", value: 9},
                    {name: "鄂尔多斯", value: 12},
                    {name: "招远", value: 12},
                    {name: "舟山", value: 12},
                    {name: "齐齐哈尔", value: 14},

                ];
    */

    return processedResults;
}



