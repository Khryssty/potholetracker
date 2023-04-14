<template>
  <div class="container">
    <!-- Adding Icons: first add the icon to the specific icons import in main.js,
            then add the icon to the library in main.js, finally you can use the icon in your template -->
    <header>Rough Road Repairs</header>

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
            >View Potholes</router-link
          >
        </div>
        <div>
          <router-link class="link" v-bind:to="{ name: 'reportPothole' }"
            >Report Pothole</router-link
          >
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
  }
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
  background-color: #9df0e6;
}

nav {
  grid-area: nav;
  background-color: #9df0e6;
}

main {
  grid-area: content;
  background-color: #bcf7f0;
}

aside {
  grid-area: side;
  background-color: #9df0e6;
}

footer {
  grid-area: footer;
  background-color: #9df0e6;
}

header,
footer {
  color: #3a0ca3;
}
nav,
main,
aside {
  color: #3a0ca3;
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

header {
  font-family: "Segoe UI", Tahoma, Geneva, Verdana, sans-serif;
  font-size: 30px;
  text-transform: uppercase;
  text-align: center;
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
  color: #3a0ca3;
}
h3 {
  margin: 0;
  padding: 1rem 0;
  border-radius: 0.25rem;
  border: 2px black solid;
}
</style>
