(function() {

    define([
        'backbone',
        'models/links/link'
    ], function(Backbone, LinkModel) {

        var LinkCollection = Backbone.Collection.extend({
            model: LinkModel,
            url: '/api/links'
        });

        return LinkCollection;
    });

})();