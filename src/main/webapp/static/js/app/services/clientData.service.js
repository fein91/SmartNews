angular
    .module('smartnews.clientDataService', [])
    .factory('clientDataService', function() {
        var data = {
            clientId: 1111,
            folderId: 0,
            client: {},
            folders: [],
            articles: []
        };

        return {
            getData: function () {
                return data;
            }
        }
    });
