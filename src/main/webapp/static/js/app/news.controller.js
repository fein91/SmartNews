angular
    .module('smartnews.NewsController', [])
    .controller('NewsController', function($scope, articlesService, clientsService, popupService, clientDataService) {
        var self = this;
        self.page = 1;
        self.size = 17;
        self.clientId = clientDataService.clientId;
        self.folderId = clientDataService.folderId;
        self.folders = clientDataService.folders;
        self.articles = clientDataService.articles;

        self.init = function() {
            return clientsService.getClient(self.clientId)
                .then(function successCallback(response){
                    var client = response.data;
                    console.log('init client from db: ' + JSON.stringify(client));
                    clientsService.initClientData(client);
                }, function errorCallback(response) {
                    console.log('got ' + response.status + ' error');
                    clientsService.initMockClientData();
                });
        };

        self.onFolderSelect = function(branch) {
            clientDataService.folderId = branch.id;
            clientDataService.articles = branch.articles
            self.page = 2;
        }

        self.nextArticlesPage = function() {
            if (self.folderId) {
                articlesService.nextPage(self.folderId, self.page, self.size)
                    .then(function(response){
                        var items = response.data;

                        if (items.length > 0) {
                            console.log('page#' + self.page + ' loaded');
                            for (var i = 0; i < items.length; i++) {
                                console.log('article#' + items[i].id +' loaded: ');
                                clientDataService.addArticle(items[i]);
                            }
                            self.page++;
                        }
                    });
            }
        };

        self.showNewArticlePopup = function() {
            popupService.showPopup();
        };

        self.init().then(function(resp) {
            self.nextArticlesPage();
        });
    });