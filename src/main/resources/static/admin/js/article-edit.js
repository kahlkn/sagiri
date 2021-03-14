var mditor, htmlEditor;
var tale = new $.tale();
var attach_url = $('#attach_url').val();
// 每60秒自动保存一次草稿
var refreshIntervalId;
Dropzone.autoDiscover = false;
Vue.component('v-select', VueSelect.VueSelect);

var vm = new Vue({
    el: '#app',
    data: {
        article: {
            id: '',
            title: '',
            slug: '',
            type: '',
            authorId: '',
            thumbnail: '',
            format: 'markdown',
            content: '',
            origin: '',
            releaseTime: moment().format('YYYY-MM-DD HH:mm:ss'),
            status: '0',
            allowFeed: 1,
            allowPing: 1,
            allowComment: 1,
            categories: '',
            tags: ''
        },
        category: {
            selected: [],
            all: []
        },
        isLoading: true
    },
    beforeCreate: function(){
        vueLoding = this.$loading.show();
    },
    mounted: function () {
        var $vm = this;

        $("#form_datetime").datetimepicker({
            format: 'yyyy-mm-dd hh:ii:ss',
            autoclose: true,
            todayBtn: true,
            weekStart: 1,
            language: 'zh-CN'
        });

        mditor = window.mditor = Mditor.fromTextarea(document.getElementById('md-editor'));
        // 富文本编辑器
        htmlEditor = $('.summernote').summernote({
            lang: 'zh-CN',
            height: 340,
            placeholder: '写点儿什么吧...',
            //上传图片的接口
            callbacks: {
                onImageUpload: function (files) {
                    var data = new FormData();
                    data.append('folder', "123");
                    for (let i = 0; i < files.length; i++) {
                        data.append('files', files[i]);
                    }
                    tale.showLoading();
                    $.ajax({
                        url: '/file/upload',
                        method: 'POST',
                        data: data,
                        processData: false,
                        dataType: 'json',
                        headers: {
                            'X-CSRF-TOKEN': document.head.querySelector("[name=csrf_token]").content
                        },
                        contentType: false,
                        success: function (result) {
                            tale.hideLoading();
                            if (result && result.success) {
                                /*var url = $('#attach_url').val() + result.payload[0].fkey;
                                console.log('url =>' + url);
                                htmlEditor.summernote('insertImage', url);*/
                                result.data.forEach(function (item) {
                                    htmlEditor.summernote('insertImage', item.address);
                                });
                            } else {
                                tale.alertError(result.msg || '图片上传失败');
                            }
                        }
                    });
                }
            }
        });

        $vm.load();
        refreshIntervalId = setInterval("vm.autoSave()", 20 * 1000);
    },
    methods: {
        load: function () {
            var $vm = this;
            // var pos = window.location.toString().lastIndexOf("?");
            // var articleId = window.location.toString().substring(pos + 4);
            var articleId = getQueryVariable('id');

            tale.post({
                url: '/api/admin/article/category/select-list',
                data: {},
                success: function (result) {
                    result.data.forEach(function (item) {
                        // $vm.categories.push(item.name);
                        $vm.category.all.push(item.name);
                    });
                },
                error: function (error) {
                    console.log(error);
                    alert(error || '数据加载失败');
                }
            });

            tale.post({
                url: '/api/admin/article/detail',
                data: { id : articleId },
                success: function (result) {
                    $vm.article = result.data;
                    //$vm.article.tags = result.data.tags || "";
                    //$vm.category.selected = [];

                    var selected = result.data.categories.split(',');
                    for(item in selected){
                        $vm.category.selected.push(selected[item]);
                    }

                    //$vm.article.createdTime = moment.unix($vm.article.created).format('YYYY-MM-DD HH:mm');
                    $vm.article.releaseTime = moment.unix($vm.article.releaseTime/1000).format('YYYY-MM-DD HH:mm:ss');

                    var tags = result.data.tags.split(',');
                    for(i in tags){
                        $('#tags').addTag(tags[i]);
                    }

                    $('#allowComment').toggles({
                        on: $vm.article.allowComment,
                        text: {
                            on: '开启',
                            off: '关闭'
                        }
                    });

                    $('#allowPing').toggles({
                        on: $vm.article.allowPing,
                        text: {
                            on: '开启',
                            off: '关闭'
                        }
                    });

                    $('#allowFeed').toggles({
                        on: $vm.article.allowFeed,
                        text: {
                            on: '开启',
                            off: '关闭'
                        }
                    });

                    if($vm.article.thumbnail && $vm.article.thumbnail !== ''){
                        $('#addThumb').toggles({
                            on: true,
                            text: {
                                on: '添加',
                                off: '取消'
                            }
                        });
                        $('#dropzone-container').removeClass('hide');
                        $('#dropzone-container').show();
                        $('.dz-image').hide();
                        $('#dropzone').css('background-image', 'url(' + $vm.article.thumbnail + ')');
                        $('#dropzone').css('background-size', 'contain');
                        $('#dropzone').css('background-repeat', 'no-repeat');
                    } else {
                        $('#addThumb').toggles({
                            on: false,
                            text: {
                                on: '添加',
                                off: '取消'
                            }
                        });
                        $('#dropzone-container').hide();
                    }

                    if (result.data.format === 'markdown') {
                        mditor.value = result.data.content;
                    } else {
                        htmlEditor.summernote("code", result.data.content);
                    }

                },
                error: function (error) {
                    console.log(error);
                    alert(error || '数据加载失败');
                }
            });
        },
        autoSave: function (callback) {
            var $vm = this;
            var content = $vm.article.format === 'markdown' ? mditor.value : htmlEditor.summernote('code');
            if ($vm.article.title !== '' && content !== '') {
                $vm.article.content = content;
                $vm.article.categories = $vm.category.selected.join(',');
                var params = tale.copy($vm.article);
                //params.selected = null;
                //params.created = moment($('#form_datetime').val(), "YYYY-MM-DD HH:mm").unix();
                params.releaseTime = moment($('#form_datetime').val(), 'YYYY-MM-DD HH:mm:ss').unix() * 1000;
                params.tags = $('#tags').val();

                tale.post({
                    url: '/api/admin/article/edit',
                    data: params,
                    success: function (result) {
                        if (result && result.success) {
                            callback && callback()
                        } else {
                            tale.alertError(result.msg || '保存文章失败');
                        }
                    },
                    error: function (error) {
                        console.log(error);
                        clearInterval(refreshIntervalId);
                    }
                });
            }
        },
        switchEditor: function (event) {
            var type = this.article.format;
            var this_ = event.target;
            if (type === 'markdown') {
                // 切换为富文本编辑器
                if ($('#md-container .markdown-body').html().length > 0) {
                    $('#html-container .note-editable').empty().html($('#md-container .markdown-body').html());
                    $('#html-container .note-placeholder').hide();
                }
                mditor.value = '';
                $('#md-container').hide();
                $('#html-container').show();

                this_.innerHTML = '切换为Markdown编辑器';

                this.article.format = 'html';
            } else {
                // 切换为markdown编辑器
                if ($('#html-container .note-editable').html().length > 0) {
                    mditor.value = '';
                    mditor.value = toMarkdown($('#html-container .note-editable').html());
                }
                $('#html-container').hide();
                $('#md-container').show();

                this.article.format = 'markdown';

                this_.innerHTML = '切换为富文本编辑器';
                htmlEditor.summernote("code", "");
            }
        },
        publish: function (status) {
            var $vm = this;
            var content = this.article.format === 'markdown' ? mditor.value : htmlEditor.summernote('code');
            var title = $vm.article.title;
            if (title === '') {
                tale.alertWarn('请输入文章标题');
                return;
            }
            if (content === '') {
                tale.alertWarn('请输入文章内容');
                return;
            }
            clearInterval(refreshIntervalId);
            $vm.article.status = status;
            $vm.autoSave(function () {
                tale.alertOk({
                    text: '文章保存成功',
                    then: function () {
                        setTimeout(function () {
                            window.location.href = '/admin/article/list';
                        }, 500);
                    }
                });
            });
        }
    }
});

