<template>
    <div class="container">
      <div class="card">
        <div class="card-header bg-primary text-white">
          <h4>Your Saved Addresses                                                                                                                                  x
            <button class="btn btn-danger ml-auto" style="float: right;" @click="$router.push({name: 'AddAddresses'})">
            Add Address
          </button>
          </h4>
         
        </div>
        <div class="card-body">
          
          <br><br>
          <table class="table table-striped">
            <thead>
              <tr>
                <th>Address Line 1</th>
                <th>Address Line 2</th>
                <th>City</th>
                <th>State</th>
                <th>Zipcode</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="(address, index) in addresses" :key="index">
                <td>{{ address.addressLine1 }}</td>
                <td>{{ address.addressLine2 }}</td>
                <td>{{ address.city }}</td>
                <td>{{ address.state }}</td>
                <td>{{ address.zipcode }}</td>
              </tr>
            </tbody>
          </table>
        </div>
      </div>
  
        <div class="modal fade" id="addAddressModal" tabindex="-1" role="dialog" aria-labelledby="addAddressModalLabel"
           aria-hidden="true">
        <div class="modal-dialog" role="document">
          <div class="modal-content">
            <div class="modal-header bg-primary text-white">
              <h5 class="modal-title" id="addAddressModalLabel">Add Address</h5>
              <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                <span aria-hidden="true">&times;</span>
              </button>
            </div>
            <div class="modal-body">
              <div v-for="(address, index) in addresses" :key="index" class="address">
                <h5>Address {{ index + 1 }}</h5>
                <div class="form-group">
                  <label for="addressLine1">Address Line 1:</label>
                  <input type="text" class="form-control" id="addressLine1" v-model="address.addressLine1">
                </div>
                <div class="form-group">
                  <label for="addressLine2">Address Line 2:</label>
                  <input type="text" class="form-control" id="addressLine2" v-model="address.addressLine2">
                </div>
                <div class="form-group">
                  <label for="city">City:</label>
                  <input type="text" class="form-control" id="city" v-model="address.city">
                </div>
                <div class="form-group">
                  <label for="state">State:</label>
                  <input type="text" class="form-control" id="state" v-model="address.state">
                </div>
                <div class="form-group">
                  <label for="zipcode">Zipcode:</label>
                  <input type="text" class="form-control" id="zipcode" v-model="address.zipcode">
                </div>
              </div>
            </div>
            <div class="modal-footer">
              <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
              <button type="button" class="btn btn-primary" @click="addAddress">Save</button>
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
      addresses: [
        {
          addressLine1: '',
          addressLine2: '',
          city: '',
          state: '',
          zipcode: ''
        }
      ]
    }
  },
  methods: {
    addAddress() {
      this.addresses.push({
        addressLine1: '',
        addressLine2: '',
        city: '',
        state: '',
        zipcode: ''
      });
      $('#addAddressModal').modal('hide');
    }
  }
}
</script>
  
  <style scoped>
  /* .container {
    height: 100vh;
    display: flex;
    justify-content: center;
    align-items: center;
  }
  
  .card {
    border-radius: 10px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0)
  } */
  </style>  