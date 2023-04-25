<template>
  <v-app>
    <v-app-bar
      app
      color="primary"
      dark
    >
      <div class="d-flex align-center" style="width: 100%;
    justify-content: space-between;">
       <h2> Power Saver HQ</h2>
        <!-- <v-img
          alt="Vuetify Logo"
          class="shrink mr-2"
          contain
          src="https://cdn.vuetifyjs.com/images/logos/vuetify-logo-dark.png"
          transition="scale-transition"
          width="40"
        />

        <v-img
          alt="Vuetify Name"
          class="shrink mt-1 hidden-sm-and-down"
          contain
          min-width="100"
          src="https://cdn.vuetifyjs.com/images/logos/vuetify-name-dark.png"
          width="100"
        /> -->
        
        <v-btn
        class="ma-2"
        color="red"
        @click="logOut()"
      >
        Logout
        <v-icon icon="md:gavel"></v-icon>
      </v-btn>

      </div>
      <v-spacer></v-spacer>
    </v-app-bar>

    <v-main>
      <router-view/>
    </v-main>
  </v-app>
</template>

<script>

export default {
  name: 'App',

  data: () => ({
    //
  }),
  computed: {
    // currentTime(){
    //   return new Date().toJSON().slice(0,10).replace(/-/g,'/')
      
    // }
  },  
  methods: {
    logOut() {
      this.$session.destroy();
      this.$router.push('/')
    }
  }
};
</script>

<style lang="scss">

</style>