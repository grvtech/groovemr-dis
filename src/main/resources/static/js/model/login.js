class GRVLogin{
	constructor(object){
		this.container = $('.grv-login');
		this.widget = $('<div>',{class:'grvlw'}).appendTo(this.container);
		Survey.StylesManager.applyTheme("bootstrap");
		var loginForm = new Survey.Model(object);
		loginForm.showQuestionNumbers = 'off';
		loginForm.showCompletedPage = true;
		loginForm.requiredText = ''; 
		$(this.widget).Survey({model:loginForm,onComplete:this.sendDataToServer});
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



	