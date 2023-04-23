<template>
  <div class="container">
      <div class="card">
        <div class="card-header bg-primary text-white">
            <h4>Your Saved Appliances for this address
                
                <button class="btn btn-danger ml-2" style="float: right;" @click="$router.push({name: 'AddAppliance', params: {addressID: addressID}})">
                    Add Appliance
                </button>
                <button class="btn btn-success ml-2" style="float: right;" @click="$router.go(-1)">
                    Go Back
                </button>
            </h4>
         
        </div>
        <div class="card-body">
          
          <table class="table table-striped">
            <thead>
              <tr>
                <th>Name</th>
                <th>No Of Days Used in a Month</th>
                <th>No Of Hours Used in a Day</th>
              </tr>
            </thead>
            <tbody v-if="appliances.length > 0">
              <tr v-for="(appliance, index) in appliances" :key="index">
                <td>{{ appliance.ApplianceName }}</td>
                <td>{{ appliance.NoOfDays }}</td>
                <td>{{ appliance.NoOfHours }}</td>
                <!-- <td>{{ appliance. }}</td>
                <td>{{ appliance. }}</td> -->
                <!-- <td><button class="btn btn-danger ml-auto" style="float: right;" @click="$router.push({name: 'ViewAppliancesPerAddress', params: {addressID: address.AddressID}})">View Appliances</button></td> -->
              </tr>
            </tbody>
            <tbody v-else>
              <tr>
                <td colspan="5" style="text-align: center;">
                  It seems that you dont have any appliances added! Please add an appliance to continue.
                </td>
              </tr>
            </tbody>
          </table>
        </div>
      </div>
    </div>
</template>

<script>
export default {
    name: "ViewAppliancesPerAddress",
    data(){
        return {
            addressID: null,
            appliances: []
        }
    },
    mounted(){
        console.log(this.$route.params);
        this.addressID = this.$route.params.addressID;
        this.getAppliancesforAddress();
    },
    methods: {
        getAppliancesforAddress(){
            this.$userHttp.post('/get-appliances-per-address', {
                addressID: this.addressID
            }).then((response) => {
                console.log(response);
                if(response.data?.Data){
                    if(response.data.Data.length > 0) {
                        this.appliances = response.data.Data;
                    }
                }
            }).catch((error) => {
                console.log("error", error);
            })
        }
    }
}
</script>

<style>

</style>