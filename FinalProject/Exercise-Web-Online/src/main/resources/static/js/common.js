function checkNull(value, message){
	if(value.trim() == ""){
		alert(message);
		return false;
	}
	return true;
}
