/*
var langFileLoaded = false;
var languageObject = null;
var pageLanguage = $('html').attr('lang');


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

deployLanguage();


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









function test(){
	
	alert("test");
}

var plot1,plot2;


$(document).ready(function(){
   plot2 = $.jqplot ('chart2',
          [ [ ["2012-01-01", 30], ["2012-01-02", -10], ["2012-01-03", 90], ["2012-01-04", 20], ["2012-01-05", 50], 
              ["2012-01-06", 130], ["2012-01-07", 80], ["2012-01-08", 120],["2012-01-09", 50] ]], {
      axes : {
        xaxis : {
          label : "X Axis Label",
          renderer:$.jqplot.DateAxisRenderer,
        }
      },
      canvasOverlay: {
        show: true,
        objects: [
          { rectangle: { xmin: new Date("2012-01-03"), xmax: new Date("2012-01-07"), xminOffset: "0px", xmaxOffset: "0px", yminOffset: "0px", ymaxOffset: "0px",
                    color: "rgba(0, 200, 200, 0.3)", showTooltip: true, tooltipFormatString: "Holidays" } },
        ]
      } 
  });
});



$(document).ready(function(){
	  plot1 = jQuery.jqplot('chart1', 
	    [[['Verwerkende industrie', 9],['Retail', 0], ['Primaire producent', 0], 
	    ['Out of home', 0],['Groothandel', 0], ['Grondstof', 0], ['Consument', 3], ['Bewerkende industrie', 2]]], 
	    {
	      title: ' ', 
	      seriesDefaults: {
	        shadow: false, 
	        renderer: jQuery.jqplot.PieRenderer, 
	        rendererOptions: { 
	          startAngle: 180, 
	          sliceMargin: 4, 
	          showDataLabels: true } 
	      }, 
	      legend: { show:true, location: 'w' }
	    }
	  );
	});



$(window).bind('resize', function(event, ui) {
	
    //$('#chart2').height($('.statsitem').height()*0.96);
    //$('#chart2').width($('.statsitem').width()*0.96);
    plot2.replot({resetAxes:true});
    plot1.replot({resetAxes:true});
});










class Button{
	constructor(containerName,obj){
		this.container = $("."+containerName);
		this.object = $('<div>',{class:"messages-container"}).appendTo(this.container);
		this.header = $('<div>',{class:"header"}).appendTo(this.object);
	}
}


class Messages{
	constructor(containerName, obj){
		this.container = $("."+containerName);
		this.object = $('<div>',{class:"messages-container"}).appendTo(this.container);
		this.header = $('<div>',{class:"header"}).appendTo(this.object);
	}
}

class Message{
	constructor(containerName, obj){
		this.container = $("."+containerName);
		this.object = $('<div>',{class:"message"}).appendTo(this.container);
		this.toolbar = $('<div>',{class:'message-toolbar'}).appendTo(this.object);
		this.body = $('<div>',{class:'message-body'}).appendTo(this.object);
		$('<div>',{class:"from"}).text(obj.fullname).appendTo(this.toolbar);
		$('<div>',{class:"status"}).text(obj.status).appendTo(this.toolbar);
		this.date = $('<div>',{class:"date"}).text(obj.date).appendTo(this.toolbar);
		this.deletebutton = $('<div>',{class:"button message-delete"}).text('Delete').appendTo(this.toolbar);
		$('<div>',{class:"text"}).text(obj.text).appendTo(this.body);
		this.respond =  $('<div>',{class:"button"}).text('Respond').appendTo($('<div>',{class:"footer"}).appendTo(this.body));
		
		
		
		this.object.bind('click',this.click);
		this.object.bind('mouseenter',this.hoverin);
		this.object.bind('mouseleave',this.hoverout);
	}
	
	hoverin(){
		$(this).find('.date').hide();
		$(this).find('.message-delete').show();
	}
	
	hoverout(){
		$(this).find('.date').show();
		$(this).find('.message-delete').hide();
	}
	
	click(){
		$(this).parent().find('.message-body').hide();
		$(this).find('.message-body').show();
	}
}


*/


var search = new GRVSearch(ob);




