<div class="row">
    <div class="col-md-12">
        <!-- BEGIN EXAMPLE TABLE PORTLET-->
        <div class="portlet light bordered">
            <div class="portlet-title">
                <div class="caption font-blue">
                    <i class="fa fa-globe font-blue"></i>数据列表
                </div>
                <div class="actions">
                    <div class="btn-group">
                        <#if SecurityUtil.hasPermission('sys_region_edit')><a class="btn red dialog" href="javascript:void(0);"
                        data-url="${ctx}/sys/region/edit" data-is-modal="" data-modal-width="950" data-table-id="#data-table-region">
                            <i class="fa fa-plus"> 添加行政区域</i>
                        </a></#if>
                    </div>
                </div>
            </div>
            <div class="portlet-body">
            	<form class="form-inline form-search" id="form-search-region">
						<div class="form-group">
					        <label class="input-label" for="name">区域名称</label>
							<input type="text" class="form-control" searchItem="searchItem" id="name" name="name" value="${(region.name)!}" attrType="String" operate="like" htmlEscape="false" maxlength="50" />
						</div>
						<div class="form-group">
					        <label class="input-label" for="parentId">父节点id</label>
							<input type="text" class="form-control" searchItem="searchItem" id="parentId" name="parentId" value="${(region.parentId)!}" attrType="Long" operate="ge" htmlEscape="false" maxlength="11" />
						</div>
						<div class="form-group">
					        <label class="input-label" for="longitude">经度</label>
							<input type="text" class="form-control" searchItem="searchItem" id="longitude" name="longitude" value="${(region.longitude)!}" attrType="String" operate="like" htmlEscape="false" maxlength="50" />
						</div>
						<div class="form-group">
					        <label class="input-label" for="latitude">纬度</label>
							<input type="text" class="form-control" searchItem="searchItem" id="latitude" name="latitude" value="${(region.latitude)!}" attrType="String" operate="in" htmlEscape="false" maxlength="50" />
						</div>
                         <div class="form-group form-btn">
                         <button class="btn btn-sm green btn-outline filter-submit-table-region margin-bottom" type="button"><i class="fa fa-search"></i> 查询</button>
                         <button class="btn btn-sm red btn-outline filter-cancel" type="reset"><i class="fa fa-times"></i> 重置</button>
                         </div>
                     </form>
                     <hr />
              		<div id="bootstrap-alerts"></div>
                    <table class="table table-striped table-bordered table-hover dataTable no-footer dt-responsive" id="data-table-region">
                        <thead>
                        <tr role="row" class="heading">
                        	<th class="all"> 区域名称 </th>
                        	<th class=""> 父节点id </th>
                        	<th class=""> 经度 </th>
                        	<th class=""> 纬度 </th>
                        	<th class=""> 创建时间 </th>
                        	<th class=""> 更新时间 </th>
                        <#if SecurityUtil.hasPermission('sys_region_edit,sys_region_delete,sys_region_lock')><th width="10%"> 操作 </th></#if>
                        </tr>
                        </thead>
                        <tbody>
                        </tbody>
                    </table>
              </div>
        </div>
        <!-- END EXAMPLE TABLE PORTLET-->
    </div>
</div>
<!-- END PAGE BASE CONTENT -->
<script type="text/javascript">
    var dataRegionTables = function () {
        var initTradeOrderTable = function () {
            var grid = new Datatable();
            grid.init({
                src: $("#data-table-region"),
                dataTable: {
                    "ajax": {
                        "url": "${ctx}/sys/region/page",
                        type: 'GET',
                        "dataType": 'json'
                    },
                    "columns": [
					{data:'name'
					, render: function(data, type, row){
						<#if SecurityUtil.hasPermission('sys_region_edit')>data = '<a href="javascript:void(0);" class="dialog" data-table-id="#data-table-region" data-is-modal="" data-url="${ctx}/sys/region/edit?id='+row.id+'" title=\"点击编辑行政区域\">'+data+'</a>'</#if>
						return data;
					}}
					, {data:'parentId'
					}
					, {data:'longitude'
					}
					, {data:'latitude'
					}
					, {data:'createDate'
					}
					, {data:'updateDate'
					}
					<#if SecurityUtil.hasPermission('sys_region_edit,sys_region_delete,sys_region_lock')>, 
                       { orderable: false, data: function ( row, type, val, meta ) {
                        	var data = '<span class="operation">'<#if SecurityUtil.hasPermission('sys_region_edit')>+'<a href="javascript:void(0);" class="dialog" data-table-id="#data-table-region" data-is-modal="" data-modal-width="950" data-url="${ctx}/sys/region/edit?id='+row.id+'"><i class=\"fa fa-lg fa-pencil\" title=\"编辑行政区域\"></i></a>'</#if>
                    		<#if SecurityUtil.hasPermission('sys_region_lock')>+'<a href="javascript:void(0);" class="confirm" data-table-id="#data-table-region" data-title="你确认要操作选中的行政区域吗？" data-url="${ctx}/sys/region/lock/'+row.id+'"><i class=\"fa fa-lg fa-'+(row.status=="正常" ? "unlock" : "lock") +'  font-yellow-gold\" title=\"'+(row.status=="正常" ? "锁定" : "解锁") +'行政区域\"></i></a></span>'</#if>
                    		<#if SecurityUtil.hasPermission('sys_region_delete')>+'<a href="javascript:void(0);" class="confirm" data-table-id="#data-table-region" data-method="post" data-title="你确认要删除选中的行政区域吗？" data-url="${ctx}/sys/region/delete/'+row.id+'"><i class=\"fa fa-lg fa-trash-o font-red-mint\" title=\"删除\"></i></a></span>'</#if>+'<\span>';
                        	return data;
                        }
                        }</#if>
                    ]
                }
            });
            $(".filter-submit-table-region").click(function(){
            	grid.submitFilter();
            })
        };
        return {
            init: function () {
                if (!jQuery().dataTable) {
                    return;
                }
                initTradeOrderTable();
            }
        };
    }();
    jQuery(document).ready(function() {
        dataRegionTables.init();
    });
</script>