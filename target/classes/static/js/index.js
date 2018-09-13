function test(){
	
	alert("test");
}


$(document).ready(function(){
	  plot2 = jQuery.jqplot('chart2', 
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