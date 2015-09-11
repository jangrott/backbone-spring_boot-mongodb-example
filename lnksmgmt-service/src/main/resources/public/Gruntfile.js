module.exports = function(grunt) {

    grunt.initConfig({
        jshint: {
            all: [
                'Gruntfile.js', 'js/**/*.js',
                '!js/text.js'
            ]
        }
    });

    grunt.loadNpmTasks('grunt-contrib-jshint');

};