var alljson=null;
var now=1;
var max=0,num=0;//num为数据总数，max为总页数
$(document).ready(function(){
    allinf();

    $(document).on('click','#addBtn',function(){
        parent.openDialog('page/dialog/person_add.html', '新增位置', 1000, 600,addCurse);
    });

    $(document).on('click','.updbtn',function(){
        var id = $(this).parent().parent().children("td").eq(0).text();
        var name = $(this).parent().parent().children("td").eq(1).text();
        var sex = $(this).parent().parent().children("td").eq(2).text();
        var birthtime = $(this).parent().parent().children("td").eq(3).text();
        var rfid = $(this).parent().parent().children("td").eq(4).text();
        parent.openDialog('page/dialog/person_update.html?id='+
            id+"&name="+name+"&sex="+sex+"&birthtime="+birthtime+"&rfid="+rfid,
            '修改位置', 1000, 600,updateCurse);
    });

    $(document).on('click','.delbtn',function(){
        var id = $(this).parent().parent().children("td").eq(0).text();
        deletePerson(id);
    });

    $(document).on('click','.pageIndex',function(){
        now = parseInt($(this).text());
        pageChange();//页面刷新
        numbarChange();//分页刷新
    });

    //上一页
    $(document).on('click','.prefixClass',function(){
        if(now>1){
            now--;
            pageChange();
            numbarChange();
        }
    });

    //下一页
    $(document).on('click','.suffixClass',function(){
        if(now<max){
            now++;
            pageChange();
            numbarChange();
        }
    });


});

function addCurse() {
    var name = window.parent.$("#dialogContainer")[0].contentWindow.$("#name").val();
    var sex = window.parent.$("#dialogContainer")[0].contentWindow.$("#sex").val();
    var birthtime = window.parent.$("#dialogContainer")[0].contentWindow.$("#birthtime").val();
    var rfid = window.parent.$("#dialogContainer")[0].contentWindow.$("#rfid").val();
    addper(name,sex,birthtime,rfid);
    alert('新增了课程');
}

function updateCurse() {
    var id = window.parent.$("#dialogContainer")[0].contentWindow.$("#id").val();
    var name = window.parent.$("#dialogContainer")[0].contentWindow.$("#name").val();
    var sex = window.parent.$("#dialogContainer")[0].contentWindow.$("#sex").val();
    var birthtime = window.parent.$("#dialogContainer")[0].contentWindow.$("#birthtime").val();
    var rfid = window.parent.$("#dialogContainer")[0].contentWindow.$("#rfid").val();
    updateper(id,name,sex,birthtime,rfid);
    alert('修改了课程');
}


function allinf() {
    $.ajax({
        type:"get",
        url:"/person",
        data:{
            method:"personDetail"
        },
        dataType:"json",
        success:function (json) {
            console.log(json);
            alljson=json;
            now=1;
            num = json.data.length;
            max=Math.ceil(json.data.length/6);
            inittable();
        },
        error:function(data){
            console.log(data.msg);
        }
    });
}

function inittable() {
    var inf = alljson.data;
    var ht = "";
    for(var i in inf){
        if(i>5){break;}
        ht += "<tr>\n" +
            "                    <td>"+inf[i].id+"</td>\n" +
            "                    <td>"+inf[i].name+"</td>\n" +
            "                    <td>"+inf[i].sex+"</td>\n" +
            "                    <td>"+inf[i].birthtime+"</td>\n" +
            "                    <td>"+inf[i].rfid+"</td>\n" +
            "<td><input class=\"btnClass updbtn\" type=\"button\" value=\"修改\" />" +
            "<input class=\"btnClass delbtn\" type=\"button\" value=\"删除\" /></td>>" +
            "                </tr>";
    }
    $("#tbo").html(ht);
}

