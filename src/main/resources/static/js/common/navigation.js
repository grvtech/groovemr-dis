/*go to user*/
function gtu(object){
	//put in local storage the session information
	console.log(object);
	if (storageAvailable('sessionStorage')) {
		sessionStorage.setItem('loginObject',object.login);
	}
	windows.location = object.next;
}

/*go to logout*/
function gtlo(object){
	//from object remove local storage traces of session
}


/*go to login*/
function gtli(object){
	//start new session
	
}
