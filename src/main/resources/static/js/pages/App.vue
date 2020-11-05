<template>
  <div>
    <upload-song></upload-song>
    <songs></songs>
  </div>
</template>

<script>
  import UploadSong from "../components/UploadSong.vue";
  import Songs from "../components/Songs.vue";

  export default {
    components: {
      UploadSong,
      Songs
    }
  }
</script>

<style>

</style>