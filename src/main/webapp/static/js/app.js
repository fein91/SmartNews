(function(){
    var app = angular.module('smartnews', ['infinite-scroll', 'angularBootstrapNavTree']);

    app.service('articlesService', [ '$http', function($http) {

        this.nextPage = function(folderId, page, size) {
            var url = "/rest/folder/" + folderId + "/articles?page=" + page + '&size=' + size;
            return $http.get(url);
        }
    }]);

    app.service('clientsService', [ '$http', function($http) {

        this.getClient = function(clientId) {
            var url = "/rest/client/" + clientId;
            return $http.get(url);
        }
    }]);

    app.controller('NewsController', [ '$scope', 'articlesService', 'clientsService', function($scope, articlesService, clientsService) {
        this.folderId = 27;
        this.clientId = 53;
        this.page = 1;
        this.size = 17;
        $scope.articles = [];
        $scope.folders = [];

        this.init = function() {
            clientsService.getClient(this.clientId).then(function(response){
                var client = response.data;
                for (var i = 0; i < client.folders.length; i++) {
                    $scope.folders.push(client.folders[i]);
                }
            });
        };

        this.nextPage = function() {
            articlesService.nextPage(this.folderId, this.page, this.size).then(function(response){
                var items = response.data;

                if (items.length > 0) {
                    this.page++;
                    for (var i = 0; i < items.length; i++) {
                        $scope.articles.push(items[i]);
                    }
                }
            });
        };
    }]);
})();


