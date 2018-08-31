// $(() => {
//     window.currentPage = 1;
//     $('.pageDiv').click((e) => {
//         let tag = e.currentTarget.innerText;
//         let page = parseInt(tag);
//         if ($(e.currentTarget).hasClass('pageSelected')) {
//             return;
//         }
//         $('.pageIndex').removeClass('pageSelected');
//         if (isNaN(page)) {
//             let pageIndexList = Array.from($('.pageIndex'));
//             if ($(e.currentTarget).hasClass('prefixClass')) {
//                 // 前箭头
//                 if (window.currentPage - 1 <= 1) {
//                     window.currentPage = 1;
//                 } else {
//                     window.currentPage = window.currentPage - 1;
//                 }
//             } else if ($(e.currentTarget).hasClass('suffixClass')) {
//                 // 后箭头
//                 if (window.currentPage + 1 >= 5) {
//                     window.currentPage = 5;
//                 } else {
//                     window.currentPage = window.currentPage + 1;
//                 }
//             }
//             pageIndexList.forEach(element => {
//                 if (element.innerText == window.currentPage) {
//                     $(element).addClass('pageSelected');
//                     return;
//                 }
//             });
//         } else {
//             $(e.currentTarget).addClass('pageSelected');
//             window.currentPage = page;
//         }
//     });
//
//     $('.col4 a').click((e) => {
//         let element = $(e.currentTarget);
//         let rowList = Array.from(element.parent().parent().children());
//         let tipStr = ' - ';
//         rowList.forEach(item => {
//             tipStr += item.innerText + ' - ';
//         });
//         alert(tipStr);
//     });
//
//     // 初始化新增按钮
//     $('#addBtn').click((e) => {
//         parent.openDialog('page/dialog/curse_add.html', '新增课程', 1000, 600, addCurse);
//     });
// });
//
// function addCurse() {
//     alert('新增了课程');
// }

var alljson=null;
var now=1;
var max=0,num=0;//num为数据总数，max为总页数
$(document).ready(function(){
    allinf();


    $(document).on('click','.pageIndex',function(){
        // $(".pageIndex").removeClass("pageSelected");
        // $(this).addClass("pageSelected");
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

function allinf() {
    $.ajax({
        type:"get",
        url:"/rfid",
        data:{
            method:"rfidDetail"
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
            "                    <td>"+inf[i].ID+"</td>\n" +
            "                    <td>"+inf[i].POS+"</td>\n" +
            "                    <td>"+inf[i].INg+"</td>\n" +
            "                    <td>"+inf[i].NowTime+"</td>\n" +
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
            "                    <td>"+inf[i].ID+"</td>\n" +
            "                    <td>"+inf[i].POS+"</td>\n" +
            "                    <td>"+inf[i].INg+"</td>\n" +
            "                    <td>"+inf[i].NowTime+"</td>\n" +
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