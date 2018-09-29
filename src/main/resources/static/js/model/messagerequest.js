class GRVMessageRequest{
	constructor(objectArray,encrypted=true){
		var result = {};
		var elements = JSON.stringify(objectArray);
		result['timestamp'] = '2018-09-24';
		result['state']  = (encrypted)?'enc':'clear';
		result['elements'] = (encrypted)?btoa(elements):elements;
		return result;
	}
}