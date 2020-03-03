<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="kor">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>jack-test</title>

    <!-- Bootstrap -->
    <link href="/resources/vendors/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
</head>

<body>
<div class="container body">
    <div class="main_container">
        <div class="col-md-12 col-sm-12 col-xs-12">
            <div class="x_panel">
                <div class="x_title">
                    <h1 class="text-center">
                        <a href="./list" style="color:black;">JACK TABLE</a>
                    </h1>
                    <div class="clearfix"></div>
                </div>

                <div class="x_content">
                    <div>
                        <div class="pull-left">
                            <div class="form-inline">
                                <div class="form-group">
                                    <button type="button" class="btn btn-sm btn-info" onclick="location.href='./add'">
                                        <i class="far fa-edit"></i> 신규등록
                                    </button>
                                </div>
                                <div class="form-group">
                                    <button type="button" id="deleteAllBtn" class="btn btn-sm btn-danger">
                                        <i class="far fa-edit"></i> 삭제
                                    </button>
                                </div>
                            </div>
                        </div>

                        <div class="pull-right">
                            <div class="form-inline">
                                <%-- 검색 폼 --%>
                                <form method="get" action="./distList">
                                    <div class="form-group">
                                        <select name="searchType" class="form-control">
                                            <option value="title">제목</option>
                                            <option value="contents">내용</option>
                                            <option value="writer">작성자</option>
                                            <option value="titleAndContents">제목+내용</option>
                                            <option value="contentsAndWriter">내용+작성자</option>
                                            <option value="name">제목+내용+작성자</option>
                                        </select>
                                    </div>

                                    <div class="form-group">
                                        <div class="input-group">
                                            <input type="text" name="searchValue" class="form-control"/>
                                            <span class="input-group-btn">
                                                <button type="submit" class="btn btn-sm btn-default">검색</button>
                                            </span>
                                        </div>
                                    </div>
                                </form>
                                <%-- 검색 폼 --%>
                            </div>
                        </div>
                    </div>

                    <div>
                        <table class="table table-bordered text-center">
                            <colgroup>
                                <col width="8%"/>
                                <col width="8%"/>
                                <col width="*%"/>
                                <col width="12%"/>
                                <col width="12%"/>
                                <col width="8%"/>
                                <col width="8%"/>
                                <col width="10%"/>
                                <col width="10%"/>
                            </colgroup>
                            <thead>
                            <tr class="bg-blue-sky" style="background-color: skyblue;">
                                <th class="text-center">
                                    <input type="checkbox" name="checked" id="allChecker"/>
                                </th>
                                <th class="text-center">No</th>
                                <th class="text-center">제목</th>
                                <th class="text-center">작성자</th>
                                <th class="text-center">작성일</th>
                                <th class="text-center">조회수</th>
                                <th class="text-center">댓글수</th>
                                <th class="text-center">편집</th>
                                <th class="text-center">삭제</th>
                            </tr>
                            </thead>
                            <form method="POST" action="./deleteAllExecute" id="submitForm">
                                <tbody id="listContainer">
                                </tbody>
                            </form>
                        </table>
                    </div>

                        
<%--                        &lt;%&ndash; 페이징 설정 &ndash;%&gt;--%>
<%--                        <c:if test="${totalPages <= endPage}">--%>
<%--                            <c:set var="endPage" value="${totalPages}"></c:set>--%>
<%--                            <c:set var="hasNext" value="false"></c:set>--%>
<%--                        </c:if>--%>
<%--                        &lt;%&ndash; 페이징 설정 &ndash;%&gt;--%>

