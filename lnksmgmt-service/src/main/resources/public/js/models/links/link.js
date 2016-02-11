(function() {

    define([
        'backbone'
    ], function(Backbone) {
        var LinkModel = Backbone.Model.extend({
            urlRoot: '/api/links',

            validate: function(attr) {
                if (!attr.url || attr.url == '') {
                    return 'Url cannot be empty!';
                }
            }
        });

        return LinkModel;
    });

})();