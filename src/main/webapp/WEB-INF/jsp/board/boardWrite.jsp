<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri ="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri ="http://java.sun.com/jsp/jstl/core" %>
<%@ page session="false" %>

<!DOCTYPE html>
<html>
<head>

    <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <style>
        div[contenteditable] {
            padding: 10px;
            border: 1px solid #555555;
            background-color: #ffffff;
            min-height: 50px;
        }
        .dragAndDropDiv {
            border: 2px dashed #92AAB0;
            width: 650px;
            height: 200px;
            color: #92AAB0;
            text-align: center;
            vertical-align: middle;
            padding: 10px 0px 10px 10px;
            font-size:200%;
            display: table-cell;
        }
        .progressBar {
            width: 200px;
            height: 22px;
            border: 1px solid #ddd;
            border-radius: 5px;
            overflow: hidden;
            display:inline-block;
            margin:0px 10px 5px 5px;
            vertical-align:top;
        }

        .progressBar div {
            height: 100%;
            color: #fff;
            text-align: right;
            line-height: 22px; /* same as #progressBar height if we want text middle aligned */
            width: 0;
            background-color: #0ba1b5; border-radius: 3px;
        }
        .statusbar{
            border-top:1px solid #A9CCD1;
            min-height:25px;
            width:99%;
            padding:10px 10px 0px 10px;
            vertical-align:top;
        }
        .statusbar:nth-child(odd){
            background:#EBEFF0;
        }
        .filename{
            display:inline-block;
            vertical-align:top;
            width:250px;
        }
        .filesize{
            display:inline-block;
            vertical-align:top;
            color:#30693D;
            width:100px;
            margin-left:10px;
            margin-right:5px;
        }
        .abort {
            background-color: #A8352F;
            -moz-border-radius: 4px;
            -webkit-border-radius: 4px;
            border-radius: 4px;
            display: inline-block;
            color: #fff;
            font-family: arial;
            font-size: 13px;
            font-weight: normal;
            padding: 4px 15px;
            cursor: pointer;
            vertical-align: top
        }

    </style>

