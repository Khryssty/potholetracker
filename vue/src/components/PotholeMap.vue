<template>
  <div id="report-container">
      <h2>Report A Pothole:</h2>
      <div class="no-location" v-if="latitude === ''">
        <p>Click the location on the map that corresponds with the pothole</p>
      </div>
      <div class="location-selected" v-else>
        <p>Latitude: {{latitude}}</p>
        <p>Longitude: {{longitude}}</p>
      </div>
      <div class="picture-upload">
        <p class="upload">Upload a picture of the pothole:</p>
        <input id="photo-upload" type="file" ref="file" alt="Upload a Picture" @change="savePhoto">
      </div>
      <button class="create-pothole" @click="createPothole">Create Pothole</button>
    <div id="select-map"></div>
  </div>
</template>

<script>
import fileService from '../services/FileService';
import potholeService from "../services/PotholeService";
import "leaflet/dist/leaflet.css";
import L from "leaflet";

export default {
  name: "pothole-map",
  data() {
    return {
      center: [39.739071, -75.539787], // Lat/Long for Wilm, not a fan of hardcoding this but not sure of alternatives
      icon: L.divIcon({
        className: 'my-icon'
      }),
      latitude: '',
      longitude: '',
      layer: '',
      photoName: 'No Photo',
      photoFile: ''
    };
  },
  // props: ["lat", "lng"],
  methods: {
    sendPhoto() {
      let formData = new FormData();
      formData.append("file", this.photoFile)
      fileService.savePhoto(formData)
        .then((response) => {
          if (response.status === 200) {
              // alert("File sent to server");
              //success
          }
        })
        .catch((error) => {
          this.handleErrorResponse(error, "adding");
        });
    },
    savePhoto(event) {
      this.photoFile = event.target.files[0];
      this.photoName = event.target.files[0].name;
    },
    setupLeafletMap() {
      const mapDiv = L.map("select-map").setView(this.center, 13);
      L.tileLayer(
        "https://api.mapbox.com/styles/v1/{id}/tiles/{z}/{x}/{y}?access_token={accessToken}",
        {
          attribution:
            '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors',
          maxZoom: 32,
          id: "mapbox/streets-v11",
          accessToken:
            "pk.eyJ1IjoiYnJhZGxleWN0YXlsb3IiLCJhIjoiY2xnZHJqMThhMDBvaTNybzdlNWQxb2M5ayJ9.ortNBF7OocvnnTGZxr063A",
        }
      ).addTo(mapDiv);
      mapDiv.on("click", (event) => {
        this.latitude = event.latlng.lat;
        this.longitude = event.latlng.lng;
        if(this.layer !== '') {
          this.layer.remove();
        }
        this.layer = L.marker(event.latlng, {icon: this.icon, title: event.latlng}).addTo(mapDiv);
      });
    },
    createPothole() {
      const newPothole = {
        location: {
          lat: this.latitude,
          lng: this.longitude
        },
        photo: this.photoName
      };
      potholeService
        .submitPothole(newPothole)
        .then((response) => {
          if (response.status === 201) {
            this.sendPhoto();
            this.$router.push({name: "viewPotholes"});
          }
        })
        .catch((error) => {
          this.handleErrorResponse(error, "adding");
        });
    },
    handleErrorResponse(error, verb) {
      if (error.response) {
        this.errorMsg =
          "Error " +
          verb +
          " pothole. Response received was '" +
          error.response.status +
          "'.";
      } else if (error.request) {
        this.errorMsg = "Error " + verb + " pothole. Server could not be reached.";
      } else {
        this.errorMsg =
          "Error " + verb + " pothole. Request could not be created.";
      }
    },
  },
  mounted() {
    this.setupLeafletMap();
  },
};
</script>

<style>
h2 {
  margin-bottom: .5rem;
}
#report-container {
  height: 100%;
  display: flex;
  flex-direction: column;
  align-items: center;
  flex: 1;
  justify-self: stretch;
}
#select-map {
  width: 100%;
  height: 100%;
  min-height: 40rem;
  flex: 1;
  align-self: stretch;
}
div.picture-upload {
  margin-top: .5rem;
  border: 2px solid black;
  border-radius: .5rem;
}
p{
  font-size: 1.25rem;
  margin: .5rem;
}
p.upload {
  font-size: 1rem;
}

::-webkit-file-upload-button {
  font-size: .75rem;
  margin-bottom: .75rem;
  padding: .75rem 1.5rem;
  border-radius: 1rem;
}
button.create-pothole {
  font-size: 1.25rem;
  margin: 1rem;
  padding: 1rem 2rem;
  border-radius: 1rem;
}
div.my-icon {
  width: 100px;
  height: 100px;
  border-radius: 50%;
  background-color: red;
}
</style>