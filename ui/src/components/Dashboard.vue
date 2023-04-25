<template>
  <div class="container bg-image">
    <div class="card">
      <!-- <div class="card-header bg-primary text-white">
        <h4>Your Previous Bills</h4>
      </div> -->
      <div class="card-body">
        <h3>Welcome {{ $session.get('FirstName') }} {{ $session.get('LastName') }}!! 
        </h3>
        <!-- <unpaid-bills></unpaid-bills> -->
        <hr>
        <div class="row">
          <div class="col-sm-12 card">
              <h5>It seems you have <strong> <em> {{ unpaidBillCount }} </em> </strong> bill payments pending!!
              <button style="float: right;" class="btn btn-warning ml-auto" @click="$router.push({name:'UnpaidBills'})">Pay Now!!</button>
            </h5>
          </div>
          <div class="col-sm-12 mt-5 card">
              <h5>You have <strong> <em> {{ totalAddressesCount }} </em> </strong> addresses registered with us!
              <button style="float: right;" class="btn btn-warning ml-auto" @click="$router.push({name:'AddAddresses'})">Add More!!</button>
            </h5>
          </div>
        </div>
        <div class="row">
          <div class="col-sm-12 card mt-5">
            <h5>
              We have some awesome Insights for You!!
              <button style="float: right;" class="btn btn-warning ml-auto" @click="$router.push({name:'Insights'})">Click here to check!!</button>
            </h5>
          </div>
        </div>
      </div>

    </div>
  </div>
</template>

<script>
import UnpaidBills from './UnpaidBills.vue';
export default {
  components: { UnpaidBills },
  name: "Dashboard-Comp",
  data() {
    return {
      bills: [],
      unpaidBillCount: null,
      userID: this.$session.get('UserID'),
      totalAddressesCount: null,
      addresses: [],
      
    }
  },
  computed: {
    
  },
  mounted(){
    this.getUnpaidBills();
    this.getTotalAddresses();
  },
  methods: {
    getUnpaidBills(){
        this.$userHttp.post('/get-unpaid-bill', {
            userID: this.userID
        }).then((response) => {
          console.log(response.data);
            if(response.data?.Data?.length>0){
                this.bills = response.data?.Data;
                this.unpaidBillCount = this.bills.length;
            }
            // console.log(response.data);
        })
    },
    getTotalAddresses(){
      this.$userHttp.post('/get-addresses-per-user',{
        userID: this.userID
      }).then((response) => {
        if(response.data.Data.length > 0){
          this.addresses = response.data.Data;
          this.totalAddressesCount = this.addresses.length
        }
      }).catch((e) => {
        console.log("err", e);
      })
    }
  }
  
};
</script>

<style scoped>
.bg-image{
  background-image: url('@/assets/background_image.jpeg');
  background-size: cover;
}
</style>