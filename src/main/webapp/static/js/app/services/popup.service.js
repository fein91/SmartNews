angular
    .module('smartnews.popupService', [])
    .factory('popupService', function(){
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