<%--                        &lt;%&ndash; list 페이징 &ndash;%&gt;--%>
<%--                        <c:choose>--%>
<%--                            <c:when test="${pageNameValue eq 'list'}">--%>
<%--                                <div class="row" style="padding-top: 20px;">--%>
<%--                                    <div class="col-md-offset-5">--%>
<%--                                        <div class="btn-toolbar">--%>
<%--                                            <div class="btn-group">--%>
<%--                                                <button class="btn btn-default btn-sm" type="button" onclick="location.href='./${pageNameValue}?page=${startPage-2}'"><<</button>--%>
<%--                                                <c:choose>--%>
<%--                                                    <c:when test="${currentPage eq totalPages}">--%>
<%--                                                        <button class="btn btn-default btn-sm" type="button" onclick="location.href='./${pageNameValue}?page=${currentPage-2}'"><</button>--%>
<%--                                                    </c:when>--%>
<%--                                                    <c:otherwise>--%>
<%--                                                        <button class="btn btn-default btn-sm" type="button" onclick="location.href='./${pageNameValue}?page=${currentPage-2}'"><</button>--%>
<%--                                                    </c:otherwise>--%>
<%--                                                </c:choose>--%>
<%--                                                <c:forEach begin="${startPage}" end="${endPage}" step="1" var="count" >--%>
<%--                                                    <c:choose>--%>
<%--                                                        <c:when test="${(currentPage) eq count}">--%>
<%--                                                            <button class="btn btn-default btn-sm active" type="button" onclick="location.href='./${pageNameValue}?page=${count-1}'">${count}</button>--%>
<%--                                                        </c:when>--%>
<%--                                                        <c:when test="${(currentPage) != count}">--%>
<%--                                                            <button class="btn btn-default btn-sm" type="button" onclick="location.href='./${pageNameValue}?page=${count-1}'">${count}</button>--%>
<%--                                                        </c:when>--%>
<%--                                                    </c:choose>--%>
<%--                                                </c:forEach>--%>
<%--                                                <c:choose>--%>
<%--                                                    <c:when test="${currentPage eq totalPages}">--%>
<%--                                                        <button class="btn btn-default btn-sm" type="button" onclick="location.href='#'">></button>--%>
<%--                                                    </c:when>--%>
<%--                                                    <c:otherwise>--%>
<%--                                                        <button class="btn btn-default btn-sm" type="button" onclick="location.href='./${pageNameValue}?page=${currentPage}'">></button>--%>
<%--                                                    </c:otherwise>--%>
<%--                                                </c:choose>--%>
<%--                                                <c:choose>--%>
<%--                                                    <c:when test="${endPage eq totalPages}">--%>
<%--                                                        <button class="btn btn-default btn-sm" type="button" onclick="location.href='./${pageNameValue}?page=${endPage-1}'">>></button>--%>
<%--                                                    </c:when>--%>
<%--                                                    <c:otherwise>--%>
<%--                                                        <button class="btn btn-default btn-sm" type="button" onclick="location.href='./${pageNameValue}?page=${endPage-1}'">>></button>--%>
<%--                                                    </c:otherwise>--%>
<%--                                                </c:choose>--%>
<%--                                            </div>--%>
<%--                                        </div>--%>
<%--                                    </div>--%>
<%--                                </div>--%>
<%--                            </c:when>--%>
<%--                            &lt;%&ndash; list 페이징 &ndash;%&gt;--%>

