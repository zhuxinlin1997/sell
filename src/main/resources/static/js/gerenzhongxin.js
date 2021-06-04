function changdiv(a){
		var b=document.getElementsByClassName("grzx-b");
		for(var i =0;i<b.length;i++){
			b[i].style.display="none";
		}
		var a=document.getElementById("di"+a);
		a.style.display="block";
}
function deleteshow(){
	var d=document.getElementById("delete");
	d.style.display="block"
}
function deleteshowdn(){
	var d=document.getElementById("delete");
	d.style.display="none"
}
function deleteyes(){
	
}
function addshowdn(){
	var d=document.getElementById("add");
	d.style.display="none"
}
function addshow(){
	var d=document.getElementById("add");
	d.style.display="block"
}


