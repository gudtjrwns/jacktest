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
                    <h1 class="text-center">JACK TABLE - 등록</h1>
                    <div class="clearfix"></div>
                </div>

                <div class="x_content">
                    <spring:form action="./addExecute" method="post" id="submitForm" onsubmit="submitValidation()" modelAttribute="notice" enctype="multipart/form-data">
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
                                        <div class="input-group">
                                            <spring:input path="title" type="text"  onchange="titleChange()" class="form-control" required="required" placeholder="제목을 입력해 주세요."/>
                                            <spring:errors path="title"></spring:errors>
                                            <span class="input-group-btn">
                                                <button type="button" class="btn btn-info btn-sm" onclick="existsTitle()">중복확인</button>
                                                <input type="hidden" id="existsValid" value="NO">
                                            </span>
                                        </div>
                                        <span id="exists_check"></span>
                                    </td>
                                </tr>

                                <tr>
                                    <th class="text-center">내용</th>
                                    <td>
                                        <spring:textarea path="contents" type="textarea" row="5" style="resize: none; overflow: auto;" class="form-control" placeholder="내용을 입력해 주세요."/>
                                        <spring:errors path="contents"></spring:errors>
                                    </td>
                                </tr>

                                <tr>
                                    <th class="text-center">작성자 이름</th>
                                    <td>
                                        <spring:input path="writer" type="text" class="form-control" required="required" placeholder="작성자 이름을 입력해 주세요."/>
                                        <spring:errors path="writer"></spring:errors>
                                    </td>
                                </tr>

                                <tr>
                                    <th class="text-center">첨부파일</th>
                                    <td>
                                        <div class="form-group m-0">
                                            <div class="input-group m-0">
                                                <input type="text" class="form-control" id="iptFileName01" placeholder="우측 버튼을 통해 첨부파일을 선택해주세요." readonly/>
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
                                <button type="submit" class="btn btn-info btn-sm">등록</button>
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
                <h4>등록 확인</h4>
                <br/>
                <p>게시글을 등록하시겠습니까?</p>
                <p class="font-orange text-bold" style="color:orange;">등록하면 게시글 목록으로 이동합니다.</p>
                <br/>
                <div class="text-center">
                    <button type="button" id="saveChanges" class="btn btn-primary btn-sm">등록</button>
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
        if($("#existsValid").val() == "YES"){
            $('#myModal').modal('show');    // Modal 확인창을 보여준다.

            // Modal 확인창에서 'ok' 클릭하면 바로 Submit 한다.
            $('#saveChanges').click(function () {
                form.submit();
            });
        }       

        e.preventDefault();
    });
</script>
<!-- form-submit 클릭 이벤트 -->

<!-- 중복확인 Validation -->
<script>
    var title = document.getElementById("title");

    // 중복 여부 확인
    function submitValidation() {
        if ($("#existsValid").val() != "YES" ){
            $("#exists_check").text("중복 여부를 확인해 주세요.");
            $("#exists_check").css("color", "red");
            $("button:submit").attr('disabled', true);
            return false;

        } else {
            $("button:submit").attr('disabled', false);
        }
    }


    // ajax로 제목 중복확인
    function existsTitle() {
        $("#exists_check").css("color", "red");
        // var title = $("input#title[name='title']").val();
        
        if (title.value == null || title.value == '') {
            $("#exists_check").text("제목을 입력해 주세요.");
            $("button:submit").attr('disabled', true);

        } else {
            $.ajax({
                type: 'GET',
                url: '/findByTitleEquals/title=' + title.value,
                dataType: 'json',
                data: { },
                success: function (data) {

                    if( data == true ) { // 사용중
                        $("#exists_check").text("이미 사용중인 제목 입니다, 다시 입력해 주세요.")
                        $("button:submit").attr('disabled', true);

                    } else {                    
                        $("button:submit").attr('disabled', false);
                        $("#exists_check").text("사용 가능한 제목 입니다.")
                        $("#exists_check").css("color", "#04a26f")
                        $('#existsValid').val('YES')
                    }
                }
            })
        }
    }

    // 제목 변경시 제출 버튼 무효화
    function titleChange() {
        $("#exists_check").css("color", "red")
        $('#existsValid').val('NO')
        $("#exists_check").text("")
        $("button:submit").attr('disabled', false);
    }
</script>
<!-- 중복확인 Validation -->


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