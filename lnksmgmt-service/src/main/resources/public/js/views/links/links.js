(function() {

    define([
        'jquery',
        'underscore',
        'backbone',
        'models/links/link',
        'collections/links/links',
        'views/links/linksList',
        'text!templates/links/linksTemplate.html'
    ], function($, _, Backbone, LinkModel, LinkCollection, LinksListView, linksTemplate) {

        var LinksView = Backbone.View.extend({
            el: '#container',

            render: function() {
                console.log('list view rendering');
                this.$el.html(linksTemplate);

                var aLinks = [
                    new LinkModel({id: '1234', url: 'http://wykop.pl', isWatched: true}),
                    new LinkModel({id: '9876', url: 'http://onet.pl', isWatched: true})
                ];

                var linksCollection = new LinkCollection(aLinks);
                var linksListView = new LinksListView({collection: linksCollection});

                linksListView.render();

            }
        });

        return LinksView;
    });

})();