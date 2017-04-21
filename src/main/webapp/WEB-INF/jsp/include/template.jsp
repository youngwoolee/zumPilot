<div id ="replyDiv" class="row">
    <div class='col-sm-10 reply' data-replyid={{replyId}} style = 'padding-left:{{depth}}*20px'>
        <h4 class='info'> {{writer.userName}}
            <small> {{regDate}}</small>
            <a class= 'replyWriteButton' href='javascript:;'><small> 답글</small></a>
            <a class= 'replyModifyButton' href='javascript:;'><small> 수정</small></a>
            <a class= 'replyDelete' href='javascript:;'><small> 삭제</small></a>
        </h4>
        <p>{{content}}</p>
    </div>
</div>