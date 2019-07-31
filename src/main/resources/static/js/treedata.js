function rePubName(that) {
    var id = that.id;   //文件的ID

    $.messager.prompt('文件重命名', '请输入文件名', function (r) {
        if (r) {

            $.ajax({
                url: 'renamePubFile',
                type: 'POST',


                success: function (data) {
                    console.log(data);
                   alert1(data);

                    window.location.href = 'gettree';

                },
                error: function (data) {
                    console.log(data);
                    alert1("系统发生错误!")
                },

                data: {
                    fileid: id,
                    filename: r

                }
            });


        }
    });
}


function rePriName(that) {
    var id = that.id;   //文件的ID

    $.messager.prompt('文件重命名', '请输入文件名', function (r) {
        if (r) {

            $.ajax({
                url: 'renamepriFile',
                type: 'POST',


                success: function (data) {
                    console.log(data);
                    alert1(data);
                    window.location.href = 'gettree';

                },
                error: function (data) {
                    console.log(data);
                    alert1("系统发生错误!");
                },

                data: {
                    fileid: id,
                    filename: r

                }
            });
        }
    });
}
function alert1(data){
    $.messager.alert('文件重命名',data);
}