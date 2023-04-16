<template>
  <div class="container">
    <!-- Adding Icons: first add the icon to the specific icons import in main.js,
            then add the icon to the library in main.js, finally you can use the icon in your template -->
    <header>
      <h1>Rough Road Repairs </h1>
      <!--<img src="img/Logo6.png" alt="Logo" />  -->

       
    </header>
    
    <nav>
      <div id="sidebar-container" class="content">
        <h3>Navigation</h3>
        <div>
          <router-link class="link" v-bind:to="{ name: 'home' }">
            <font-awesome-icon icon="fa-solid fa-house" />
            Home</router-link
          >
        </div>
        <div>
          <router-link class="link" v-bind:to="{ name: 'viewPotholes' }"
            > <font-awesome-icon icon="fa-solid fa-person-digging" />
            
            View Potholes</router-link
          >
        </div>
        <div v-if="currentUser.username"> 
          <!-- v-if statement
        if user logged in?  route to and then do a v-else? -->
          <router-link class="link" v-bind:to="{ name: 'reportPothole' }"
            >Report Pothole</router-link
          >
        </div>
       <div v-else>
         <router-link :to="{name: 'register'}">Report a Pothole</router-link>
       </div>

      </div>
    </nav>

    <main>
      <div class="content">
        <router-view />
        <!-- Maybe a map with TBD-->
      </div>
    </main>

    <aside>
      <user-info/>
    </aside>

    <footer>
      <div class="content">Footer content</div>
    </footer>
  </div>
</template>

  

<script>
import userInfo from '../src/components/UserInfo.vue';
export default {
  components: {
    userInfo
  },
  computed: {    
  currentUser() {      
    return this.$store.state.user;
  } }
};
</script>

<!-- Need to add Holy Grail css -->

<style>
body {
  margin: 0;
  padding: 0;
}

/* Add CSS Grid here  background colors: Header - #f69a00;  Nav - #f2ead2;  
    main - #f9f5f0;  aside - #f2ead2;  footer - #331312;
    font colors header, footer - #ffffff;  nav, main, aside - #74889a;*/

.container {
  display: grid;

  grid-template-areas:
    "header header header"
    "nav content side"
    "footer footer footer";

  grid-template-columns: 200px 1fr 200px;
  grid-template-rows: 125px 1fr 85px;
  gap: 4px;

  height: 100vh;
}
/*handle responsive behavior */
@media (max-width: 768px) {
  .container {
    grid-template-areas:
      "header"
      "nav"
      "content"
      "side"
      "footer";
    grid-template-columns: 1fr;
    grid-template-rows:
      auto /*Header */
      75px /*Nav*/
      1fr /* content */
      75px /* Sidebar */
      auto; /* Footer */
  }
  header,
  footer,
  nav,
  main,
  aside {
    padding: 5px 0;
  }
  nav,
  aside {
    margin: 0;
  }
}

header {
  grid-area: header;
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: #E1E5EE;
  font-family: "Segoe UI", Tahoma, Geneva, Verdana, sans-serif;
  font-size: 50px;
  text-transform: uppercase;
  text-align: center;
}

nav {
  grid-area: nav;
  background-color: #cfc5e8;
}



main {
  grid-area: content;
  background-color: #cfc5e8;
}


aside {
  grid-area: side;
  background-color: #cfc5e8;
}

footer {
  grid-area: footer;
  background-color: #E1E5EE;
}

header,
footer {
  color: black;
}
nav,
main,
aside {
  color: black;
}
header,
footer,
nav,
main,
aside {
  font-family: "Segoe UI", Tahoma, Geneva, Verdana, sans-serif;
  border-radius: 4px;
  font-weight: bold;
  text-transform: uppercase;
}
.header-container {
  display: flex;
}

img{
  height: 25px;
  width: 50px;
}

div.content {
  text-align: center;
  /* padding-top: 20px; */
}
div#sidebar-container {
  padding: 0;
  display: flex;
  flex-direction: column;
  gap: 0.75rem;
}
.link {
  color: #2E3440;
  font-size: 30px;
  text-decoration: underline;
}
h3 {
  margin: 0;
  padding: 1rem 0;
  border-radius: 0.25rem;
  border: 2px black solid;
}

</style>
