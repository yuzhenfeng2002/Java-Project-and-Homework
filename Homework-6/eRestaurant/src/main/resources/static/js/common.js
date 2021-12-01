function checkNull(myvalue, mymessage){
	if(myvalue.trim() == ""){
		alert(mymessage);
		return false;
	}
	return true;
}