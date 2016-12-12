/**
 * Created by wangshaobo on 2015/10/21.
 */

/**
 *
 * @param reqUrl 请求路径
 * @param form 提交form表单
 */
var sendAjaxRequest = function (reqUrl,form) {
    if (reqUrl == undefined) {
        return;
    }
    var reqData = form.serializeArray();
    $.ajax({
        url: reqUrl ,
        cache: false,
        data: reqData,

        success: function (data) {

            try{
                var status = data.respStatus;
                if(status =='SUCCESS'){
                    alert("操作异常成功");
                }else{
                    alert("操作异常: "+data.respMsg);
                }
            } catch(e) {
                alert("操作异常: "+data.respMsg);
                form[0].reset();
            }

        },
        complete: function (XMLHttpRequest, textStatus) {
            //console.log('请求完成:' + textStatus);


        }
    });
}
/**
 *
 * @param reqUrl 请求路径
 * @param form 提交form表单
 * @param model 弹出模态层
 */
var sendAjaxRequest = function (reqUrl,form,model) {
    if (reqUrl == undefined) {
        return;
    }
    var reqData = form.serializeArray();
    $.ajax({
        url: reqUrl ,
        cache: false,
        data: reqData,
        type:"POST",
        success: function (data) {
            try{
                var status = data.status;
                if(status =='SUCCESS'){
                    clearForm(form,model);
                    $.messager.popup("操作成功!");
                }else{
                     $.messager.popup("操作异常: "+data.message);
                }
            } catch(e) {
                 $.messager.popup("操作异常: "+data.message);
            }
        },
        complete: function (XMLHttpRequest, textStatus) {
            //console.log('请求完成:' + textStatus);

        }
    });
}
/**
 * 当关闭模态时清除Form表单
 * @param form
 * @param model
 */
var clearForm = function(form,model){
    form[0].reset();
    model.modal('hide');
    form.data('bootstrapValidator').resetForm(true);
}