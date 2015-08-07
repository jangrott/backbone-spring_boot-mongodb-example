(function() {

    require.config({
        paths: {
            jquery: '../bower_components/jquery/dist/jquery',
            bootstrap: '../bower_components/bootstrap/dist/js/bootstrap',
            underscore: '../bower_components/underscore/underscore',
            backbone: '../bower_components/backbone/backbone',
        },
        shim: {
            underscore: {
                exports: '_'
            },
            bootstrap: {
                deps: [
                    'jquery'
                ]
            },
            backbone: {
                deps: [
                    'jquery', 'underscore'
                ]
            }
        }
    });

    require(['app'], function(App) {
        App.initialize();
    });

})();