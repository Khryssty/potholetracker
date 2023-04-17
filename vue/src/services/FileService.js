import axios from 'axios';

export default {

  savePhoto(file) {
    return axios.post('/uploadFile', file)
  },

  getPhoto(photoName) {
    return axios.get(`/downloadFile/${photoName}`, {responseType: 'blob'});
  }

}