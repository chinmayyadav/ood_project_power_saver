<template>
        <div class="card-body" >
            <div class="row">
            <div class="col-sm-6" style="height: 400px;">
                <div id="appliance_insights" style="width: 100%; height: 100%;"></div> 
            </div>
            <div class="col-sm-6" style=" margin-top: auto;
    margin-bottom: auto;">
               <h4>Herein you can see how much you your usage of appliances compare with those in your neighbourhood. </h4> 
            </div>
        </div>
      </div>
</template>

<script>
import * as echarts from 'echarts';
export default {
    name: "ApplianceInsights",
    mounted(){
        var myChart2 = echarts.init(document.getElementById('appliance_insights'));
        myChart2.setOption({
        legend: {},
        tooltip: {},
        dataset: {
            source: [
    [
        "Appliance",
        "NeighboursDailyHoursUsage",
        "NeighboursMonthlyHoursUsage",
        "YourDailyHoursUsage",
        "YourMonthlyHoursUsage"
    ],
    [
        "heater",
        1,
        15,
        38,
        494
    ],
    [
        "oven",
        12,
        48,
        19,
        304
    ],
    [
        "refrigerator",
        24,
        264,
        34,
        340
    ],
    [
        "air conditioner",
        19,
        361,
        14,
        336
    ],
    [
        "heater",
        16,
        208,
        6,
        144
    ],
    [
        "toaster",
        7,
        154,
        12,
        84
    ]
]
        },
        xAxis: { type: 'category' },
        yAxis: {},
        // Declare several bar series, each will be mapped
        // to a column of dataset.source by default.
        series: [{ type: 'bar' }, { type: 'bar' }, { type: 'bar' }, { type: 'bar' }]
        });

    }
}
</script>

<style>

</style>