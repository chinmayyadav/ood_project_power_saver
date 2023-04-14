<template>
  <div>
    <div class="flex-shrink-0 p-3" style="width: 280px;">
    <a href="/" class="d-flex align-items-center pb-3 mb-3 link-body-emphasis text-decoration-none border-bottom">
      <svg class="bi pe-none me-2" width="30" height="24"><use xlink:href="#bootstrap"/></svg>
      <span class="fs-5 fw-semibold">Dashboard</span>
    </a>
    <ul class="list-unstyled ps-0">
      <li class="mb-1">
        <button class="btn btn-toggle d-inline-flex align-items-center rounded border-0 collapsed" data-bs-toggle="collapse" data-bs-target="#home-collapse" aria-expanded="false">
          Home
        </button>
        <div class="collapse" id="home-collapse">
          <ul class="btn-toggle-nav list-unstyled fw-normal pb-1 small">
            <li><a href="#" class="link-body-emphasis d-inline-flex text-decoration-none rounded">Overview</a></li>
            <li><a href="#" class="link-body-emphasis d-inline-flex text-decoration-none rounded">Updates</a></li>
            <li><a href="#" class="link-body-emphasis d-inline-flex text-decoration-none rounded">Reports</a></li>
          </ul>
        </div>
      </li>
      <li class="mb-1">
        <button class="btn btn-toggle d-inline-flex align-items-center rounded border-0 collapsed" data-bs-toggle="collapse" data-bs-target="#dashboard-collapse" aria-expanded="false">
          Billing
        </button>
        <div class="collapse" id="dashboard-collapse">
          <ul class="btn-toggle-nav list-unstyled fw-normal pb-1 small">
            <li><a href="javascript:void(0)" @click="goToRoute('add-credit-card')" class="link-body-emphasis d-inline-flex text-decoration-none rounded">Add Credit Card</a></li>
            <li><a href="javascript:void(0)" @click="goToRoute('add-bank-account')" class="link-body-emphasis d-inline-flex text-decoration-none rounded">Add Bank Account</a></li>
           </ul>
        </div>
      </li>
      <li class="mb-1">
        <button class="btn btn-toggle d-inline-flex align-items-center rounded border-0 collapsed" data-bs-toggle="collapse" data-bs-target="#orders-collapse" aria-expanded="false">
          Insights
        </button>
        <div class="collapse" id="orders-collapse">
          <ul class="btn-toggle-nav list-unstyled fw-normal pb-1 small">
            <li><a href="javascript:void(0)" class="link-body-emphasis d-inline-flex text-decoration-none rounded">Appliances</a></li>
            <li><a href="javascript:void(0)" class="link-body-emphasis d-inline-flex text-decoration-none rounded">Compare with Neighbours</a></li>
            <li><a href="javascript:void(0)" class="link-body-emphasis d-inline-flex text-decoration-none rounded">Shipped</a></li>
            <li><a href="javascript:void(0)" class="link-body-emphasis d-inline-flex text-decoration-none rounded">Returned</a></li>
          </ul>
        </div>
      </li>
      <li class="border-top my-3"></li>
      <li class="mb-1">
        <button class="btn btn-toggle d-inline-flex align-items-center rounded border-0 collapsed" data-bs-toggle="collapse" data-bs-target="#account-collapse" aria-expanded="false">
          Account
        </button>
        <div class="collapse" id="account-collapse">
          <ul class="btn-toggle-nav list-unstyled fw-normal pb-1 small">
            <li><a href="javascript:void(0)" class="link-dark d-inline-flex text-decoration-none rounded">New...</a></li>
            <li><a href="javascript:void(0)" class="link-dark d-inline-flex text-decoration-none rounded">Profile</a></li>
            <li><a href="javascript:void(0)" class="link-dark d-inline-flex text-decoration-none rounded">Settings</a></li>
            <li><a href="javascript:void(0)" class="link-dark d-inline-flex text-decoration-none rounded">Sign out</a></li>
          </ul>
        </div>
      </li>
    </ul>
  </div>
  </div>
</template>

<script>
export default {
    // eslint-disable-next-line
    name: "Sidebar",
    data () {
      return {
            drawer: true,
            navLinks: [
                { icon: "dashboard", text: "Dashboard", route: "/" },
                { icon: "star", text: "Ratings", route: "/ratings" },
                {
                    icon: "people",
                    text: "Bills & Payments",
                    route: "",
                    subLinks: [
                        { text: "Add Credit Card", route: "" },
                        { text: "Add Bank Account", route: "" }
                    ]
                },
    
                {
                    icon: "person_outline",
                    text: "Manager",
                    route: "/managers",
                    subLinks: [
                        { text: "Add Manager", route: "" },
                        { text: "Managers", route: "" }
                    ]
                },
                { icon: "person", text: "Manage Users", route: "/users" }
            ]
      }
    },
    mounted(){
      // const tooltipTriggerList = Array.from(document.querySelectorAll('[data-bs-toggle="tooltip"]'))
      // tooltipTriggerList.forEach(tooltipTriggerEl => {
      //   new bootstrap.Tooltip(tooltipTriggerEl)
      // })
    },
    methods : {
      goToRoute(name){
        this.$router.push({
          name: name
        })
      }
    }
}
</script>

<style scoped>

.dropdown-toggle { outline: 0; }

.btn-toggle {
  padding: .25rem .5rem;
  font-weight: 600;
  color: var(--bs-emphasis-color);
  background-color: transparent;
}
.btn-toggle:hover,
.btn-toggle:focus {
  color: rgba(var(--bs-emphasis-color-rgb), .85);
  background-color: var(--bs-tertiary-bg);
}

.btn-toggle::before {
  width: 1.25em;
  line-height: 0;
  content: url("data:image/svg+xml,%3csvg xmlns='http://www.w3.org/2000/svg' width='16' height='16' viewBox='0 0 16 16'%3e%3cpath fill='none' stroke='rgba%280,0,0,.5%29' stroke-linecap='round' stroke-linejoin='round' stroke-width='2' d='M5 14l6-6-6-6'/%3e%3c/svg%3e");
  transition: transform .35s ease;
  transform-origin: .5em 50%;
}

[data-bs-theme="dark"] .btn-toggle::before {
  content: url("data:image/svg+xml,%3csvg xmlns='http://www.w3.org/2000/svg' width='16' height='16' viewBox='0 0 16 16'%3e%3cpath fill='none' stroke='rgba%28255,255,255,.5%29' stroke-linecap='round' stroke-linejoin='round' stroke-width='2' d='M5 14l6-6-6-6'/%3e%3c/svg%3e");
}

.btn-toggle[aria-expanded="true"] {
  color: rgba(var(--bs-emphasis-color-rgb), .85);
}
.btn-toggle[aria-expanded="true"]::before {
  transform: rotate(90deg);
}

.btn-toggle-nav a {
  padding: .1875rem .5rem;
  margin-top: .125rem;
  margin-left: 1.25rem;
}
.btn-toggle-nav a:hover,
.btn-toggle-nav a:focus {
  background-color: var(--bs-tertiary-bg);
}

.scrollarea {
  overflow-y: auto;
}


</style>