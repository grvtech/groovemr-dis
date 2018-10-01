


var loginConfig={
	container:'.login',
	loginTarget:'.login',
	subscribeTarget:'.frontpage',
	forgotTarget:'.frontpage',
	surveyLogin:{
		locale: "en",
	    pages: [
	        {
	            name: "groovemr-dis-login", elements: [
	                { type: "text", name: "username", title:{en:"Username",fr:"Usager"} , isRequired:true, requiredErrorText:{en:"Required field",fr:"Champ requis"},validators : [{type : "text",minLength: 2,text:{en:"Username length is wrong",fr:"Longuer usager erroné"}}]},
	                { type: "text", inputType:"password", name: "password", title: {en:"Password",fr:"Mot de passe"}, isRequired : true, requiredErrorText:{en:"Required field",fr :"Champ requis"}}
	            ]
	        }
	    ],
	    completeText:{en:"Login",fr:"Login"}
	},
	surveySubscribe:{
	    pages: [
	        {
	            name: "groovemr-dis-subscribe", elements: [
	                { type: "text", name: "fname", title: {en:"First Name",fr:"Prenom"},isRequired:true, requiredErrorText:{en:"Required field",fr:"Champ requis"}},
	                { type: "text", name: "lname", title: {en:"Last Name",fr:"Nom"},isRequired:true, requiredErrorText:{en:"Required field",fr:"Champ requis"}},
	                { type: "text", name: "email", title: {en:"Email",fr:"Courriel"},isRequired:true, requiredErrorText:{en:"Required field",fr:"Champ requis"},validators : [{type : "email",text: {en:"Email format is wrong",fr:"Format courriel erroné"}}]},
	                { type: "text", inputType:"password", name: "password", title: {en:"Password",fr:"Mot de passe"},isRequired:true, requiredErrorText:{en:"Required field",fr:"Champ requis"}},
	                { type: "text", inputType:"password", name: "cpassword", title: {en:"Confirm password",fr:"Confirmation mot de passe"},isRequired:true, requiredErrorText:{en:"Required field",fr:"Champ requis"},validators : [{type : "expression",expression:"{password} = {cpassword}",text: {en:"Wrong password confirmation",fr:"Confirmation erroné"}}]}
	            ]
	        }
	    ],
	    completeText: {en:"Send Subscription",fr:"Envoie demande de suscription"}
	},
	surveyForgot:{
	    pages: [
	        {
	            name: "groovemr-dis-forgot", elements: [
	                { type: "text", name: "username", title:{en:"Username",fr:"Usager"} ,isRequired:true, requiredErrorText:{en:"Required field",fr:"Champ requis"}},
	                { type: "text", name: "email", title: {en:"Email",fr:"Courriel"},isRequired:true,requiredErrorText:{en:"Required field",fr:"Champ requis"} }
	            ]
	        }
	    ],
	    completeText: {en:"Send password reset",fr:"Initialisé mot de passe"}
	}
};



fetchConfig("login",GRVLogin);


//var login = new GRVLogin(loginConfig);
//var languageConfig = {"container":".language",languages : [{lang:"en"},{lang:"fr"},{lang:"ro"}]};
//var language = new GRVLanguage(languageConfig);
fetchConfig("language",GRVLanguage);

//var frontpageConfig = {"container":".frontpage",elements:[{"text":"lorem ipsum lorem ipsum  lorem ipsum  lorem ipsum  lorem ipsum  lorem ipsum  lorem ipsum  lorem ipsum  lorem ipsum  lorem ipsum "},{"text":"<h1>bla bla bla</h1><p style='color:red'>text test</p><h1>bla bla bla</h1>"},{"text":"lorem ipsum lorem ipsum  lorem ipsum  lorem ipsum  lorem ipsum  lorem ipsum  lorem ipsum  lorem ipsum  lorem ipsum  lorem ipsum "},{"text":"<h1>bla bla bla</h1><p style='color:red'>text test</p><h1>bla bla bla</h1>"}]};
//var frontpage = new GRVFrontPage(frontpageConfig);
fetchConfig("frontpage",GRVFrontPage);





/*
  // cache refresh logic. It is important to put it early to avoid bug in the app
  // break the refresh logic. (If we got into that state, user must clear the 
  // cache manually.)
  setTimeout(function() {
    var appCache = window.applicationCache;
    var confirmAndUpdate = function() {
      console.warn('update ready');
      if (confirm("A new version of BeeDesk is available. Do you want to reload BeeDesk now?")) {                   
        appCache.swapCache();
        console.log('updated');
        location.reload();
        console.log('reloaded');
      }
    };

    console.log('poll check on appcache');
    if (appCache.status === appCache.UPDATEREADY) {
      console.log('UPDATEREADY');
      confirmAndUpdate();
    } else {
      appCache.addEventListener('checking', function() {
        console.log('checking cache');
      }, false);

      appCache.addEventListener('noupdate', function() {
        console.log('no update');
      }, false);

      appCache.addEventListener('error', function() {
        console.error('failed to update cache');
      }, false);

      appCache.addEventListener('updateready', confirmAndUpdate, false);

      if (appCache.status === appCache.UPDATEREADY) {
        console.warn('UPDATEREADY when event handlers were added.');
        confirmAndUpdate();
      } else if (appCache.status !== undefined && appCache.status !== null ) {
        if (!appCache) {
          appCache.update();
        }
      }
    }
  }, 10000);

*/










/*messages functions*/









