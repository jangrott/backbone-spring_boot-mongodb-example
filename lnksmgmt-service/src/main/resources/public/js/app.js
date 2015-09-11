(function() {

    define([
        'backbone',
        'router'
    ], function(Backbone, Router) {

        var initialize = function() {
            console.log('Initialize app ...');
            Router.initialize();
        };

        return {
            initialize: initialize
        };
    });
})();