<%--                            &lt;%&ndash; distList 페이징 &ndash;%&gt;--%>
<%--                            <c:when test="${pageNameValue eq 'distList'}">--%>
<%--                                <div class="row" style="padding-top: 20px;">--%>
<%--                                    <div class="col-md-offset-5">--%>
<%--                                        <div class="btn-toolbar">--%>
<%--                                            <div class="btn-group">--%>
<%--                                                <button class="btn btn-default btn-sm" type="button" onclick="location.href='./${pageNameValue}?page=${startPage-2}&searchType=${searchType}&searchValue=${searchValue}'"><<</button>--%>
<%--                                                <c:choose>--%>
<%--                                                    <c:when test="${currentPage eq totalPages}">--%>
<%--                                                        <button class="btn btn-default btn-sm" type="button" onclick="location.href='./${pageNameValue}?page=${currentPage-2}&searchType=${searchType}&searchValue=${searchValue}'"><</button>--%>
<%--                                                    </c:when>--%>
<%--                                                    <c:otherwise>--%>
<%--                                                        <button class="btn btn-default btn-sm" type="button" onclick="location.href='./${pageNameValue}?page=${currentPage-2}&searchType=${searchType}&searchValue=${searchValue}'"><</button>--%>
<%--                                                    </c:otherwise>--%>
<%--                                                </c:choose>--%>
<%--                                                <c:forEach begin="${startPage}" end="${endPage}" step="1" var="count" >--%>
<%--                                                    <c:choose>--%>
<%--                                                        <c:when test="${(currentPage) eq count}">--%>
<%--                                                            <button class="btn btn-default btn-sm active" type="button" onclick="location.href='./${pageNameValue}?page=${count-1}&searchType=${searchType}&searchValue=${searchValue}'">${count}</button>--%>
<%--                                                        </c:when>--%>
<%--                                                        <c:when test="${(currentPage) != count}">--%>
<%--                                                            <button class="btn btn-default btn-sm" type="button" onclick="location.href='./${pageNameValue}?page=${count-1}&searchType=${searchType}&searchValue=${searchValue}'">${count}</button>--%>
<%--                                                        </c:when>--%>
<%--                                                    </c:choose>--%>
<%--                                                </c:forEach>--%>
<%--                                                <c:choose>--%>
<%--                                                    <c:when test="${currentPage eq totalPages}">--%>
<%--                                                        <button class="btn btn-default btn-sm" type="button" onclick="location.href='#'">></button>--%>
<%--                                                    </c:when>--%>
<%--                                                    <c:otherwise>--%>
<%--                                                        <button class="btn btn-default btn-sm" type="button" onclick="location.href='./${pageNameValue}?page=${currentPage}&searchType=${searchType}&searchValue=${searchValue}'">></button>--%>
<%--                                                    </c:otherwise>--%>
<%--                                                </c:choose>--%>
<%--                                                <c:choose>--%>
<%--                                                    <c:when test="${endPage eq totalPages}">--%>
<%--                                                        <button class="btn btn-default btn-sm" type="button" onclick="location.href='./${pageNameValue}?page=${endPage-1}&searchType=${searchType}&searchValue=${searchValue}'">>></button>--%>
<%--                                                    </c:when>--%>
<%--                                                    <c:otherwise>--%>
<%--                                                        <button class="btn btn-default btn-sm" type="button" onclick="location.href='./${pageNameValue}?page=${endPage-1}&searchType=${searchType}&searchValue=${searchValue}'">>></button>--%>
<%--                                                    </c:otherwise>--%>
<%--                                                </c:choose>--%>
<%--                                            </div>--%>
<%--                                        </div>--%>
<%--                                    </div>--%>
<%--                                </div>--%>
<%--                            </c:when>--%>
<%--                        </c:choose>--%>
<%--                        &lt;%&ndash; distList 페이징 &ndash;%&gt;--%>

<%--                    </c:when>--%>
<%--                    <c:otherwise></c:otherwise>--%>
<%--                </c:choose>--%>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- jQuery -->
<script src="/resources/vendors/jquery/dist/jquery.min.js"></script>
<!-- Bootstrap -->
<script src="/resources/vendors/bootstrap/dist/js/bootstrap.min.js"></script>


<!-- view 모달 -->
<div class="modal fade view-modal" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header bg-blue-sky" >
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span></button>
                <h4 class="modal-title" style="font-size:1.2em;"><b>게시글 정보</b></h4>
            </div>
            <div class="modal-body">
                <table class="table table-bordered table-striped text-center m-b-0">
                    <tbody id="viewContainer">
                    <tr>
                        <td class="text-center">
                            <input type="hidden" name="getId">
                            정보를 불러오는 중 입니다.
                        </td>
                    </tr>
                    </tbody>
                </table>

                <h4>댓글</h4>

                <%--댓글 Add--%>
                <div id="addWrap">
                    <textarea name="content" id="contentsAdd" rows="3" class="form-control"
                                        type="text" style="resize:none; overflow: auto;"
                                        placeholder="댓글을 입력해 주세요."></textarea>
                    <input type="text" id="writerAdd" class="form-control" style="width: 200px;" placeholder="이름을 입력해 주세요.">
                    <div class="pull-right" style="margin-top:15px;">
                        <button type="button" id="replyAddBtn" class="btn btn-xs btn-primary"><i
                                class="fa fa-check"></i> 댓글 달기
                        </button>
                    </div>
                </div>
                <div id="editWrap" hidden>
                    <input type="hidden" name="getIdEdit" id="getIdEdit">
                    <textarea name="content" id="contentsEdit" rows="3" class="form-control"
                                        type="text" style="resize:none; overflow: auto;"
                                        placeholder="댓글을 입력해 주세요."></textarea>
                    <div class="pull-right" style="margin-top:5px;">
                        <button type="button" id="replyEditBtn" class="btn btn-xs btn-info"><i
                                class="fa fa-check"></i> 댓글 수정
                        </button>
                        <button type="button" id="cancelBtn" class="btn btn-xs btn-danger"><i
                                class="fa fa-check"></i> 취소
                        </button>
                    </div>
                </div>
                <%--댓글 Add--%>

                <div class="clearfix"></div>
                <div class="ln_solid" style="border-top:1.5px solid gray;"></div>

                <%--댓글 list--%>
                <table class="table table-striped m-b-0">
                    <tbody id="replyContainer">
                        <tr>
                            <td>정보를 불러오는 중 입니다.</td>
                        </tr>
                    </tbody>
                </table>
                <%--댓글 list--%> 
                
            </div>
            <div class="clearfix"></div>
            <div class="modal-footer">
                <div class="text-center">
                    <button type="button" class="btn btn-default btn-sm" data-dismiss="modal">닫기</button>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- view 모달 -->

