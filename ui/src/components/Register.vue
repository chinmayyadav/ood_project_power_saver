<template>
    <div class="container">
      <div class="row justify-content-center mt-5">
        <div class="col-lg-6 col-md-8 col-sm-10">
          <div class="card">
            <div class="card-header bg-primary text-white">
              <h4 class="card-title mb-0">Register</h4>
            </div>
            <div class="card-body">
              <form @submit.prevent="registerUser">
                <div class="mb-3">
                  <label for="first-name" class="form-label">First Name:</label>
                  <input type="text" class="form-control" id="first-name" v-model="firstName" required>
                </div>
                <div class="mb-3">
                  <label for="last-name" class="form-label">Last Name:</label>
                  <input type="text" class="form-control" id="last-name" v-model="lastName" required>
                </div>
                <div class="mb-3">
                  <label for="email" class="form-label">Email:</label>
                  <input type="email" class="form-control" id="email" v-model="email" required>
                </div>
                <div class="mb-3">
                  <label for="password" class="form-label">Password:</label>
                  <input type="password" class="form-control" id="password" v-model="password" required>
                </div>
                <button type="submit" class="btn btn-primary w-100">Register</button>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
  </template>
  
  <script>
  export default {
    data() {
      return {
        firstName: "",
        lastName: "",
        email: "",
        password: ""
      };
    },
    methods: {
      registerUser() {
        // Code to register user with the given details
        console.log(this.firstName, this.lastName, this.email, this.password);
        this.$userHttp.post('/register-user', {
          firstName: this.firstName,
          lastName: this.lastName,
          email: this.email,
          password: this.password
        }).then((response) => {
          console.log(response);
        }).catch((e) => {
          console.log(e);
        })
      }
    }
  };
  </script>
  
  <style scoped>
  .container {
    height: 100vh;
    display: flex;
    justify-content: center;
    align-items: center;
  }
  
  .card {
    border-radius: 10px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
  }
  
  .card-header {
    border-radius: 10px 10px 0 0;
  }
  
  .form-label {
    font-weight: bold;
  }
  
  .btn-primary {
    background-color: #007bff;
    border-color: #007bff;
  }
  
  .btn-primary:hover {
    background-color: #0069d9;
    border-color: #0062cc;
  }
  
  input[type="text"],
  input[type="email"],
  input[type="password"] {
    border-radius: 5px;
  }
  </style>