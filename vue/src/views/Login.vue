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
    
    <form @submit.prevent="login">
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
      <router-link :to="{ name: 'register' }">Need an account? Sign up.</router-link></p>
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
  border: 5px solid #2E3440;
  width: 300px;
  position: absolute;
  top: 520px;
  right: 800px;

  

}
#pothole-winner-img{
  height: 743px;
  width: 825px;
  margin: 0;
}

</style>