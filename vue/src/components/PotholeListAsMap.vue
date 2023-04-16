<template>
  <div class="map-container">
    <div id="map"></div>
  </div>
</template>

<script>
import "leaflet/dist/leaflet.css";
import L from "leaflet";
export default {
  name: "pothole-list-as-map",
  data() {
    return {
      center: [39.739071, -75.539787], // Lat/Long for Wilm, not a fan of hardcoding this but not sure of alternatives
      icon: L.divIcon({
        className: "my-icon",
      })
    };
  },
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
      this.$store.state.potholes.forEach((element) => {
        L.marker(element.location, {icon: this.icon}).addTo(mapDiv);
      });
    },
  },
  mounted() {
    this.setupLeafletMap();
  },
};
</script>

<style>
div.map-container {
  display: flex;
  justify-content: center;
}
#map {
  flex: 1;
}
div.my-icon {
  width: 200px;
  height: 200px;
  border-radius: 50%;
  background-color: red;
}
</style>