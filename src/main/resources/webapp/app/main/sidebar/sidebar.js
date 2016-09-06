/**
 * Created by kevin on 9/5/16.
 */
angular.module('sidebar', []).component('sidebar', {
  templateUrl: 'main/sidebar/sidebar.html',
  controller: function SidebarController() {
    var self = this

    self.menus = sidebarMenus
    self.activeMenu = self.menus[0]
    self.expandMenu = {}

    self.toggle = function (menu) {
      if (menu.isActive) return

      if (!menu.subMenus) {
        menu.isActive = true
        self.activeMenu.isActive = false
        self.activeMenu = menu
      } else {
        if (self.expandMenu !== {} && self.expandMenu !== menu) {
          self.expandMenu.isFold = true
        }
        menu.isFold = !menu.isFold
        self.expandMenu = menu
      }
    }
  }
})
