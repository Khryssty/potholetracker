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
                    
                    <!-- In the dropdown list, i need the current status to be displayed instead of blank -->
                    <td v-if = "currentUser && currentUser.authorities[0].name === 'ROLE_ADMIN'">    
                        <select v-model="pothole.status" v-bind:key="pothole.status">
                            <option v-for="option in statusOptions" v-bind:key="option.value">{{option.label}}</option>
                        </select>                        
                    </td>                    

                    <!-- List of potholes is not displayed if no logged in user. Tried using v-else-if currentUser isEmpty or null, did not work-->                    
                    <td v-else>{{pothole.status}}</td>                    

                    <td>{{pothole.statusDate}}</td>

                    <!-- Once display issues resolved in status, this will be updated accordingly. -->                    
                    <td>{{pothole.severity}}</td>
                    <td v-if="pothole.location.street == ''">{{pothole.location.lat}}, {{pothole.location.lng}}</td>
                    <td v-else>{{pothole.location.street}}, {{pothole.location.city}}, {{pothole.location.state}}, {{pothole.location.postalCode}}</td>
                    <td>{{pothole.username}}</td>
                    
                    <td>{{pothole.photo}}</td>



                      <!--ADDED TO DELETE -->
                    <td v-if="currentUser && currentUser.authorities[0].name === 'ROLE_ADMIN'">
                    <button @click="deletePothole(pothole.potholeId)">Delete</button>

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
    computed:{
        currentUser(){
            return this.$store.state.user;
        },
        statusOptions(){
            return this.$store.state.statusOptions;
        }
    },
    methods: {
        getAllPotholes() {
            potholeService.viewPotholes().then(response => {
                this.$store.commit("SET_POTHOLES", response.data);
            });
        }    
    }, 
    //Need to fix 
    deletePothole(id) {
        potholeService.deletePothole(id).then()) => {
            this.getAllPotholes();
        }
    }    
    }

</script>

<style scoped>
table {
    width: 100%;
}
</style>

