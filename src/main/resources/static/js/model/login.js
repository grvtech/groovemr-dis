class GRVLogin{
	constructor(object){
		this.container = $('.grv-login');
		this.widget = $('<div>',{class:'grvlw'}).appendTo(this.container);
		Survey.StylesManager.applyTheme("bootstrap");
		var loginForm = new Survey.Model(object);
		loginForm.showQuestionNumbers = 'off';
		loginForm.showCompletedPage = false;
		loginForm.requiredText = ''; 
		$(this.widget).Survey({model:loginForm,onComplete:this.sendDataToServer});
	}
	 sendDataToServer(survey){
		var resultAsString = JSON.stringify(survey.data);
		alert(resultAsString); //send Ajax request to your web server. 
	 }
}



	