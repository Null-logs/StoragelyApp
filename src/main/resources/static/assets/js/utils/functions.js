const swal = async (title, msg, typeAlert) => {
	Swal.fire(title, msg, typeAlert)
}

async function postData(url = "", data = {}) {

	const rs = await fetch(url, {
		method: "POST",
		mode: "cors",
		cache: "no-cache",
		credentials: "same-origin",
		headers: {
			"Content-Type": "application/json",
		},
		redirect: "follow",
		referrerPolicy: "no-referrer",
		body: JSON.stringify(data),
	})
	
	return rs.json();
}

function roundToFourDecimals(num){
	return Number(parseFloat(num).toFixed(4));
}

function isANumber(str){
	return !isNaN(str);
}

function blockPage(color = '#000000', state = 'primary', message ='Cargando...'){
	KTApp.blockPage({
			overlayColor: color,
			state: state,
			message: message,
		});
}

function unblockPage(timeToUnblock = 1500){
	setTimeout(function() {
				KTApp.unblockPage();
			}, timeToUnblock);
}

function isNull(value){
	return value === null;
}

function isUndefined(value){
	return value === undefined;
}

function isEmpityString(value){
	return value === "";
}

function isAObject(o){
	return o !== null && typeof(o) === 'object'
}

function isNullOrUndefinedOrNaN(value) {
    return isNull(value) || isUndefined(value) || isNaN(value);
}

function isNullOrUndefinedOrEmptyString(value) {
	value.trim();
  return isNull(value) || isUndefined(value) || isEmpityString(value);
}




// Question: Si tengo una funcion que declaro como async pero dentro no realizo operaciones async solo quiero que 
// en el flujo de ejección se espere a que se ejecute esta sentencia, es un buena manera de hacerlo;