(function(){
    var app = angular.module('smartnews', ['infinite-scroll']);
    app.controller('NewsController', [ '$http', function($http) {
        var smartnews = this;

        this.articles = [];
        this.fodlerId = 27;
        this.page = 1;
        this.size = 17;

        this.nextPage = function() {
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
        };

    }]);
})();

