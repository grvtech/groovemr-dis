


var loginObject = {
    pages: [
        {
            name: "customerContact", elements: [
                { type: "text", name: "username", title: "Username",isRequired:true },
                { type: "text", inputType:"password", name: "password", title: "Password",isRequired:true }
            ]
        }
    ],
    completeText: "Login"
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