<!-- delete 모달 -->
<div class="modal fade del-modal" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <form method="post" action="./delExecute">
            <input type="hidden" name="getId">
                <div class="modal-body text-center">
                    <h4>삭제 확인</h4>
                    <br/>
                    <p><span id="getName">""</span> 게시글을 삭제하시겠습니까?</p>
                    <br/>
                    <div class="text-center">
                        <button type="submit" class="btn btn-danger btn-sm">삭제</button>
                        <button type="button" class="btn btn-default btn-sm" data-dismiss="modal">취소</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
<!-- delete 모달 -->

<!-- deleteAll 모달 -->
<div class="modal fade delAll-modal" id="myModal" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-body text-center">
                <h4>삭제 확인</h4>
                <br/>
                <p>선택된 여러 게시글을 삭제하시겠습니까?</p>
                <br/>
                <div class="text-center">
                    <button type="button" id="saveChanges" class="btn btn-danger btn-sm">삭제</button>
                    <button type="button" class="btn btn-default btn-sm" data-dismiss="modal">취소</button>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- deleteAll 모달 -->

<!-- reply delete 모달 -->
<div class="modal fade replyDel-modal" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-body text-center">
                <h4>삭제 확인</h4>
                <br/>
                <p>댓글을 삭제하시겠습니까?</p>
                <br/>
                <div class="text-center">
                    <button type="button" id="replyDelBtn" class="btn btn-danger btn-sm">삭제</button>
                    <button type="button" class="btn btn-default btn-sm" data-dismiss="modal">취소</button>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- reply delete 모달 -->






<%-- list --%>
<script>
    $(()=>{
            getNoticePage();
        }
    );

    function getNoticePage() {

        $.ajax({
            type: 'GET',
            url: '${request.getContextPath}/getNoticePage',
            dataType: 'json',
            data: {},
            success: function (data) {

                var innerValue = '';

                for (var i = 0; i < data.noticeList.content.length; i++) {

                    innerValue += '\n' +
                        '                                        <tr>\n' +
                        '                                            <td>\n' +
                        '                                                <input type="checkbox" name="checkedArr" value="'+data.noticeList.content[i].id+'"/>\n' +
                        '                                            </td>\n' +
                        '                                            <td>' + (i + 1) + '</td>\n' +
                        '                                            <td>\n' +
                        '                                                <a class="font-blue text-bold underline pointer" style="cursor: pointer;" data-toggle="modal" data-target=".view-modal" data-whatever="' + data.noticeList.content[i].id + '">' + data.noticeList.content[i].title + '</a>' +
                        '                                            </td>\n' +
                        '                                            <td>'+data.noticeList.content[i].writer+'</td>\n' +
                        '                                            <td>'+timestampToStringDate(data.noticeList.content[i].credate)+'</td>\n' +
                        '                                            <td data-whatever="'+data.noticeList.content[i].id+'" class="view-count-'+data.noticeList.content[i].id+'}">'+data.noticeList.content[i].viewcnt+'</td>\n' +
                        '                                            <td data-whatever="'+data.noticeList.content[i].id+'" class="reply-count-'+data.noticeList.content[i].id+'">'+data.noticeList.content[i].replycnt+'</td>\n' +
                        '                                            <td>\n' +
                        '                                                <button type="button" class="btn btn-xs btn-success m-0" onclick="location.href=\'./edit?noticeId='+data.noticeList.content[i].id+'\'">편집</button>\n' +
                        '                                            </td>\n' +
                        '                                            <td>\n' +
                        '                                                <button type="button" class="btn btn-xs btn-danger m-0" data-toggle="modal" data-target=".del-modal" data-whatever="'+data.noticeList.content[i].id+'">삭제</button>\n' +
                        '                                            </td>\n' +
                        '                                        </tr>';
                }

                $("#listContainer").html(innerValue);
            },
            error: function (request, status, error){
                alert("에러가 발생했습니다.");
                console.log("code:" + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);
            },
            complete: function (data){
            }
        })
    }
