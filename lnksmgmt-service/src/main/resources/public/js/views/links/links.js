(function() {

    define([
        'backbone',
        'models/links/link',
        'collections/links/links',
        'views/links/linksList',
        'text!templates/links/linksTemplate.html'
    ], function(Backbone, LinkModel, LinksCollection, LinksListView, linksTemplate) {

        var LinksView = Backbone.View.extend({
            el: '#container',

            initialize: function() {
                this.linksCollection = new LinksCollection();
            },

            render: function() {
                console.log('list view rendering');
                this.$el.html(linksTemplate);

                this.linksCollection.fetch({success: function(linksCollection) {
                    var linksListView = new LinksListView({collection: linksCollection});

                    linksListView.render();
                }});

            }
        });

        return LinksView;
    });

})();