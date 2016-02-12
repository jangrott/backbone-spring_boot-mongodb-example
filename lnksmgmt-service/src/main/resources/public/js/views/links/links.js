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

                this.listenTo(this.linksCollection, 'reset', this.render);
            },

            events: {
                'submit': 'addUrl',
                'click .delUrlBtn': 'deleteUrl',
                'click .goToUrlBtn': 'goToUrl'
            },

            addUrl: function(e) {
                e.preventDefault();
                var urlValue = $(e.currentTarget).find('input[type=url]').val();

                var link = new LinkModel({url: urlValue, watched: false});

                if (link.isValid()) {
                    link.save();

                    this.linksCollection.reset();
                }

            },

            deleteUrl: function(e) {
                var linkId = $(e.currentTarget).attr('id');
                var link = this.linksCollection.get(linkId);
                link.destroy();

                this.linksCollection.reset();
            },

            goToUrl: function(e) {
                var linkId = $(e.currentTarget).attr('id');
                var link = this.linksCollection.get(linkId);
                link.set('watched', true);
                link.save();

                window.open(link.get('url'), '_blank');

                this.linksCollection.reset();
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