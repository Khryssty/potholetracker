<template>
  <div class="details-container">    
     <div class="potholeId">POTHOLE ID : {{pothole.potholeId}}</div>   
     <div class="status">STATUS: {{pothole.status}}</div>
     <div class="severity">SEVERITY: {{pothole.severity}}</div>
     <div class="date">LAST UPDATE: {{pothole.statusDate}}</div> 
     <div class="modifiedBy">UDATED BY: {{pothole.username}}</div>
     <div class="address">ADDRESS: {{pothole.location.street}}, 
         {{pothole.location.city}}, {{pothole.location.state}}, {{pothole.location.postalCode}}
     </div>    
      
    <div class="photo">
        <img class="pothole-image" :src="potholeImage" alt="Pothole Photo">
        <!-- <img src ="../../public/img/cone.gif" alt="Stay Away from Potholes"/> -->
    </div>
    
  </div>  
</template>

<script>
import fileService from '../services/FileService.js'
export default {
    name: "pothole-details",
    data() {
        return {
            potholeImage: ''
        }
    },
    computed:{
        pothole(){
            return this.$store.getters.pothole;
        }
    },
    methods: {
        downloadFile() {
            fileService.getPhoto(this.pothole.photo)
                .then(response => {
                    if(response.status == 200) {
                        this.potholeImage = window.URL.createObjectURL(new Blob([response.data]));
                    }
                });
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
.details-container {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: space-evenly;
}
.pothole-image {
    width: 20rem;
    height: 20rem;
}

</style>