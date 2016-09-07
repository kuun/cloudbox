/**
 * Created by kevin on 9/7/16.
 */
(function () {
  var mod = angular.module('vboxHost', [])
  mod.controller('controller', VboxHostController)
  VboxHostController.$inject = ['NgTableParams']

  function VboxHostController(NgTableParams) {
    var self = this

    self.tableParams = new NgTableParams({}, {
      dataset: [
        {name: 'christian', age: 21, id: 1},
        {name: 'anthony', age: 88, id: 2},
        {name: 'anthony', age: 88, id: 3}
        ]
    })
  }
  mod.component('vboxHost', {
    templateUrl: 'vboxhost/vbox-host.html',
    controller: VboxHostController
  })
    .component('vboxHostEdit', {
      templateUrl: 'vboxhost/vbox-host-edit.html',
      controller: function VboxHostController() {

      }
    })
})()

