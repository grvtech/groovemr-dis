function deployLanguage(){
	if(languageObject)
	$('[i18n]').text(function(){return eval('languageObject.'+$(this).attr('i18n'))});
}

function loadLanguage(lang){
	console.log('execute loadlanguage '+new Date());
	var jqxhr = $.getJSON( "lang/lang_"+lang+".json", function(lang) {
		languageObject = lang;
		console.log( "success load lang file" );
		console.log(languageObject);
	})
	  .fail(function(xhr, textStatus, errorThrown) {
	    console.log( "error loading language object" );
	    console.log(errorThrown);
	  })
}

function getTag(tag){
	console.log('execute getTag '+new Date());
	console.log('tag:'+pageName+'_'+tag);
	var result = pageName+'_'+tag;
	if(languageObject != null){
		result = eval('languageObject.'+pageName+'_'+tag);
	}
	return result;
}

function setPageLanguage(lang){
	$('html').attr('lang',lang);
	getPageLanguage();
}

function getPageLanguage(){
	pageLanguage = $('html').attr('lang');
}

function getPageName(){
	var sPath = window.location.pathname;
	var sPage = sPath.substring(sPath.lastIndexOf('/') + 1);
	if(sPage == '')sPage = 'index';
	return sPage;
}

