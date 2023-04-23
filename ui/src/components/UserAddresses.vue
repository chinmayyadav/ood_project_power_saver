<template>
    <div class="container">
      <div class="card">
        <div class="card-header bg-primary text-white">
          <h4>Your Saved Addresses
            <button class="btn btn-danger ml-auto" style="float: right;" @click="$router.push({name: 'AddAddresses'})">
            Add Address
          </button>
          </h4>
         
        </div>
        <div class="card-body">
          
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
            <tbody v-if="addresses.length > 0">
              <tr v-for="(address, index) in addresses" :key="index">
                <td>{{ address.AddressLine1 }}</td>
                <td>{{ address.AddressLine2 }}</td>
                <td>{{ address.City }}</td>
                <td>{{ address.State }}</td>
                <td>{{ address.ZipCode }}</td>
                <td><button class="btn btn-danger ml-auto" style="float: right;" @click="$router.push({name: 'ViewAppliancesPerAddress', params: {addressID: address.AddressID}})">View Appliances</button></td>
              </tr>
            </tbody>
            <tbody v-else>
              <tr>
                <td colspan="5" style="text-align: center;">
                  It seems that you dont have any addresses added! Please add an address to continue.
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
  data() {
    return {
      addresses: []
    }
  },
  mounted(){
    this.getUserAddresses();
  },
  methods: {
    getUserAddresses(){
      let userID = this.$session.get('UserID');
      console.log(userID);
      this.$userHttp.post('/get-addresses-per-user',{
        userID: userID
      }).then((response) => {
        if(response.data.Data.length > 0){
          this.addresses = response.data.Data;
        }
        console.log(response);
      }).catch((e) => {
        console.log("err", e);
      })
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