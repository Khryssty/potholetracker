<template>
  <div class="pothole-list">
    <table class="list-of-potholes">
      <thead>
        <tr>
          <th>POTHOLE ID</th>
          <th
            v-if="
              currentUser.username &&
              currentUser.authorities[0].name === 'ROLE_ADMIN'
            "
          >
            ACTION
          </th>
          <th>STATUS</th>
          <th>SEVERITY</th>
          <th>LAST UPDATE</th>
          <th>UPDATED BY</th>
          <th>ADDRESS/LAT-LONG</th>
          <th>PHOTO</th>
        </tr>
      </thead>
      <tbody>
        <template v-for="pothole in paginatedPotholes">
          <template
            v-if="
              !(
                !currentUser.username ||
                currentUser.authorities[0].name !== 'ROLE_ADMIN'
              ) ||
              !(pothole.status === 'deleted' || pothole.status === 'repaired')
            "
          >
            <tr class="pothole-row" v-bind:key="pothole.potholeId">
              <router-link
                :to="{
                  name: 'viewPotholeDetails',
                  params: { potholeId: pothole.potholeId },
                }"
              >
                <td>{{ pothole.potholeId }}</td>
              </router-link>
              <template
                v-if="
                  currentUser.username &&
                  currentUser.authorities[0].name === 'ROLE_ADMIN'
                "
              >
                <td>
                  <button id="save"
                    v-bind:key="pothole.potholeId"
                    v-if="pothole.hasChanges"
                    v-on:click="saveChanges(pothole)"
                  >
                    Save
                  </button>
                </td>
              </template>

              <template
                v-if="
                  currentUser.username &&
                  currentUser.authorities[0].name === 'ROLE_ADMIN'
                "
              >
                <td>
                  <select
                    class="status"
                    v-model="pothole.status"
                    v-bind:key="pothole.status"
                    @change="onStatusChange(pothole)"
                  >
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
                  <select
                    class="severity"
                    v-model="pothole.severity"
                    v-bind:key="pothole.severity"
                    @change="onSeverityChange(pothole)"
                  >
                    >
                    <option
                      v-for="option in severityOptions"
                      v-bind:key="option.value"
                      v-bind:value="option.value"
                      v-bind:select="pothole.severity === option.value"
                    >
                      {{ option.label }}
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
    <div class="change-page">
      <button @click="prevPage">Prev</button>
      <h2 class="current-page">{{ currentPage }}</h2>
      <button @click="nextPage">Next</button>
    </div>
  </div>
</template>

<script>
import potholeService from "../services/PotholeService";

export default {
  name: "pothole-list-paginated",
  data() {
    return {
      potholes: this.$store.state.potholes.map((pothole) => ({
        ...pothole,
        hasChanges: false,
      })),
      pageSize: 6,
      currentPage: 1,
    };
  },
  computed: {
    paginatedPotholes() {
      return this.$store.state.potholes.filter((hole, index) => {
        let start = (this.currentPage - 1) * this.pageSize;
        let end = this.currentPage * this.pageSize;
        if (index >= start && index < end) {
          return true;
        }
      });
    },
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
    onStatusChange(pothole) {
      pothole.hasChanges = true;
    },
    onSeverityChange(pothole) {
      pothole.hasChanges = true;
    },
    saveChanges(pothole) {
      //execute the update
      potholeService.updatePothole(pothole).then((response) => {
        this.$store.commit("SET_POTHOLES", response.data);
      });
      alert("Update to the pothole has been saved.");
      pothole.hasChanges = false;
      //do a refresh page of pothole list
      window.location.reload();
    },
    showSaveButton() {},
    nextPage() {
      if (
        this.currentPage * this.pageSize <
        this.$store.state.potholes.length
      ) {
        this.currentPage++;
      }
    },
    prevPage() {
      if (this.currentPage > 1) {
        this.currentPage--;
      }
    },
  },
  created() {
    this.getAllPotholes();
  },
};
</script>

<style scoped>
div.change-page {
  margin-right: 1rem;
  align-self: flex-end;
  display: flex;
}
h2.current-page {
  margin: 1rem;
}

.change-page button {
  padding: 0.5rem 1rem;
  margin: 0.75rem 0;
  border-radius: 0.5rem;
}

.pothole-list {
  display: flex;
  flex-direction: column;
}

.list-of-potholes {
  flex: 1;
  border-collapse: collapse;
  margin: 0;
  font-size: 1em;
  font-family: sans-serif;
  text-transform: capitalize;
  text-align: center;
  min-width: 400px;
  box-shadow: 0 0 20px rgba(0, 0, 0, 0.15);
}

.list-of-potholes thead {
  background-color: #3a0098a1;
  color: lightgray;
  text-align: center;
}

.list-of-potholes th,
.list-of-potholes td {
  padding: 12px 15px;
}

.list-of-potholes tbody tr {
  border-bottom: 1px solid #dddddd;
}

.list-of-potholes tr:nth-of-type(even) {
  background-color: #f3f3f3;
}

.list-of-potholes tbody tr:last-of-type {
  border-bottom: 2px solid #3a0098a1;
}

.list-of-potholes tbody tr.pothole-row {
  font-weight: normal;
  color: black;
}
#save {
  background-color: green;
  color: white;
  border-radius: 7px;
  height: 30px;
  width: 60px;  
}
select{
  border-style: double;
  border-radius: 7px;
  height: 25px;
  width: 110px;
}
</style>

