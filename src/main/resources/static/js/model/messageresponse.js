class GRVMessageResponse{
	constructor(object){
		var result = object;
		if(object.state == 'clear'){
			if(result.elements && typeof(result.elements) == "object"){
				$.each(result.elements, function(index, value){
					result.elements[index] = JSON.parse(atob(value));
				});
			}
		}else{
			if(result.elements && typeof(result.elements) == "object"){
				$.each(result.elements, function(index, value){
					//result.elements[index] = JSON.parse(atob(value));
					result.elements[index] = JSON.parse(Tea.decrypt(value,licence));
				});
			}
		}
		return result;
	}
}