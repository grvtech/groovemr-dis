var loginCss = {
		"root": "sv_main sv_bootstrap_css",
		"header": "panel-heading card-header",
		"body": "panel-body card-block mt-4",
		"footer": "panel-footer card-footer",
		"navigationButton": "button btn-lg",
		"completedPage": "",
		"navigation": {
			"complete": "btn sv_complete_btn",
			"prev": "btn sv_prev_btn",
			"next": "btn sv_next_btn",
			"start": "btn sv_start_btn"
		},
		"progress": "progress center-block mx-auto mb-4",
		"progressBar": "progress-bar",
		"page": {
			"root": "",
			"title": "",
			"description": ""
		},
		"pageTitle": "",
		"pageDescription": "small",
		"row": "sv_row",
		"question": {
			"mainRoot": "sv_qstn",
			"title": "",
			"description": "small",
			"comment": "form-control",
			"required": "",
			"titleRequired": "",
			"hasError": "has-error",
			"indent": 20
		},
		"panel": {
			"title": "sv_p_title",
			"description": "small",
			"container": "sv_p_container"
		},
		"error": {
			"root": "alert alert-danger",
			"icon": "glyphicon glyphicon-exclamation-sign",
			"item": ""
		},
		"boolean": {
			"root": "sv_qbln form-inline checkbox",
			"item": "",
			"label": "",
			"materialDecorator": "checkbox-material"
		},
		"checkbox": {
			"root": "sv_qcbc sv_qcbx form-inline",
			"item": "checkbox",
			"itemControl": "",
			"controlLabel": "",
			"materialDecorator": "checkbox-material",
			"other": "sv_q_checkbox_other form-control"
		},
		"comment": "form-control",
		"dropdown": {
			"root": "",
			"control": "form-control",
			"other": "sv_q_dd_other form-control"
		},
		"matrix": {
			"root": "table table-striped",
			"label": "sv_q_m_label",
			"cellText": "sv_q_m_cell_text",
			"cellTextSelected": "sv_q_m_cell_selected bg-primary"
		},
		"matrixdropdown": {
			"root": "table"
		},
		"matrixdynamic": {
			"root": "table",
			"button": "button",
			"buttonAdd": "",
			"buttonRemove": ""
		},
		"paneldynamic": {
			"root": "",
			"button": "button",
			"buttonPrev": "",
			"buttonNext": "",
			"buttonAdd": "",
			"buttonRemove": ""
		},
		"multipletext": {
			"root": "table",
			"itemTitle": "",
			"itemValue": "sv_q_mt_item_value form-control"
		},
		"radiogroup": {
			"root": "sv_qcbc form-inline",
			"item": "radio",
			"label": "",
			"itemControl": "",
			"controlLabel": "",
			"materialDecorator": "circle",
			"other": "sv_q_radiogroup_other form-control",
			"clearButton": "sv_q_radiogroup_clear button"
		},
		"imagepicker": {
			"root": "sv_imgsel",
			"item": "sv_q_imgsel",
			"label": "sv_q_imgsel_label",
			"itemControl": "sv_q_imgsel_control_item",
			"image": "sv_q_imgsel_image",
			"itemText": "sv_q_imgsel_text",
			"clearButton": "sv_q_radiogroup_clear"
		},
		"rating": {
			"root": "btn-group",
			"item": "btn btn-default btn-secondary",
			"selected": "active",
			"minText": "sv_q_rating_min_text",
			"itemText": "sv_q_rating_item_text",
			"maxText": "sv_q_rating_max_text"
		},
		"text": "form-control",
		"expression": "form-control",
		"file": {
			"root": "sv_q_file",
			"placeholderInput": "sv_q_file_placeholder",
			"preview": "sv_q_file_preview",
			"removeButton": "sv_q_file_remove_button",
			"fileInput": "sv_q_file_input",
			"removeFile": "sv_q_file_remove"
		},
		"saveData": {
			"root": "",
			"saving": "alert alert-info",
			"error": "alert alert-danger",
			"success": "alert alert-success",
			"saveAgainButton": ""
		},
		"window": {
			"root": "modal-content",
			"body": "modal-body",
			"header": {
				"root": "modal-header panel-title",
				"title": "pull-left",
				"button": "glyphicon pull-right",
				"buttonExpanded": "glyphicon pull-right glyphicon-chevron-up",
				"buttonCollapsed": "glyphicon pull-right glyphicon-chevron-down"
			}
		}
	};

