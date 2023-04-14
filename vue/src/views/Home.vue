<template>
  <div class="home">
    <h1>Home</h1>
    <p>If you are seeing this, you are authenticated.</p>
    <p>
      Welcome to Rough Road Repairs, an application for reporting potholes and allowing city employees
      to review reported potholes and make updates as well as schedule repairs accordingly.
    </p>

    
    
  </div>
</template>

<script>
export default {
  name: "home"
};
</script>


<style scoped>
  div.home {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
  }
  p {
    width: 50%;
  }
</style>

