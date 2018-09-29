function deployLanguage(){
	if(languageObject )
	$('div[grv]').text(function(){return eval('languageObject.'+$(this).attr('i18n'))});
}

function loadLanguage(lang = pageLanguage){
	var jqxhr = $.getJSON( "lang/lang_"+lang+".json", function(lang) {
		languageObject = lang;
		console.log( "success load lang file" );
		console.log(languageObject);
		console.log($('div[grv-button]').attr('i18n'));
	})
	  .done(function() {
	    console.log( "second success" );
	  })
	  .fail(function() {
	    console.log( "error" );
	  })
	  .always(function() {
	    console.log( "complete" );
	  });
}
loadLanguage(pageLanguage);


function getTag(tag,lang='en'){
	if(pageLanguage == lang){
		return eval('languageObject'+tag);
	}else{
		setPageLanguage();
		loadLanguage();
		return eval('languageObject'+tag);
	}
}

function setPageLanguage(lang){
	$('html').attr('lang',lang);
	getPageLanguage();
}

function getPageLanguage(){
	pageLanguage = $('html').attr('lang');
}
