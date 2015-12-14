angular
    .module('smartnews.clientsService', [])
    .service('clientsService', function($http, articlesService, clientDataService) {
        this.getClient = function(clientId) {
            var url = "/rest/client/" + clientId;
            console.log("get request produced: " + url);
            return $http.get(url);
        }

        this.initClientData = function(client) {
            var folders = client.folders;
            for (var i = 0; i < folders.length; i++) {
                clientDataService.addFolder(folders[i]);
            }
            var rootFolder = articlesService.findRootFolder(folders);
            clientDataService.folderId = rootFolder.id
            clientDataService.client = client;
        }

        this.initMockClientData = function() {
            clientDataService.addFolder(
                {
                    name: "default folder",
                    parent: {},
                    children: [],
                    articles: []
                }
            );
        }
    });