</script>
<%-- list --%>





















<!-- 파일 다운로드 -->
<script>
    function downloadFileData(data) {
        location.href = './downloadNoticeFileData/id=' + data;
    }
</script>

<!-- 파일 다운로드 -->














<!-- view 모달 -->
<script>
    $('.view-modal').on('show.bs.modal', function (event) {
        var button = $(event.relatedTarget);
        var recipient1 = button.data('whatever');
        var viewContainer = document.getElementById("viewContainer");
        var innerValue = '';
        var innerFileValue = '';
        var viewCountBox = document.getElementsByClassName("view-count-"+recipient1);
        var replyContainer = document.getElementById("replyContainer");


        // 게시글 정보 불러오기
        $.ajax({
            type: 'POST',
            url: '${request.getContextPath}/findByNoticeOne/id=' + recipient1,
            dataType: 'json',
            data: {},
            success: function (data) {
                if(data.filename === 'NONE') {
                    innerFileValue = '등록된 파일이 없습니다.';
                } else {
                    innerFileValue = '<a onclick="downloadFileData('+data.id+')">'+data.filename+'</a>';
                }

                innerValue = '<input type="hidden" name="getId" value="' + data.id + '"/>'+
                    '<tr><th class="text-center">제목</th><td>' + data.title + '</td></tr>'+
                    '<tr><th class="text-center">내용</th><td>' + data.contents + '</td></tr>'+
                    '<tr><th class="text-center">작성자</th><td>' + data.writer + '</td></tr>'+
                    '<tr><th class="text-center">작성일</th><td>' + data.credate + '</td></tr>'+
                    '<tr><th class="text-center">조회수</th><td>' + data.viewcnt + '</td></tr>'+
                    '<tr><th class="text-center">댓글수</th><td>' + data.replycnt + '</td></tr>'+
                    '<tr><th class="text-center">파일</th><td>' + innerFileValue + '</td></tr>';


                viewContainer.innerHTML = innerValue;
                viewCountBox[0].innerHTML = data.viewcnt;
            }
        })


        // 댓글 목록 불러오기
        showReplyList(recipient1);
    });
</script>
<!-- view 모달 -->



<!-- delete 모달 -->
<script>
    $('.del-modal').on('show.bs.modal', function (event) {
        var button = $(event.relatedTarget);
        var recipient1 = button.data('whatever');
        var getId = document.getElementsByName("getId");
        var getName = document.getElementById("getName");
        var innerValue = '';

        $.ajax({
            type: 'POST',
            url: '${request.getContextPath}/findByNoticeOne/id=' + recipient1,
            dataType: 'json',
            data: {},
            success: function (data) {
                innerValue = '<b>"' + data.writer + '님의 ' + data.title + '"</b>';

                getName.innerHTML = innerValue;
                getId[1].value = data.id;
            }
        })
    });
</script>
<!-- delete 모달 -->



