<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link href="https://cdn.bootcss.com/twitter-bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.bootcss.com/jquery/1.5.1/jquery.min.js"></script>
    <script src="../js/echarts.js"></script>
</head>
<body>

<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <h3>
                ${engineId}的详细信息
            </h3>
            <table class="table table-bordered">
                <thead>
                <tr>
                    <th>柴油机编号</th>
                    <th>时间</th>
                    <th>所在位置</th>
                    <th>所在经纬度</th>
                    <th>风向</th>
                    <th>风速</th>
                    <th>温度</th>
                    <th>压强</th>
                    <th>颗粒物PM排放</th>
                    <th>氮氧化物NOx排放</th>
                    <th>一氧化碳CO排放</th>
                    <th>碳氢化物HC排放</th>
                </tr>
                </thead>
                <tbody>
                <#list engineSpecificVoList.data as engineSpecific>
                    <tr>
                        <td>${engineSpecific.engineId}</td>
                        <td>${engineSpecific.time?string('yyyy-MM-dd HH:mm:ss')}</td>
                        <td>${engineSpecific.location}</td>
                        <td>${"(${engineSpecific.longitude},${engineSpecific.latitude})"}</td>
                        <td>${engineSpecific.windDirection}</td>
                        <td>${"${engineSpecific.windSpeed}km/h"}</td>
                        <td>${"${engineSpecific.temperature}℃"}</td>
                        <td>${"${engineSpecific.pressure}hPa"}</td>
                        <#if engineSpecific.pm gt 1.8>
                            <td style="color: red">${engineSpecific.pm}</td>
                        <#else>
                            <td>${engineSpecific.pm}</td>
                        </#if>

                        <td>${engineSpecific.no}</td>
                        <td>${engineSpecific.co}</td>
                        <td>${engineSpecific.hc}</td>
                    </tr>
                </#list>
                </tbody>
            </table>
        </div>
    </div>
</div>

<div id="Main" style="text-align:center; width: 600px; height: 400px;border: 1px;"></div>

<script type="text/javascript">
    var myChart = echarts.init(document.getElementById('Main'));
    // 显示标题，图例和空的坐标轴
    var option={
        title : {
            text : '${engineId}的图表显示',
            fontsize : 20,
            left : 'center'
        },
        tooltip : {
            trigger: 'axis', //坐标轴触发提示框，多用于柱状、折线图中
        },
        legend : {
            data : [ '排放量' ],
            left : '150'
        },
        toolbox: {
            feature: {
                dataView: {show: true, readOnly: false},
                magicType: {show: true, type: ['line', 'bar']},
                restore: {show: true},
                saveAsImage: {show: true}
            }
        },
        xAxis : {
            name : '污染物种类',
            type : 'category',
            data : []
        },
        yAxis : {},
        series : [ {
            name : '排放量',
            type : 'bar',
            data : []
        } ]
    };

    myChart.showLoading(); //数据加载完之前先显示一段简单的loading动画

    var names = [];     //类别数组（实际用来盛放X轴坐标值）
    var nums = [];       //销量数组（实际用来盛放Y坐标值）

    $.ajax({
        type : "get",
        async : true, //异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
        url : "/car/echart?carId=${carId}&time=${time}",
        dataType : "json",
        success : function(data) {
            //请求成功时执行该函数内容，result即为服务器返回的json对象
            if (data.status == 0) {
                var obj = eval(data.data);     //解析后台传来的json数据
                for (var i = 0; i < obj.length; i++) {
                    names.push(obj[i].name);
                }
                for (var i = 0; i < obj.length; i++) {
                    nums.push(obj[i].num);
                }
                myChart.hideLoading(); //隐藏加载动画
                myChart.setOption({ //加载数据图表
                    xAxis : {
                        data : names
                    },
                    series : [ {
                        // 根据名字对应到相应的系列
                        name : '排放量',
                        data : nums
                    } ]
                });
            }else{
                alert("后台数据获取失败!");
            }
        },
        error : function(errorMsg) {
            //请求失败时执行该函数
            alert("图表请求数据失败!");
            myChart.hideLoading();
        }
    })

    myChart.setOption(option);
</script>
</body>
</html>