$(document).ready(function () {

    // Tags Input
    $('#tags').tagsInput({
        width: '100%',
        height: '35px',
        defaultText: '请输入文章标签'
    });

    $('#allowComment').toggles({
        on: true,
        text: {
            on: '开启',
            off: '关闭'
        }
    });

    $('#allowPing').toggles({
        on: true,
        text: {
            on: '开启',
            off: '关闭'
        }
    });

    $('#allowFeed').toggles({
        on: true,
        text: {
            on: '开启',
            off: '关闭'
        }
    });

    $('#addThumb').toggles({
        on: true,
        text: {
            on: '添加',
            off: '取消'
        }
    });

    $('#allowComment').on('toggle', function (e, active) {
        vm.article.allowComment = active ? 1 : 0;
    });

    $('#allowPing').on('toggle', function (e, active) {
        vm.article.allowPing = active ? 1 : 0;
    });

    $('#allowFeed').on('toggle', function (e, active) {
        vm.article.allowFeed = active ? 1 : 0;
    });

    $('#addThumb').on('toggle', function (e, active) {
        if (active) {
            $('#dropzone-container').removeClass('hide');
            $('#dropzone-container').show();
            var thumbImage = $("#dropzone").css("backgroundImage");
            if(thumbImage && thumbImage.indexOf('url') !== -1){
                thumbImage = thumbImage.split("(")[1].split(")")[0];
                vm.article.thumbnail = thumbImage.substring(1, thumbImage.length - 1);
            }
        } else {
            $('#dropzone-container').addClass('hide');
            vm.article.thumbnail = '';
        }
    });

    // console.log(vm.article.selected);
    // console.log(vm.categories);

    var thumbdropzone = $('.dropzone');

    // 缩略图上传
    $("#dropzone").dropzone({
        url: "/file/upload",
        filesizeBase: 1024,//定义字节算法 默认1000
        maxFilesize: '10', //MB
        paramName: 'files',
        fallback: function () {
            tale.alertError('暂不支持您的浏览器上传!');
        },
        acceptedFiles: 'image/*',
        dictFileTooBig: '您的文件超过10MB!',
        dictInvalidInputType: '不支持您上传的类型',
        headers: {
            'X-CSRF-TOKEN': document.head.querySelector("[name=csrf_token]").content
        },
        init: function () {
            this.on('sending', function (data, xhr, formData) {
                //向后台发送该文件的参数
                formData.append('folder', '123');
            });
            this.on('success', function (files, result) {
                console.log("upload success..");
                console.log(" result => " + result);
                if (result && result.success) {
                    // var url = attach_url + result.payload[0].fkey;
                    // console.log('url => ' + url);
                    console.log(result);
                    var url = result.data[0].address;

                    vm.article.thumbnail = url;
                    thumbdropzone.css('background-image', 'url(' + url + ')');
                    thumbdropzone.css('background-size', 'contain');
                    thumbdropzone.css('background-repeat', 'no-repeat');
                    $('.dz-image').hide();
                }
            });
            this.on('error', function (a, errorMessage, result) {
                if (!result.success && result.msg) {
                    tale.alertError(result.msg || '缩略图上传失败');
                }
            });
        }
    });

    vm.isLoading = false;
    vueLoding.hide();

});