class GRVLogin{
	constructor(object){
		this.container = $(object.container);
		this.loginContainer = $('<div>',{class:'grv grv-login-container shadow border'}).appendTo(this.container);
		this.loginWidget = $('<div>',{class:'grv-widget grv-login',id:'loginWidget'}).appendTo(this.loginContainer);
		Survey.StylesManager.applyTheme("bootstrap");
		var loginForm = new Survey.Model(object.surveyLogin);
		loginForm.showQuestionNumbers = 'off';
		loginForm.showCompletedPage = true;
		loginForm.requiredText = '';
		loginForm.locale = pageLanguage;
		pageForms.push(loginForm);
		$(this.loginWidget).Survey({model:loginForm,onComplete:this.sendDataToServer});
		this.subscribeWidget = $('<div>',{class:'grv-widget grv-subscribe',id:'subscribeWidget'}).appendTo(this.loginContainer);
		this.subscribeButton = $('<div>',{class:'btn btn-secondary'}).text('i18n:subscribe_btn').appendTo(this.subscribeWidget);
		this.forgotWidget = $('<div>',{class:'grv-widget grv-forgot',id:'forgotWidget'}).appendTo(this.loginContainer);
		this.forgotButton = $('<div>',{class:'btn btn-secondary'}).text('i18n:forgot_btn').appendTo(this.forgotWidget);
		
		
		this.subscribeButton.click({param1:object},function(event){
			var target = $(event.data.param1.subscribeTarget);
			//hide all grv containers in container
			target.find('.grv').hide();
			//remove all subscribe or forgot widgets
			$('.grv-subscribe-container').remove();
			$('.grv-forgot-container').remove();
			var subscribeFormContainer = $('<div>',{class:'grv grv-subscribe-container shadow border'}).appendTo(target);
			Survey.StylesManager.applyTheme("bootstrap");
			var subscribeForm = new Survey.Model(object.surveySubscribe);
			subscribeForm.showQuestionNumbers = 'off';
			subscribeForm.showCompletedPage = true;
			subscribeForm.requiredText = '';
			subscribeForm.locale = pageLanguage;
			pageForms.push(subscribeForm);
			subscribeForm.onComplete.add(function (survey,options){
				var resultAsString = JSON.stringify(survey.data);
				alert(resultAsString);
				
				/*
				var mr = new GRVMessageRequest(survey.data,true);
				console.log(mr);
				var xhr = new XMLHttpRequest();
			    xhr.open("POST", "http://localhost:8090/login", true);
			    xhr.setRequestHeader("Content-Type", "application/json");
			    var dataStringify = JSON.stringify(mr);
			    xhr.onreadystatechange = function () {
			        if (xhr.readyState === 4 && xhr.status === 200) {
			            var json = JSON.parse(xhr.responseText);
			            console.log(json);
			        }
			    };
			    xhr.send(dataStringify);
			    */
			 });
			subscribeFormContainer.Survey({model:subscribeForm,onComplete:this.sendSubscribeDataToServer});
			var subscribeCancel = $('<div>',{class:'btn btn-secondary'}).text('i18n:cancel_btn').insertBefore($('.grv-subscribe-container .sv_complete_btn'));
			subscribeCancel.click({target:target},function(event){
				$('.grv-subscribe-container').remove();
				event.data.target.find('.grv').show();
			});
			deployLanguage();
		});
		
		
		
		this.forgotButton.click({param1:object},function(event){
			var target = $(event.data.param1.subscribeTarget);
			//hide all grv containers in container
			target.find('.grv').hide();
			//remove all subscribe or forgot widgets
			$('.grv-subscribe-container').remove();
			$('.grv-forgot-container').remove();
			var forgotFormContainer = $('<div>',{class:'grv grv-forgot-container shadow border'}).appendTo(target);
			Survey.StylesManager.applyTheme("bootstrap");
			var forgotForm = new Survey.Model(event.data.param1.surveyForgot);
			forgotForm.showQuestionNumbers = 'off';
			forgotForm.showCompletedPage = true;
			forgotForm.requiredText = '';
			forgotForm.locale = pageLanguage;
			
			
			forgotForm.onComplete.add(function(survey,options){
				var resultAsString = JSON.stringify(survey.data);
				alert(resultAsString);
				
				/*
				var mr = new GRVMessageRequest(survey.data,true);
				console.log(mr);
				var xhr = new XMLHttpRequest();
			    xhr.open("POST", "http://localhost:8090/login", true);
			    xhr.setRequestHeader("Content-Type", "application/json");
			    var dataStringify = JSON.stringify(mr);
			    xhr.onreadystatechange = function () {
			        if (xhr.readyState === 4 && xhr.status === 200) {
			            var json = JSON.parse(xhr.responseText);
			            console.log(json);
			        }
			    };
			    xhr.send(dataStringify);
			    */
			 });
			
			pageForms.push(forgotForm);
			
			
			forgotFormContainer.Survey({model:forgotForm});
			/*
			var forgotCancel = $('<div>',{class:'btn btn-secondary'}).text('i18n:cancel_btn').insertBefore($('.grv-forgot-container .sv_complete_btn'));
			forgotCancel.click({target:target},function(event){
				$('.grv-forgot-container').remove();
				event.data.target.find('.grv').show();
			});
			/**/
			deployLanguage();
		});
		
		
	}
	 
	
	
	
	
	sendForgotDataToServer(survey,options){
		var resultAsString = JSON.stringify(survey.data);
		alert(resultAsString);
		
		/*
		var mr = new GRVMessageRequest(survey.data,true);
		console.log(mr);
		var xhr = new XMLHttpRequest();
	    xhr.open("POST", "http://localhost:8090/login", true);
	    xhr.setRequestHeader("Content-Type", "application/json");
	    var dataStringify = JSON.stringify(mr);
	    xhr.onreadystatechange = function () {
	        if (xhr.readyState === 4 && xhr.status === 200) {
	            var json = JSON.parse(xhr.responseText);
	            console.log(json);
	        }
	    };
	    xhr.send(dataStringify);
	    */
	 }
	
	sendDataToServer(survey){
		
		var resultAsString = JSON.stringify(survey.data);
		alert(resultAsString);
		/*
		var mr = new GRVMessageRequest(survey.data,true);
		console.log(mr);
		var xhr = new XMLHttpRequest();
	    xhr.open("POST", "http://localhost:8090/login", true);
	    xhr.setRequestHeader("Content-Type", "application/json");
	    var dataStringify = JSON.stringify(mr);
	    xhr.onreadystatechange = function () {
	        if (xhr.readyState === 4 && xhr.status === 200) {
	            var json = JSON.parse(xhr.responseText);
	            console.log(json);
	        }
	    };
	    xhr.send(dataStringify);
	    */
	 }
}



	