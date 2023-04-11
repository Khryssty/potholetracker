<template>
  <div class="pothole-list">
      <div>
      <table>
            <thead>
                <tr>
                    <th>POTHOLE ID</th>
                    <th>STATUS</th>
                    <th>STATUS DATE</th>
                    <th>SEVERITY</th>
                    <th>ADDRESS</th>
                    <th>ASSIGNED TO</th>
                    <th>PHOTO</th>                    
                </tr>
            </thead>
            <tbody>
                <tr v-for="pothole in this.$store.state.potholes" v-bind:key="pothole.potholeId">
                    <td>{{pothole.potholeId}}</td>
                    <td>{{pothole.status}}</td>
                    <td>{{pothole.statusDate}}</td>
                    <td>{{pothole.severity}}</td>
                    <td>{{pothole.address}}</td>
                    <td>{{pothole.username}}</td>
                    <td>{{pothole.photo}}</td>
                </tr>              
            </tbody>
      </table>
      </div>
      
       <div>
           TBD: Maybe display a map here with outstanding potholes to repair.
       </div>
  </div>
</template>

<script>
import potholeService from '../services/PotholeService';

export default {
    name: 'pothole-list',    
    methods: {
        getAllPotholes() {
            potholeService.viewPotholes().then(response => {
                this.$store.commit("SET_POTHOLES", response.data);
            });
        }    
    },
    created(){
        this.getAllPotholes();
    }
}
</script>

<style scoped>

</style>>

