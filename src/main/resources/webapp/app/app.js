/**
 * Created by kevin on 9/5/16.
 */
angular.module('cloudbox', [
  'ngAnimate',
  'ngRoute',
  'ngResource',
  'ngTable',
  'sidebar',
  'dashboard',
  'vboxHost'
]).config(['$locationProvider', '$routeProvider',
  function config($locationProvider, $routeProvider) {
    $locationProvider.hashPrefix('!')

    $routeProvider
      .when('/dashboard', {
        template: '<dashboard></dashboard>'
      })
      .when('/vboxHost', {
        template: '<vbox-host></vbox-host>'
      }).when('/editVboxHost', {
        template: '<vbox-host-edit></vbox-host-edit>'
      }).otherwise('/dashboard');
  }
])