<!-- 댓글 -->
<script>
    // 댓글 View
    function showReplyList(dataId) {
        var replyContainer = document.getElementById("replyContainer");

        $.ajax({
            type: 'GET',
            url: '${request.getContextPath}/findAllReplyListByDistIdEquals/distName=notice&distId=' + dataId,
            dataType: 'json',
            data: {},
            success: function (data) {
                var innerText = '';

                if(data.length === 0){
                    innerText = '<tr><td>댓글이 없습니다.</td></tr>';

                }else {
                    for(var i=0; i<data.length; i++){
                        innerText += '<tr><td><div>'+
                            '' + data[i].writer + ' <br> ' + timestampToStringDate(data[i].credate) + ''+
                            '</div>'+
                            '<p style="margin: 10px 0;">'+data[i].contents+'</p>'+
                            '<div><span style="color:blue; font-weight:500; cursor:pointer;" onclick="replyEditFnc(' + data[i].id + ');">수정</span> / <span style="color:red; font-weight:500; cursor:pointer;" data-toggle="modal" data-target=".replyDel-modal" data-whatever="'+data[i].id+'" data-value="'+data[i].noticeid+'">삭제</span></div>'+
                            '</td></tr>';
                    }
                }

                replyContainer.innerHTML = innerText;

            },
            error: function (request, status, error){
                alert("에러가 발생했습니다.");
                console.log("code:" + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);
            },
            complete: function (data){
            }
        })
    }
    // 댓글 View


    // 댓글 Add
    $('#replyAddBtn').on('click', function () {
        var getId = document.getElementsByName("getId")[0].value;
        var contents = $("#contentsAdd").val();
        var writer = $("#writerAdd").val();
        var replyContainer = document.getElementById("replyContainer");
        var replyCountBox = document.getElementsByClassName("reply-count-"+getId);
        var replyCountValue = replyCountBox[0].innerHTML;


        if(contents === null || contents === ''){
            alert("댓글 내용을 입력해 주세요.");
            return false;
        }

        if(writer === null || writer === ''){
            alert("작성자 이름을 입력해 주세요.");
            return false;
        }

        $.ajax({
            type: 'POST',
            url: '${request.getContextPath}/replyAddExecute/distName=notice&distId=' + getId + '&contents=' + contents + '&writer=' + writer,
            dataType: 'json',
            data: {},
            success: function (data) {
                var innerText = '';

                // 댓글 채워넣기
                innerText = '<tr><td><div>'+
                    '' + data.writer + ' <br> ' + timestampToStringDate(data.credate) + ''+
                    '</div>'+
                    '<p style="margin: 10px 0;">'+data.contents+'</p>'+
                    '<div><span style="color:blue; font-weight:500; cursor:pointer;" onclick="replyEditFnc(' + data.id + ');">수정</span> / <span style="color:red; font-weight:500; cursor:pointer;" data-toggle="modal" data-target=".replyDel-modal" data-whatever="'+data.id+'" data-value="'+data.noticeid+'">삭제</span></div>'+
                    '</td></tr>';

                $("#replyContainer").append(innerText);

                alert("댓글 등록 성공!");

                // 작성 댓글 input박스에서 지우기
                $("#contentsAdd").val('');
                $("#writerAdd").val('');

                // 게시판 list에 댓글수 추가하기
                replyCountBox[0].innerHTML = parseInt(replyCountValue) + 1;

            },
            error: function (request, status, error){
                alert("에러가 발생했습니다.");
                console.log("code:" + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);
            },
            complete: function (data){
            }
        })
    });
    // 댓글 Add


    // 댓글 Edit
    $("#replyEditBtn").on('click', function() {
        var editReplyId = $("#getIdEdit").val();
        var editReplyContents = $("#contentsEdit").val();


        $.ajax({
            type: 'PUT',
            url: '${request.getContextPath}/replyEditExecute/replyId=' + editReplyId + '&contents=' + editReplyContents,
            dataType: 'json',
            data: {},
            success: function (data) {

                showReplyList(data.noticeid);

                $("#editWrap").hide();      // 댓글 편집 wrap 숨기기
                $("#addWrap").show();       // 댓글 추가 wrap 보여주기
                $("#contentsEdit").val(""); // 댓글 내용 없애기
                $("#getIdEdit").val("");    // 댓글 id 없애기
            },
            error: function (request, status, error){
                alert("에러가 발생했습니다.");
                console.log("code:" + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);
            },
            complete: function (data){
            }
        })
    })
    // 댓글 Edit


    // 댓글 Delete
    var button;
    var recipient1;
    var noticeId;
    $('.replyDel-modal').on('show.bs.modal', function (event) {
        button = $(event.relatedTarget);
        recipient1 = button.data('whatever');
        noticeId = button.data('value');
    })

    // 댓글 삭제 확인창에서 삭제 버튼 클릭 이벤트
    $("#replyDelBtn").on('click', function(){
        var replyCountBox = document.getElementsByClassName("reply-count-"+noticeId);
        var replyCountValue = replyCountBox[0].innerHTML;

        $.ajax({
            type: 'DELETE',
            url: '${request.getContextPath}/replyDeleteExecute/distName=notice&replyId=' + recipient1,
            dataType: 'text',
            data: {},
            success: function (data) {

                $(".replyDel-modal").modal('hide');

                showReplyList(noticeId);

                // 게시판 list에서 댓글수 추가하기
                replyCountBox[0].innerHTML = parseInt(replyCountValue) - 1;

            },
            error: function (request, status, error){
                alert("에러가 발생했습니다.");
                console.log("code:" + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);
            },
            complete: function (data){
            }
        })
    })
    // 댓글 Delete


    // 댓글 add-edit 박스
    // 댓글 수정 버튼 이벤트
    function replyEditFnc(data) {

        console.log("replyEditFnc");

        $("#addWrap").hide();   // 댓글 추가 wrap 숨기기
        $("#editWrap").show();  // 댓글 편집 wrap 보여주기

        var editReplyId = data;

        $.ajax({
            type: 'GET',
            url: '${request.getContextPath}/findByReplyOne/replyId=' + editReplyId,
            dataType: 'json',
            data: {},
            success: function (data) {
                $("#getIdEdit").val(data.id);
                $("#contentsEdit").val(data.contents);

            },
            error: function (request, status, error){
                alert("에러가 발생했습니다.");
                console.log("code:" + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);
            },
            complete: function (data){
            }
        })
    }


    // 댓글 수정 취소 버튼
    $("#cancelBtn").on('click', function(){
        $("#editWrap").hide();      // 댓글 편집 wrap 숨기기
        $("#addWrap").show();       // 댓글 추가 wrap 보여주기
        $("#contentsEdit").val(""); // 댓글 내용 없애기
        $("#getIdEdit").val("");    // 댓글 id 없애기
    })
    // 댓글 add-edit 박스
