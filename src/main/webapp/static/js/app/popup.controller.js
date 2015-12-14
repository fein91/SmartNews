(function(){
    angular
        .module('smartnews.PopupController', [])
        .controller('PopupController', function($scope, popupService, articlesService, clientDataService) {
            var self = this;
            self.name = '';
            self.url = '';
            self.descr = '';
            //used on index.html
            $scope.popupService = popupService;

            self.submit = function() {
                var article = {
                    name: self.name,
                    url: self.url,
                    description: self.descr,
                    folderId: clientDataService.folderId
                };

                articlesService.addArticle(article);
                popupService.hidePopup();
                self.cleanData();
            }

            self.close = function() {
                popupService.hidePopup();
                self.cleanData();
            }

            self.cleanData = function() {
                self.name = '';
                self.url = '';
                self.descr = '';
            }
        });
})();
