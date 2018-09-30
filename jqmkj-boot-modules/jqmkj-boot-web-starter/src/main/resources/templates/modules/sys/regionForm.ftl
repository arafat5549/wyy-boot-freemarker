<div class="portlet light bordered">
	<div class="portlet-title">
		<div class="caption font-blue">
            <i class="fa fa-globe font-blue"></i><#if regionVo.id??>编辑<#else>添加</#if>行政区域
        </div>
		<div class="actions">
			<div class="btn-group">
                <a id="region_list" class="btn red list" href="javascript:void(0);" data-table-id="#data-table-region">
                    <i class="fa fa-list"> 行政区域列表</i>
                </a>
            </div>
		</div>
	</div>
	<div class="portlet-body form form-no-modal">
		<!-- BEGIN FORM-->
		<div id="bootstrap-alerts"></div>
		<form id="ajax-form-region" action="${ctx}/sys/region/edit" method="post"
			class="form-horizontal form-validation form-bordered form-label-stripped"
			config="{rules:{
						},
                       messages:{
					   }}}">
			<div class="form-body">
				<input type="hidden" name="id" value="${(regionVo.id)!}" />
				<div class="form-group">
					<label class="control-label col-md-3">区域名称<span class="required">*</span></label>
					<div class="col-md-5">
						<input type="text" name="name" id="name" class="form-control required" value="${(regionVo.name)!}" htmlEscape="false" maxlength="50" />
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-md-3">父节点id<span class="required">*</span></label>
					<div class="col-md-5">
						<input type="text" name="parentId" id="parentId" class="form-control required digits" value="${(regionVo.parentId)!}" htmlEscape="false" maxlength="11" />
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-md-3">经度<span class="required">*</span></label>
					<div class="col-md-5">
						<input type="text" name="longitude" id="longitude" class="form-control required" value="${(regionVo.longitude)!}" htmlEscape="false" maxlength="50" />
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-md-3">纬度<span class="required">*</span></label>
					<div class="col-md-5">
						<input type="text" name="latitude" id="latitude" class="form-control required" value="${(regionVo.latitude)!}" htmlEscape="false" maxlength="50" />
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-md-3">创建时间<span class="required">*</span></label>
					<div class="col-md-5">
						<div class="input-group date"><input type="text" name="createDate" readonly class="form-control date-time-picker required" value="${(regionVo.createDate?string('yyyy-MM-dd HH:mm:ss'))!}"><span class="input-group-btn"><button class="btn default date-set" type="button"><i class="fa fa-calendar"></i></button></span></div>
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-md-3">更新时间</label>
					<div class="col-md-5">
						<div class="input-group date"><input type="text" name="updateDate" readonly class="form-control date-time-picker " value="${(regionVo.updateDate?string('yyyy-MM-dd HH:mm:ss'))!}"><span class="input-group-btn"><button class="btn default date-set" type="button"><i class="fa fa-calendar"></i></button></span></div>
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-md-3">描述</label>
					<div class="col-md-5">
						<textarea name="description" rows="5" maxlength="255" class="form-control input-xxlarge ">${(regionVo.description)! }</textarea>
					</div>
				</div>
				<div class="form-actions">
                   <div class="row">
                       <div class="col-md-offset-3 col-md-9">
                           <button type="button" class="btn add green">
                               <i class="fa fa-check"></i> 保存</button>
                           <button type="reset" class="btn default">重置</button>
                           <button type="button" class="btn list" data-table-id="#data-table-region">返回</button>
                       </div>
                   </div>
               </div>
			</div>
		</form>
	</div>
</div>
