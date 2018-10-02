class GRVMessageRequest{
	constructor(objectArray,action,idsession,encrypted=true){
		var result = {};
		var elements = JSON.stringify(objectArray);
		result['timestamp'] = '2018-09-24';
		result['action'] = action;
		result['uuidsession'] = idsession;
		result['state']  = (encrypted)?'enc':'clear';
		result['elements'] = (encrypted)?btoa(elements):elements;
		return result;
	}
}