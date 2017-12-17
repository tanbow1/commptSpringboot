/**
 * Created by Tanbo on 2017/12/17.
 */
$(function () {
    $('input[name="ck_all"]').eq(0).on('click', function () {
        if (this.checked) {
            $('input[name="ck_single"]').prop('checked', true);
        } else {
            $('input[name="ck_single"]').prop('checked', false);
        }
    })

    $('input[name="ck_single"]').on('click', function () {
        if (!this.checked) {
            $('input[name="ck_all"]').prop('checked', false);
        }

        if ($('input[name="ck_single"]:checked').length == $('input[name="ck_single"]').length) {
            $('input[name="ck_all"]').prop('checked', true);
        }
    })

    $('input[name="uploadFile"]').on('change', function () {
        checkFile(this);
    })
})

function addFile() {
    $('#comm_fileuploadDialog_content tbody').append(
        $('#comm_fileuploadDialog_content tbody').find('tr:first').clone(true)
    );
}

function removeFiles() {
    var cks = $('input[name="ck_single"]');
    var cksChecked = $('input[name="ck_single"]:checked');
    if (cks.length < 2 || cksChecked.length == 0) {
        return;
    }

    if (cksChecked.length == cks.length) {
        easyMsg.toast("请至少保留一个文件");
        return;
    }

    for (var i = 0, len = cksChecked.length; i < len; i++) {
        $(cksChecked[i]).parents('tr:first').remove();
    }
}

function getFileObjs() {
    var files = [];
    var cksChecked = $('input[name="ck_single"]:checked');
    if (cksChecked.length == 0) {
        var cks = $('input[name="ck_single"]');
        for (var i = 0, len = cks.length; i < len; i++) {
            var file = $(cks[i]).parents('tr:first').find('input[name="uploadFile"]')[0];
            if (file.value != '')
                files.push(file);
        }
    } else {
        for (var i = 0, len = cksChecked.length; i < len; i++) {
            var file = $(cksChecked[i]).parents('tr:first').find('input[name="uploadFile"]')[0];
            if (file.value != '')
                files.push(file);
        }
    }
    return files;
}

function reset() {
    $("#comm_fileuploadDialog_content tbody").find('input[name="uploadFile"]:first')[0].value = '';
    $("#comm_fileuploadDialog_content tbody").find('tr:not(":first")').remove();
    bootstraProgress.init("comm_fileuploadDialog_content");
}
