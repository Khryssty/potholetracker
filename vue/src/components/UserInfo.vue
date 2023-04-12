<template>
  <div id="sidebar-container" class="content">
    <h3>User Info</h3>
    <h4>Current User:</h4>
    <div class="current-user">
      <p v-if="$store.state.token === ''">
        <font-awesome-icon icon="fa-solid fa-user" /> Guest
      </p>
      <p v-else>
        <font-awesome-icon icon="fa-solid fa-user" />
        {{ $store.state.user.username }}
      </p>
    </div>
    <div class="logged-out" v-if="$store.state.token === ''">
      <router-link class="link" :to="{ name: 'login' }">Login</router-link>
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
div#logout {
  justify-self: end;
}
div.current-user {
  display: flex;
  justify-content: center;
}
p {
  margin: 0;
}
.link {
  color: #3a0ca3;
}
h4 {
  margin: 0.5rem;
}
</style>