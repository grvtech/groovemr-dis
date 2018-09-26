function deployLanguage(){
	var deployText = function(){
		console.log('deploy text');
		var t = $(this).text();
		var tag = t.substring(5,t.length);
		console.log($(this).prop('tagName'));
		console.log('languageObject.'+pageLanguage+'.'+pageName+'_'+tag);
		$(this).attr('i18n','txt:'+tag);
		$(this).text(eval('languageObject.'+pageLanguage+'.'+pageName+'_'+tag));
	};
	var deployValue = function(){
		console.log('deploy value');
		var t = $(this).val();
		var tag = t.substring(5,t.length);
		console.log($(this).prop('tagName'));
		console.log('languageObject.'+pageLanguage+'.'+pageName+'_'+tag);
		$(this).attr('i18n','val:'+tag);
		$(this).val(eval('languageObject.'+pageLanguage+'.'+pageName+'_'+tag));
	};
	
	var deployAttribute = function(index){
		var result='';
		var t = $(this).attr('i18n');
		var tag = t.substring(4,t.length);
		if(t.indexOf('val:')){
			console.log('before : '+$(this).val());
			result = eval('languageObject.'+pageLanguage+'.'+pageName+'_'+tag);
			console.log('after : '+$(this).val());
		}
		if(t.indexOf('txt:')){
			console.log('before : '+$(this).text());
			result = eval('languageObject.'+pageLanguage+'.'+pageName+'_'+tag);
			
			console.log('after : '+$(this).text());
		}
		
	};
	
	if(languageObject){
		//$("[i18n]").each(deployAttribute);
		$.each($("[i18n]"),function(index,object){
			//console.log($(object));
			var t = $(object).attr('i18n');
			var tag = t.substring(4,t.length);
			
			if(t.indexOf('val:') >=0){
				console.log('before : '+$(object).val());
				$(object).val(eval('languageObject.'+pageLanguage+'.'+pageName+'_'+tag));
				console.log('after : '+$(object).val());
			}else if(t.indexOf('txt:')>=0){
				console.log('before : '+$(object).text());
				$(object).text(eval('languageObject.'+pageLanguage+'.'+pageName+'_'+tag));
				console.log('after : '+$(object).text());
			}
			/**/
			
		});
		
		
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

