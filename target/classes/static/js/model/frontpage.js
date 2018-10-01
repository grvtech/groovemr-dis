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
/*
class GRVFrontPage{
	constructor(object){
		if(object.elements.length > 0){
			this.container = $(object.container);
			this.frontpageWidget = $('<div>',{class:'grv grv-frontpage-container shadow border carousel slide','data-ride':'carousel','data-interval':10000}).appendTo(this.container);
			this.inner = $('<div>',{class:'carousel-inner'}).appendTo(this.frontpageWidget);
			this.indicators = $('<ol>',{class:'carousel-indicators'}).appendTo(this.frontpageWidget);
			for(var i=0;i<object.elements.length;i++){
				var item = object.elements[i];
				var a = (i == 0)?'active':'';
				$('<div>',{class:'carousel-item '+a}).html(item.text).appendTo(this.inner);
				$('<li>',{class:a,'data-target':'.grv-frontpage-container', 'data-slide-to':i}).appendTo(this.indicators);
			}
		}
	}
}
*/

function GRVFrontPage(object){
	if(object.elements.length > 0){
		this.container = $(object.container);
		this.frontpageWidget = $('<div>',{class:'grv grv-frontpage-container shadow border carousel slide','data-ride':'carousel','data-interval':10000}).appendTo(this.container);
		this.inner = $('<div>',{class:'carousel-inner'}).appendTo(this.frontpageWidget);
		this.indicators = $('<ol>',{class:'carousel-indicators'}).appendTo(this.frontpageWidget);
		for(var i=0;i<object.elements.length;i++){
			var item = object.elements[i];
			var a = (i == 0)?'active':'';
			$('<div>',{class:'carousel-item '+a}).html(item.text).appendTo(this.inner);
			$('<li>',{class:a,'data-target':'.grv-frontpage-container', 'data-slide-to':i}).appendTo(this.indicators);
		}
	}
}

