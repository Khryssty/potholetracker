<template>
  <div class="map-container">
    <div id="map" @click="drawPotholes"></div>
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
        className: "my-icon"
      }),
      mapDiv: '',
    };
  },
  methods: {
    setupLeafletMap() {
      this.mapDiv = L.map("map").setView(this.center, 13);
      L.tileLayer(
        "https://api.mapbox.com/styles/v1/{id}/tiles/{z}/{x}/{y}?access_token={accessToken}",
        {
          maxZoom: 32,
          id: "mapbox/streets-v11",
          accessToken:
            "pk.eyJ1IjoiYnJhZGxleWN0YXlsb3IiLCJhIjoiY2xnZHJqMThhMDBvaTNybzdlNWQxb2M5ayJ9.ortNBF7OocvnnTGZxr063A",
        }
      ).addTo(this.mapDiv);
      this.drawPotholes();
    },
    drawPotholes() {
      this.$store.state.potholes.forEach((element) => {
        const newMarker = L.marker(element.location, {
          icon: this.icon,
          title: `ID: ${element.potholeId}\nSeverity: ${element.severity}\nStatus: ${element.status}\nLast Updated: ${element.statusDate}`,
          opacity: .65
        });
        newMarker.bindPopup(`ID: ${element.potholeId}`);
        newMarker.addTo(this.mapDiv);
      });
    },
    clickedMarker() {
      alert("click");
    }
  },
  mounted() {
    this.setupLeafletMap();
  },
};
</script>

<style>
div.map-container {
  flex-grow: 1;
  flex-shrink: 0;
  display: flex;
  justify-content: center;
}
#map {
  width:100%;
  min-height: 40rem;
  align-self: stretch;
  flex-grow: 1;
  flex-shrink: 0;
}
div.my-icon {
  border-radius: 50%;
  background-color: red;
}
</style>