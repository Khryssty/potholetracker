<template>
  <div class="home">        
    
      <img class="home animate-fading" src="img/Repaired1.jpg" style="width:100%">
      <img class="home animate-fading" src="img/Repaired2.jpg" style="width:100%">
      <img class="home animate-fading" src="img/Repaired3.jpg" style="width:100%">
      <img class="home animate-fading" src="img/Repaired4.jpg" style="width:100%">
      <img class="home animate-fading" src="img/Repaired5.jpg" style="width:100%">
      <p class="slideshow-text">Report a pothole and we'll fix it for you!</p>
    
  </div>
</template>

<script>
export default {
  name: "home",
  mounted() {
    var myIndex = 0;
    var carousel = () => {
      var i;
      var x = document.getElementsByClassName("animate-fading");
      for (i = 0; i < x.length; i++) {
        x[i].style.display = "none";
      }
      myIndex++;
      if (myIndex > x.length) {
        myIndex = 1
      }
      x[myIndex - 1].style.display = "block";
      setTimeout(carousel, 10000);
    };
    carousel();
  }
};
</script>

<style scoped>
.home {
  height: 100vh;
}
.animate-fading {
  animation: fade 5s;
}

@keyframes fade {
  from {
    opacity: 1;
  }
  
}

.slideshow-text {
  position: absolute;
  top: 85%;
  left: 50%;
  transform: translateX(-50%);
  text-align: center;
  width: 100%;
  font-family: Cambria, Cochin, Georgia, Times, 'Times New Roman', serif;
  font-size: 3.5em;
  color: rgb(57, 90, 8);
  
}


  
</style>

