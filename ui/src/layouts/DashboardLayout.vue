<template>
  <div style="display: flex;">
    <sidebar></sidebar>
    <router-view></router-view>
  </div>
</template>

<script>
import Sidebar from '@/components/Sidebar.vue'
export default {
  components: { Sidebar },
name: "DashboardLayout"
}
</script>

<style>

</style>