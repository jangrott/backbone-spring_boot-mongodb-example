(function() {

    define([
        'jquery',
        'underscore',
        'backbone',
        'views/links/links'
    ], function($, _, Backbone, LinksView) {
        var AppRouter = Backbone.Router.extend({
            routes: {
                'links': 'showLinks',

                '*actions': 'defaultAction'
            }
        });

        var initialize = function() {
            console.log('Initialize router ...');
            var app_router = new AppRouter();

            app_router.on('route:showLinks', function() {
                console.log('render view');
                var linksView = new LinksView();
                linksView.render();
            });

            app_router.on('route:defaultAction', function(actions) {
                console.log('no route: ', actions);
                app_router.navigate('links', true);
            });

            Backbone.history.start();
        };

        return {
            initialize: initialize
        };
    });

})();