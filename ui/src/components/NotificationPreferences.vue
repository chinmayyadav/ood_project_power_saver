<template>
  <div class="container">
    <div class="card">
        <div class="card-header bg-primary text-white">
          <h4>Your Notifcation Preferences
            <button class="btn btn-danger ml-auto" style="float: right;" @click="savePreferences">
                Save All
            </button>
          </h4>
         
        </div>
        <div class="card-body">
          
          <table class="table table-striped">
            <thead>
              <tr>
                <th>#</th>
                <th>Address Line 1</th>
                <th>Address Line 2</th>
                <th>City</th>
                <th>State</th>
                <th>Zipcode</th>
                <th>EmailNotification</th>
                <th>PhoneNotification</th>
                <th>ServicePhoneCalls</th>
              </tr>
            </thead>
            <tbody v-if="showPreferences">
              <tr v-for="(address, index) in preferences" :key="index">
                <td>{{ index + 1 }}</td>
                <td>{{ address.addressLine1 }}</td>
                <td>{{ address.addressLine2 }}</td>
                <td>{{ address.city }}</td>
                <td>{{ address.state }}</td>
                <td>{{ address.zipcode }}</td>
                <td @click="address.EmailNotification=!address.EmailNotification">{{ address.EmailNotification }}</td>
                <td @click="address.PhoneNotification=!address.PhoneNotification">{{ address.PhoneNotification }}</td>
                <td @click="address.ServicePhoneCalls=!address.ServicePhoneCalls">{{ address.ServicePhoneCalls }}</td>
                <!-- <td style="text-align: center;">
                  <span style="cursor: pointer;" title="Delete Your Address" @click="deleteAddress(address)" class="mdi mdi-delete"></span>
                  <span style="cursor: pointer;" title="Edit Your Address" @click="$router.push({name: 'EditAddress', params: {addressID: address.AddressID}})" class="mdi mdi-home-edit-outline"></span>
                  <button class="btn btn-danger ml-auto" style="float: right;" @click="$router.push({name: 'ViewAppliancesPerAddress', params: {addressID: address.AddressID}})">
                    View Appliances
                  </button>
                </td> -->
              </tr>
            </tbody>
            <!-- <tbody v-else>
              <tr>
                <td colspan="5" style="text-align: center;">
                  It seems that you dont have any addresses added! Please add an address to continue.
                </td>
              </tr>
            </tbody> -->
          </table>
        </div>
      </div>
  </div>
</template>

<script>
export default {
    name: "NotificationPreferences",
    data() {
        return {
            preferences: [],
            showPreferences: false
        }
    },
    mounted() {
        this.getPreferences();
    },
    methods: {
        getPreferences() {
            this.$userHttp.post('/get-notification-preference', {
                userID: this.$session.get('UserID')
            }).then((response) => {
                console.log(response.data);
                this.preferences = response.data.Data;
                for (let i = 0; i < this.preferences.length; i++) {
                        this.$userHttp.post(
                    '/get-addresses-by-id', 
                    {
                        addressID: this.preferences[i].addressID
                    }
                ).then((response) => {
                    if (response.data.Data.length > 0){
                    let address = response.data.Data[0];
                    for(let key in address){
                        this.preferences[i][key] = address[key]
                    }
                    // this.address["addressID"] = this.addressID;
                    }
                    console.log(this.preferences);
                    this.showPreferences = true;
                })
                    
                }
                
                
            })
        },
        savePreferences(address){
        this.$Swal.fire({
          title: "Are you sure?",
          text: "You want to save these preferences?",
          icon: "question",
          showCancelButton: true
        }).then((result) => {
          if(result.value){
            // this.addresses[0].userID = this.$session.get('UserID'),
            this.$userHttp.post('/save-notification-preference', 
            address
            ).then((response) => {
              if(response.data.Status == "Success"){
                this.$Swal.fire({
                  text: "Your preference was saved! ",
                  title: "Success",
                  icon: "success"
                });
                this.getPreferences();
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
        }
    }
    
}
</script>

<style>

</style>