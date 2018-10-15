var ob = {
		"container":".grv-search",
		"title":"Search patient",
		"criteria":[
			{"label":"RAMQ","value":"ramq","default":"0"},
			{"label":"Chart Number","value":"chart","default":"0"},
			{"label":"Index Patient","value":"qia","default":"1"},
			{"label":"Full name","value":"lname_and_fname","default":"0"}
		]
};

function GRVSearch(object){
	var inHeader = false;
	this.container = $(object.container);
	if(this.container.parent().hasClass('grv-header')){inHeader = true;}
	this.searchContainer = $('<div>',{class:'grv grv-search-container'}).appendTo(this.container);
	
	this.searchWidget = $('<div>',{class:'grv-widget grv-search-widget input-group small-shadow', tabindex:"10",id:"searchWidget"}).appendTo(this.searchContainer);
	if(inHeader){
		this.criteria = $('<div>',{class:'input-group-prepend'}).appendTo(this.searchWidget);
		this.criteriaButton = $('<button>',{class:'btn btn-secondary btn-sm dropdown-toggle',type:'button','data-toggle':'dropdown','aria-haspopup':'true','aria-expanded':'false'}).appendTo(this.criteria).text('Criteria');
		this.criteriaMenu = $('<div>',{class:'dropdown-menu'}).appendTo(this.criteria);
		for(var i=0;i<object.criteria.length;i++){
			var item = object.criteria[i];
			var active = (item.default == "1")?"active":"";
			this.criteriaMenuItem = $('<div>',{class:'dropdown-item '+active}).appendTo(this.criteriaMenu).text(item.label);
		};
		this.input = $('<input>',{class:'form-control',"aria-label":"text input"}).appendTo(this.searchWidget);
		this.buttonContainer = $('<div>',{class:'input-group-apend'}).appendTo(this.searchWidget);
		this.searchButton = $('<i>',{class:'fas fa-search'}).appendTo(this.buttonContainer); 
	}else{
		this.searchTitleWidget = $('<div>',{class:'grv-widget grv-search-title'}).insertBefore(this.searchWidget).text(object.title);
		this.searchCriteriaWidget = $('<div>',{class:'grv-search-criteria'}).appendTo(this.searchContainer);
		for(var i=0;i<object.criteria.length;i++){
			var item = object.criteria[i];
			
			this.criteriaItem = $('<input>',{type:'radio', name:'searchCriteria'});
			if(item.default == "1"){this.criteriaItem.attr("checked","checked");}
			this.criteriaItemLabel = $('<label>',{class:'radio-inline'}).append(this.criteriaItem).append(item.label).appendTo(this.searchCriteriaWidget);
		};

		this.input = $('<input>',{class:'form-control',"aria-label":"text input"}).appendTo(this.searchWidget);
		this.buttonContainer = $('<div>',{class:'input-group-apend'}).appendTo(this.searchWidget);
		this.searchButton = $('<button>',{class:'btn btn-primary'}).append($('<i>',{class:'fas fa-search'})).appendTo(this.buttonContainer); 
	}
	
	this.input
      // don't navigate away from the field on tab when selecting an item
      .on( "keydown", function( event ) {
        if ( event.keyCode === $.ui.keyCode.TAB &&
            $( this ).autocomplete( "instance" ).menu.active ) {
          event.preventDefault();
        }
      })
      .autocomplete({
        source: function( request, response ) {
          $.getJSON( "search.json", {term: extractLast( request.term )}, response );
        },
        search: function() {
          var term = extractLast( this.value );
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
      });
}



