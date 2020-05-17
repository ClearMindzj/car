<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>统计图表示例</title>
    <script src="https://cdn.bootcss.com/jquery/1.5.1/jquery.min.js"></script>
    <script src="../js/echarts.js"></script>
</head>
<body>
<!-- 为ECharts准备一个具备大小（宽高 -->
<div id="Main" style="width: 600px; height: 400px;border: 1px;"></div>

<script type="text/javascript">
    var myChart = echarts.init(document.getElementById('Main'));
    // 显示标题，图例和空的坐标轴
    myChart.setOption({
        title : {
            text : '统计图表异步数据加载示例'
        },
        tooltip : {
            trigger: 'axis', //坐标轴触发提示框，多用于柱状、折线图中
        },
        legend : {
            data : [ '销量' ]
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
            type : 'category',
            data : []
        },
        yAxis : {},
        series : [ {
            name : '销量',
            type : 'bar',
            data : []
        } ]
    });

    myChart.showLoading(); //数据加载完之前先显示一段简单的loading动画

    var names = [];     //类别数组（实际用来盛放X轴坐标值）
    var nums = [];       //销量数组（实际用来盛放Y坐标值）

    $.ajax({
        type : "get",
        async : true, //异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
        url : "${}",
        dataType : "json",
        success : function(data) {
            //请求成功时执行该函数内容，result即为服务器返回的json对象
            if (data.code == 'success') {
                var obj = eval(data.items);     //解析后台传来的json数据
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
                        name : '销量',
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

