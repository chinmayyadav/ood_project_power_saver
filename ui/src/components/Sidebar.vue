<template>
  <div>
    <v-navigation-drawer permanent>
      <!-- <v-list-item>
        <v-list-item-content>
          <v-list-item-title class="text-h6">
            Application
          </v-list-item-title>
          <v-list-item-subtitle>
            subtext
          </v-list-item-subtitle>
        </v-list-item-content>
      </v-list-item> -->

      <v-divider></v-divider>

      <v-list
        dense
        nav
      >
        <v-list-item
          v-for="item in items"
          :key="item.title"
          link
        >
          <v-list-item-icon>
            <v-icon>{{ item.icon }}</v-icon>
          </v-list-item-icon>

          <v-list-item-content>
            <v-list-item-title>{{ item.title }}</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
      </v-list>
    </v-navigation-drawer>
    <!-- sdfsfdssdffd -->
    <!-- <v-card>
    <v-layout> -->
        <!-- class="bg-deep-purple" -->
        <!-- <v-navigation-drawer
            color="#09151E"
            permanent
            expand-on-hover
        >
            <v-divider></v-divider>
            <v-list nav dense>
                <v-list-item link href="#">
                    <v-list-item-icon>
                        <v-icon color="white" small>mdi-lightbulb</v-icon>
                    </v-list-item-icon>
                    <v-list-item-title class="title">Blue</v-list-item-title>
                </v-list-item>
            </v-list>
        </v-navigation-drawer>
        <v-card>Navbar</v-card> -->
      <!-- <v-main style="height: 400px"></v-main> -->
    <!-- </v-layout>
  </v-card> -->
      <!-- <v-main style="height: 400px"></v-main> -->
    <!-- </v-layout> -->

  </div>
</template>

<script>
export default {
    // eslint-disable-next-line
    name: "Sidebar",
    data () {
      return {
        items: [
          { title: 'Dashboard', icon: 'mdi-view-dashboard' },
          { title: 'Bills & Payments', icon: 'mdi-image' },
          { title: 'Insights', icon: 'mdi-help-box' },
          { title: 'Settings & Preferences', icon: 'mdi-setting' },
        ],
        right: null,
      }
    },
}
</script>

<style>

</style>