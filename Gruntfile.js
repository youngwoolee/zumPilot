module.exports = function(grunt) {

    // Project configuration.
    grunt.initConfig({
        pkg: grunt.file.readJSON('package.json'),

        copy : {
            jquery : {
                src : 'node_modules/jquery/dist/jquery.js',
                dest : 'src/main/webapp/assets/js/lib/jquery.min.js' },
            handlebars : {
                src : 'node_modules/handlebars/dist/handlebars.js',
                dest : 'src/main/webapp/assets/js/lib/handlebars.js' }

        },


        //handlebar 설정
        'handlebars': {
            options: {
                namespace: "Handlebars.templates",
                processName:function (filePath) {
                    var pattern=/handlebar\/(.+\/)*(.+)\.handlebars/gi;
                    return pattern.exec(filePath)[2];

                }
            },
            compile : {
                files: {
                    "src/main/webapp/assets/js/templates.js" : ["src/main/webapp/assets/handlebar/*.handlebars"]
                }
            }
        }


    });

    grunt.loadNpmTasks('grunt-contrib-handlebars');
    grunt.loadNpmTasks('grunt-contrib-copy');

    // Default task(s).
    grunt.registerTask('default', ['handlebars','copy']); //grunt 명령어로 실행할 작업

};