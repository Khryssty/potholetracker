<template>  
  <div class="details-container">   
      <div><router-link :to="{name: 'viewPotholes'}"><font-awesome-icon class="back" icon="fa-solid fa-circle-arrow-left" size="2xl" /></router-link></div>
      <div class="left">    
     <div class="potholeId">POTHOLE ID : {{pothole.potholeId}}</div>   
     <div class="status">STATUS: {{pothole.status}}</div>
     <div class="severity">SEVERITY: {{pothole.severity}}</div>
     <div class="date">LAST UPDATE: {{pothole.statusDate}}</div> 
     <div class="modifiedBy">UPDATED BY: {{pothole.username}}</div>
     <div class="address">ADDRESS: {{pothole.location.street}}, 
         {{pothole.location.city}}, {{pothole.location.state}}, {{pothole.location.postalCode}}
     </div>    
     </div> 
      <div class="center">
    <div class="photo">
        <img v-if="pothole.photo !== 'No Photo'" class="pothole-image" :src="pothole.photo" alt="Pothole Photo">
        <!-- <div v-else>{{pothole.photo}}</div> -->
    </div>
    </div>
    
  </div>  
</template>

<script>
// import fileService from '../services/FileService.js'
export default {
    name: "pothole-details",
    computed:{
        pothole(){
            return this.$store.getters.pothole;
        }
    },
    created(){
        const activePotholeId = this.$route.params.potholeId;
        this.$store.commit("SET_ACTIVE_POTHOLE", activePotholeId);
        this.downloadFile();
    }
}

</script>

<style scoped>
div {
    /* text-transform: none; */
    font-size: 2rem;
    display: flex;
    /* flex-direction: column; */
    align-items: flex-start;
    justify-content: space-between;
    margin: 4px;
    padding: 10px;    
}
div.left {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
}
div.photo {
    margin-top: 2rem;
}

.pothole-image {    
    width: 20rem;
    height: 20rem;
}

.details-container{
    margin: 0;
    height: 100%;
    background: linear-gradient(to left, #b1a1ed, rgb(112, 176, 219));
    background-repeat: no-repeat;
    background-attachment: fixed;
}
</style>