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

const dataTableLanguaje = {
				// url: "/assets/js/pages/crud/datatables/search-options/es_es.json", ///'lengthMenu': 'Display _MENU_',
				decimal: "",
				emptyTable: "No hay datos disponibles en la tabla",
				info: "Mostrando _START_ a _END_ de _TOTAL_ registros",
				infoEmpty: "Mostrando 0 a 0 de 0 registros",
				infoFiltered: "(filtrados de _MAX_ registros totales)",
				infoPostFix: "",
				thousands: ",",
				lengthMenu: "Mostrar _MENU_   registros",
				loadingRecords: "Cargando...",
				processing: "Procesando...",
				search: "Buscar:",
				zeroRecords: "No se encontraron registros coincidentes",
				paginate: {
					first: "Primera",
					last: "Última",
					next: "Siguiente",
					previous: "Anterior",
				},
				aria: {
					sortAscending: ": activar para ordenar la columna ascendente",
					sortDescending: ": activar para ordenar la columna descendente",
				},
			}

const ROUTES = {
	ACCESS: {
		GET_ROLES: "/access/roles",
		LOGIN: "/access/login",
	},
	EMPLOYEE: {
		STORER: {
			HOME: "/storer/view/productos.html",
			ONE_BY_ONE: "/storer/view/oneByOne.html",
			MASSIVE: "/storer/view/massive.html",
			SAVE_ITEMS: "/storer/register",
			GET_ALL_ITEMS:"/storer/get"
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
