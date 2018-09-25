


var loginObject={
	container:'grv-login',
	loginTarget:'grv-login',
	subscribeTarget:'grv-frontpage',
	subscribeMode:'modal',
	forgotTarget:'grv-frontpage',
	surveyLogin:{
	    pages: [
	        {
	            name: "groovemr-dis-login", elements: [
	                { type: "text", name: "username", title: getTag("username"),isRequired:true, requiredErrorText:getTag("username_required"), 
	                	validators : [{
	                		type : "text",
	                		minLength: 2,
	                		maxLength : 6,
	                		text: "Username length is wrong"
	                	}]
	                },
	                { type: "text", inputType:"password", name: "password", title: "Password",isRequired:true,requiredErrorText:getTag("password_required") }
	            ]
	        }
	    ],
	    completeText: getTag("login_btn")
	},
	surveySubscribe:{
	    pages: [
	        {
	            name: "groovemr-dis-subscribe", elements: [
	                { type: "text", name: "fname", title: getTag("fname_txt"),isRequired:true, requiredErrorText:getTag("fname_required"), 
	                	validators : [{
	                		type : "text",
	                		minLength: 2,
	                		maxLength : 6,
	                		text: "first name length is wrong"
	                	}]
	                },
	                { type: "text", name: "lname", title: getTag("lname_txt"),isRequired:true, requiredErrorText:getTag("lname_required"), 
	                	validators : [{
	                		type : "text",
	                		minLength: 2,
	                		maxLength : 6,
	                		text: "first name length is wrong"
	                	}]
	                },
	                { type: "text", name: "email", title: getTag("email_txt"),isRequired:true, requiredErrorText:getTag("email_required"), 
	                	validators : [{
	                		type : "text",
	                		minLength: 2,
	                		maxLength : 6,
	                		text: "Username length is wrong"
	                	}]
	                },
	                { type: "text", inputType:"password", name: "password", title: getTag("password"),isRequired:true, requiredErrorText:getTag("password_required"), 
	                	validators : [{
	                		type : "text",
	                		minLength: 2,
	                		maxLength : 6,
	                		text: "Username length is wrong"
	                	}]
	                },
	                { type: "text", inputType:"password", name: "cpassword", title: getTag("cpassword"),isRequired:true, requiredErrorText:getTag("cpassword_required"), 
	                	validators : [{
	                		type : "expression",
	                		expression:"{password} = {cpassword}",
	                		text: "Password and confirm password must be the same"
	                	}]
	                }
	            ]
	        }
	    ],
	    completeText: getTag("subscribe_btn")
	},
	surveyForgot:{
	    pages: [
	        {
	            name: "groovemr-dis-forgot", elements: [
	                { type: "text", name: "username", title: getTag("username"),isRequired:true, requiredErrorText:getTag("username_required"), 
	                	validators : [{
	                		type : "text",
	                		minLength: 2,
	                		maxLength : 6,
	                		text: "Username length is wrong"
	                	}]
	                },
	                { type: "text", inputType:"password", name: "password", title: "Password",isRequired:true,requiredErrorText:getTag("password_required") }
	            ]
	        }
	    ],
	    completeText: getTag("forgot_btn")
	}
};


var login = new GRVLogin(loginObject);


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









