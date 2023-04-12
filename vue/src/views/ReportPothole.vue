<template>
  <div class="container">
      Report A Pothole:
      <pothole-map/>
    <!-- <div v-show="errorMsg !== ''">
        <h2>{{errorMsg}}</h2>
    </div>
    <form @submit.prevent="createPothole">
      <input
        id="latitude"
        type="text"
        v-model="pothole.location.lat"
        placeholder="Latitude"
      />
      <input
        id="longitude"
        type="text"
        v-model="pothole.location.lng"
        placeholder="Longitude"
      />
      <div>
          <p>photo list placeholder</p>
      </div>
      <input type="submit" />
    </form> -->
    
  </div>
</template>

<script>
// import potholeService from "../services/PotholeService";
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
//   methods: {
//     createPothole() {
//       potholeService
//         .submitPothole(this.pothole)
//         .then((response) => {
//           if (response.status === 201) {
//               this.$router.push({name: "viewPotholes"});
//           }
//         })
//         .catch((error) => {
//           this.handleErrorResponse(error, "adding");
//         });
//     },
//     handleErrorResponse(error, verb) {
//       if (error.response) {
//         this.errorMsg =
//           "Error " +
//           verb +
//           " pothole. Response received was '" +
//           error.response.status +
//           "'.";
//       } else if (error.request) {
//         this.errorMsg = "Error " + verb + " pothole. Server could not be reached.";
//       } else {
//         this.errorMsg =
//           "Error " + verb + " pothole. Request could not be created.";
//       }
//     },
//   },
};
</script>

<style scoped>
div.container {
    display: flex;
    flex-direction: column;
    align-items: center;
}
/* form {
    width: 35%;
    display: flex;
    flex-direction: column;
    gap: 5px;
} */
</style>