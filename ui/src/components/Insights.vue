<template>
   <div class="container">
      <div class="card">
        <div class="card-header bg-secondary text-white">
          <h4 class="d-flex">Welcome to Insights!!
            <button class="btn btn-primary ml-auto mr-2"  @click="showEnergyInsights=false;showAppliancesInsights=true;">
            Appliances Insights
          </button> 
            <button class="btn btn-primary ml-2" @click="showEnergyInsights=true;showAppliancesInsights=false;">
            Energy Insights
          </button>
          
          </h4>
         
        </div>
        <!-- <div class="card-body" v-show="showEnergyInsights"> -->
        <!-- <div class="card-body" >
            <div class="row">
            <div class="col-sm-6" style="height: 400px;">
                <div id="energy_insights" style="width: 100%; height: 100%;"></div> 
            </div>
            <div class="col-sm-6" style=" margin-top: auto;
    margin-bottom: auto;">
               <h4>You use {{ ((35-5) / 100) * 100 }}% less energy than your neighbours. </h4> 
            </div>
        </div>
        
          
        </div> -->
        <!-- <div class="card-body" v-show="showAppliancesInsights"> -->
            <template v-if="showEnergyInsights">
                <energy-insights></energy-insights>
            </template>
            <template v-if="showAppliancesInsights">
                <appliance-insights></appliance-insights>
            </template>
        <!-- <div class="card-body" >
            <div class="row">
            <div class="col-sm-6" style="height: 400px;">
                <div id="appliance_insights" style="width: 100%; height: 100%;"></div> 
            </div>
            <div class="col-sm-6" style=" margin-top: auto;
    margin-bottom: auto;">
               <h4>Herein you can see how much you your usage of appliances compare with those in your neighbourhood. </h4> 
            </div>
        </div> -->
          
          <!-- <table class="table table-striped">
            <thead>
              <tr>
                <th>Address Line 1</th>
                <th>Address Line 2</th>
                <th>City</th>
                <th>State</th>
                <th>Zipcode</th>
                <th style="text-align: center;">Actions</th>
              </tr>
            </thead>
            <tbody v-if="addresses.length > 0">
              <tr v-for="(address, index) in addresses" :key="index">
                <td>{{ address.AddressLine1 }}</td>
                <td>{{ address.AddressLine2 }}</td>
                <td>{{ address.City }}</td>
                <td>{{ address.State }}</td>
                <td>{{ address.ZipCode }}</td>
                <td style="text-align: center;">
                  <span style="cursor: pointer;" title="Delete Your Address" @click="deleteAddress(address)" class="mdi mdi-delete"></span>
                  <span style="cursor: pointer;" title="Edit Your Address" @click="$router.push({name: 'EditAddress', params: {addressID: address.AddressID}})" class="mdi mdi-home-edit-outline"></span>
                  <button class="btn btn-danger ml-auto" style="float: right;" @click="$router.push({name: 'ViewAppliancesPerAddress', params: {addressID: address.AddressID}})">
                    View Appliances
                  </button>
                </td>
              </tr>
            </tbody>
            <tbody v-else>
              <tr>
                <td colspan="5" style="text-align: center;">
                  It seems that you dont have any addresses added! Please add an address to continue.
                </td>
              </tr>
            </tbody>
          </table> -->
        <!-- </div> -->
      </div>
    </div>
</template>

