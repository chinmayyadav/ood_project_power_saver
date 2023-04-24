<template>
    <div class="container" style="align-items: start;">
      <div class="row justify-content-center mt-5">
        <div class="col-lg-8 col-md-10 col-sm-12">
          <div class="card">
            <div class="card-header bg-primary text-white">
              <h4 class="card-title mb-0">Add Addresses

                <button class="btn btn-danger ml-auto" style="float: right;" @click="$router.push({name: 'UserAddresses'})">
                  View All
                </button>
              </h4>
            </div>
            <div class="card-body">
              <form @submit.prevent="addAddresses">
                <div v-for="(address, index) in addresses" :key="index" class="address mb-3">
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
                <!-- <button type="button" class="btn btn-primary mb-3" @click="addAddress">Add Address</button> -->
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
    data() {
      return {
        addresses: [{
          addressLine1: "",
          addressLine2: "",
          city: "",
          state: "",
          zipcode: ""
        }],
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
  { name: 'South Carolina (SC)', value: 'South Carolina (SC)' },]
      };
    },
    methods: {
      addAddress() {
        this.addresses.push({
          addressLine1: "",
          addressLine2: "",
          city: "",
          state: "",
          zipcode: ""
        });
      },
      addAddresses() {
        // Code to save the addresses
        console.log(this.addresses);
        this.$Swal.fire({
          title: "Are you sure?",
          text: "You want to save this address?",
          icon: "question",
          showCancelButton: true
        }).then((result) => {
          if(result.value){
            this.addresses[0].userID = this.$session.get('UserID'),
            this.$userHttp.post('/save-address-for-user', 
            this.addresses[0]
            ).then((response) => {
              if(response.data.Status == "Success"){
                this.$Swal.fire({
                  text: "Your address was saved! ",
                  title: "Success",
                  icon: "success"
                });
                this.clearFields();
              } else {
                this.$Swal.fire({
                  text: "Some error occurred! Please Try Again.",
                  icon: "error"
                })
              }
              console.log(response.data);
            }).catch((err) => {
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
        this.addresses = [{
          addressLine1: "",
          addressLine2: "",
          city: "",
          state: "",
          zipcode: ""
        }]
      }
    }
  };
  </script>
  
  <style scoped>
.container {
  height: 100vh;
  display: flex;
  justify-content: center;
  align-items: center;
}

.card {
  border-radius: 10px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
}

.card-header {
  border-radius: 10px 10px 0 0;
}

.card-body {
  padding: 30px;
}

.address {
  border: 1px solid #ccc;
  padding: 20px;
  border-radius: 10px;
}

.btn-primary {
  background-color: #007bff;
  border-color: #007bff;
}

.btn-primary:hover {
  background-color: #0069d9;
  border-color: #0062cc;
}

.btn-success {
  background-color: #28a745;
  border-color: #28a745;
}

.btn-success:hover {
  background-color: #218838;
  border-color: #1e7e34;
}

.form-label {
  font-weight: 600;
}

.form-control:focus {
  box-shadow: none;
}

@media screen and (max-width: 576px) {
  .card {
    width: 100%;
    border-radius: 0;
    box-shadow: none;
  }
}
</style>