<html>
<link href="https://cdn.bootcss.com/twitter-bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet">
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <h3>
                柴油机最新污染信息
            </h3>
            <table class="table table-bordered">
                <thead>
                <tr>
                    <th>柴油机编号</th>
                    <th>时间</th>
                    <th>颗粒物PM排放</th>
                    <th>氮氧化物NOx排放</th>
                    <th>一氧化碳CO排放</th>
                    <th>碳氢化物HC排放</th>
                    <th>历史信息</th>
                    <th>详细信息</th>
                    <th>地图位置</th>
                </tr>
                </thead>
                <tbody>
                <#list engineLatestVoList.data as engineInfoList>
                    <tr>
                        <td>${engineInfoList.engineId}</td>
                        <td>${engineInfoList.time?string('yyyy-MM-dd HH:mm:ss')}</td>
                        <#if engineInfoList.pm gt 1.8>
                        <td style="color: red">${engineInfoList.pm}</td>
                        <#else>
                        <td>${engineInfoList.pm}</td>
                        </#if>
                        <td>${engineInfoList.no}</td>
                        <td>${engineInfoList.co}</td>
                        <td>${engineInfoList.hc}</td>
                        <td><a href="/car/history?carId=${engineInfoList.carId}">查看历史信息</a></td>
                        <td><a href="/car/detail?carId=${engineInfoList.carId}
                        &time=${engineInfoList.time?string('yyyy-MM-dd HH:mm:ss')}
                        &longitude=${engineInfoList.longitude}
                        &latitude=${engineInfoList.latitude}">查看详细信息</a></td>
                        <td><a href="/car/map?longitude=${engineInfoList.longitude}
                        &latitude=${engineInfoList.latitude}">查看地图位置</a></td>
                    </tr>
                </#list>
                </tbody>
            </table>
        </div>
    </div>
</div>
</html>