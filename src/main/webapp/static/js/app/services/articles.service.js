angular
    .module('smartnews.articlesService', [])
    .service('articlesService', function($http, clientDataService) {
        var self = this;

        self.addArticle = function(article) {
            clientDataService.addArticle(article);

            var url = "/rest/article";
            console.log("post request produced: " + url + " with body: " + JSON.stringify(article));
            return $http.post(url, article);
        }

        self.nextPage = function(folderId, page, size) {
            var url = "/rest/folder/" + folderId + "/articles?page=" + page + '&size=' + size;
            console.log("get request produced: " + url);
            return $http.get(url);
        }

        self.findRootFolder = function(folders) {
            return folders.length > 0 ? folders[0] : undefined;
        }

        self.addRootFolder = function(branch) {
            branch.add_root_branch({
                name: 'root',
                children: []
            });
        }
    });