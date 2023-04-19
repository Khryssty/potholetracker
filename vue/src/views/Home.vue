<template>
  <div class="home">
   <h2> Welcome to Rough Road Repairs!</h2>
  <p>We're here to make it easy for you to report potholes in your community. 
    By reporting potholes, you're helping us to keep our roads in good condition, prevent damage to vehicles, and ensure the safety of drivers. <span id = "bottom"> Thank you for taking the time to help us improve our roads and make our community a better place to live.</span></p>
    
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
    justify-content: left;
    align-items: center;
    background-color: #cfc5e8;
    color: purple;
  }
  p {
    width: 97%;
    color: purple;
    font-size: 23px;
    margin: 0px;
    text-align: center;
    padding: 10px;
  }
  h2{
    font-size: 40px;
    margin-top: 5px;

  }
#bottom{
 display: block;
 margin-top: 500px;
 font-size: 24px;

  

}
  
</style>

