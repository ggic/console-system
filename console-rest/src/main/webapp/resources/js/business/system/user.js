/**
 * 增加用户操作
 * ==========================================
 */
$('#editUserForm').bootstrapValidator({

    message: 'This value is not valid',
    feedbackIcons: {
        valid: 'glyphicon glyphicon-ok',
        invalid: 'glyphicon glyphicon-remove',
        validating: 'glyphicon glyphicon-refresh'
    },
    submitHandler: function(validator, form, submitButton) {
        sendAjaxRequest(submitButton.attr('submit-url'),  form,$('#editUserModal'));
        sendQueryRequest4callBack(baseUrl+'/system/user/query', 1, null,completeCallBack);
    },
    fields: {

        password: {
            validators: {
                notEmpty: {
                    message: '密码必填'
                }
            }
        },
        fullName: {
            validators: {
                notEmpty: {
                    message: '姓名必填'
                }
            }
        },
        phone: {
            validators: {
                notEmpty: {
                    message: '手机必填'
                }
            }
        }
    }
});
/**
 * 增加用户操作
 * ==========================================
 */
$('#addUserForm').bootstrapValidator({

    message: 'This value is not valid',
    feedbackIcons: {
        valid: 'glyphicon glyphicon-ok',
        invalid: 'glyphicon glyphicon-remove',
        validating: 'glyphicon glyphicon-refresh'
    },
    submitHandler: function(validator, form, submitButton) {
        // Use Ajax to submit form data
        sendAjaxRequest(submitButton.attr('submit-url'),  form,$('#addUserModal'));
        sendQueryRequest4callBack(baseUrl+'/system/user/query', 1, null,completeCallBack);
    },
    fields: {
        name: {
            message: 'The username is not valid',
            validators: {
                notEmpty: {
                    message: 'The username is required and cannot be empty'
                },
                stringLength: {
                    min: 6,
                    max: 30,
                    message: '用户名必须大于6位小于30位'
                },
                regexp: {
                    regexp: /^[a-zA-Z0-9_]+$/,
                    message: '只能字母或数字'
                }
            }
        },
        password: {
            validators: {
                notEmpty: {
                    message: '密码必填'
                }
            }
        },
        fullName: {
            validators: {
                notEmpty: {
                    message: '姓名必填'
                }
            }
        },
        phone: {
            validators: {
                notEmpty: {
                    message: '手机必填'
                }
            }
        }
    }
});
//
/**
 * 页面初始化查询用户信息
 *
 * primaryKey:主键名称
 * title 标题栏 type取值规则 ： field从返回json取值，config，取配置项
 * @type {{primaryKey: string, columns: {title: string, type: string, field: string, config: string}[]}}
 *
 * ===================================================================================
 */
var grid_templete ={
    primaryKey:'userId',
    columns:[
        {title: "用户名",type:"field", field: "name",config:""}
        , {title: "姓名", type:"field",field: "fullName",config:""}
        , {title: "密码", type:"field",field: "password",config:""}
        , {title: "电话", type:"field",field: "phone",config:""}
        , {title: "邮箱",type:"field", field: "email",config:""}
        , {title: "操作", type:"config",field: '',config:'<a class="btn btn-link" role="button" name="editBtn" data-toggle="modal" data-target="#editUserModal" >编辑 </a>'}
        , {title: "1", type:"config",field: '',config:'<a class="btn btn-link" role="button" name="delBtn" data-toggle="modal"  >删除 </a>'}

    ]
};

/**
 *请求完成后执行-
 * 绑定编辑按钮动作
 */
var completeCallBack = function(){
    $("a[name='editBtn']").on("click",function(){
        var reqUrl = baseUrl+'system/user/';
        var _id = $(this).parent().parent().attr('_id');
        $.ajax({
            url: reqUrl+_id,
            cache: false,

            success: function (data) {
                try{
                    var status = data.status;
                    var content = data.content;
                    if(status =='SUCCESS'){
                        $("#editUserForm").find("input[name='userId']").val(content.userId);
                        $("#editUserForm").find("input[name='name']").val(content.name);
                        $("#editUserForm").find("input[name='password']").val(content.password);
                        $("#editUserForm").find("input[name='fullName']").val(content.fullName);
                        $("#editUserForm").find("input[name='phone']").val(content.phone);
                        $("#editUserForm").find("input[name='email']").val(content.email);

                    }else{
                        alert("操作异常: "+data.message);
                    }
                } catch(e) {
                    alert("操作异常: "+data.message);
                }
            },
            complete: function (XMLHttpRequest, textStatus) {
                //console.log('请求完成:' + textStatus);
            }
        });
    })

}
sendQueryRequest4callBack(baseUrl+'/system/user/query', 1, null,completeCallBack);


/**
 * 模态关闭时清除表单
 * ====================================
 */
$('#addUserModal').on('hidden.bs.modal', function (e) {
    clearForm( $("#addUserForm"),$("#addUserModal"));
})

