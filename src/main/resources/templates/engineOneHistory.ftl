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
                ${engineId}的历史信息
            </h3>
            <table class="table table-bordered">
                <thead>
                <tr>
                    <th>时间</th>
                    <th>PM</th>
                    <th>NOx</th>
                    <th>CO</th>
                    <th>HC</th>
                </tr>
                </thead>
                <tbody>
                <#list engineHistoryVoList.data as engineOneInfoList>
                    <tr>
                        <td>${engineOneInfoList.time?string('yyyy-MM-dd HH:mm:ss')}</td>
                        <#if engineOneInfoList.pm gt 1.8>
                            <td style="color: red">${engineOneInfoList.pm}</td>
                        <#else>
                            <td>${engineOneInfoList.pm}</td>
                        </#if>
                        <td>${engineOneInfoList.no}</td>
                        <td>${engineOneInfoList.co}</td>
                        <td>${engineOneInfoList.hc}</td>
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
    var option = {
        title: {
            text: '${engineId}历史信息的图表显示',
            fontsize: 20

        },
        tooltip: {
            trigger: 'axis', //坐标轴触发提示框，多用于柱状、折线图中
        },
        legend: {
            data: ['pm', 'no', 'co', 'hc'],
            left: '250'
        },
        toolbox: {
            feature: {
                dataView: {show: true, readOnly: false},
                magicType: {show: true, type: ['stack', 'tiled']},
                restore: {show: true},
                saveAsImage: {show: true}
            }
        },
        xAxis: {
            name: '时间',
            type: 'category',
            data: []
        },
        yAxis: {},
        series: [{
            name: 'pm',
            type: 'line',
            smooth: true,
            itemStyle : { normal: {label : {show: true}}},
            data: []
        }, {
            name: 'no',
            type: 'line',
            smooth: true,
            itemStyle : { normal: {label : {show: true}}},
            data: []
        },
            {
                name: 'co',
                type: 'line',
                smooth: true,
                itemStyle : { normal: {label : {show: true}}},
                data: []
            },
            {
                name: 'hc',
                type: 'line',
                smooth: true,
                itemStyle : { normal: {label : {show: true}}},
                data: []
            }]
    };

    myChart.showLoading(); //数据加载完之前先显示一段简单的loading动画

    var times = [];     //类别数组（实际用来盛放X轴坐标值）
    var numsPm = [];       //销量数组（实际用来盛放Y坐标值）
    var numsNo = [];       //销量数组（实际用来盛放Y坐标值）
    var numsCo = [];       //销量数组（实际用来盛放Y坐标值）
    var numsHc = [];       //销量数组（实际用来盛放Y坐标值）

    $.ajax({
        type: "get",
        async: true, //异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
        url: "/car/echarts?carId=${carId}",
        dataType: "json",
        success: function (data) {
            //请求成功时执行该函数内容，result即为服务器返回的json对象
            if (data.status == 0) {
                var obj = eval(data.data);     //解析后台传来的json数据
                for (var i = 0; i < obj.length; i++) {
                    times.push(obj[i].time);
                }
                for (var i = 0; i < obj.length; i++) {
                    numsPm.push(obj[i].pm);
                    numsNo.push(obj[i].no);
                    numsCo.push(obj[i].co);
                    numsHc.push(obj[i].hc);

                }
                myChart.hideLoading(); //隐藏加载动画
                myChart.setOption({ //加载数据图表
                    xAxis: {
                        data: times
                    },
                    series: [{
                        // 根据名字对应到相应的系列
                        name: 'pm',
                        data: numsPm
                    },{
                        // 根据名字对应到相应的系列
                        name: 'no',
                        data: numsNo
                    },{
                        // 根据名字对应到相应的系列
                        name: 'co',
                        data: numsCo
                    },{
                        // 根据名字对应到相应的系列
                        name: 'hc',
                        data: numsHc
                    }]
                });
            } else {
                alert("后台数据获取失败!");
            }
        },
        error: function (errorMsg) {
            //请求失败时执行该函数
            alert("图表请求数据失败!");
            myChart.hideLoading();
        }
    })

    myChart.setOption(option);
</script>
</body>
</html>