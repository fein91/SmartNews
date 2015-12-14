(function(){
    var app = angular.module('smartnews', [
        'infinite-scroll',
        'angularBootstrapNavTree',
        'smartnews.PopupController',
        'smartnews.NewsController',
        'smartnews.clientDataService',
        'smartnews.articlesService',
        'smartnews.clientsService',
        'smartnews.modalDialog',
        'smartnews.popupService'
    ]);
})();


