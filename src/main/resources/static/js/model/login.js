var loginCss = {
    matrix: {
        root: "table table-striped"
    },
    navigation: {
		complete: "btn sv_complete_btn mybutton",
	},
    navigationButton: "button btn-lg mybutton"
};


class GRVLogin{
	constructor(object){
		this.container = $('.grv-login');
		this.loginWidget = $('<div>',{class:'grvlw'}).appendTo(this.container);
		Survey.StylesManager.applyTheme("bootstrap");
		var loginForm = new Survey.Model(object.surveyLogin);
		loginForm.showQuestionNumbers = 'off';
		loginForm.showCompletedPage = true;
		loginForm.requiredText = ''; 
		$(this.loginWidget).Survey({model:loginForm,onComplete:this.sendDataToServer,css:loginCss});
		this.subscribeWidget = $('<div>',{class:'btn btn-primary',i18n:'subscribe'}).text(getTag('subscribe')).appendTo(this.container);
		this.forgotWidget = $('<div>',{class:'btn btn-secondary',i18n:'forgot'}).text(getTag('forgot')).appendTo(this.container);
		
		
		this.subscribeWidget.click(function(){
			alert(object.subscribeTarget);
			$('.'+object.subscribeTarget).show();
			Survey.StylesManager.applyTheme("bootstrap");
			var subscribeForm = new Survey.Model(object.surveySubscribe);
			subscribeForm.showQuestionNumbers = 'off';
			subscribeForm.showCompletedPage = true;
			subscribeForm.requiredText = ''; 
			$('.'+object.subscribeTarget).Survey({model:subscribeForm,onComplete:this.sendSubscribeDataToServer});
		});
		
		this.forgotWidget.click(function(){
			
		});
	}
	 
	sendSubscribeDataToServer(survey){
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
		//var resultAsString = JSON.stringify(survey.data);
		//alert(resultAsString);
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
	 }
}



	