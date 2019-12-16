<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<script type="text/javascript" src="${pageContext.request.contextPath}/back/echarts/echarts.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/back/echarts/china.js"></script>

<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
<div id="china" style="width: 600px;height: 600px;margin-top: 30px;margin-left: 30px">

</div>

<script type="text/javascript">
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('china'));

    function randomData() {
        return Math.round(Math.random() * 10000);
    }

   var option = {
        title: {
            text: 'IPhone手机销量',
            subtext: '2017年6月15日 最新数据',
            left: 'center'
        },
        tooltip: {},
        // 说明
        legend: {
            orient: 'vertical',
            left: 'left',
            data: ['iphone5', 'iphone6']
        },
        visualMap: {
            min: 0,
            max: 30000,
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
                name: 'iphone5',
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
                data: [
                    {name: "安徽", value: randomData()},
                    {name: "吉林", value: randomData()},
                    {name: "辽宁", value: randomData()},
                    {name: "西藏", value: randomData()},
                    {name: "湖南", value: randomData()},
                    {name: "新疆", value: randomData()},
                    {name: "青海", value: randomData()},
                    {name: "甘肃", value: randomData()},
                    {name: "宁夏", value: randomData()},
                    {name: "陕西", value: randomData()},
                    {name: "重庆", value: randomData()},
                    {name: "四川", value: randomData()},
                    {name: "云南", value: randomData()},
                    {name: "广西", value: randomData()},
                    {name: "海南", value: randomData()},
                    {name: "贵州", value: randomData()},
                    {name: "上海", value: randomData()},
                    {name: "重庆", value: randomData()},
                    {name: "湖北", value: randomData()},
                    {name: "江西", value: randomData()},
                    {name: "台湾", value: randomData()},
                    {name: "江西", value: randomData()},
                    {name: "福建", value: randomData()},
                    {name: "江苏", value: randomData()},
                    {name: "浙江", value: randomData()},
                    {name: "河南", value: randomData()},
                    {name: "陕西", value: randomData()},
                    {name: "河北", value: randomData()},
                    {name: "山东", value: randomData()},
                    {name: "山西", value: randomData()},
                    {name: "天津", value: randomData()},
                    {name: "北京", value: randomData()},
                    {name: "广东", value: randomData()},
                    {name: "内蒙古", value: randomData()},
                    {name: "黑龙江", value: randomData()},
                    {name: "南海诸岛", value: randomData()}
                ]
            },
            {
                name: 'iphone6',
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
                data: [
                    {name: "安徽", value: randomData()},
                    {name: "吉林", value: randomData()},
                    {name: "辽宁", value: randomData()},
                    {name: "西藏", value: randomData()},
                    {name: "湖南", value: randomData()},
                    {name: "新疆", value: randomData()},
                    {name: "青海", value: randomData()},
                    {name: "甘肃", value: randomData()},
                    {name: "宁夏", value: randomData()},
                    {name: "陕西", value: randomData()},
                    {name: "重庆", value: randomData()},
                    {name: "四川", value: randomData()},
                    {name: "云南", value: randomData()},
                    {name: "广西", value: randomData()},
                    {name: "海南", value: randomData()},
                    {name: "贵州", value: randomData()},
                    {name: "上海", value: randomData()},
                    {name: "重庆", value: randomData()},
                    {name: "湖北", value: randomData()},
                    {name: "江西", value: randomData()},
                    {name: "台湾", value: randomData()},
                    {name: "江西", value: randomData()},
                    {name: "福建", value: randomData()},
                    {name: "江苏", value: randomData()},
                    {name: "浙江", value: randomData()},
                    {name: "河南", value: randomData()},
                    {name: "陕西", value: randomData()},
                    {name: "河北", value: randomData()},
                    {name: "山东", value: randomData()},
                    {name: "山西", value: randomData()},
                    {name: "天津", value: randomData()},
                    {name: "北京", value: randomData()},
                    {name: "广东", value: randomData()},
                    {name: "内蒙古", value: randomData()},
                    {name: "黑龙江", value: randomData()},
                    {name: "南海诸岛", value: randomData()}
                ]
            }
        ]
    };
    myChart.setOption(option);

</script>












