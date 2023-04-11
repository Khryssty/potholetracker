<template>
  <div class="viewPotholes">
        <pothole-list></pothole-list>
  </div>
</template>

<script>
import PotholeList from '../components/PotholeList.vue'

export default {
    name: 'viewPotholes',
    components:{
        PotholeList
    }
}
</script>