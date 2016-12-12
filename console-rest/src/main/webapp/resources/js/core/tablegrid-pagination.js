/**
 * Created by wangshaobo on 2015/7/21.
 */
var reqData;
var curPage = 1;
$('#btn_submit').click(function () {

    var totalPages;
    reqData = $("form").serializeArray();
    console.log('提交url:' + $(this).attr('submit-url') + '?pageSize=10&pageNo=' + curPage);

    sendQueryRequest($(this).attr('submit-url'), curPage, reqData);

});
/**
 *
 * @param reqUrl
 * @param curPage
 * @param reqData
 */
var sendQueryRequest = function (reqUrl, curPage, reqData) {
    if (reqUrl == undefined) {
        return;
    }
    $.ajax({
        url: reqUrl + '?pageSize=10&pageNum=' + curPage,
        cache: false,
        data: reqData,
        success: function (data) {
            var _totalPages = 0;
            var _totalItems = 0;
            var content ="";
            try{
                _totalPages = data.queryPage.pages;
                _totalItems = data.queryPage.total;
                content = data.info;
                console.log('收到服务器响应: 内容' + content + "总页数：" + _totalPages);
            } catch(e) {
                $("#resultgrid").empty();
                alert("查询异常,data: "+data);
            }
                renderGridTable($("#resultgrid"),content);
                renderPageInfo(_totalPages,_totalItems);

                console.log('渲染分页');
                var options = {
                    bootstrapMajorVersion: 3,
                    currentPage: curPage,
                    //处理返回0页js异常，如果0页，页面置为1
                    totalPages: _totalPages<1?1:_totalPages,
                    onPageClicked: function (e, originalEvent, type, page) {
                        sendQueryRequest(reqUrl, page, reqData);
                    }
                }
                $('#pagination').bootstrapPaginator(options);

        },
        beforeSend: function () {
            $('#progressModal').modal('show')
        },
        complete: function (XMLHttpRequest, textStatus) {
            $('#progressModal').modal('hide')
        }
    });
}
/**
 *
 * @param reqUrl
 * @param curPage
 * @param reqData
 * @param completeCallBack 请求完成后执行
 */
var sendQueryRequest4callBack = function (reqUrl, curPage, reqData,completeCallBack) {
    if (reqUrl == undefined) {
        return;
    }
    $.ajax({
        url: reqUrl + '?pageSize=10&pageNum=' + curPage,
        cache: false,
        data: reqData,
        success: function (data) {
            var _totalPages = 0;
            var _totalItems = 0;
            var content ="";
            try{
                _totalPages = data.queryPage.pages;
                _totalItems = data.queryPage.total;
                content = data.info;
                console.log('收到服务器响应: 内容' + content + "总页数：" + _totalPages);
            } catch(e) {
                $("#resultgrid").empty();
                alert("查询异常,data: "+data);
            }

            renderGridTable($("#resultgrid"),content);
            renderPageInfo(_totalPages,_totalItems);

            console.log('渲染分页');
            var options = {
                bootstrapMajorVersion: 3,
                currentPage: curPage,
                //处理返回0页js异常，如果0页，页面置为1
                totalPages: _totalPages<1?1:_totalPages,
                onPageClicked: function (e, originalEvent, type, page) {
                    sendQueryRequest4callBack(reqUrl, page, reqData,completeCallBack);
                }
            }
            $('#pagination').bootstrapPaginator(options);

        },
        beforeSend: function () {
            $('#progressModal').modal('show')
        },
        complete: function (XMLHttpRequest, textStatus) {
            //console.log('请求完成:' + textStatus);
            completeCallBack();
            $('#progressModal').modal('hide')
        }

    });
}
var renderPageInfo = function(totalPages,totalItems){
    $("#totalPages").html(totalPages);
    $("#totalItems").html(totalItems);
}

var renderGridTable = function(table ,gridData){
    table.empty();
    var columns = grid_templete.columns;
    if(!columns){
        throw "页面模板不能是空";
    }
        var $h = "<thead><tr>";
        for (var i = 0,l=columns.length;i< l;  i++){
            var col = columns[i];
            var th_title = col.title;
            $h += "<th>"+(th_title|| "")+"</th>";
        }
        $h += "</tr></thead>";
        table.append($h);

        var primaryKey =grid_templete.primaryKey;

        var $r="<tbody>";
        for (var i = 0,l=gridData.length;i< l;  i++){
            var o = gridData[i];
            if(primaryKey!=undefined&&primaryKey.length>0){
                $r += "<tr _id =" + o[primaryKey] + ">" ;
            }else{
                $r += "<tr>";
            }

            for (var j = 0,ll=columns.length; j<ll ; j++){
                var col = columns[j];
                var type = col.type;
                $r += "<td>";
                if(type=='field'){
                    $r += o[col.field];
                }else if(type=='config'){
                    $r += col.config;
                }else{

                }
                $r += "</td>";
            }
            $r += "</tr>";
        }
        $r += "</tbody>";
        table.append($r);
}