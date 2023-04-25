<template>
  <v-container>
      <v-layout wrap>
        <v-flex sm12 md6 offset-md3>
          <v-card elevation="4" light tag="section">
            <v-card-title>
              <v-layout align-center justify-space-between>
                <h3 class="headline">
                  {{ platformName }}
                </h3>
                <v-flex>
                  <v-img :alt="platformName" class="ml-3" contain height="48px" position="center right" src="https://www.mobygames.com/images/i/12/25/1435075.png"></v-img>
                </v-flex>
              </v-layout>
            </v-card-title>
            <v-divider></v-divider>
            <v-card-text>
              <p>Sign in with your username and password:</p>
              <v-form>
                <v-text-field
                  :rules="['Required']"
                  outline
                  label="Username"
                  type="text"
                  v-model="username"></v-text-field>
                <v-text-field
                  :rules="['Required']"
                  outline
                  hide-details
                  label="Password"
                  type="password"
                  v-model="password"></v-text-field>
              </v-form>
            </v-card-text>
            <v-divider></v-divider>
            <v-card-actions :class="{ 'pa-3': $vuetify.breakpoint.smAndUp }">
              <v-btn color="info" text>
                Forgot password?
              </v-btn>
              <v-spacer></v-spacer>
              <v-btn color="info" :large="$vuetify.breakpoint.smAndUp" @click="loginSubmit">
                <!-- <v-icon left>lock</v-icon> -->
                Login
              </v-btn>
            </v-card-actions>
            <v-spacer></v-spacer>
            <v-btn color="info" :large="$vuetify.breakpoint.smAndUp" style="margin: 1rem;" @click="$router.push({ name : 'Register' })">
                <!-- <v-icon left>lock</v-icon> -->
                New User? Register
            </v-btn>
            <v-spacer></v-spacer>
          </v-card>
        </v-flex>
        <v-flex sm12 md6 offset-md3>
          <v-layout align-center justify-space-between>
            <p class="caption my-3">
              <a href="#">Privacy Policy</a>
              |
              <a href="#">Terms of Service</a>
            </p>
          </v-layout>
        </v-flex>
      </v-layout>
    </v-container>
</template>
  
  <script>
export default {
  name: "LoginView",
  data: () => ({
    platformName: 'PowerSaver HQ',
    password: null,
    username: null,
    firstName: "",
    rules: [
      (value) => {
        if (value) return true;

        return "You must enter a first name.";
      },
    ],
  }),
  mounted() {
  },
  methods: {
    loginSubmit(){
      this.$userHttp.post('login', {
        email: this.username,
        password: this.password
      })
      .then(({data})=>{
        if(data.Result.length > 0){
          this.$session.start();
          for(const obj in data.Result[0]){
            this.$session.set(obj, data.Result[0][obj])
          }
          this.$router.push({
            name: "dashboard-home "
          })
        } else {
          this.$Swal.fire({
            icon: 'error',
            title: 'Some Error Occurred!',
            text: 'It seems you entered wrong credentials. Please try again.'
          })
        }
      }).catch((err)=>{
        console.log("err", err);
        this.$Swal.fire({
            icon: 'error',
            title: 'Some Error Occurred!',
            text: 'Please try again.'
          })
      })
      // this.$router.push({
      //   name:'dashboard'
      // })
    }
  }
};
</script>