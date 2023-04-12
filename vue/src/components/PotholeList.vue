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
                    <th>ADDRESS/LAT-LONG</th>
                    <th>REPORTED BY</th>
                    <th>PHOTO</th>                    
                </tr>
            </thead>
            <tbody>
                <tr v-for="pothole in this.$store.state.potholes" v-bind:key="pothole.potholeId">
                    <td>{{pothole.potholeId}}</td>
                    <td>{{pothole.status}}</td>
                    <td>{{pothole.statusDate}}</td>
                    <td>{{pothole.severity}}</td>
                    <td v-if="pothole.location.street == ''">{{pothole.location.lat}}, {{pothole.location.lng}}</td>
                    <td v-else>{{pothole.location.street}}, {{pothole.location.city}}, {{pothole.location.state}}, {{pothole.location.postalCode}}</td>
                    <td>{{pothole.username}}</td>
                    <td>{{pothole.photo}}</td>
                </tr>              
            </tbody>
      </table>
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
table {
    width: 100%;
}
</style>

