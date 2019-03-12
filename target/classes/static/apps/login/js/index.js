function GRVlogin(object){
		this.container = $(object.container);
		this.loginContainer = $('<div>',{class:'grv grv-login-container shadow border'}).appendTo(this.container);
		this.loginWidget = $('<div>',{class:'grv-widget grv-login',id:'loginWidget'}).appendTo(this.loginContainer);
		
		Survey.StylesManager.applyTheme("bootstrap");
		var loginForm = new Survey.Model(object.surveyLogin);
		loginForm.showQuestionNumbers = 'off';
		loginForm.showCompletedPage = true;
		loginForm.completedHtml = "Logging in...";
		loginForm.requiredText = '';
		loginForm.locale = pageLanguage;
		pageForms.push(loginForm);
		$(this.loginWidget).Survey({model:loginForm,onServerValidateQuestions : function(survey,options){
			var mr = new GRVMessageRequest(survey.data,'login',emptysession,0);
			//call the ajax method
		    $.ajax({
		    	url: pageObject.origin+object.actions.login,
		    	type: 'post',
		        dataType: 'json',
		        contentType: 'application/json',
		        data: JSON.stringify(mr)
		    }).then(function (data) {
				var mresp = new GRVMessageResponse(data);
				if (mresp.status == "error"){
					localiseObject(mresp);
					options.errors = mresp.elements.error;
				}else{
					//tell survey that we are done with the server validation
					if (storageAvailable('sessionStorage')) {
						  // Yippee! We can use sessionStorage awesomeness
						var login = {};
						login['timestamp'] = moment().format('YYYY-MM-DD HH:mm:ss');
						login['user'] = mresp.elements.uuiduser;
						login['session'] = mresp.elements.uuidsession;
						
						options['login'] = login;
						options['next'] = object.actions.loginsuccess;
						options['prev'] = '/index';
					}
					else {
					  // Too bad, no localStorage for us
						console.log("no local storage")
					}
				}
				options.complete();
			});
		},onComplete:function(survey,options){
			gtu(options);
		}});
			
		this.subscribeWidget = $('<div>',{class:'grv-widget grv-subscribe',id:'subscribeWidget'}).appendTo(this.loginContainer);
		this.subscribeButton = $('<div>',{class:'btn btn-secondary'}).text('i18n:subscribe_btn').appendTo(this.subscribeWidget);
		this.forgotWidget = $('<div>',{class:'grv-widget grv-forgot',id:'forgotWidget'}).appendTo(this.loginContainer);
		this.forgotButton = $('<div>',{class:'btn btn-secondary'}).text('i18n:forgot_btn').appendTo(this.forgotWidget);
		
		
		setTimeout(deployLanguage, 1);
		
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
			subscribeForm.completedHtml = "Logging in..."
			pageForms.push(subscribeForm);
			
			subscribeFormContainer.Survey({model:subscribeForm,onServerValidateQuestions:function(survey,options){
				var url = getPageObject();
				var mr = new GRVMessageRequest(survey.data,'subscribe','',true);
				//call the ajax method
			    $.ajax({
	    			url: url.origin+"/login/subscribe",
	    			type: 'post',
	                dataType: 'json',
	                contentType: 'application/json',
	                data: JSON.stringify(mr)
	    		}).then(function (data) {
			            var mresp = new GRVMessageResponse(data);
			            if (mresp.status == "error"){
			            	localiseObject(mresp);
			            	options.errors = mresp.elements.error;
			            }else{
			            	
			            }
			            //tell survey that we are done with the server validation
			            options.complete();
			       });
			},onComplete:function(survey,options){
				//window.location = "/user";
			}});

			var subscribeCancel = $('<div>',{class:'btn btn-secondary'}).text('i18n:cancel_btn').insertBefore($('.grv-subscribe-container .sv_complete_btn'));
			subscribeCancel.click({target:target},function(event){
				$('.grv-subscribe-container').remove();
				event.data.target.find('.grv').show();
			});
			
			deployLanguage(subscribeFormContainer);
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
			pageForms.push(forgotForm);
			forgotFormContainer.Survey({model:forgotForm});
			var forgotCancel = $('<div>',{class:'btn btn-secondary'}).text('i18n:cancel_btn').insertBefore($('.grv-forgot-container .sv_complete_btn'));
			forgotCancel.click({target:target},function(event){
				$('.grv-forgot-container').remove();
				event.data.target.find('.grv').show();
			});
			
			
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
			
			deployLanguage(forgotFormContainer);
		});
		
		
}

