<template>
  <div class="container">
    <!-- Adding Icons: first add the icon to the specific icons import in main.js,
            then add the icon to the library in main.js, finally you can use the icon in your template -->
    <header>
      <img class ="logo" src="img/RRR7.png" alt="Logo" />
      <h1>Rough Road Repairs </h1>
      <!--<img src="img/Logo6.png" alt="Logo" />  -->

       
    </header>
    
    <nav class="sidebar-container">
        <h3>
          <font-awesome-icon icon="fa-solid fa-map-location" size="lg" style="color: #d13a15;"/>                    
          Navigation</h3>
        <hr/> 

        
        <router-link class="link" v-bind:to="{ name: 'home' }">
          
          <font-awesome-icon icon="fa-solid fa-house" size="xl" />
          <div>Home</div>
        </router-link>
                
        <hr/>
        <router-link class="link" v-bind:to="{ name: 'viewPotholes' }">
          <font-awesome-icon icon="fa-solid fa-person-digging" size="xl" />
          <div>View Potholes</div>
        </router-link>
        <hr/>        
        <router-link class="link" v-bind:to="{ name: 'reportPothole' }">          
          <font-awesome-icon icon="fa-solid fa-flag" size="xl" />
          <div>Report Pothole</div>
        </router-link>
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
  /* background-color: lightgray; */
  background-color: #b1a1ed;
  /* background-color: hsl(194, 77%, 59%); */
  /* background-color: #b1a1ed; */
  font-family: "Segoe UI", Tahoma, Geneva, Verdana, sans-serif;
  font-size: 45px;
  text-transform: uppercase;
  text-align: center;
  position: relative;
  

  }
.logo {
  width: 150px;
  height: 150px;
  top: -10px;
  left: 10px;
  position: absolute;

}

.cone {
  width: 350px;
  height: 130px;
  top: -5px;
  left: 1500px;
  position: absolute;

}

nav.sidebar-container {
  grid-area: nav;
  /* background-color: hsl(194, 77%, 59%); */
  background-color: lightgray;
  font-size: 25px;
  padding: 0;
  display: flex;
  align-items: center;
  flex-direction: column;
  gap: 0.75rem;
  text-transform: capitalize;
}

main {
  grid-area: content;
  background-color: #cfc5e8;
}


aside {
  grid-area: side;
  background-color: lightgray;
  font-size: 25px;
  text-transform: capitalize;
}

footer {
  grid-area: footer;
  /* background-color: hsl(194, 77%, 59%); */
  background-color: #b1a1ed;
  
}

header,
footer {
  color: black;
}
nav,
main,
aside {
  color:;
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
  height: 100%;
  /* padding-top: 20px; */
}

.link {
  color: #2E3440;
  font-size: 25px;
  text-align: center;
  text-decoration: underline;
}
h3 {  
  align-self: stretch;
  text-align: center;
  margin: 0;
  padding: 1rem 0;
  border-radius: 0.25rem;
  border: 2px black solid;  
}


</style>
