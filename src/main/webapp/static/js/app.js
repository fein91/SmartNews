(function(){
    var app = angular.module('smartnews', ['infinite-scroll', 'angularBootstrapNavTree', 'popup']);

    app.service('articlesService', [ '$http', function($http) {

        this.articles = [];

        this.addArticle = function(name, url, descr) {
            var article = {
                name: name,
                url: url,
                description: descr
            };
            this.articles.push(article);

            var url = "/rest/article";
            return $http.post(url, article);
        }

        this.nextPage = function(folderId, page, size) {
            var url = "/rest/folder/" + folderId + "/articles?page=" + page + '&size=' + size;
            console.log(url);
            return $http.get(url);
        }

        this.findRootFolderId = function(folders) {
            return folders.length > 0 ? folders[0].id : undefined;
        }
    }]);

    app.service('clientsService', [ '$http', function($http) {

        this.getClient = function(clientId) {
            var url = "/rest/client/" + clientId;
            console.log(url);
            return $http.get(url);
        }
    }]);

    app.controller('NewsController', [ '$scope', 'articlesService', 'clientsService', 'popupService', function($scope, articlesService, clientsService, popupService) {
        var self = this;
        self.clientId = 1111;
        self.page = 1;
        self.size = 17;
        self.popupService = popupService;
        $scope.client = {};
        $scope.articles = articlesService.articles;
        $scope.folders = [];
        var folderId;

        self.init = function() {
            return clientsService.getClient(self.clientId).then(function(response){
                var client = response.data;
                var folders = client.folders;
                for (var i = 0; i < folders.length; i++) {
                    $scope.folders.push(folders[i]);
                }
                folderId = articlesService.findRootFolderId(folders)
                $scope.client = client;
            });
        };

        self.onFolderSelect = function(branch) {
            folderId = branch.id;
            $scope.articles = branch.articles
            self.page = 2;
        }

        self.nextArticlesPage = function() {
            if (folderId) {
                articlesService.nextPage(folderId, self.page, self.size).then(function(response){
                    var items = response.data;

                    if (items.length > 0) {
                        self.page++;
                        for (var i = 0; i < items.length; i++) {
                            $scope.articles.push(items[i]);
                        }
                    }
                });
            }
        };

        self.showPopup = function() {
            self.popupService.showPopup();
        };

        self.init().then(function(resp) {
            self.nextArticlesPage();
        });
    }]);
})();


