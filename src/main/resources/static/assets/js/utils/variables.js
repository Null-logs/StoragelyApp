const app = angular.module("app", []);

const typeAlert = {
	success: 'success',
	error: 'error',
	warning: 'warning',
	info: 'info',
	question: "question"
}

const statusFormValid = {
	NotValidated: "NotValidated",
	Valid: "Valid",
	Invalid: "Invalid",
}

const responseType = {
	Success: "SUCCESS",
	Warning: "WARNING",
	Error: "ERROR"
}

const TITLES = {
	SUCCESSFULLY: "Bien!!!",
	WRONGLY: "Error",
	WARNINGLY: "Algo no fue del todo bien",
	QUESTIONABLY: "Veamos..."
}

const MSJ = {
	SUCCESSFUL_ACCESS: "Sus credenciales con correctas.",
	WRONGLY_ACCESS: "Algo salio mál en el proceso, sentimos las molestias.",
	WARNINGLY_ACCESS: "Algunos de los datos proporcionados es incorrectos.",
	QUESTION_SAVE_REGISTER_PRODUCTS: "¿Desea guardar la información sobre estos productos?",
	CAN_NOT_READ_FILE: "Lamentamos las molestias, no se pudo leer el archivo, verifique que tenga el formato correcto o no este corrupto e intente nuevamente.",
	THE_FILE_HAS_NOT_DATA: "Lamentamos las molestias, no se pudo leer el archivo, verifique que tenga el formato correcto o no este corrupto e intente nuevamente.",
	VALIDATOR_FILED_REQUIERED: "Campo requerido.",
	VALIDATOR_VAL_INVALID: "El valor ingresado es incorrecto.",
	VALIDATOR_FORM_INVALID: "Lo sentimos, parece que se han detectado algunos errores. Inténtalo de nuevo.",
	QUESTION_ARE_SURE_DELETE_TABLE: "¿Desea eliminar la tabla? ",
	QUESTION_ARE_SURE_SAVE_DATA: "¿Desea guardar los registros?",
	CONTINUE: "Sí, continuar.",
	CANCEL: "No, cancelar.",
	AGREE: "De acuerdo",
}

const ROUTES = {
	ACCESS: {
		GET_ROLES: "/access/roles",
		LOGIN: "/access/login",
	},
	EMPLOYEE: {
		STORER: {
			HOME: "/storer/view/oneByOne.html",
			ONE_BY_ONE: "/",
			MASSIVE: "/storer/view/massive.html",
			SAVE_ITEMS: "/storer/register",
		},
	}
}

const ROLES = {
	"Almacenista": "STORER",
	"Vendedor": "VENDOR",
	"Administrador": "ADMIN",
}

const CERO = 0;
const MAX_VALUE = 1000000000000.00;
const MIN_VALUE = 0.00
const CIEN = 100;
