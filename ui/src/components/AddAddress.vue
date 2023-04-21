<template>
    <div class="container">
      <div class="row justify-content-center mt-5">
        <div class="col-lg-8 col-md-10 col-sm-12">
          <div class="card">
            <div class="card-header bg-primary text-white">
              <h4 class="card-title mb-0">Add Addresses</h4>
            </div>
            <div class="card-body">
              <form @submit.prevent="addAddresses">
                <div v-for="(address, index) in addresses" :key="index" class="address mb-3">
                  <h5 class="mb-3">Address {{ index + 1 }}</h5>
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
                        <input type="text" class="form-control" id="state" v-model="address.state" required>
                      </div>
                    </div>
                    <div class="col-md-3">
                      <div class="mb-3">
                        <label for="zipcode" class="form-label">Zip Code:</label>
                        <input type="text" class="form-control" id="zipcode" v-model="address.zipcode" required>
                      </div>
                    </div>
                  </div>
                  <hr>
                </div>
                <button type="button" class="btn btn-primary mb-3" @click="addAddress">Add Address</button>
                <button type="submit" class="btn btn-success">Save Addresses</button>
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
        }]
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