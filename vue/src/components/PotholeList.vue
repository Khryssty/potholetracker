<template>
  <div class="pothole-list">
    <div>
      <table>
        <thead>
          <tr>
            <th>POTHOLE ID</th>
            <th>STATUS</th>
            <th>SEVERITY</th>
            <th>LAST UPDATE</th>
            <th>UPDATED BY</th>
            <th>ADDRESS/LAT-LONG</th>            
            <th>PHOTO</th>
          </tr>
        </thead>
        <tbody>
          <template v-for="pothole in $store.state.potholes">            
            <template  v-if="!(!currentUser.username || currentUser.authorities[0].name !== 'ROLE_ADMIN') ||
                  !(pothole.status === 'deleted' || pothole.status === 'repaired')">                  
              <tr v-bind:key="pothole.potholeId">
                  <td>{{ pothole.potholeId }}                      
                    <button v-bind:key="pothole.potholeId" v-if = "pothole.hasChanges" v-on:click="saveChanges(pothole)">Save</button>
                  </td>            

                  <template v-if="currentUser.username && currentUser.authorities[0].name === 'ROLE_ADMIN'">
                    
                    <td>
                      <select class="status" v-model="pothole.status" v-bind:key="pothole.status" @change="onStatusChange(pothole)">
                        <option
                          v-for="option in statusOptions"
                          v-bind:key="option.value"
                          v-bind:value="option.value"
                          v-bind:selected="pothole.status === option.value"
                        >
                          {{ option.label }}
                        </option>
                      </select>              
                    </td>                    
                    
                    <td>
                      <select class="severity" v-model="pothole.severity" v-bind:key="pothole.severity" @change="onSeverityChange(pothole)">>
                        <option 
                          v-for="option in severityOptions"
                          v-bind:key="option.value"
                          v-bind:value="option.value"
                          v-bind:select="pothole.severity === option.value">
                          {{option.label}}
                        </option>
                      </select>            
                    </td>                            
                </template>   


                <template v-else>
                    <td>{{ pothole.status }}</td>
                    <td>{{ pothole.severity }}</td>
                </template>

                <td>{{ pothole.statusDate }}</td>
                <td>{{ pothole.username }}</td>

                <td v-if="pothole.location.street == ''">
                  {{ pothole.location.lat }}, {{ pothole.location.lng }}
                </td>
                <td v-else>
                  {{ pothole.location.street }}, {{ pothole.location.city }},
                  {{ pothole.location.state }}, {{ pothole.location.postalCode }}
                </td>
                
                <td>{{ pothole.photo }}</td>              
              </tr>          
            </template>
          </template>          
        </tbody>
      </table>
    </div>
  </div>
</template>

<script>
import potholeService from "../services/PotholeService";

export default {
  name: "pothole-list",
  data(){
  return {      
    potholes: this.$store.potholes.map(pothole => ({ ...pothole, hasChanges: false}))
  }
  }, 
  computed: {    
    currentUser() {      
      return this.$store.state.user;
    },
    statusOptions() {
      return this.$store.state.statusOptions;
    },
    severityOptions() {
      return this.$store.state.severityOptions;
    },
  },
  methods: {
    getAllPotholes() {
      potholeService.viewPotholes().then((response) => {
        this.$store.commit("SET_POTHOLES", response.data);
      });
    },
    onStatusChange(pothole){
      pothole.hasChanges = true
    },
    onSeverityChange(pothole){
      pothole.hasChanges = true
    },
    saveChanges(pothole){
       //execute the update
       potholeService.updatePothole(pothole).then((response) => {
         this.$store.commit("SET_POTHOLES", response.data);
       });
      pothole.hasChanges = false
    },
    showSaveButton(){
      
    }
  },
  created() {
    this.getAllPotholes();
  },
};
</script>

<style scoped>

table {
  /* added */ border-collapse: collapse;
               width: 100%; /* given */
               border-color: grey; /* added */
}

tr {
  border: 5px solid #5D576B; /* added */
  border-radius: 25px;
  padding: 20px;
  width: 200px;
  height: 150px;
}
td {
  border: 5px solid #5D576B; /* added */
  
}
</style>

