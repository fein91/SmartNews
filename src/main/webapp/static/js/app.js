(function(){
    var app = angular.module('smartnews', ['infinite-scroll']);

    app.service('articlesService', [ '$http', function($http) {

        this.nextPage = function(folderId, page, size) {
            var url = "/rest/folder/" + folderId + "/articles?page=" + page + '&size=' + size;
            return $http.get(url);
        }
    }]);

    app.controller('NewsController', [ '$scope', 'articlesService', function($scope, articlesService) {
        var smartnews = this;
        this.fodlerId = 27;
        this.page = 1;
        this.size = 17;
        $scope.articles = [];

        this.nextPage = function() {
            articlesService.nextPage(this.fodlerId, this.page, this.size).then(function(response){
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

