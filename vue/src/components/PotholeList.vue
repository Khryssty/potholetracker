<template>
  <div class="pothole-list">
      <table>
            <thead>
                <tr>
                    <th>POTHOLE ID</th>
                    <th>STATUS</th>
                    <th>SEVERITY</th>
                    <th>ADDRESS</th>
                    <th>DATE</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="pothole in this.$store.state.potholes" v-bind:key="pothole.id">
                    <td>{{pothole.id}}</td>
                    <td>{{pothole.status}}</td>
                    <td>{{pothole.severity}}</td>
                    <td>{{pothole.address}}</td>
                    <td>{{pothole.date}}</td>
                </tr>
            </tbody>
      </table>
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

