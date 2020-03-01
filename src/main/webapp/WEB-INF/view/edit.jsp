<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                    <h1 class="text-center">JACK TABLE - 편집</h1>
                    <div class="clearfix"></div>
                </div>

                <div class="x_content">
                    <spring:form action="./editExecute" method="post" id="submitForm" modelAttribute="noticeOne" enctype="multipart/form-data">
                    <input type="hidden" name="noticeId" value=${noticeId}>
                        <div class="col-md-12">
                            <table class="table table-bordered m-0">
                                <colgroup>
                                    <col width="30%"/>
                                    <col width="*%"/>
                                </colgroup>
                                <tbody>

                                <tr>
                                    <th class="text-center">제목</th>
                                    <td>
                                        <spring:input path="title" type="text" class="form-control" required="required" placeholder="제목을 입력해 주세요."/>
                                        <spring:errors path="title"></spring:errors>
                                    </td>
                                </tr>

                                <tr>
                                    <th class="text-center">내용</th>
                                    <td>
                                        <spring:textarea path="contents" row="5" style="height: 50px; resize: none;" class="form-control" placeholder="내용을 입력해 주세요."/>
                                        <spring:errors path="contents"></spring:errors>
                                    </td>
                                </tr>

                                <tr>
                                    <th class="text-center">작성자 이름</th>
                                    <td>
                                        <input name="writer" value="${noticeOne.writer}" type="text" class="form-control" readonly/>
                                        <spring:errors path="writer"></spring:errors>
                                    </td>
                                </tr>

                                <tr>
                                    <th class="text-center">첨부파일</th>
                                    <td>
                                        <div class="form-group m-0">
                                            <div class="input-group m-0">
                                                <input type="text" class="form-control" value="${noticeOne.filename}" id="iptFileName01" placeholder="우측 버튼을 통해 첨부파일을 선택해주세요." readonly/>
                                                <div class="input-group-btn">
                                                    <button type="button" class="btn btn-info btn-sm" id="btnFile01">파일첨부</button>
                                                </div>
                                            </div>
                                            <div hidden>
                                                <input name="uploadFile01" type="file" id="iptFile01" accept="" onchange="javascript: document.getElementById('iptFileName01').value=this.files[0].name"/>
                                            </div>
                                        </div>
                                    </td>
                                </tr>

                                </tbody>
                            </table>
                        </div>

                        <div class="clearfix"></div>
                        <div class="ln_solid"></div>

                        <div class="col-xs-12">
                            <div class="text-center">
                                <button type="submit" class="btn btn-info btn-sm">편집</button>
                                <!-- <button type="button" class="btn btn-default btn-sm" onclick="history.back();">취소</button> -->
                                <button type="button" class="btn btn-default btn-sm" onclick="location.href='./list'">취소</button>
                            </div>
                        </div>
                    </spring:form>
                </div>
            </div>
        </div>
    </div>
</div>


<!-- jQuery -->
<script src="/resources/vendors/jquery/dist/jquery.min.js"></script>
<!-- Bootstrap -->
<script src="/resources/vendors/bootstrap/dist/js/bootstrap.min.js"></script>

<!-- 확인 모달 -->
<div class="modal fade Execute-modal" id="myModal" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-body text-center">
                <h4>편집 확인</h4>
                <br/>
                <p>게시글을 편집하시겠습니까?</p>
                <p class="font-orange text-bold">편집하면 게시글 목록으로 이동합니다.</p>
                <br/>
                <div class="text-center">
                    <button type="button" id="saveChanges" class="btn btn-primary btn-sm">편집</button>
                    <button type="button" class="btn btn-default btn-sm" data-dismiss="modal">취소</button>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- 확인 모달 -->


<!-- form-submit 클릭 이벤트 -->
<script>
    var form = document.querySelector('#submitForm');

    $('#submitForm').on('submit', function(e){
        $('#myModal').modal('show');    // Modal 확인창을 보여준다.

        // Modal 확인창에서 'ok' 클릭하면 바로 Submit 한다.
        $('#saveChanges').click(function () {
            form.submit();
        });

        e.preventDefault();
    });
</script>
<!-- form-submit 클릭 이벤트 -->


<!-- 파일 업로드 -->
<script>
    $(document).ready(function () {
        $("#btnFile01").on("click", function() {
            $("#iptFile01").click();
        });

        $("#iptFile01").change(function() {
            $("#iptFileName01").val($("#iptFile01").val().replace(/C:\\fakepath\\/i, ''));
        });
    });
</script>
<!-- 파일 업로드 -->

</body>
</html>