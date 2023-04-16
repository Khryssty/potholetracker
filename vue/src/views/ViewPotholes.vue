<template>
  <div class="viewPotholes">    
        <pothole-list/>
        <pothole-list-as-map/>
        
  </div>
</template>

<script>
import PotholeList from '../components/PotholeList.vue'
import PotholeListAsMap from '../components/PotholeListAsMap.vue'

export default {
    name: 'viewPotholes',
    components:{
        PotholeList,
        PotholeListAsMap
    }
}
</script>
<style scoped>
div.viewPotholes {
  flex: 1;
  display: flex;
  flex-direction: column;
}
</style>