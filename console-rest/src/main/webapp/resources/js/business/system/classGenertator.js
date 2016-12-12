$('#btn_submit').click(function () {

    $.ajax({
        url:  $(this).attr('submit-url')+"?code="+$('#code').val(),
        cache: false,
        data: null,
        success: function (data) {

            $("#I").html(data.I);
            $("#T").html(data.T);
            $("#M").html(data.M);
        },
        complete: function (XMLHttpRequest, textStatus) {
            //console.log('请求完成:' + textStatus);
        }
    });

});