function pageChange() {
    var inf = alljson.data;
    var ht = "";
    var start = (now-1)*6;
    var end = now*6;
    for(var i=start;i<end;i++){
        if(i>=num){break;}
        ht += "<tr>\n" +
            "                    <td>"+inf[i].id+"</td>\n" +
            "                    <td>"+inf[i].name+"</td>\n" +
            "                    <td>"+inf[i].sex+"</td>\n" +
            "                    <td>"+inf[i].birthtime+"</td>\n" +
            "                    <td>"+inf[i].rfid+"</td>\n" +
            "<td><input class=\"btnClass updbtn\" type=\"button\" value=\"修改\" />" +
            "<input class=\"btnClass delbtn\" type=\"button\" value=\"删除\" /></td>>" +
            "                </tr>";
    }
    $("#tbo").html(ht);
}

function numbarChange() {
    var ht="";
    var inow = parseInt(now);
    var imax = parseInt(max);
    if(imax>5){
        if(inow<=3){
            ht+='<div class="pageDiv prefixClass">&lt;</div>\n' +
                '            <div class="pageDiv pageIndex" id="num_1">1</div>\n' +
                '            <div class="pageDiv pageIndex" id="num_2">2</div>\n' +
                '            <div class="pageDiv pageIndex" id="num_3">3</div>\n' +
                '            <div class="pageDiv pageIndex" id="num_4">4</div>\n' +
                '            <div class="pageDiv pageIndex" id="num_5">5</div>\n' +
                '            <div class="pageDiv suffixClass">&gt;</div>';
        }else if(inow>=imax-2){
            ht+='<div class="pageDiv prefixClass">&lt;</div>\n' +
                '            <div class="pageDiv pageIndex" id="num_'+(imax-4)+'">'+(imax-4)+'</div>\n' +
                '            <div class="pageDiv pageIndex" id="num_'+(imax-3)+'">'+(imax-3)+'</div>\n' +
                '            <div class="pageDiv pageIndex" id="num_'+(imax-2)+'">'+(imax-2)+'</div>\n' +
                '            <div class="pageDiv pageIndex" id="num_'+(imax-1)+'">'+(imax-1)+'</div>\n' +
                '            <div class="pageDiv pageIndex" id="num_'+imax+'">'+imax+'</div>\n' +
                '            <div class="pageDiv suffixClass">&gt;</div>';
        }else{
            ht+='<div class="pageDiv prefixClass">&lt;</div>\n' +
                '            <div class="pageDiv pageIndex" id="num_'+(inow-2)+'">'+(inow-2)+'</div>\n' +
                '            <div class="pageDiv pageIndex" id="num_'+(inow-1)+'">'+(inow-1)+'</div>\n' +
                '            <div class="pageDiv pageIndex" id="num_'+inow+'">'+inow+'</div>\n' +
                '            <div class="pageDiv pageIndex" id="num_'+(inow+1)+'">'+(inow+1)+'</div>\n' +
                '            <div class="pageDiv pageIndex" id="num_'+(inow+2)+'">'+(inow+2)+'</div>\n' +
                '            <div class="pageDiv suffixClass">&gt;</div>';
        }
        console.log(ht);
        $("#numbar").html(ht);
    }
    $(".pageIndex").removeClass("pageSelected");
    var the = "#num_"+inow;
    $(the).addClass("pageSelected");
}

//添加position
function addper(name,sex,birthtime,rfid) {
    $.ajax({
        type:"get",
        url:"/person",
        data:{
            name:name,
            sex:sex,
            birthtime:birthtime,
            rfid:rfid,
            method:"addper"
        },
        dataType:"json",
        success:function (json) {
            console.log(json);
        },
        error:function(data){
            console.log(data.msg);
        }
    });
}

//修改position
function updateper(id,name,sex,birthtime,rfid) {
    $.ajax({
        type:"get",
        url:"/person",
        data:{
            id:id,
            name:name,
            sex:sex,
            birthtime:birthtime,
            rfid:rfid,
            method:"updateper"
        },
        dataType:"json",
        success:function (json) {
            console.log(json);
        },
        error:function(data){
            console.log(data.msg);
        }
    });
}

function deletePerson(id) {
    alert(id);
    $.ajax({
        type:"get",
        url:"/person",
        data:{
            id:id,
            method:"deleteper"
        },
        dataType:"json",
        success:function (json) {
            console.log(json);
            alert(json.msg)
        },
        error:function(data){
            console.log(data.msg);
        }
    });
}