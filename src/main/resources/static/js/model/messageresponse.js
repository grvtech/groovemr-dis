class GRVMessageResponse{
	constructor(object){
		var result = object;
		if(object.state == 'enc'){
			result.elements = JSON.parse(atob(result.elements));
		}
		return result;
	}
}