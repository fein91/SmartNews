angular
    .module('smartnews.NewsController', [])
    .controller('NewsController', function($scope, articlesService, clientsService, popupService, clientDataService) {
        var self = this;
        self.page = 1;
        self.size = 17;
        self.clientData = clientDataService.getData();

        self.init = function() {
            return clientsService.getClient(self.clientData.clientId).then(function(response){
                var client = response.data;
                console.log('init client: ' + JSON.stringify(client));
                var folders = client.folders;
                for (var i = 0; i < folders.length; i++) {
                    self.clientData.folders.push(folders[i]);
                }
                var rootFolder = articlesService.findRootFolder(folders);
                self.clientData.folderId = rootFolder.id
                self.clientData.client = client;
                //self.clientData.articles = rootFolder.articles;
            });
        };

        self.onFolderSelect = function(branch) {
            self.clientData.folderId = branch.id;
            self.clientData.articles = branch.articles
            self.page = 2;
        }

        self.nextArticlesPage = function() {
            if (self.clientData.folderId) {
                articlesService.nextPage(self.clientData.folderId, self.page, self.size).then(function(response){
                    var items = response.data;

                    if (items.length > 0) {
                        console.log('page#' + self.page + ' loaded');
                        for (var i = 0; i < items.length; i++) {
                            console.log('article#' + items[i].id +' loaded: ');
                            self.clientData.articles.push(items[i]);
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