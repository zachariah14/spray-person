'use strict';

angular.module('gangstead.SprayPerson', ['ngAnimate', 'ngRoute','ngResource'])

  .config(function($locationProvider, $routeProvider) {

    $locationProvider.html5Mode(false);

    $routeProvider
      .when('/', {
        templateUrl: 'views/home.html',
        controller: 'MainCtrl'
      })
      .when('/person', {
        templateUrl: 'views/person.html',
        controller: 'PersonCtrl'
      })
      .when('/person/new', {
        templateUrl: 'views/person-new.html',
        controller: 'PersonNewCtrl'
      })
      .when('/person/:id', {
        templateUrl: 'views/person-view.html',
        controller: 'PersonViewCtrl'
      })
      .when('/person/:id/edit', {
        templateUrl: 'views/person-edit.html',
        controller: 'PersonEditCtrl'
      })
      .otherwise({
        redirectTo: '/'
      });

  });
