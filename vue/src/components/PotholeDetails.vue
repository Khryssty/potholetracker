<template>
  <div>      
      {{pothole.potholeId}},
      {{pothole.status}}, {{pothole.severity}}
  </div>
  
  
</template>

<script>
export default {
    name: "pothole-details",    
    computed: {
     pothole(){
         return this.$store.getters.pothole(this.$route.params.potholeId);
     }
    }
    // created(){
    //     const activePotholeID = this.$route.params.potholeId;
        
    // }
    

}
</script>

<style>

</style>