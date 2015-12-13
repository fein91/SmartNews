angular
    .module('smartnews.clientDataService', [])
    .factory('clientDataService', function() {
        var data = {};

        data.clientId = 1111;
        data.folderId = 0;
        data.client = {};
        data.folders = [];
        data.articles = [];

        data.addArticle = function(article) {
            data.articles.push(article);
        }

        data.addFolder = function(folder) {
            data.folders.push(folder);
        }

        return data;
    });
