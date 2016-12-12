//日期集成
$.getScript(baseUrl+'resources/js/core/datetimepicker.js');
//渲染页面表格
$.getScript(baseUrl+'resources/js/core/tablegrid-pagination.js');

//gridtable模板
var grid_templete ={
    columns:[
        {title: "机构订单号", type:"field",field: "instOrderNo"}
        , {title: "渠道API代码", type:"field",field: "fundChannelApi"}
        , {title: "支付订单号", type:"field",field: "paymentSeqNo"}
        , {title: "渠道方客户号", type:"field",field: "instUserNo"}
        , {title: "产品代码", type:"field",field: "productCode"}
        , {title: "请求类型", type:"field",field: "requestType"}
        , {title: "金额", type:"field",field: "amount"}
        , {title: "状态", type:"field",field: "status"}
        , {title: "通信类型", type:"field",field: "communicateType"}
        , {title: "通信状态",type:"field", field: "communicateStatus"}
        , {title: "重试次数",type:"field", field: "retryTimes"}
        , {title: "创建日期",type:"field", field: "gmtCreate"}
        , {title: "修改时间", type:"field",field: "gmtModified"}
    ]
}