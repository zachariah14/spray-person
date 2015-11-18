'use strict';

angular.module('gangstead.SprayPerson')
  .factory('People', function($resource){
    return $resource('api/person/:id', { id: '@id'}, {
      update: {
        method: 'PUT'
      }
    });
  });
