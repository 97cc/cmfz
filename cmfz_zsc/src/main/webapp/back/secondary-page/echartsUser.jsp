<%@page isELIgnored="false" contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set value="${pageContext.request.contextPath}" var="path"></c:set>


<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
<div class="row">
    <div id="main" class="col-lg-6" style="width: 600px;height:400px;"></div>
    <br/>
    <div id="china" class="col-lg-6" style="width: 600px;height: 400px;"></div>
</div>

<script type="text/javascript">

    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('main'));





    //用户数量
    $.ajax({
        url:'${path}/User/resultUser',
        type:'get',
        datatype:'json',
        success:function (result) {
            //alert(result)
            //console.log(result);
            var option = {
                series: [{

                    data: [result.week0,result.week1,result.week2]
                }]
            }
            myChart.setOption(option);
        }
    });


    // 指定图表的配置项和数据
    var option = {
        title: {
            text: '用户注册量条形图',
            textStyle:{
                color:'#95ff76',
                fontStyle:'italic',
                fontWeight:'bold'
            },

        },
        tooltip: {},
        legend: {
            data:['用户注册量']
        },
        xAxis: {
            data: ["第一周","第二周","第三周"]
        },
        yAxis: {},
        series: [{
            name: '用户注册量',
            type: 'bar',
            //data: [5, 20, 36, 10, 10, 20]
        }]
    };
    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);


//////////////////////////////////////////////////////////////////////////////////////////////////////////
    // 基于准备好的dom，初始化echarts实例
    var myChina = echarts.init(document.getElementById('china'));

    $.ajax({
        url:'${path}/User/userCount',
        type:'post',
        datatype:'json',
        success:function (result) {
           // alert(result)
            console.log(result);
            var nen = result.nen;
            console.log(nen);
            var female = result.female;
            console.log(female);
            var myOption = {
                title: {
                    text: '持明法洲信仰用户省份信息展示',
                    subtext: '2017年6月15日 最新数据',
                    left: 'center'
                },
                tooltip: {},
                // 说明
                legend: {
                    orient: 'vertical',
                    left: 'left',
                    data: ['男', '女']
                },
                visualMap: {
                    min: 0,
                    max: 30,
                    left: 'left',
                    top: 'bottom',
                    text: ['高', '低'],           // 文本，默认为数值文本
                    calculable: true
                },
                // 工具箱
                toolbox: {
                    show: true,
                    orient: 'vertical',
                    left: 'right',
                    top: 'center',
                    feature: {
                        dataView: {readOnly: false},
                        restore: {},
                        saveAsImage: {}
                    }
                },
                series: [
                    {
                        name: '男',
                        type: 'map',
                        mapType: 'china',
                        roam: false,
                        label: {
                            normal: {
                                show: true
                            },
                            emphasis: {
                                show: true
                            }
                        },
                        data: result.nen
                    },
                    {
                        name: '女',
                        type: 'map',
                        mapType: 'china',
                        label: {
                            normal: {
                                show: true
                            },
                            emphasis: {
                                show: true
                            }
                        },
                        data: result.female
                    }
                ]
            };
            myChina.setOption(myOption);
        }
    });

///////////////////////////////////////////////////////////////////////////////////////////////////////


   //长连接
    var goEasy = new GoEasy({
        host:'hangzhou.goeasy.io', //应用所在的区域地址: 【hangzhou.goeasy.io |singapore.goeasy.io】
        appkey: "BC-5c87e20b04854bd590eb273711322ba1", //替换为您的应用appkey
    });
    //监听添加操作
    goEasy.subscribe({
        channel: "my_cmfz", //替换为您自己的channel
        onMessage: function (message) {
            $.ajax({
                url:'${past}/User/resultUser',
                type:'get',
                datatype:'json',
                success:function (result) {
                    var option = {
                        series: [{
                            data: [result.week0,result.week1,result.week2]
                        }]
                    }
                    myChart.setOption(option);
                }
            })
        }
    });

</script>

