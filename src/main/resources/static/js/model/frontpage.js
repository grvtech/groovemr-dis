/*
 * fpc = front page container
 * fpm = front page message
 * 
 * 
 * 
 * message array format = [{messageid,priority, severity,  text},{messageid,priority,severity text}, ...] 
 * 
 * priority = order to be displayed
 * severity = colors 
 * 
 * */
class GRVFrontPage(){
	constructor(frontpageContainer, messagesArray){
		this.container = $('#fpc');
		if(messagesArray.length > 0 ){
			
		}else{
			this.container.hide();
		}
	}
}


class GRVFrontpageMessage(){
	constructor(container,message){
		
	}
}