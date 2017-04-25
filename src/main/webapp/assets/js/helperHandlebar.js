var templateList = Handlebars.templates.replyList;
var templateForm = Handlebars.templates.replyForm;

Handlebars.registerHelper('multiple', function(context) {
    return context*20;
});

Handlebars.registerHelper('formatTime', function(timestamp) {
    var date = new Date(timestamp);
    var year = date.getFullYear();
    var month = date.getMonth()+1;
    var day = date.getDay();
    var hour = date.getHours();
    var minute = date.getMinutes();
    var seconds = date.getSeconds();
    var formattedTime = year+'-'+month+'-'+day+' '+hour + ':' + minute + ':' + seconds;
    return formattedTime;
});