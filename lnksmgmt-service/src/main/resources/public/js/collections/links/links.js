(function() {

    define([
        'jquery',
        'underscore',
        'backbone',
        'models/links/link'
    ], function($, _, Backbone, LinkModel) {

        var LinkCollection = Backbone.Collection.extend({
            model: LinkModel
        });

        return LinkCollection;
    });

})();