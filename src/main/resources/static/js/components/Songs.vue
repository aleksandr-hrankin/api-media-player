<template>
  <div style="border: 1px solid black">
    <p>song list</p>
    <div v-for="song in songs"
         :key="song.id">
      <p>{{song.name}}</p>
    </div>

  </div>
</template>

<script>
  import axios from 'axios';

  export default {
    data() {
      return {
        songs: []
      }
    },
    methods: {
      load: function() {
        axios
            .get('http://localhost:8080/songs')
            .then(response => {
              this.songs = response.data
              console.log(this.songs);
            })
      }
    },
    beforeMount() {
      this.load();
    }
  }
</script>

<style>

</style>