this["Handlebars"] = this["Handlebars"] || {};
this["Handlebars"]["templates"] = this["Handlebars"]["templates"] || {};

this["Handlebars"]["templates"]["replyForm"] = Handlebars.template({"1":function(container,depth0,helpers,partials,data) {
    var helper;

  return container.escapeExpression(((helper = (helper = helpers.content || (depth0 != null ? depth0.content : depth0)) != null ? helper : helpers.helperMissing),(typeof helper === "function" ? helper.call(depth0 != null ? depth0 : {},{"name":"content","hash":{},"data":data}) : helper)));
},"3":function(container,depth0,helpers,partials,data) {
    return "modifyButton";
},"5":function(container,depth0,helpers,partials,data) {
    return "writeButton";
},"compiler":[7,">= 4.0.0"],"main":function(container,depth0,helpers,partials,data) {
    var stack1, alias1=depth0 != null ? depth0 : {};

  return "<div class='answerForm replyForm'>\r\n    <textarea name= 'content' class='form-control replyContent'\r\n              rows='2' required>"
    + ((stack1 = helpers["if"].call(alias1,(depth0 != null ? depth0.content : depth0),{"name":"if","hash":{},"fn":container.program(1, data, 0),"inverse":container.noop,"data":data})) != null ? stack1 : "")
    + "</textarea>\r\n    <button class='btn btn-success "
    + ((stack1 = helpers["if"].call(alias1,(depth0 != null ? depth0.content : depth0),{"name":"if","hash":{},"fn":container.program(3, data, 0),"inverse":container.program(5, data, 0),"data":data})) != null ? stack1 : "")
    + "'>Submit</button>\r\n</div>";
},"useData":true});

this["Handlebars"]["templates"]["replyList"] = Handlebars.template({"1":function(container,depth0,helpers,partials,data) {
    return "            <a class= 'replyWriteButton' href='javascript:;'><small> 답글</small></a>\r\n            <a class= 'replyModifyButton' href='javascript:;'><small> 수정</small></a>\r\n            <a class= 'replyDelete' href='javascript:;'><small> 삭제</small></a>\r\n";
},"3":function(container,depth0,helpers,partials,data) {
    var helper;

  return "        <p>"
    + container.escapeExpression(((helper = (helper = helpers.content || (depth0 != null ? depth0.content : depth0)) != null ? helper : helpers.helperMissing),(typeof helper === "function" ? helper.call(depth0 != null ? depth0 : {},{"name":"content","hash":{},"data":data}) : helper)))
    + "</p>\r\n";
},"5":function(container,depth0,helpers,partials,data) {
    return "        <p>삭제된 댓글입니다.</p>\r\n";
},"compiler":[7,">= 4.0.0"],"main":function(container,depth0,helpers,partials,data) {
    var stack1, helper, alias1=depth0 != null ? depth0 : {}, alias2=helpers.helperMissing, alias3=container.escapeExpression;

  return "<div class='col-sm-10 reply' data-replyid="
    + alias3(((helper = (helper = helpers.replyId || (depth0 != null ? depth0.replyId : depth0)) != null ? helper : alias2),(typeof helper === "function" ? helper.call(alias1,{"name":"replyId","hash":{},"data":data}) : helper)))
    + " style = 'padding-left:"
    + alias3((helpers.multiple || (depth0 && depth0.multiple) || alias2).call(alias1,(depth0 != null ? depth0.depth : depth0),{"name":"multiple","hash":{},"data":data}))
    + "px'>\r\n    <h4 class='info'> "
    + alias3(container.lambda(((stack1 = (depth0 != null ? depth0.writer : depth0)) != null ? stack1.userName : stack1), depth0))
    + "\r\n        <small> "
    + alias3((helpers.formatTime || (depth0 && depth0.formatTime) || alias2).call(alias1,(depth0 != null ? depth0.regDate : depth0),{"name":"formatTime","hash":{},"data":data}))
    + "</small>\r\n"
    + ((stack1 = helpers["if"].call(alias1,(depth0 != null ? depth0.status : depth0),{"name":"if","hash":{},"fn":container.program(1, data, 0),"inverse":container.noop,"data":data})) != null ? stack1 : "")
    + "    </h4>\r\n"
    + ((stack1 = helpers["if"].call(alias1,(depth0 != null ? depth0.status : depth0),{"name":"if","hash":{},"fn":container.program(3, data, 0),"inverse":container.program(5, data, 0),"data":data})) != null ? stack1 : "")
    + "</div>";
},"useData":true});