class GRVMessageResponse{
	constructor(object){
		var result = object;
		if(object.state == 'enc'){
			if(result.elements && typeof(result.elements) == "object"){
				$.each(result.elements, function(index, value){
					result.elements[index] = JSON.parse(atob(value));
				});
			}
		}
		return result;
	}
}