<script>
import * as echarts from 'echarts';
import ApplianceInsights from './ApplianceInsights.vue';
import EnergyInsights from './EnergyInsights.vue';
export default {
  components: { ApplianceInsights, EnergyInsights },
    name: "Insights",
    data(){
        return {
            showEnergyInsights: true,
            showAppliancesInsights: false,
            appliancesInsights: null,
        }
    },
    mounted() {
        this.getEnergyInsights();
        this.getAppliancesInsights();
        // var myChart = echarts.init(document.getElementById('energy_insights'));
        // myChart.setOption({
        // title: {
        //     text: 'Your comparison with your neighbour of energy usage'
        // },
        // tooltip: {},
        // xAxis: {
        //     data: ['You', 'Your Neighbours']
        // },
        // yAxis: {},
        // series: [
        //     {
        //     name: 'sales',
        //     type: 'bar',
        //     data: [5, 35]
        //     }
        // ]
        // });

        // this.appliancesInsights = {"Appliances":[{"UserDailyHoursUsage":38,"ApplianceName":"heater","NeighboursNoOfDays":15,"UserMonthlyHoursUsage":494,"NeighboursMonthlyHoursUsage":15,"ApplianceRating":4,"NeighboursDailyHoursUsage":1,"UserNoOfDays":13},{"UserDailyHoursUsage":19,"ApplianceName":"oven","NeighboursNoOfDays":4,"UserMonthlyHoursUsage":304,"NeighboursMonthlyHoursUsage":48,"ApplianceRating":5,"NeighboursDailyHoursUsage":12,"UserNoOfDays":16},{"UserDailyHoursUsage":34,"ApplianceName":"refrigerator","NeighboursNoOfDays":11,"UserMonthlyHoursUsage":340,"NeighboursMonthlyHoursUsage":264,"ApplianceRating":3,"NeighboursDailyHoursUsage":24,"UserNoOfDays":10},{"UserDailyHoursUsage":14,"ApplianceName":"air conditioner","NeighboursNoOfDays":19,"UserMonthlyHoursUsage":336,"NeighboursMonthlyHoursUsage":361,"ApplianceRating":3,"NeighboursDailyHoursUsage":19,"UserNoOfDays":24},{"UserDailyHoursUsage":6,"ApplianceName":"heater","NeighboursNoOfDays":13,"UserMonthlyHoursUsage":144,"NeighboursMonthlyHoursUsage":208,"ApplianceRating":4,"NeighboursDailyHoursUsage":16,"UserNoOfDays":24},{"UserDailyHoursUsage":12,"ApplianceName":"toaster","NeighboursNoOfDays":22,"UserMonthlyHoursUsage":84,"NeighboursMonthlyHoursUsage":154,"ApplianceRating":2,"NeighboursDailyHoursUsage":7,"UserNoOfDays":7},{"UserDailyHoursUsage":46,"ApplianceName":"heater","NeighboursNoOfDays":14,"UserMonthlyHoursUsage":552,"NeighboursMonthlyHoursUsage":280,"ApplianceRating":1,"NeighboursDailyHoursUsage":20,"UserNoOfDays":12}],"Data":"Success"}
        // this.appliancesInsights = this.appliancesInsights.Appliances;
        // console.log(this.appliancesInsights);


//         var myChart2 = echarts.init(document.getElementById('appliance_insights'));
//         myChart2.setOption({
//         legend: {},
//         tooltip: {},
//         dataset: {
//             source: [
//     [
//         "Appliance",
//         "NeighboursDailyHoursUsage",
//         "NeighboursMonthlyHoursUsage",
//         "YourDailyHoursUsage",
//         "YourMonthlyHoursUsage"
//     ],
//     [
//         "heater",
//         1,
//         15,
//         38,
//         494
//     ],
//     [
//         "oven",
//         12,
//         48,
//         19,
//         304
//     ],
//     [
//         "refrigerator",
//         24,
//         264,
//         34,
//         340
//     ],
//     [
//         "air conditioner",
//         19,
//         361,
//         14,
//         336
//     ],
//     [
//         "heater",
//         16,
//         208,
//         6,
//         144
//     ],
//     [
//         "toaster",
//         7,
//         154,
//         12,
//         84
//     ]
// ]
//         },
//         xAxis: { type: 'category' },
//         yAxis: {},
//         // Declare several bar series, each will be mapped
//         // to a column of dataset.source by default.
//         series: [{ type: 'bar' }, { type: 'bar' }, { type: 'bar' }, { type: 'bar' }]
//         });



    },
    methods: {
        getEnergyInsights(){
            this.$insightsHttp.post('/compare-with-neighbours-energy',{
                userID: this.$session.get('UserID')
            }).then((response) => {
                // console.log(response.data);
            }).catch((e) => {
                console.log("E",e);
            })
        },
        getAppliancesInsights(){
            this.$insightsHttp.post('/compare-with-neighbours-appliances',{
                userID: this.$session.get('UserID')
            }).then((response) => {
                // console.log(response.data);
            }).catch((e) => {
                console.log("E",e);
            })
        }
    }
}
</script>

<style>

</style>