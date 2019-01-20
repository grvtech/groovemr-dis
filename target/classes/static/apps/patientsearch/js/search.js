function GRVsearch(object){
	var inHeader = false;
	this.container = $(object.container);
	if(this.container.parent().hasClass('grv-header')){inHeader = true;}
	this.searchContainer = $('<div>',{class:'grv grv-search-container'}).appendTo(this.container);
	this.criteriaValue = '';
	this.searchWidget = $('<div>',{class:'grv-widget grv-search-widget input-group small-shadow', tabindex:"10",id:"searchWidget"}).appendTo(this.searchContainer);
	if(inHeader){
		this.criteria = $('<div>',{class:'input-group-prepend'}).appendTo(this.searchWidget);
		this.criteriaButton = $('<button>',{class:'btn btn-secondary btn-sm dropdown-toggle',type:'button','data-toggle':'dropdown','aria-haspopup':'true','aria-expanded':'false'}).appendTo(this.criteria).text('Criteria');
		this.criteriaMenu = $('<div>',{class:'dropdown-menu'}).appendTo(this.criteria);
		for(var i=0;i<object.criteria.length;i++){
			var item = object.criteria[i];
			var active = "";
			if(item.default == "1"){
				active = "active";
				this.criteriaValue = item.value;
			}
			
			this.criteriaMenuItem = $('<div>',{class:'dropdown-item '+active,"data-value":item.value}).appendTo(this.criteriaMenu).text(item.label);
		};
		/*
		this.input = $('<input>',{class:'form-control',"aria-label":"text input"}).appendTo(this.searchWidget);
		this.buttonContainer = $('<div>',{class:'input-group-apend'}).appendTo(this.searchWidget);
		this.searchButton = $('<i>',{class:'fas fa-search'}).appendTo(this.buttonContainer);
		*/ 
	}else{
		this.searchTitleWidget = $('<div>',{class:'grv-widget grv-search-title'}).insertBefore(this.searchWidget).text(object.title);
		this.searchCriteriaWidget = $('<div>',{class:'grv-search-criteria'}).appendTo(this.searchContainer);
		for(var i=0;i<object.criteria.length;i++){
			var item = object.criteria[i];
			
			this.criteriaItem = $('<input>',{type:'radio', name:'searchCriteria',"data-value":item.value});
			if(item.default == "1"){
				this.criteriaItem.attr("checked","checked");
				this.criteriaValue = item.value;
			}
			this.criteriaItemLabel = $('<label>',{class:'radio-inline'}).append(this.criteriaItem).append(item.label).appendTo(this.searchCriteriaWidget);
		};
		
		/*
		this.input = $('<input>',{class:'form-control',"aria-label":"text input"}).appendTo(this.searchWidget);
		this.buttonContainer = $('<div>',{class:'input-group-apend'}).appendTo(this.searchWidget);
		this.searchButton = $('<button>',{class:'btn btn-primary'}).append($('<i>',{class:'fas fa-search'})).appendTo(this.buttonContainer);
		*/ 
	}
	this.inputCriteria = $('<input>',{type:'hidden', id:'inputCriteria', value:this.criteriaValue}).appendTo(this.searchWidget);
	this.input = $('<input>',{class:'form-control',"aria-label":"text input"}).appendTo(this.searchWidget);
	this.buttonContainer = $('<div>',{class:'input-group-apend'}).appendTo(this.searchWidget);
	this.searchButton = $('<button>',{class:'btn btn-primary'}).append($('<i>',{class:'fas fa-search'})).appendTo(this.buttonContainer);
	
	
	this.input
      // don't navigate away from the field on tab when selecting an item
      .on( "keydown", function( event ) {
        if ( event.keyCode === $.ui.keyCode.TAB &&
            $( this ).autocomplete( "instance" ).menu.active ) {
          event.preventDefault();
        }
      })
      .autocomplete({
    	delay:200,
      	minLenght:2,
      	autofocus:true,
        source: function( request, response ) {
          //$.getJSON( "http://localhost:8090/search/patient", {term: request.term}, response );
        	$.ajax({
        		url:"http://localhost:8090/search/patient",
        		dataType:"json",
        		data : {
        			criteria: $("#inputCriteria").val(),
        			term: request.term,
        			language : pageLanguage,
        			sid : ''
        		},
        		success : function(data){
        			response ($.map(data, function(item){
        				return {
        					firstname : item.firstname,
        					lastname : item.lastname,
        					chart: item.chat
        				}
        			}));
        		}
        	});

        },
        search: function() {
          var term = this.value ;
          if ( term.length < 2 ) {
            return false;
          }
        },
        focus: function() {
          // prevent value inserted on focus
          return false;
        },
        select: function( event, ui ) {
          var terms = split( this.value );
          // remove the current input
          terms.pop();
          // add the selected item
          terms.push( ui.item.value );
          // add placeholder to get the comma-and-space at the end
          terms.push( "" );
          this.value = terms.join( ", " );
          return false;
        }
        
      }).data("ui-autocomplete")._renderItem = function( ul, item ) {
        return $( "<li>" )
          .append( "<div> " + item.firstname + "<br>" + item.lastname + "</div>" )
          .appendTo( ul );
      };
      
      this.input.data("ui-autocomplete")._resizeMenu = function () {
          var ul = this.menu.element;
          ul.outerWidth(this.element.outerWidth()+40);
          var s = this.element.offset();
          var d = ul.offset().top;
          ul.offset({top:d + 10});
          ul.css("top",d+10+"px");
          console.log(d+10);
          
  }
}



