class GRVMessageRequest{
	constructor(objectArray,action,idsession,iduser,encrypted=true){
		var result = {};
		var elements = JSON.stringify(objectArray);
		result['timestamp'] = moment().unix();
		result['uuidsession'] = idsession;
		result['uuiduser'] = iduser;
		result['action'] = action;
		result['state']  = (encrypted)?'enc':'clear';
		Object.keys(obj).forEach(function(key) {
			var k = key;
			var v = obj[key];
			console.log(key, obj[key]);
			obj[key] = Tea.encrypt(v,)
		});
		result['elements'] = (encrypted)?btoa(elements):elements;
		return result;
	}
}