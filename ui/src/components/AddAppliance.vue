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
                <form @submit.prevent="addAddresses">
                    <div v-for="(appliance, index) in appliances" :key="index" class="address mb-3">
                    <h5 class="mb-3">Address {{ index + 1 }}</h5>
                    <div class="mb-3">
                            <label for="state" class="form-label">Name:</label>
                            <select class="form-control" v-model="address.state">
                            <option value="">Select Appliance</option>
                            <option v-for="(appliance, id) in masterAppliances" :key="id" :value="appliance.ID" >{{ appliance.name }}</option>
                            </select>
                    </div>
                    <div class="mb-3">
                        <label for="address-line-2" class="form-label">Count:</label>
                        <input type="number" class="form-control" id="address-line-2" v-model="appliance.count">
                    </div>
                    <div class="row">
                        <div class="col-md-6">
                        <div class="mb-3">
                            <label for="city" class="form-label">No of Hours Used:</label>
                            <input type="text" class="form-control" id="city" v-model="address.city" required>
                        </div>
                        </div>
                        <div class="col-md-3">
                        <div class="mb-3">
                            <label for="state" class="form-label">No of Days Used:</label>
                            <select class="form-control" v-model="address.state">
                            <option value="">Select State</option>
                            <option v-for="(state, id) in states" :key="id" :value="state.value" >{{ state.name }}</option>
                            </select>
                        </div>
                        </div>
                        <div class="col-md-3">
                        <div class="mb-3">
                            <label for="zipcode" class="form-label">Zip Code:</label>
                            <input type="text" class="form-control" id="zipcode" v-model="address.zipcode" required>
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
            addressID: this.$route.params.addressID,
            masterAppliances: [],
            appliances: []
        }
    },
    beforeCreate(){
    },  
    mounted(){
        this.getAllMasterAppliances();
        // this.
    },
    methods: {
        getAllMasterAppliances(){
        this.$userHttp.post('/get-all-appliances', {
         
        }).then((resp) => {
            console.log(resp.data.Data);
          this.masterAppliances = resp.data.Data;
        })
        }
    }
}
</script>

<style>

</style>