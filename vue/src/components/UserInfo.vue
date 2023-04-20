<template>
  <div id="sidebar-container" class="content">
    
    
    <h3>
      <font-awesome-icon icon="fa-solid fa-user-gear" size="xl"  style="color: #3a0098a1;"/>
      <div>User Info</div>
    </h3>
    
    <hr/>
    <hr/>
    
    <!-- <h4>Current User:</h4> -->    
    <font-awesome-icon icon="fa-solid fa-user" size="xl"/>  
    <div class="current-user">    
      
      <p v-if="$store.state.token === ''">
        Guest
      </p>
      <p v-else>        
        {{ $store.state.user.username }}
      </p>      
    </div>

    <hr/>
    <hr/>

    <div title="Login to report a pothole" class="logged-out" v-if="$store.state.token === ''">
      <router-link class="link" :to="{ name: 'login' }">
        <font-awesome-icon icon="fa-solid fa-right-to-bracket" size="xl"/>
        <div>Login</div>
        </router-link>
    </div>
    <div id="logout" v-else>
      <router-link
        class="link"
        v-bind:to="{ name: 'logout' }"
        v-if="$store.state.token != ''"
        >Logout</router-link>
    </div>
  </div>
</template>

<script>
export default {
  name: "user-info",
};
</script>

<style scoped>
div.content{
  text-transform: capitalize;
}
div#logout {
  justify-self: end;
}
div.current-user {
  display: flex;
  justify-content: center;
}
p {
  margin: 0;
  font-size: 25px;
}
.link {
  color: #2E3440;
  font-size: 25px;

}
h3{
  font-size: 25px;
}

h4 {
  margin: 0.5rem;
}

hr {
	border:none;
}

</style>