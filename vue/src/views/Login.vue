<template>
  <div id="login">   
     <!-- Added company header and logo 
 
   <h1> Login </h1>
   
   -->
  <div class="container">
  
  <img src="img/mock4.png" alt="Home Page" /> 
  <img src="img/POTHOLEWINNER1.gif" alt="gif" id="pothole-winner-img" />

  
  </div>
  


   <!-- <img src="img/pothole5.jpg" alt="Logo" /> -->
    
    <form @submit.prevent="login" class="login-form">
      <h2 >Please Sign In</h2>
      <div role="alert" v-if="invalidCredentials">
        Invalid username and password!
      </div>
      <div role="alert" v-if="this.$route.query.registration">
        Thank you for registering, please sign in.
      </div>
      <div class="form-input-group">
        <label for="username">Username</label>
        <input type="text" id="username" v-model="user.username" required autofocus />
      </div>
      <div class="form-input-group">
        <label for="password">Password</label>
        <input type="password" id="password" v-model="user.password" required />
      </div>
      <button type="submit">Sign in</button>
      <p>
      <router-link :to="{ name: 'register' }">
        <div>Need an account?</div>
        <div>Sign up.</div></router-link></p>
    </form>
  </div>
</template>

<script>
import authService from "../services/AuthService";

export default {
  name: "login",
  components: {},
  data() {
    return {
      user: {
        username: "",
        password: ""
      },
      invalidCredentials: false
    };
  },
  methods: {
    login() {
      authService
        .login(this.user)
        .then(response => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
            this.$router.push("/");
          }
        })
        .catch(error => {
          const response = error.response;

          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
    }
  }
};
</script>

<style scoped>

.form-input-group {
  margin-bottom: 1rem;
  text-align: center;
  bottom: -50px;
  
}
label {
  margin-right: 0.5rem;  
}
button{
  width: 28%;
  margin: 5px;
  padding: 5px;
  border-radius: 4px;
  font-weight: bold;
}

input {
  border-radius: 4px;
  margin: 5px;
  padding: 5px;
}
.container{ 
  display: flex;
  max-width: 1500px;
  margin: auto;
  padding: none;
  text-align: center;
  height: 10vh;
  justify-content: center;
  font-family: "Helvetica", Tahoma, Geneva, Verdana, sans-serif;

 }
img{
  height: 743px;
  width: 1000px;

  
}

form{
  position: relative;
  margin-top: 320px;
  margin-right: auto;
  margin-left: auto;
  width: 300px;  
  border: 5px solid #2E3440;
  /* width: 300px;  
  top: 520px;
  right: 800px;   */


}
#pothole-winner-img{
  height: 743px;
  width: 825px;
  margin: 0;
}

/* Media queries for responsiveness */
@media (max-width: 768px) {
  form {
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