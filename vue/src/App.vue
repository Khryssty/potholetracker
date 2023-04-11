<template>

<!-- Added -->
<div class = "container"> 
  <header>
  Road Rough Repairs 

  <!-- need to add logo and name -->
  
  </header>



<!-- Provided in source code
  <div id="app">
    <div id="nav">
      <router-link v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>

    </div>
    <router-view />

    -->

    <!--TODO router link to PotholeList for Navigation Bar
    route to login 

    view potholes, register, login on navigation bar

    -->
    
      
          <nav>
      <div class="content">
        Navigation
        <div><router-link v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp;
        <div><router-link v-bind:to="{ name: 'login' }">Login</router-link>
        <div><router-link v-bind:to="{ name: 'register'}">Register</router-link>



  </div>
  </nav>

  <main>
    <div class="content">

      </div>

      </main>
      <aside>
        <div class="content">
          Sidebar / Info
        </div>
        </aside>
        <footer>
          <div class="content">
            Footer content
            </div>

        </footer>
        </div>

        </template>

  


<!-- Need to add Holy Grail css -->

