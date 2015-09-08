(function(){
    var app = angular.module('smartnews', ['infinite-scroll', 'ArticlesService']);
    app.controller('NewsController', [ '$http', function($http) {
        var smartnews = this;



        this.nextPage = function(ArticlesService) {
            ArticlesService.nextPage(true);
        };
    }]);

    app.service('ArticlesService', function() {
            this.articles = [];
            this.fodlerId = 27;
            this.page = 1;
            this.size = 17;

            this.nextPage = function(isBackEndDisabled) {
                if (isBackEndDisabled) {
                    for (var i = 0; i < this.size; i++) {
                        var randomId = Math.floor((Math.random() * 1000) + 1);
                        var testArticle = {
                            id: randomId,
                            name: 'test article' + randomId,
                            description: 'test description test description test description test description test description test description'
                        };
                        this.articles.push(testArticle);
                    }
                } else {
                    var url = "/rest/folder/" + this.fodlerId + "/articles?page=" + this.page + '&size=' + this.size;
                    $http.get(url).success(function(data) {
                        var items = data;

                        if (items.length > 0) {
                            smartnews.page++;
                            for (var i = 0; i < items.length; i++) {
                                smartnews.articles.push(items[i]);
                            };
                        }
                    });
                }
            }
        }
    );
})();

