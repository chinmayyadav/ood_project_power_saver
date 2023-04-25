<template>
   <div class="container">
      <div class="card">
        <div class="card-header bg-primary text-white">
          <h4>Your Previous Bills
          </h4>
         
        </div>
        <div class="card-body">
          
          <table class="table table-striped">
            <thead>
              <tr>
                <th>#</th>
                <th>Bill Period</th>
                <!-- <th>To Date</th> -->
                <th>Payment Status</th>
                <th style="text-align: center;">Pay</th>
              </tr>
            </thead>
            <tbody v-if="bills.length > 0">
              <tr v-for="(bill, index) in bills" :key="index">
                <td>{{ index+1 }}</td>
                <!-- <td>{{ bill.SumOfElectricityAmount }}</td>
                <td>{{ bill.SumOfGasAmount }}</td>
                <td style="text-align: center;">
                  <span style="cursor: pointer;" title="Delete Your Address" @click="deleteAddress(address)" class="mdi mdi-delete"></span>
                  <span style="cursor: pointer;" title="Edit Your Address" @click="$router.push({name: 'EditAddress', params: {addressID: address.AddressID}})" class="mdi mdi-home-edit-outline"></span>
                  <button class="btn btn-danger ml-auto" style="float: right;" @click="$router.push({name: 'ViewAppliancesPerAddress', params: {addressID: address.AddressID}})">
                    View Appliances
                  </button>
                </td> -->
              </tr>
            </tbody>
            <tbody v-else>
              <tr>
                <td colspan="5" style="text-align: center;">
                  It seems that you dont have any bills! Please check back later.
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

}
</script>

<style>

</style>