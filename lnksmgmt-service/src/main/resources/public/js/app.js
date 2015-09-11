(function() {

    define([
        'jquery',
        'underscore',
        'backbone',
        'router'
    ], function($, _, Backbone, Router) {

        var initialize = function() {
            console.log('Initialize app ...');
            Router.initialize();
        };

        return {
            initialize: initialize
        };
    });
})();