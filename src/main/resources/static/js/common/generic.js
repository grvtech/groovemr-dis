function deployLanguage(){
	var deployText = function(){
		console.log('deploy text');
		var t = $(this).text();
		var tag = t.substring(5,t.length);
		console.log($(this).prop('tagName'));
		console.log('languageObject.'+pageLanguage+'.'+tag);
		$(this).attr('i18n','txt:'+tag);
		$(this).text(eval('languageObject.'+pageLanguage+'.'+tag));
	};
	var deployValue = function(){
		console.log('deploy value');
		var t = $(this).val();
		var tag = t.substring(5,t.length);
		console.log($(this).prop('tagName'));
		console.log('languageObject.'+pageLanguage+'.'+tag);
		$(this).attr('i18n','val:'+tag);
		$(this).val(eval('languageObject.'+pageLanguage+'.'+tag));
	};
	
	var deployAttribute = function(index){
		var result='';
		var t = $(this).attr('i18n');
		var tag = t.substring(4,t.length);
		if(t.indexOf('val:')>=0){
			result = eval('languageObject.'+pageLanguage+'.'+tag);
			$(this).val(result);
		}
		if(t.indexOf('txt:')>=0){
			result = eval('languageObject.'+pageLanguage+'.'+tag);
			$(this).text(result);
		}
	};
	
	if(languageObject){
		$("[i18n]").each(deployAttribute);
		$(":contains('i18n:'):not(:has(:contains('i18n:')))").each(deployText);
		$("[value^='i18n:']").each(deployValue);
	}
	
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
	//console.log('execute getTag '+new Date());
	//console.log('tag:'+pageName+'_'+tag);
	var result = pageLanguage+'.'+pageName+'_'+tag;
	if(languageObject != null){
		result = eval('languageObject.'+pageLanguage+'.'+pageName+'_'+tag);
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

function getPageObject(){
	var obj = {};
	var objParameters = {};
	var url = new URL(window.location);
	
	objParameters = Array.from(url.searchParams).reduce((objParameters, [key, val]) => {
		objParameters[key] = val;
		  return objParameters;
		}, {});
	
	obj = url;
	obj['parameters'] = objParameters;
	return obj;
}


function fetchData(url,dataArray, callback){
	var p = getPageObject();
	console.log(p);
} 




function fetchConfig(configName,callback){
	var jqxhr = $.getJSON( "js/config/"+configName+".json", function(object) {
		if (callback && typeof(callback) === "function") {
			callback(object);
	    }
		console.log( "success load config file" );
	}).fail(function(xhr, textStatus, errorThrown) {
	    console.log( "error loading language object" );
	    console.log(errorThrown);
	})
}


