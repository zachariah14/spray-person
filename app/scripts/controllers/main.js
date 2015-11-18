'use strict';

angular.module('gangstead.SprayPerson')

  .controller('MainCtrl', function($scope, $location, version) {
    $scope.$path = $location.path.bind($location);
    $scope.version = version;
  })
  .controller('PersonCtrl', function($scope, $location, version, GetPeople) {
    $scope.$path = $location.path.bind($location);
    $scope.version = version;
    $scope.data = {};

    GetPeople.query(function(response) {
      $scope.data.people = response;
    });

  });
