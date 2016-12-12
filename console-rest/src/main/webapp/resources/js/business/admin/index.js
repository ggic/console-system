/**
 * Created by wangshaobo on 2015/7/21.
 */
$(document).ready(function () {
    generateMenus();
    //动态地址传递
    $('#navbar .redirct-url').click(function (event) {
        console.log('请求URL：'+this.href);

        if (event && event.preventDefault) {
            event.preventDefault();
        } else {
            window.event.returnValue = false;
        }
        $('#main').attr("src", this.href);
    })

});


var generateMenus = function() {
    var dropMenu = createTree(jsonData);
    $("#navbar").html(dropMenu);
}

//创建菜单tree
function createTree(jsonData) {
    var dropMenu ='<li class="active"><a href="#" >Home</a></li>';
    if (jsonData != null&&jsonData.length>0) {
        for (var i = 0; i < jsonData.length; i++) {
            var mm = jsonData[i];
            dropMenu += '<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown" >' + mm.description + '<span class="caret"></span></a> ';
            var childs = mm.childMenus;
            if(childs!=null&&childs.length>0) {
                dropMenu = setChildNode(dropMenu,childs);
            }
            dropMenu += "</li>";
        }
    }
    return dropMenu;
}

function setChildNode(dropMenu,childs){
        dropMenu +='<ul class="dropdown-menu">';
        for (var j = 0; j < childs.length; j++) {
            var child = childs[j];
            var sub_childs = child.childMenus;
            if(sub_childs!=null&&sub_childs.length>0){
                dropMenu += '<li class="dropdown-submenu"><a href="'+child.url+'" >' + child.description + '</a>';
                dropMenu = setChildNode(dropMenu,sub_childs );
                dropMenu +='</li>';
            }else{
                dropMenu += '<li><a href="'+child.url+'" class="redirct-url">' + child.description + '</a></li>';
            }

        }
        dropMenu += "</ul>";
     return dropMenu;
}
