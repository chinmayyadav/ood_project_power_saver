<template>
   <div class="container">
      <div class="card">
        <div class="card-header bg-primary text-white">
          <h4>Your Saved Cards
            <button v-if="cards.length == 0" class="btn btn-danger ml-auto" style="float: right;" @click="$router.push({name: 'add-credit-card'})">
                Add Card
            </button>
          </h4>
         
        </div>
        <div class="card-body">
          
          <table class="table table-striped">
            <thead>
              <tr>
                <th>Card Number</th>
                <th>Delete Card</th>
                <!-- <th>Address Line 2</th>
                <th>City</th>
                <th>State</th>
                <th>Zipcode</th>
                <th style="text-align: center;">Actions</th> -->
              </tr>
            </thead>
            <tbody v-if="cards.length > 0">
              <tr v-for="(card, index) in cards" :key="index">
                <td>{{ card.CardDisplayNumber }}</td>
								<!-- <span style="cursor: pointer;" title="Delete Your Card" @click="deleteCard(card)" class="mdi mdi-delete">
								</span> -->
								<td>
									<button class="btn btn-danger ml-auto"  @click="deleteCard(card)">
									Delete  Card
								</button>
								</td>
								
                <!-- <td>{{ address.AddressLine2 }}</td>
                <td>{{ address.City }}</td>
                <td>{{ address.State }}</td>
                <td>{{ address.ZipCode }}</td> -->
                <!-- <td style="text-align: center;">
                  <span style="cursor: pointer;" title="Delete Your Address" @click="deleteAddress(address)" class="mdi mdi-delete"></span>
                  <span style="cursor: pointer;" title="Edit Your Address" @click="$router.push({name: 'EditAddress', params: {addressID: address.AddressID}})" class="mdi mdi-home-edit-outline"></span>
                  <button class="btn btn-danger ml-auto" style="float: right;" @click="$router.push({name: 'ViewAppliancesPerAddress', params: {addressID: address.AddressID}})">
                    View Cards
                  </button>
                </td> -->
              </tr>
            </tbody>
            <tbody v-else>
              <tr>
                <td colspan="5" style="text-align: center;">
                  It seems that you dont have any cards added! Please add an address to continue.
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
 name: "ViewCards",
 data(){
	return {
		cards: []
	}
 },
 mounted(){
	this.getCards();
 },
 methods: {
	getCards(){
		this.$userHttp.post('/get-payment-details', {
			userID: this.$session.get('UserID')
		}).then((response) => {
			console.log(response.data);
			if(response.data?.Data.length > 0){
				this.cards = response.data.Data;
			}
		}).catch((err) => {
			console.log("err", err);
		})
	},
	deleteCard(card){
		this.$Swal.fire({
        icon: 'question',
        title: 'Are You Sure?',
        text: 'This will delete this Address.',
        showCancelButton: true
      }).then((result) => {
        if(result.value){
					this.$userHttp.post('/delete-card', {
            cardID: card.ID
          }).then((response) => {
            if(response.data.Status === "Success"){
                this.$Swal.fire({
                  text: "Your card was deleted! ",
                  title: "Success",
                  icon: "success",
                })
                this.getUserAddresses();
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