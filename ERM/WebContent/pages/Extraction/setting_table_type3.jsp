<%@ taglib prefix="s" uri="/struts-tags" %>  
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<div class="panel panel-default" id="settingTable">
<div class="panel-body">
	<div class="row">
			<div class="col-sm-5">
				<s:select
					name="currencyListAPIPool"
					id="ddlCurrencyAPIPool"
					class="form-control"
				    cssStyle="width:100%;"
					list="currencyListAPIPool"
					listKey="id"
					listValue="code"
					size="11"
					multiple="true"
				/>
			</div>
			<div class="col-sm-2" align="center">
				<button style="width:70%; margin-bottom:10px;" type="button" class="btn btn-primary btn-sm" 
					onclick="onClickAPIAddButton(ddlCurrencyAPIPool,ddlSelectedCurrencyAPI); sortAPICurrencyPool(ddlCurrencyAPIPool,ddlSelectedCurrencyAPI);">
					<span><s:text name='btn.add'/></span>
				</button>
				<button style="width:70%; margin-bottom:10px;" type="button" class="btn btn-primary btn-sm" 
					onclick="onClickAPIAddAllButton(ddlCurrencyAPIPool,ddlSelectedCurrencyAPI); sortAPICurrencyPool(ddlCurrencyAPIPool,ddlSelectedCurrencyAPI);">
					<span><s:text name='btn.add.all'/></span>
				</button>
				<button style="width:70%; margin-bottom:10px;" type="button" class="btn btn-primary btn-sm" 
					onclick="onClickAPIRemoveButton(ddlCurrencyAPIPool,ddlSelectedCurrencyAPI); sortAPICurrencyPool(ddlCurrencyAPIPool,ddlSelectedCurrencyAPI);">
					<span><s:text name='btn.remove'/></span>
				</button>
				<button style="width:70%; margin-bottom:10px;" type="button" class="btn btn-primary btn-sm" 
					onclick="onClickAPIRemoveAllButton(ddlCurrencyAPIPool,ddlSelectedCurrencyAPI); sortAPICurrencyPool(ddlCurrencyAPIPool,ddlSelectedCurrencyAPI);">
					<span><s:text name='btn.remove.all'/></span>
				</button>
			</div>
			<div class="col-sm-5">
				<s:select
					name="extractionForm.selectedCurrencyAPI"
					class="form-control"
				    cssStyle="width:100%;"
					list="currencyListSelectedAPI"
					id="ddlSelectedCurrencyAPI"
					listKey="id"
					listValue="code"
					size="11"
					multiple="true"
					value=""
				/>
			</div>
	</div>
</div>
</div>
<div class="has-error"><s:property value="errors['errorSettingTable3']" /></div>