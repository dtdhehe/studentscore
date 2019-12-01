Events = {
    //bootstrap表单初始化
    TableInit :function (params){
        var queryFormId = params.queryFormId;
        var formQueryParams = {};
        if (queryFormId && $(queryFormId).length > 0) {
            var fields = $(queryFormId).serializeArray();
            $.each(fields, function (i, field) {
                formQueryParams[field.name] = field.value;
            });
        }
        let tableId = params.bootstrapTableId;
        let _options = $.extend({},{
            url: params.url,//请求后台的URL
            method: 'get',//请求方式（*）
            idField:'id',//主键列
            search: false,//是否显示表格搜索，此搜索是客户端搜索，不会进服务端
            cache: false,//是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            sidePagination: "server",//分页方式：client客户端分页，server服务端分页（*）
            singleSelect: true,//单选
            pagination: true,//是否显示分页（*）
            showRefresh: false,//是否显示刷新按钮
            showToggle: false,//是否显示详细视图和列表视图的切换按钮
            showColumns: false,//是否显示所有的列
            pageNumber: 1,//初始化加载第一页，默认第一页
            pageSize: 5,//每页的记录行数（*）
            pageList: [5],//可供选择的每页的行数（*）[5,10]
            pageStore: false,//分页时保存所选（*）
            iconSize: 'outline',
            striped: true,//是否显示行间隔色
            clickToSelect: true,//是否启用点击选中行
            minimumCountColumns: 0,
            // height: getHeight() - 55,
            contentType: "application/x-www-form-urlencoded",//为了让后台获取页码和行数的参数（切记不可少）
            queryParams: function (params) {
                //这里的键的名字和控制器的变量名必须一致，这边改动，控制器也需要改成一样的
                let temp = $.extend(formQueryParams,{
                    rows: params.limit,                         //页面大小
                    page: (params.offset / params.limit),   //页码
                    sort: params.sort,      //排序列名
                    sortOrder: params.order //排位命令（desc，asc）
                });
                return temp;
            }
        },params.option);
        $(tableId).bootstrapTable(_options);
    },
};
//表单序列化成json对象
$.fn.serializeObject = function() {
    let o = {};
    let a = this.serializeArray();
    $.each(a, function() {
            if (o[this.name]) {
                    if (!o[this.name].push) {
                            o[this.name] = [o[this.name]];
                        }
                    o[this.name].push(this.value || '');
                } else {
                    o[this.name] = this.value || '';
                }
        });
    return o;
};
    



