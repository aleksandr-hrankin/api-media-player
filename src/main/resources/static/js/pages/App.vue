<template>
  <div>
    <div class="container">
      <div class="content">
        <div class="left__wrapper">
          <search-song :songs="songs"
                       @filterName="filterName"
                       @filterGenre="filterGenre"
                       @filterCountry="filterCountry"
                       @filterYear="filterYear"
          ></search-song>
          <songs :filterSongs="filterSongs" @songId="play"></songs>
        </div>
        <div class="right__wrapper">
          <upload-song @updatePlaylist="update"></upload-song>
        </div>
      </div>
    </div>
    <player-wrapper :id="songId"></player-wrapper>
  </div>
</template>

<script>
import PlayerWrapper from "../components/PlayerWrapper.vue";
import UploadSong from "../components/UploadSong.vue";
import SearchSong from "../components/SearchSong.vue";
import Songs from "../components/Songs.vue";
import axios from "axios";

export default {
  components: {
    PlayerWrapper,
    UploadSong,
    SearchSong,
    Songs
  },
  data() {
    return {
      songId: "",
      songs: [],
      filterSongs: [],
      filter: {
        name: "",
        genre:"",
        country:"",
        year:""
      }
    }
  },
  methods: {
    play(id) {
      this.songId = id;
    },
    update: function() {
      this.load();
    },
    load: function() {
      axios
          .get('http://localhost:8080/songs')
          .then(response => {
            this.songs = response.data
            this.filterSongs = response.data
            console.log(response.data);
          })
    },
    filterName: function(name) {
      let newSongs = [];
      for (let i = 0; i < this.songs.length; i++) {
        if (this.songs[i].name.toLowerCase().includes(name.toLowerCase())
            || this.songs[i].singer.toLowerCase().includes(name.toLowerCase())
            || this.songs[i].squad.toLowerCase().includes(name.toLowerCase())) {
          newSongs.push(this.songs[i]);
        }
      }
      this.filterSongs = newSongs;
    },
    filterGenre: function(genre) {
      if (genre === "") {
        this.copySongsToFilterSongs();
        return;
      }
      let newSongs = [];
      for (let i = 0; i < this.songs.length; i++) {
        if (this.songs[i].genre === genre) {
          newSongs.push(this.songs[i]);
        }
      }
      this.filterSongs = newSongs;
    },
    filterCountry: function(country) {
      if (country === "") {
        this.copySongsToFilterSongs();
        return;
      }
      let newSongs = [];
      for (let i = 0; i < this.songs.length; i++) {
        if (this.songs[i].country === country) {
          newSongs.push(this.songs[i]);
        }
      }
      this.filterSongs = newSongs;
    },
    filterYear: function(year) {
      if (year === "") {
        this.copySongsToFilterSongs();
        return;
      }
      let newSongs = [];
      for (let i = 0; i < this.songs.length; i++) {
        if (this.songs[i].year === year) {
          newSongs.push(this.songs[i]);
        }
      }
      this.filterSongs = newSongs;
    },
    copySongsToFilterSongs: function() {
      this.filterSongs = [];
      for (let i = 0; i < this.songs.length; i++) {
        this.filterSongs.push(this.songs[i]);
      }
    }
  },
  created() {
    this.load();
  }
}
</script>

<style lang="scss">
 .container {
   width: 100%;
   max-width: 1200px;
   padding: 0 15px;
   margin: 0 auto;
   display: block;
 }
 .content {
   display: flex;
   flex-direction: row;
   justify-content: space-between;
   margin-top: 15px;
   min-height: 105vh;
 }
 .right__wrapper,
 .left__wrapper {
   margin: 0 15px;
 }
 .left__wrapper {
   width: 70%;
 }
 .right__wrapper {
   width: 30%;
 }
</style>