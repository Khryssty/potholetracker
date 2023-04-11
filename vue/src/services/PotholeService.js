import axios from 'axios';

export default {

  viewPotholes() {
    return axios.get('/potholes')
  },

  viewPotholesById(id) {
    return axios.get(`/potholes/${id}`)
  },

  submitPothole(pothole){
      return axios.post('/potholes', pothole)
  },

  updatePothole(pothole){
      return axios.put('/potholes', pothole)
  },

  deletePothole(id){
      return axios.delete(`/potholes/${id}`)
  }

}
