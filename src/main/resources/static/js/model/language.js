class GRVLanguage{
	constructor(configObject){
		this.config = configObject;
		this.container = $(configObject.container);
		this.dropdown = $('<div>',{class:'dropdown'}).appendTo(this.container);
		this.label = getTag('language_btn_'+pageLanguage);
		this.labeltag = 'language_btn_'+pageLanguage;
		this.button = $('<button>',{type:'button',class:'btn btn-seondary dropdown-toggle','data-toggle':'dropdown',i18n:this.labeltag}).text(this.label).appendTo(this.dropdown);
		this.menu = $('<div>',{class:'dropdown-menu'}).appendTo(this.dropdown);
		for(var i=0;i<configObject.languages.length;i++){
			var l = configObject.languages[i];
			this.button = $('<a>',{class:'dropdown-item',lang:l.lang}).text('i18n:language_btn_'+l.lang).appendTo(this.menu);
			this.button.click({config:this.config},this.clickChoice);
		}
	}
	
	clickChoice(event){
		var btntag = $(event.data.config.container).find('button').attr('i18n');
		var stag = $(this).attr('i18n');
		console.log(btntag + '    '+stag);
		if(btntag != stag){
			$(event.data.config.container).find('button').attr('i18n',stag);
			setPageLanguage($(this).attr('lang'));
			deployLanguage();
			for(var i=0;i<pageForms.length;i++){
				var form = pageForms[i];
				form.locale = pageLanguage;
				form.render();
			}
		}
	}
}