</head>
<body>

    <c:import url="../include/navigation.jsp"/>

    <div class="container">

        <div style="padding : 30px;">
            <%--<form method="POST" action="/board/write" enctype="multipart/form-data" >--%>
                <%--<div class="form-group">--%>
                    <%--<label>제목</label>--%>
                    <%--<input type="text" name="title" class="form-control" id = "title">--%>
                <%--</div>--%>
                <%--<div class="form-group">--%>
                    <%--<label>작성자</label>--%>
                    <%--<input type="text" name="writer" class="form-control" value="${auth}" readonly >--%>
                <%--</div>--%>
                <%--<div class="form-group">--%>
                    <%--<label>내용</label>--%>
                    <%--&lt;%&ndash;<div id = "content" name="content" contenteditable="true">&ndash;%&gt;--%>
                        <%--&lt;%&ndash;<img src="http://lego.zumst.com/resources/current/images/img_zum_logo_20161201.png">&ndash;%&gt;--%>
                            <%--&lt;%&ndash;<div id="image_preview">&ndash;%&gt;--%>
                                <%--&lt;%&ndash;<img src="#" />&ndash;%&gt;--%>
                            <%--&lt;%&ndash;</div>&ndash;%&gt;--%>
                    <%--<textarea name="content" class="form-control" rows="5" id ="content"></textarea>--%>
                    <%--&lt;%&ndash;</div>&ndash;%&gt;--%>

                <%--</div>--%>


                <%--<button type="submit" class="btn btn-success clearfix pull-right">작성</button>--%>

            <%--</form>--%>


            <form id="board-write" method="POST" action="/board/write" enctype="multipart/form-data" >
                <div class="form-group">
                    <label>제목</label>
                    <input type="text" name="title" class="form-control" id = "title">
                </div>
                <div class="form-group">
                    <label>작성자</label>
                    <input type="text" name="writer" class="form-control" value="${auth}" readonly >
                </div>
                <div class="form-group">
                    <label>내용</label>
                    <%--<div id = "content" name="content" contenteditable="true">--%>
                    <%--<img src="http://lego.zumst.com/resources/current/images/img_zum_logo_20161201.png">--%>
                    <%--<div id="image_preview">--%>
                    <%--<img src="#" />--%>
                    <%--</div>--%>
                    <textarea name="content" class="form-control" rows="5" id ="content"></textarea>
                    <%--</div>--%>

                </div>

                <%--<div id="fileUpload" class="dragAndDropDiv">Drag & Drop Files Here</div>--%>
                <input type="file" name="upload">


                <button type="submit" class="btn btn-success clearfix pull-right">작성</button>

            </form>


            <script src="http://code.jquery.com/jquery-3.2.0.min.js"></script>
            <%--<script type="text/javascript">--%>
                <%--$(document).ready(function() {--%>
                    <%--var objDragAndDrop = $(".dragAndDropDiv");--%>

                    <%--$(document).on("dragenter", ".dragAndDropDiv", function (e) {--%>
                        <%--e.stopPropagation();--%>
                        <%--e.preventDefault();--%>
                        <%--$(this).css('border', '2px solid #0B85A1');--%>
                    <%--});--%>
                    <%--$(document).on("dragover", ".dragAndDropDiv", function (e) {--%>
                        <%--e.stopPropagation();--%>
                        <%--e.preventDefault();--%>
                    <%--});--%>
                    <%--$(document).on("drop", ".dragAndDropDiv", function (e) {--%>

                        <%--$(this).css('border', '2px dotted #0B85A1');--%>
                        <%--e.preventDefault();--%>
                        <%--var files = e.originalEvent.dataTransfer.files;--%>

                        <%--handleFileUpload(files, objDragAndDrop);--%>
                    <%--});--%>

                    <%--$(document).on('dragenter', function (e) {--%>
                        <%--e.stopPropagation();--%>
                        <%--e.preventDefault();--%>
                    <%--});--%>
                    <%--$(document).on('dragover', function (e) {--%>
                        <%--e.stopPropagation();--%>
                        <%--e.preventDefault();--%>
                        <%--objDragAndDrop.css('border', '2px dotted #0B85A1');--%>
                    <%--});--%>
                    <%--$(document).on('drop', function (e) {--%>
                        <%--e.stopPropagation();--%>
                        <%--e.preventDefault();--%>
                    <%--});--%>

                    <%--$(document).on('submit', "#board-write",function (e) {--%>
                        <%--console.log("submit start");--%>
                        <%--var files = e.originalEvent.dataTransfer.files;--%>
                                                <%----%>
                        <%--for (var i = 0; i < files.length; i++) {--%>
                            <%--console.log("for");--%>
                            <%--var fd = new FormData();--%>
                            <%--fd.append('file', files[i]);--%>
                            <%--sendFileToServer(fd,status);--%>
                        <%--}--%>

                    <%--});--%>



                    <%--function handleFileUpload(files, obj) {--%>
                        <%--for (var i = 0; i < files.length; i++) {--%>
                            <%--var fd = new FormData();--%>
                            <%--fd.append('file', files[i]);--%>

                            <%--var status = new createStatusbar(obj); //Using this we can set progress.--%>
                            <%--status.setFileNameSize(files[i].name, files[i].size);--%>

                        <%--}--%>
                    <%--}--%>

                    <%--var rowCount = 0;--%>

                    <%--function createStatusbar(obj) {--%>

                        <%--rowCount++;--%>
                        <%--var row = "odd";--%>
                        <%--if (rowCount % 2 == 0) row = "even";--%>
                        <%--this.statusbar = $("<div class='statusbar " + row + "'></div>");--%>
                        <%--this.filename = $("<div class='filename'></div>").appendTo(this.statusbar);--%>
                        <%--this.size = $("<div class='filesize'></div>").appendTo(this.statusbar);--%>
                        <%--this.progressBar = $("<div class='progressBar'><div></div></div>").appendTo(this.statusbar);--%>
                        <%--this.abort = $("<div class='abort'>중지</div>").appendTo(this.statusbar);--%>

                        <%--obj.after(this.statusbar);--%>

                        <%--this.setFileNameSize = function (name, size) {--%>
                            <%--var sizeStr = "";--%>
                            <%--var sizeKB = size / 1024;--%>
                            <%--if (parseInt(sizeKB) > 1024) {--%>
                                <%--var sizeMB = sizeKB / 1024;--%>
                                <%--sizeStr = sizeMB.toFixed(2) + " MB";--%>
                            <%--} else {--%>
                                <%--sizeStr = sizeKB.toFixed(2) + " KB";--%>
                            <%--}--%>

                            <%--this.filename.html(name);--%>
                            <%--this.size.html(sizeStr);--%>
                        <%--}--%>

                        <%--this.setProgress = function (progress) {--%>
                            <%--var progressBarWidth = progress * this.progressBar.width() / 100;--%>
                            <%--this.progressBar.find('div').animate({width: progressBarWidth}, 10).html(progress + "% ");--%>
                            <%--if (parseInt(progress) >= 100) {--%>
                                <%--this.abort.hide();--%>
                            <%--}--%>
                        <%--}--%>

                        <%--this.setAbort = function (jqxhr) {--%>
                            <%--var sb = this.statusbar;--%>
                            <%--this.abort.click(function () {--%>
                                <%--jqxhr.abort();--%>
                                <%--sb.hide();--%>
                            <%--});--%>
                        <%--}--%>
                    <%--}--%>

                    <%--function sendFileToServer(formData,status)--%>
                    <%--{--%>
                        <%--var uploadURL = "/board/test"; //Upload URL--%>
                        <%--var extraData ={}; //Extra Data.--%>
                        <%--var jqXHR=$.ajax({--%>
                            <%--xhr: function() {--%>
                                <%--var xhrobj = $.ajaxSettings.xhr();--%>
                                <%--if (xhrobj.upload) {--%>
                                    <%--xhrobj.upload.addEventListener('progress', function(event) {--%>
                                        <%--var percent = 0;--%>
                                        <%--var position = event.loaded || event.position;--%>
                                        <%--var total = event.total;--%>
                                        <%--if (event.lengthComputable) {--%>
                                            <%--percent = Math.ceil(position / total * 100);--%>
                                        <%--}--%>
                                        <%--//Set progress--%>
                                        <%--status.setProgress(percent);--%>
                                    <%--}, false);--%>
                                <%--}--%>
                                <%--return xhrobj;--%>
                            <%--},--%>
                            <%--url: uploadURL,--%>
                            <%--type: "POST",--%>
                            <%--contentType:false,--%>
                            <%--processData: false,--%>
                            <%--cache: false,--%>
                            <%--data: formData,--%>
                            <%--success: function(data){--%>
                                <%--status.setProgress(100);--%>

                                <%--//$("#status1").append("File upload Done<br>");--%>
                            <%--}--%>
                        <%--});--%>

                        <%--status.setAbort(jqXHR);--%>
                    <%--}--%>

                <%--});--%>



            <%--</script>--%>

            <%--<script type="text/javascript">--%>

                <%--$(function(){ $("#btn").click(function(e){--%>
                    <%--e.preventDefault();--%>
                    <%--var formData = new FormData();--%>
                    <%--formData.append("test2", $("input[name=test2]").val());--%>
                    <%--formData.append("test3", $("textarea[name=test3]").text());--%>
                    <%--formData.append("test4", $("input[name=test4]")[0].files[0]);--%>

                    <%--$.ajax({--%>
                        <%--url: '/board/test',--%>
                        <%--data: formData,--%>
                        <%--processData: false,--%>
                        <%--contentType: false,--%>
                        <%--type: 'POST',--%>
                        <%--success: function(data){--%>
                            <%--alert($("input[name=test4]")[0].files[0]);--%>
<%--//                            $("input[name=test2]").val("aaa");--%>
<%--//                            var content = document.getElementById('content');--%>
<%--//                            content.value = content.value + "<img src = http://lego.zumst.com/resources/current/images/img_zum_logo_20161201.png />"--%>
                            <%--$("#content").html('<img id="newImg" src="" alt="your file"/>');--%>
                            <%--$("#newImg").attr("src","http://lego.zumst.com/resources/current/images/img_zum_logo_20161201.png");--%>



                        <%--}--%>
                    <%--});--%>
                <%--});--%>
                <%--});--%>

            <%--</script>--%>

        </div>
    </div>

</body>

</html>