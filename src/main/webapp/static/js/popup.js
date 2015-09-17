(function(){
    var app = angular.module('popup', []);

    app.controller('PopupController', [ '$scope', function($scope) {
        var self = this;
        self.name = '';
        self.url = '';
        self.descr = '';
        self.show = true;

        self.submit = function() {
            console.log("submit")
        }

        self.close = function() {
            console.log("cancel");
            self.show = false;
        }
    }]);
})();


