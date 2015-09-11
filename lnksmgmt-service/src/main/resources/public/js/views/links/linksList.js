(function() {

    define([
        'jquery',
        'underscore',
        'backbone',
        'models/links/link',
        'collections/links/links',
        'text!templates/links/linksListTemplate.html'
    ], function($, _, Backbone, LinkModel, LinkCollection, linksListTemplate) {
        var LinksListView = Backbone.View.extend({
            el: '#links-list',

            render: function() {
                console.log('links list view rendering');

                var data = {
                    links: this.collection.models,
                    _: _
                };

                var compiledTemplate = _.template(linksListTemplate);
                this.$el.html(compiledTemplate(data));
            }
        });

        return LinksListView;
    });

})();