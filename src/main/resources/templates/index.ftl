<!DOCTYPE html>
<html>
    <head>
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="/resource/css/bootstrap.min.css" rel="stylesheet">
    </head>


    <body>

    <nav class="navbar navbar-default">
        <div class="container-fluid">

            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="#">줌 인터넷 게시판</a>
            </div>


            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">

                <form id="signin" class="navbar-form navbar-right" role="form">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                        <input id="email" type="email" class="form-control" name="email" value="" placeholder="Email Address">
                    </div>

                    <div class="input-group">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                        <input id="password" type="password" class="form-control" name="password" value="" placeholder="Password">
                    </div>

                    <button type="submit" class="btn btn-primary">Login</button>
                    <button type="button" class="btn btn-primary" onClick="location.href='/join.ftl';">Join</button>


                </form>
            </div>


        </div>
    </nav>


    <#include '/pages/board.ftl'>


    <!-- jQuery Version 1.11.1 -->
    <script src="/resource/js/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="/resource/js/bootstrap.min.js"></script>

    </body>
</html>