<template>
  <div class="card-body" >
            <div class="row">
            <div class="col-sm-6" style="height: 400px;">
                <div id="energy_insights" style="width: 100%; height: 100%;"></div> 
            </div>
            <div class="col-sm-6" style=" margin-top: auto;
    margin-bottom: auto;">
               <h4>You use {{ ((35-5) / 100) * 100 }}% less energy than your neighbours. </h4> 
            </div>
        </div>
          
        </div>
</template>

<script>
import * as echarts from 'echarts';
export default {
    name: "EnergyInsights",
    mounted(){
        var myChart = echarts.init(document.getElementById('energy_insights'));
        myChart.setOption({
        title: {
            text: 'Your comparison with your neighbour of energy usage'
        },
        tooltip: {},
        xAxis: {
            data: ['You', 'Your Neighbours']
        },
        yAxis: {},
        series: [
            {
            name: 'sales',
            type: 'bar',
            data: [5, 35]
            }
        ]
        });

        this.appliancesInsights = {"Appliances":[{"UserDailyHoursUsage":38,"ApplianceName":"heater","NeighboursNoOfDays":15,"UserMonthlyHoursUsage":494,"NeighboursMonthlyHoursUsage":15,"ApplianceRating":4,"NeighboursDailyHoursUsage":1,"UserNoOfDays":13},{"UserDailyHoursUsage":19,"ApplianceName":"oven","NeighboursNoOfDays":4,"UserMonthlyHoursUsage":304,"NeighboursMonthlyHoursUsage":48,"ApplianceRating":5,"NeighboursDailyHoursUsage":12,"UserNoOfDays":16},{"UserDailyHoursUsage":34,"ApplianceName":"refrigerator","NeighboursNoOfDays":11,"UserMonthlyHoursUsage":340,"NeighboursMonthlyHoursUsage":264,"ApplianceRating":3,"NeighboursDailyHoursUsage":24,"UserNoOfDays":10},{"UserDailyHoursUsage":14,"ApplianceName":"air conditioner","NeighboursNoOfDays":19,"UserMonthlyHoursUsage":336,"NeighboursMonthlyHoursUsage":361,"ApplianceRating":3,"NeighboursDailyHoursUsage":19,"UserNoOfDays":24},{"UserDailyHoursUsage":6,"ApplianceName":"heater","NeighboursNoOfDays":13,"UserMonthlyHoursUsage":144,"NeighboursMonthlyHoursUsage":208,"ApplianceRating":4,"NeighboursDailyHoursUsage":16,"UserNoOfDays":24},{"UserDailyHoursUsage":12,"ApplianceName":"toaster","NeighboursNoOfDays":22,"UserMonthlyHoursUsage":84,"NeighboursMonthlyHoursUsage":154,"ApplianceRating":2,"NeighboursDailyHoursUsage":7,"UserNoOfDays":7},{"UserDailyHoursUsage":46,"ApplianceName":"heater","NeighboursNoOfDays":14,"UserMonthlyHoursUsage":552,"NeighboursMonthlyHoursUsage":280,"ApplianceRating":1,"NeighboursDailyHoursUsage":20,"UserNoOfDays":12}],"Data":"Success"}
        this.appliancesInsights = this.appliancesInsights.Appliances;
        console.log(this.appliancesInsights);
    }
}
</script>

<style>

</style>