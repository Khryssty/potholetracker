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