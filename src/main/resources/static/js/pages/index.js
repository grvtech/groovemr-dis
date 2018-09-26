


var loginObject={
	container:'.grv-login',
	loginTarget:'.grv-login',
	subscribeTarget:'.grv-frontpage',
	subscribeMode:'modal',
	forgotTarget:'.grv-frontpage',
	surveyLogin:{
	    pages: [
	        {
	            name: "groovemr-dis-login", elements: [
	                { type: "text", name: "username", title: "i18n:username", isRequired:true, requiredErrorText:" ",
	                	validators : [{
	                		type : "text",
	                		minLength: 2,
	                		text: "Username length is wrong"
	                	}]
	                },
	                { type: "text", inputType:"password", name: "password", title: "i18n:password", isRequired : true, requiredErrorText:" " }
	            ]
	        }
	    ],
	    completeText: "i18n:login_btn"
	},
	surveySubscribe:{
	    pages: [
	        {
	            name: "groovemr-dis-subscribe", elements: [
	                { type: "text", name: "fname", title: "i18n:fname_txt",isRequired:true, requiredErrorText:"i18n:fname_required", 
	                	validators : [{
	                		type : "text",
	                		minLength: 2,
	                		maxLength : 6,
	                		text: "first name length is wrong"
	                	}]
	                },
	                { type: "text", name: "lname", title: "i18n:lname_txt",isRequired:true, requiredErrorText:"i18n:lname_required", 
	                	validators : [{
	                		type : "text",
	                		minLength: 2,
	                		maxLength : 6,
	                		text: "first name length is wrong"
	                	}]
	                },
	                { type: "text", name: "email", title: "i18n:email_txt",isRequired:true, requiredErrorText:"i18n:email_required", 
	                	validators : [{
	                		type : "text",
	                		minLength: 2,
	                		maxLength : 6,
	                		text: "Username length is wrong"
	                	}]
	                },
	                { type: "text", inputType:"password", name: "password", title: "i18n:password",isRequired:true, requiredErrorText:"i18n:password_required", 
	                	validators : [{
	                		type : "text",
	                		minLength: 2,
	                		maxLength : 6,
	                		text: "Username length is wrong"
	                	}]
	                },
	                { type: "text", inputType:"password", name: "cpassword", title: "i18n:cpassword",isRequired:true, requiredErrorText:"i18n:cpassword_required", 
	                	validators : [{
	                		type : "expression",
	                		expression:"{password} = {cpassword}",
	                		text: "Password and confirm password must be the same"
	                	}]
	                }
	            ]
	        }
	    ],
	    completeText: "i18n:subscribe_btn"
	},
	surveyForgot:{
	    pages: [
	        {
	            name: "groovemr-dis-forgot", elements: [
	                { type: "text", name: "username", title:"i18n:username" ,isRequired:true, requiredErrorText:"i18n:username_required", 
	                	validators : [{
	                		type : "text",
	                		minLength: 2,
	                		maxLength : 6,
	                		text: "Username length is wrong"
	                	}]
	                },
	                { type: "text", inputType:"password", name: "password", title: "i18n:password",isRequired:true,requiredErrorText:"i18n:password_required" }
	            ]
	        }
	    ],
	    completeText: getTag("forgot_btn")
	}
};


var login = new GRVLogin(loginObject);

var languageConfig = {
	"container":".grv-language"	
};

var language = new GRVLanguage(languageConfig);


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









