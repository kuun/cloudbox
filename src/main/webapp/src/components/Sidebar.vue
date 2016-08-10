<template>
  <div class="sidebar">
    <div class="sidebar-bg brand">
    </div>
    <div id="sidebar" class="sidebar-bg">
      <ul v-for="menu in menus">
        <li>
          <a :class="['menu', {'menu-active': menu.isActive}]" v-link="{ name: menu.link }" @click="toggle(menu)">
            <span class="icon is-small" v-if="menu.icon">
              <i :class="['fa', menu.icon]"></i>
            </span>
              <span class="menu-label">{{ menu.label }}</span>
              <span class="menu-toggle-state" v-if="menu.subMenus">
              <i :class="['fa', {'fa-chevron-down': menu.isFold, 'fa-chevron-up': !menu.isFold}]"></i>
            </span>
          </a>
          <div class="submenu-bg" transition="expand" v-if="menu.subMenus && !menu.isFold">
            <ul class="submenu-group">
              <li v-for="subMenu in menu.subMenus">
                <a :class="['menu', 'submenu', {'menu-active': subMenu.isActive}]" v-link="{ name: subMenu.link }" @click="toggle(subMenu)">
                  <span class="menu-label">{{ subMenu.label }}</span>
                </a>
              </li>
            </ul>
          </div>
        </li>
      </ul>
    </div>
  </div>
</template>

<style scoped>
  #sidebar {
    position: fixed;
    top: 50px;
    bottom: 0;
    float: left;
    width: 230px;
    height: 100%;
    color: #ffffff;
    font-size: 16px;

    -webkit-touch-callout: none; /* iOS Safari */
    -webkit-user-select: none; /* Chrome/Safari/Opera */
    -moz-user-select: none; /* Firefox */
    -ms-user-select: none; /* Internet Explorer/Edge */
    user-select: none;
    /* Non-prefixed version, currently
                           not supported by any browser */
  }
  .sidebar-bg {
    /*background-color: rgba(48, 62, 69, 0.8);*/
  }
  .brand {
    height: 50px;
  }
  .sidebar {
    position: fixed;
    top: 0;
    bottom: 0;
    float: left;
    width: 230px;
    /*background: #607D8B;*/
    background: url("../assets/sidebar-bg.png");
    -webkit-background-size: contain;
    background-size: contain;
    box-shadow: 0 2px 5px 0 rgba(0,0,0,1);
    z-index: 2000;
  }

  .menu-label {
    margin-left: 4px;
  }

  .menu-toggle-state {
    float: right;
    margin-right: 6px;
    font-size: 14px;
  }

  .menu {
    width: 100%;
    height: 42px;
  }

  .menu:hover {
    color: #f9eae3;
  }

  .submenu {
    height: 36px;
  }

  .menu-active {
    color: #f9eae3;
    background-color: #009688;
    border-radius: 3px;
  }

  li > a {
    display: block;
    text-decoration: none;
    color: #162643;
    padding-left: 8px;
    padding-top: 8px;
    height: 100%;
    cursor: default;
  }

  ul {
    list-style-type: none;
    margin: 0;
    padding: 0;
  }

  .submenu-group {
    border-left: #CFD8DC dashed 1px;
    margin-left: 16px;
    margin-right: 5px;
    padding-left: 5px;
    font-size: 16px;
  }

  .submenu-bg {
    background-color: rgba(57, 68, 74, 0.29);
    overflow-y: hidden;
  }

  .expand-transition {
    max-height: 500px;
    transition: all 0.7s ease-in-out;
    -moz-transition: all 0.7s ease-in-out; /* Firefox 4 */
    -webkit-transition: all 0.7s ease-in-out; /* Safari, Chrome */
  }

  .expand-enter, .expand-leave {
    max-height: 0;
  }
</style>

<script>
  export default{
    data: () => {
      let menus = [{
        label: 'Dashboard',
        link: '!/dashboard',
        icon: 'fa-dashboard',
        isFold: true,
        isActive: true
      }, {
        label: 'System Settings',
        icon: 'fa-dashboard',
        isFold: true,
        subMenus: [{
          label: 'Manage Address',
          link: '!/dashboard',
          icon: 'fa-dashboard',
          isActive: false
        }, {
          label: 'Syslog',
          link: '!/dashboard',
          icon: 'fa-dashboard',
          isActive: false
        }, {
          label: 'Syslog',
          link: '!/dashboard',
          icon: 'fa-dashboard',
          isActive: false
        }, {
          label: 'Syslog',
          link: '!/dashboard',
          icon: 'fa-dashboard',
          isActive: false
        }, {
          label: 'Syslog',
          link: '!/dashboard',
          icon: 'fa-dashboard',
          isActive: false
        }, {
          label: 'Syslog',
          link: '!/dashboard',
          icon: 'fa-dashboard',
          isActive: false
        }, {
          label: 'Syslog',
          link: '!/dashboard',
          icon: 'fa-dashboard',
          isActive: false
        }]
      }, {
        label: 'System Settings',
        icon: 'fa-dashboard',
        isFold: true,
        subMenus: [{
          label: 'Manage Address',
          link: '!/dashboard',
          icon: 'fa-dashboard',
          isActive: false
        }, {
          label: 'Syslog',
          link: '!/dashboard',
          icon: 'fa-dashboard',
          isActive: false
        }]
      }]
      let activeMenu = menus[0]
      return {
        activeMenu: activeMenu,
        expandMenu: {},
        menus: menus
      }
    },
    methods: {
      toggle (menu) {
        if (menu.isActive) return

        if (!menu.subMenus) {
          menu.isActive = true
          this.activeMenu.isActive = false
          this.activeMenu = menu
        } else {
          if (this.expandMenu !== {} && this.expandMenu !== menu) {
            this.expandMenu.isFold = true
          }
          menu.isFold = !menu.isFold
          this.expandMenu = menu
        }
      }
    }
  }
</script>
