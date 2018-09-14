function test(){
	
	alert("test");
}




$(document).ready(function(){
  var plot2 = $.jqplot ('chart2',
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
	  plot2 = jQuery.jqplot('chart1', 
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