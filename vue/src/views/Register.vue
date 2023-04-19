<template>
  <div id="register" class="register-img">
      <!-- <img src="img/registerpage2.png" alt="Register Page" />  -->


    <form @submit.prevent="register" class="register-form">
      <h3>Create Account</h3>      
      <div role="alert" v-if="registrationErrors">
        {{ registrationErrorMsg }}
      </div>
      <div class="form-input-group">
        <label for="username">Username:</label>
        <input type="text" id="username" v-model="user.username" required autofocus />
      </div>
      <div class="form-input-group">
        <label for="password">Password:</label>
        <input type="password" id="password" v-model="user.password" required />
      </div>
      <div class="form-input-group">
        <label for="confirmPassword">Confirm Password:</label>
        <input type="password" id="confirmPassword" v-model="user.confirmPassword" required />
      </div>
      <button type="submit">Create Account</button>
      <router-link :to="{ name: 'login' }"><span>Login if you have an account</span></router-link>
    </form>

    
  </div>
</template>

<script>
import authService from '../services/AuthService';

export default {
  name: 'register',
  data() {
    return {
      user: {
        username: '',
        password: '',
        confirmPassword: '',
        role: 'user',
      },
      registrationErrors: false,
      registrationErrorMsg: 'There were problems registering this user.',
    };
  },
  methods: {
    register() {
      if (this.user.password != this.user.confirmPassword) {
        this.registrationErrors = true;
        this.registrationErrorMsg = 'Password & Confirm Password do not match.';
      } else {
        authService
          .register(this.user)
          .then((response) => {
            if (response.status == 201) {
              this.$router.push({
                path: '/login',
                query: { registration: 'success' },
              });
            }
          })
          .catch((error) => {
            const response = error.response;
            this.registrationErrors = true;
            if (response.status === 400) {
              this.registrationErrorMsg = 'Bad Request: Validation Errors';
            }
          });
      }
    },
    clearErrors() {
      this.registrationErrors = false;
      this.registrationErrorMsg = 'There were problems registering this user.';
    },
  },
};
</script>

<style scoped>
.register-img{
  background-image: url("https://drive.google.com/uc?id=1mQanX__aTknypqfjH8NEsl1tlVCim7U-");
  
    
    /* Control the height of the image */
  height: 100%;
  width: 100%;

  /* Center and scale the image nicely */
  background-position: center bottom;
  background-repeat: no-repeat;
  background-size: 100%;
  position: static;
}

/* Add styles to the form container */
.register-form {
  position: absolute;  
  right: 11rem;
  /* top: 5rem; */
  margin: 20px 20px;
  max-width: 300px;  
  padding: 10px 10px;
  background-color:transparent;
}
/* Full-width input fields */
  input[type=text], input[type=password] {
  
  width: 80%;
  padding: 7px;
  margin: 5px 0 10px 0;
  border: none;
  background: #f1f1f1;
}

input[type=text]:focus, input[type=password]:focus {
  background-color: #ddd;
  outline: none;
}


label
 {
  color: #b1a1ed;
  text-transform: capitalize;
  font-family: cursive;
  font-weight: normal;
}

/* Set a style for the submit button */
button {
  background-color: #04AA6D;
  font-family: cursive;
  font-weight: 2;  
  font-size: 100%;
  color: white;
  margin: 5px;
  padding: 5px;  
  border: none;
  cursor: pointer;
  width: 88%;
  opacity: 5;
}

button:hover {
  opacity: 1;
}
h3 {
  background-color: transparent;
  margin: 1px;
  padding: 2px;
  border: none;
  color: #66cef7;
}
span {
  color: #66cef7;
}

/* Media queries for responsiveness */
@media (max-width: 768px) {
  .register-form {
    max-width: 100%;
    margin: 20px;
    right: 0;
    top: 0;
    bottom: 0;
    left: 0;
  }
  input[type=text],
  input[type=password] {
    width: 100%;
  }

  button {
    width: 100%;
  }
}

</style>
