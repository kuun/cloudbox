<template>
  <div id="app">
    <sidebar></sidebar>
    <navbar></navbar>
    <div>
      <levelbar></levelbar>
      <router-view
        class="animated"
        transition="fade"
        transition-mode="out-in"
        keep-alive>
      </router-view>
    </div>
    <footer-bar></footer-bar>
  </div>
</template>

<style lang="css">
  @import "~font-awesome/css/font-awesome.css";
  @import "~bootstrap/dist/css/bootstrap.css";

  html body {
    height: 100%;
    width: 100%;
    overflow-x: hidden;
    overflow-y: hidden;
    margin: 0;
  }
  body {
    background: url("assets/body-bg.jpg") repeat;
  }
</style>

<script>
  import Navbar from './components/Navbar'
  import Sidebar from './components/Sidebar'
  import Levelbar from './components/Levelbar'
  import FooterBar from './components/FooterBar'

  export default {
    components: {
      Navbar,
      Sidebar,
      Levelbar,
      FooterBar
    }
  }
</script>

