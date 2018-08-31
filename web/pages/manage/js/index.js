$(function () {
    $('#accordion').accordion({
        collapsible: true,
        heightStyle: 'content'
    });

    $('#dateFrom').datepicker();
    $('#dateTo').datepicker();

    $('#modalDialog').dialog({
        autoOpen: false,
        modal: true
    });

    $('.leftClass #accordion ul li:first').addClass('menuSelected');

    $('.leftClass #accordion ul li').click((e) => {
        $('.leftClass #accordion ul li').removeClass('menuSelected');
        $(e.currentTarget).addClass('menuSelected');
        $('#menuLabel').html(e.currentTarget.innerText);

        // 设置该菜单对应的界面
        let pageUrl = $(e.currentTarget).children().attr('data-value');
        if (pageUrl)
            $('#pageContainer').attr('src', pageUrl);
    });
});

function openDialog(dialogUrl = '', title = '', width = 400, height = 400, successCallback, cancelCallback) {
    let modalDialog = $('#modalDialog');
    $('#dialogContainer').attr('src', dialogUrl);
    // 设置title 
    modalDialog.dialog('option', 'title', title);
    // 设置宽高
    modalDialog.dialog('option', 'width', width);
    modalDialog.dialog('option', 'height', height);
    // 设置按钮事件
    let defaultEvent = () => {
        modalDialog.dialog('close');
    }

    modalDialog.dialog('option', 'buttons', [
        {
            text: '确定',
            click: () => {
                successCallback();
                defaultEvent();
            }
        }, {
            text: '取消',
            click: () => {
                cancelCallback();
                defaultEvent();
            }
        }
    ]);

    modalDialog.dialog('open');
}