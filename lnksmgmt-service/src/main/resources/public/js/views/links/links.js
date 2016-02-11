(function() {

    define([
        'backbone',
        'models/links/link',
        'collections/links/links',
        'views/links/linksList',
        'text!templates/links/linksTemplate.html'
    ], function(Backbone, LinkModel, LinksCollection, LinksListView, linksTemplate) {

        var LinksView = Backbone.View.extend({
            el: '#content',

            initialize: function() {
                this.linksCollection = new LinksCollection();
            },

            events: {
                'submit': 'addUrl'
            },

            addUrl: function(e) {
                e.preventDefault();
                var urlValue = $(e.currentTarget).find('input[type=url]').val();

                var link = new LinkModel({url: urlValue, watched: false});

                if (link.isValid()) {
                    link.save();
                    this.linksCollection.add(link);

                    this.render();
                }

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