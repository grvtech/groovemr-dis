/*
 * test if soemone is already sign in with this tab
 * */
if(pageName == 'index'){
	if (storageAvailable('sessionStorage')) {
		  // Yippee! We can use sessionStorage awesomeness
		if(isItemExist('loginObject')){
			window.location = "/user";
		}else{
			console.log('login object does not exists : login first');
		}
	}
	else {
	  // Too bad, no localStorage for us
		console.log("no local storage");
	}
}

/*
 * deploy the language
 * */
deployLanguage();