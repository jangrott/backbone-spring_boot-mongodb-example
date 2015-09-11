(function() {

    define([
        'backbone'
    ], function(Backbone) {
        var LinkModel = Backbone.Model.extend({
            urlRoot: '/api/links'
        });

        return LinkModel;
    });

})();