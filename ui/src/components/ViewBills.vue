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
                <th>Gas Amount</th>
                <th>Electricity Amount</th>
                <th>Payment Status</th>
                <!-- <th style="text-align: center;">Pay</th> -->
              </tr>
            </thead>
            <tbody v-if="bills.length > 0">
              <tr v-for="(bill, index) in bills" :key="index">
                <td>{{ index+1 }}</td>
                <td>{{ formateDate(bill) }}</td>
                <td>${{ bill.GasAmount }}</td>
                <td>${{ bill.ElectricityAmount }}</td>
                <td v-if="!bill.IsPaid">
                  <!-- <span style="cursor: pointer;" title="Delete Your Address" @click="deleteAddress(address)" class="mdi mdi-delete"></span>
                  <span style="cursor: pointer;" title="Edit Your Address" @click="$router.push({name: 'EditAddress', params: {addressID: address.AddressID}})" class="mdi mdi-home-edit-outline"></span> -->
                  <button class="btn btn-danger ml-auto" style="" @click="payBill(bill)">
                    Pay Bill
                  </button>
                </td>
                <td v-else>
                    Paid!!
                </td>
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
import moment from 'moment';

export default {
    name: "ViewBills",
    data(){
        return {
            userID: this.$session.get('UserID'),
            bills: []
        }
    },
    mounted(){
        this.getAllBills();
    },
    methods: {
        getAddresses(){

            this.$userHttp.post('/get-addresses-per-user',{
                userID: this.userID
            }).then((response) => {
                if(response.data.Data.length > 0){
                this.addresses = response.data.Data;
                }
                // console.log(response);
            }).catch((e) => {
                console.log("err", e);
            })
        },
        getAllBills(){
            let now = moment().format('LLLL');
            this.$userHttp.post('/get-all-bills', {
                userID: this.userID
            }).then((response) => {
                if(response.data?.Data?.length>0){
                    this.bills = response.data?.Data;
                }
                // console.log(response.data);
            })
        },
        formateDate(bill){
            return moment(bill.FromDate).format("MMM Do YY") + " to " + moment(bill.ToDate).format("MMM Do YY");
        },
        payBill(bill){
            this.$Swal.fire({
                icon: 'question',
                title: 'Are you sure?',
                text: `This will pay a total amount of ${bill.ElectricityAmount+bill.GasAmount} from your saved payment method.` 
            })
        }
    }
}
</script>

<style>

</style>