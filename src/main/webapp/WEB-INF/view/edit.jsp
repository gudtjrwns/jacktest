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
                    <form action="/" method="post" enctype="multipart/form-data" id="submitForm" onsubmit="submitValidation()">
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
                                        <input type="text" name="title" onchange="titleChange()" class="form-control" required="required" placeholder="제목을 입력해 주세요." />
                                    </td>
                                </tr>

                                <tr>
                                    <th class="text-center">내용</th>
                                    <td>
                                        <textarea name="contents" rows="5" style="resize: none; overflow: auto;" class="form-control" placeholder="내용을 입력해 주세요." ></textarea>
                                    </td>
                                </tr>

                                <tr>
                                    <th class="text-center">작성자 이름</th>
                                    <td>
                                        <input type="text" name="writer" class="form-control" required="required" placeholder="작성자 이름을 입력해 주세요." readonly />
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
                                <button type="submit" class="btn btn-info btn-sm">편집</button>
                                <!-- <button type="button" class="btn btn-default btn-sm" onclick="history.back();">취소</button> -->
                                <button type="button" class="btn btn-default btn-sm" onclick="location.href='./list'">취소</button>
                            </div>
                        </div>
                    </form>
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


<script>
    // function getUrlParams(){
    //     var params = {};
    //     window.location.search.replace(
    //         /[?&]+([^=&]+)=([^&]*)gi,
    //             function(str, key, value){params[key] = value;}
    //     );
    //     return params;
    // }
</script>




<%-- 폼 전송 함수 --%>
<script>
    var title = $("input[name='title']")[0];
    var contents = $("textarea[name='contents']")[0];
    var writer = $("input[name='writer']")[0];
    var uploadFile01 = $("input[name='uploadFile01']")[0];

    function transferForm() {
        var form = $("#submitForm")[0];
        var formData = new FormData();

        formData.append("title", title.value);
        formData.append("contents", contents.value);
        formData.append("writer", writer.value);
        formData.append("uploadFile01", uploadFile01.files[0]);


        $.ajax({
            type: 'POST',
            enctype: 'multipart/form-data',
            url: '${request.getContextPath}/addExecute',
            data: formData,
            contentType: false,
            processData: false,
            success: function (data) {
                alert("저장 완료!");
                location.href='./list';
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
<%-- 폼 전송 함수 --%>




<!-- form-submit 클릭 이벤트 -->
<script>
    $('#submitForm').on('submit', function(e){
        $('#myModal').modal('show');    // Modal 확인창을 보여준다.

        // Modal 확인창에서 'ok' 클릭하면 바로 Submit 한다.
        $('#saveChanges').click(function () {
            transferForm();
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