</script>
<!-- 댓글 -->




<!-- 체크박스 전체 선택 -->
<script>    
    var check = false;
    var chk = document.getElementsByName("checkedArr");
    var form = document.querySelector('#submitForm');
    var chkCheker = [];

    // 체크박스 전체 선택 버튼 이벤트
    $("#allChecker").click(function(){        
        if (check == false) {
            check = true;
            for (var i = 0; i < chk.length; i++) {
                chk[i].checked = true; //모두 체크
            }
        } else {
            check = false;
            for (var i = 0; i < chk.length; i++) {
                chk[i].checked = false; //모두 해제
            }
        }
    });


    // 삭제 버튼 이벤트  
    $('#deleteAllBtn').on('click', function(e){
        for (var i = 0; i < chk.length; i++) {
            if (chk[i].checked) {
                chkCheker[i] = true;
            }
        }

        // 선택된 체크박스 0개일 경우, 삭제 버튼 작동 안함
        if(chkCheker.length == 0) {
            alert("선택된 항목이 없습니다.");
            return false;
        }

        $('#myModal').modal('show');    // Modal 확인창을 보여준다.

        // Modal 확인창에서 'ok' 클릭하면 바로 Submit 한다.
        $('#saveChanges').click(function () {
            form.submit();
        });

        e.preventDefault();
    });
</script>
<!-- 체크박스 전체 선택 -->



<!-- timestamp To stringDate -->
<script>
    function timestampToStringDate(dataDate) {
        var date = new Date(dataDate);
        var dateString = "";
        var dateHours = "";
        var dateMinutes = "";
        var dateSeconds = "";

        if (date.getHours() <= 9) {
            dateHours = "0" + date.getHours();
        }else{
            dateHours = date.getHours();
        }

        if (date.getMinutes() <= 9) {
            dateMinutes = "0" + date.getMinutes();
        }else{
            dateMinutes = date.getMinutes();
        }

        if (date.getSeconds() <= 9) {
            dateSeconds = "0" + date.getSeconds();
        } else {
            dateSeconds = date.getSeconds();
        }

        dateString = date.getFullYear() + "-" + date.getMonth() + "-" + date.getDate() + " " + dateHours + ":" + dateMinutes;

        return dateString;
    }
</script>
<!-- timestamp To stringDate -->



</body>
</html>