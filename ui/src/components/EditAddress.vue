<template>
  <div class="container" style="align-items: start;">
      <div class="row justify-content-center mt-5">
        <div class="col-lg-8 col-md-10 col-sm-12">
          <div class="card">
            <div class="card-header bg-primary text-white">
              <h4 class="card-title mb-0">Edit Address

                <button class="btn btn-danger ml-auto" style="float: right;" @click="$router.push({name: 'UserAddresses'})">
                  Go Back
                </button>
              </h4>
            </div>
            <div class="card-body">
              <form @submit.prevent="editAddress">
                <!-- <div v-for="(address, index) in addresses" :key="index" class="address mb-3"> -->
                  <!-- <h5 class="mb-3">Address {{ index + 1 }}</h5> -->
                  <div class="mb-3">
                    <label for="address-line-1" class="form-label">Address Line 1:</label>
                    <input type="text" class="form-control" id="address-line-1" v-model="address.addressLine1" required>
                  </div>
                  <div class="mb-3">
                    <label for="address-line-2" class="form-label">Address Line 2:</label>
                    <input type="text" class="form-control" id="address-line-2" v-model="address.addressLine2">
                  </div>
                  <div class="row">
                    <div class="col-md-6">
                      <div class="mb-3">
                        <label for="city" class="form-label">City:</label>
                        <input type="text" class="form-control" id="city" v-model="address.city" required>
                      </div>
                    </div>
                    <div class="col-md-3">
                      <div class="mb-3">
                        <label for="state" class="form-label">State:</label>
                        <select class="form-control" v-model="address.state">
                          <option value="">Select State</option>
                          <option v-for="(state, id) in states" :key="id" :value="state.state" >{{ state.state }}</option>
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
                <!-- </div> -->
                <!-- <button type="button" class="btn btn-primary mb-3" @click="addAddress">Add Address</button> -->
                <button type="submit" class="btn btn-success">Edit Address</button>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
</template>

<script>
export default {
    name: "EditAddress",
    data(){
        return {
            addressID: null,
            address: {
                addressID: null,
                addressLine1: null,
                addressLine2: null,
                city: null,
                state: null,
                zipcode: null
            }, 
            states: [
  { name: 'Alabama (AL)', value: 'Alabama (AL)' },
  { name: 'Alaska (AK)', value: 'Alaska (AK)' },
  { name: 'Arizona (AZ)', value: 'Arizona (AZ)' },
  { name: 'Arkansas (AR)', value: 'Arkansas (AR)' },
  { name: 'California (CA)', value: 'California (CA)' },
  { name: 'Colorado (CO)', value: 'Colorado (CO)' },
  { name: 'Connecticut (CT)', value: 'Connecticut (CT)' },
  { name: 'Delaware (DE)', value: 'Delaware (DE)' },
  { name: 'Florida (FL)', value: 'Florida (FL)' },
  { name: 'Georgia (GA)', value: 'Georgia (GA)' },
  { name: 'Hawaii (HI)', value: 'Hawaii (HI)' },
  { name: 'Idaho (ID)', value: 'Idaho (ID)' },
  { name: 'Illinois (IL)', value: 'Illinois (IL)' },
  { name: 'Indiana (IN)', value: 'Indiana (IN)' },
  { name: 'Iowa (IA)', value: 'Iowa (IA)' },
  { name: 'Kansas (KS)', value: 'Kansas (KS)' },
  { name: 'Kentucky (KY)', value: 'Kentucky (KY)' },
  { name: 'Louisiana (LA)', value: 'Louisiana (LA)' },
  { name: 'Maine (ME)', value: 'Maine (ME)' },
  { name: 'Maryland (MD)', value: 'Maryland (MD)' },
  { name: 'Massachusetts (MA)', value: 'Massachusetts (MA)' },
  { name: 'Michigan (MI)', value: 'Michigan (MI)' },
  { name: 'Minnesota (MN)', value: 'Minnesota (MN)' },
  { name: 'Mississippi (MS)', value: 'Mississippi (MS)' },
  { name: 'Missouri (MO)', value: 'Missouri (MO)' },
  { name: 'Montana (MT)', value: 'Montana (MT)' },
  { name: 'Nebraska (NE)', value: 'Nebraska (NE)' },
  { name: 'Nevada (NV)', value: 'Nevada (NV)' },
  { name: 'New Hampshire (NH)', value: 'New Hampshire (NH)' },
  { name: 'New Jersey (NJ)', value: 'New Jersey (NJ)' },
  { name: 'New Mexico (NM)', value: 'New Mexico (NM)' },
  { name: 'New York (NY)', value: 'New York (NY)' },
  { name: 'North Carolina (NC)', value: 'North Carolina (NC)' },
  { name: 'North Dakota (ND)', value: 'North Dakota (ND)' },
  { name: 'Ohio (OH)', value: 'Ohio (OH)' },
  { name: 'Oklahoma (OK)', value: 'Oklahoma (OK)' },
  { name: 'Oregon (OR)', value: 'Oregon (OR)' },
  { name: 'Pennsylvania (PA)', value: 'Pennsylvania (PA)' },
  { name: 'Rhode Island (RI)', value: 'Rhode Island (RI)' },
              { name: 'South Carolina (SC)', value: 'South Carolina (SC)' },
            ]
        }
    },
    mounted() {
      console.log(this.$route.params.addressID);
        this.addressID = this.$route.params.addressID;
        this.address["addressID"] = this.$route.params.addressID;
        this.getAddressDetails();
        
    },
    methods: {
        getAddressDetails(){
            this.$userHttp.post(
                '/get-addresses-by-id', 
                {
                    addressID: this.addressID
                }
            ).then((response) => {
                console.log(response.data);
                if (response.data.Data.length > 0){
                  this.address = response.data.Data[0];
                  this.address["addressID"] = this.addressID;
                }
            })
        },
        editAddress() {
          console.log(this.address);
          this.$Swal.fire({
          title: "Are you sure?",
          text: "You want to save this address?",
          icon: "question",
          showCancelButton: true
        }).then((result) => {
            if(result.value){
              this.$userHttp.post('/edit-address-for-user', this.address)
              .then((response) => {
                  console.log(response.data);
                  if(response.data.Status === "Success"){
                    this.$Swal.fire({
                      text: "Your address was saved! ",
                      title: "Success",
                      icon: "success",
                      timer: 5000,
                      allowOutsideClick: false
                    }).then((result) =>{
                      this.$router.go(-1);
                    });
                    // this.clearFields();
                  } else {
                    this.$Swal.fire({
                      text: "Some error occurred! Please Try Again.",
                      icon: "error"
                    })
                  }
              }).catch((err) => {
                  console.log("err", err);
              })
            }
          })
        } 
    }
}
</script>

<style>

</style>