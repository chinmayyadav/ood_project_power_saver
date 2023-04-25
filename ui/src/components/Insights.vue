<template>
   <div class="container">
      <div class="card">
        <div class="card-header bg-secondary text-white">
          <h4>Welcome to Insights!!
            <!-- <button class="btn btn-danger ml-auto" style="float: right;" @click="$router.push({name: 'AddAddresses'})">
            Add Address
          </button> -->
          </h4>
         
        </div>
        <!-- <div class="card-body">
          
          <table class="table table-striped">
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
          </table>
        </div> -->
      </div>
    </div>
</template>

<script>
export default {

}
</script>

<style>

</style>