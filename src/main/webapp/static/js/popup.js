(function(){
    var app = angular.module('popup', []);

    app.directive('modalDialog', function() {
        return {
            restrict: 'E',
            scope: {
                show: '='
            },
            replace: true, // Replace with the template below
            transclude: true, // we want to insert custom content inside the directive
            link: function(scope, element, attrs) {
                scope.dialogStyle = {};

                if (attrs.width)
                    scope.dialogStyle.width = attrs.width;
                if (attrs.height)
                    scope.dialogStyle.height = attrs.height;

                scope.hideModal = function() {
                    scope.show = false;
                };
            },
            templateUrl: 'partials/popup.html'
        };
    });

    app.factory('popupService', function(){
        var data = {
            popupShown: false
        }
        return {
            getData: function() {
                return data;
            },

            showPopup: function() {
                data.popupShown = true;
            },

            hidePopup: function() {
                data.popupShown = false;
            }
        };
    });

    app.controller('PopupController', [ '$scope', 'popupService', 'articlesService', function($scope, popupService, articlesService) {
        var self = this;
        self.name = '';
        self.url = '';
        self.descr = '';
        //used on index.html
        self.popupService = popupService;

        self.submit = function() {
            articlesService.addArticle(self.name, self.url, self.descr);
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
    }]);
})();
