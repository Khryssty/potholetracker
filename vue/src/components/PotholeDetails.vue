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
        <img src ="../../public/img/cone.gif" alt="Stay Away from Potholes"/>
    </div>
  
  </div>  
</template>

<script>
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

</style>