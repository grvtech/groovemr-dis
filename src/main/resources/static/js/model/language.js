class GRVLanguage{
	constructor(configObject){
		this.config = configObject;
		this.container = $(configObject.container);
		this.dropdown = $('<div>',{class:'dropdown'}).appendTo(this.container);
		this.label = (pageLanguage == 'en')?getTag('language_btn_en'):getTag('language_btn_fr');
		this.labeltag = (pageLanguage == 'en')?'language_btn_en':'language_btn_fr';
		this.button = $('<button>',{type:'button',class:'btn btn-primary dropdown-toggle','data-toggle':'dropdown',i18n:this.labeltag}).text(this.label).appendTo(this.dropdown);
		this.menu = $('<div>',{class:'dropdown-menu'}).appendTo(this.dropdown);
		this.enbutton = $('<a>',{class:'dropdown-item',lang:'en'}).text('i18n:language_btn_en').appendTo(this.menu);
		this.frbutton = $('<a>',{class:'dropdown-item',lang:'fr'}).text('i18n:language_btn_fr').appendTo(this.menu);
		
		
		this.enbutton.click({config:this.config},this.clickChoice);
		this.frbutton.click({config:this.config}, this.clickChoice);
		
	}
	
	clickChoice(event){
		var btntag = $(event.data.config.container).find('button').attr('i18n');
		var stag = $(this).attr('i18n');
		console.log(btntag + '    '+stag);
		if(btntag == stag){
			//do nothing same language
		}else{
			$(event.data.config.container).find('button').attr('i18n',stag);
			setPageLanguage($(this).attr('lang'));
			deployLanguage();
		}
	}
	
}