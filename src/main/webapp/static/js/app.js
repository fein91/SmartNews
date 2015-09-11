(function(){
    var app = angular.module('smartnews', ['infinite-scroll', 'angularBootstrapNavTree']);

    app.service('articlesService', [ '$http', function($http) {

        this.nextPage = function(folderId, page, size) {
            var url = "/rest/folder/" + folderId + "/articles?page=" + page + '&size=' + size;
            return $http.get(url);
        }

        this.findRootFolderId = function(folders) {
            return folders.length > 0 ? folders[0]: undefined;
        }
    }]);

    app.service('clientsService', [ '$http', function($http) {

        this.getClient = function(clientId) {
            var url = "/rest/client/" + clientId;
            return $http.get(url);
        }
    }]);

    app.controller('NewsController', [ '$scope', 'articlesService', 'clientsService', function($scope, articlesService, clientsService) {
        this.clientId = 53;
        this.page = 1;
        this.size = 17;
        $scope.client = {};
        $scope.articles = [];
        $scope.folders = [];

        this.init = function() {
            clientsService.getClient(this.clientId).then(function(response){
                var client = response.data;
                for (var i = 0; i < client.folders.length; i++) {
                    $scope.folders.push(client.folders[i]);
                }
                $scope.client = client;
            });
        };

        this.nextPage = function() {
            var folderId = articlesService.findRootFolderId($scope.folders);

            if (folderId) {
                articlesService.nextPage(folderId, this.page, this.size).then(function(response){
                    var items = response.data;

                    if (items.length > 0) {
                        this.page++;
                        for (var i = 0; i < items.length; i++) {
                            $scope.articles.push(items[i]);
                        }
                    }
                });
            }
        };
    }]);
})();


