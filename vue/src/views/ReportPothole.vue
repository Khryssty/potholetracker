<template>
  <!-- <div class="report-container"> -->
      
      <pothole-map/>
  <!-- </div> -->
</template>

<script>
import PotholeMap from "../components/PotholeMap.vue";
export default {
  components: { PotholeMap },
  name: "reportPothole",
  data() {
    return {
      pothole: {
        location: {
          lat: "",
          lng: "",
        },
        photo: "placeholder",
      },
      errorMsg: ''
    };
  },
};
</script>

<style scoped>
/* div.report-container {
    display: flex;
    flex: 1;
    flex-direction: column;
    align-items: center;
    justify-self: stretch;
} */
</style>