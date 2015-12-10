angular
    .module('smartnews.articlesService', [])
    .service('articlesService', function($http, clientDataService) {

        this.addArticle = function(article) {
            clientDataService.getData().articles.push(article);

            var url = "/rest/article";
            console.log("post request produced: " + url + " with body: " + JSON.stringify(article));
            return $http.post(url, article);
        }

        this.nextPage = function(folderId, page, size) {
            var url = "/rest/folder/" + folderId + "/articles?page=" + page + '&size=' + size;
            console.log("get request produced: " + url);
            return $http.get(url);
        }

        this.findRootFolder = function(folders) {
            return folders.length > 0 ? folders[0] : undefined;
        }
    });