module.exports = function(grunt) {

    // Project configuration.
    grunt.initConfig({
        pkg: grunt.file.readJSON('package.json'),
        //uglify 설정
        uglify: {
            options: {
                banner: '/* <%= grunt.template.today("yyyy-mm-dd") %> / ' //파일의 맨처음 붙는 banner 설정
            },
            build: {
                src: 'public/build/result.js', //uglify할 대상 설정
                dest: 'public/build/result.min.js' //uglify 결과 파일 설정
            }
        },
        //concat 설정
        concat:{
            basic: {
                src: ['public/js/common/util.js', 'public/js/app.js', 'public/js/lib/.js', 'public/js/ctrl/.js'],
                dest: 'public/build/result.js' //concat 결과 파일
            }
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

    // Load the plugin that provides the "uglify", "concat" tasks.
    grunt.loadNpmTasks('grunt-contrib-uglify');
    grunt.loadNpmTasks('grunt-contrib-concat');
    grunt.loadNpmTasks('grunt-contrib-handlebars');

    // Default task(s).
    grunt.registerTask('default', ['concat', 'uglify', 'handlebars']); //grunt 명령어로 실행할 작업

};