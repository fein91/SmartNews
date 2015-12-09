angular
    .module('smartnews.clientsService', [])
    .service('clientsService', function($http) {
        this.getClient = function(clientId) {
            var url = "/rest/client/" + clientId;
            console.log("get request produced: " + url);
            return $http.get(url);
        }
    });