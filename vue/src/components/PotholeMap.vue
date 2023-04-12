<template>
  <div id="container">
    <div>
      <p>Latitude: {{latitude}}</p>
      <p>Longitude: {{longitude}}</p>
      <button @click="createPothole">Create Pothole</button>
    </div>
    <div id="map"></div>
  </div>
</template>

<script>
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
      layer: ''
    };
  },
  // props: ["lat", "lng"],
  methods: {
    setupLeafletMap() {
      const mapDiv = L.map("map").setView(this.center, 13);
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
        photo: 'placeholder'
      };
      potholeService
        .submitPothole(newPothole)
        .then((response) => {
          if (response.status === 201) {
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
#map {
  width: 600px;
  height: 600px;
}
div.my-icon {
  width: 200px;
  height: 200px;
  border-radius: 50%;
  background-color: red;
}
</style>