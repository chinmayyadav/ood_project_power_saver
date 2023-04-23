<template>
<div class="container" style="align-items: start;">
      <div class="row justify-content-center mt-5">
        <div class="col-lg-8 col-md-10 col-sm-12">
          <div class="card">
            <div class="card-header bg-primary text-white">
              <h4 class="card-title mb-0">Add Appliance

                <button class="btn btn-danger ml-2" style="float: right;" @click="$router.push({name: 'UserAddresses'})">
                  View All
                </button>
                <button class="btn btn-success ml-2" style="float: right;" @click="$router.go(-1)">
                    Go Back
                </button>
              </h4>
            </div>
            <div class="card-body">
                <form @submit.prevent="addApplianceForAddress">
                    <div v-for="(appliance, index) in appliances" :key="index" class="address mb-3">
                    <h5 class="mb-3">Appliance {{ index + 1 }}</h5>
                    <div class="mb-3">
                            <label for="state" class="form-label">Name:</label>
                            <select class="form-control" v-model="appliance.applianceID">
                            <option value="">Select Appliance</option>
                            <option v-for="(mast_appliance, id) in masterAppliances" :key="id" :value="mast_appliance.id" >{{ mast_appliance.name }}</option>
                            </select>
                    </div>
                    <div class="mb-3">
                        <label for="address-line-2" class="form-label">Count:</label>
                        <input type="number" min="1" class="form-control" id="address-line-2" v-model="appliance.count" required>
                    </div>
                    <div class="row">
                        <div class="col-md-6">
                        <div class="mb-3">
                            <label for="city" class="form-label">No of Hours Used:</label>
                            <input type="number" min="1" max="24" class="form-control" id="city" v-model="appliance.hours" required>
                        </div>
                        </div>
                        <div class="col-md-6">
                        <div class="mb-3">
                            <label for="zipcode" class="form-label">No of Days Used:</label>
                            <input type="number" min="1" max="30" class="form-control" id="zipcode" v-model="appliance.days" required>
                        </div>
                        </div>
                    </div>
                    </div>
                    <button type="submit" class="btn btn-success">Save Address</button>
                </form>
            </div>
          </div>
        </div>
      </div>
    </div>
</template>

<script>
export default {
    name: "AddAppliance",
    data(){
        return {
            addressID: null,
            masterAppliances: [],
            appliances: [{
                applianceID: Number,
                count: Number,
                hours: Number,
                days: Number
            }]
        }
    },
    beforeCreate(){
    },  
    mounted(){
        this.$session.set('CurrentAddressID', this.$route.params.addressID);
        this.addressID = this.$route.params.addressID;
        this.getAllMasterAppliances();
        // this.
    },
    methods: {
        getAllMasterAppliances(){
            this.$userHttp.post('/get-all-appliances', {
            }).then((resp) => {
                this.masterAppliances = resp.data.Data;
            })
        },
        addApplianceForAddress(){
            console.log(this.appliances);
            let appliance = this.appliances[0];
            appliance.addID = this.addressID;
            console.log(appliance);
            appliance.days = Number(appliance.days);
            appliance.hours = Number(appliance.hours);
            appliance.count = Number(appliance.count);
            this.$Swal.fire({
                icon: 'question',
                title: 'Are You Sure?',
                text: 'This will link this appliance to your address'
            }).then((result) => {
                if(result.value){
                    this.$userHttp.post('/add-home-appliance', 
                        appliance
                    ).then((response) => {
                        console.log(response.data);

                        if(response.data.Status === 'Success'){
                            this.$Swal.fire({
                            title: "Success",
                            text: "Data Saved!!",
                            icon: "success"
                            })
                            this.clearFields();
                        }

                    }).catch((err) =>{
                        console.log("err", err);
                        this.$Swal.fire({
                            text: "Some error occurred! Please Try Again.",
                            icon: "error"
                        })
                    })
                }
            })
        },
        clearFields(){
            this.appliances = [{
                applianceID: Number,
                count: Number,
                hours: Number,
                days: Number
            }]
        }
    }
}
</script>

<style>

</style>