app.controller("massiveAddProductCtrl", ["$scope", "$http", function($scope, $http) {

	const FILE_TYPE_ALLOWED = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
	const BASIC_ITEM_STRUCT = {
		description: '',
		finalPrice: 0,
		information: '',
		key: '',
		price: 0,
		profitPercent: 0,
		quantity: 0,
		unit: ''
	};
	const ITEM_OBJECT_KEYS = Object.keys(BASIC_ITEM_STRUCT);
	const map = {
		Clave: "key",
		Descripción: "description",
		Precio: "price",
		"Porcentaje de ganancia": "profitPercent",
		"Precio final": "finalPrice",
		Cantidad: "quantity",
		Unidad: "unit",
		Información: "information",
	}
	const correctRowClass = 'table-success';
	const incorrectRowClass = 'table-danger';
	const validateTextInputs = {
		validators: {
			notEmpty: {
				message: MSJ.VALIDATOR_FILED_REQUIERED
			},
		}
	}

	const validateNumberInputs = {
		validators: {
			notEmpty: {
				message: MSJ.VALIDATOR_FILED_REQUIERED
			},
			between: {
				inclusive: true,
				min: MIN_VALUE,
				max: MAX_VALUE,
				message: 'El valor debe ser mayor a 0',
			},
			numeric: {
				decimalSeparator: ".",
				message: MSJ.VALIDATOR_VAL_INVALID,
			},
		}
	}
	//#region Variables
	$scope.itIsVisible = false;
	$scope.item = {};
	let tableProduct;
	let rowSelector;
	let priceDefault = 0;
	let profitPercentDefault = 0;
	//#endregion

	//	angular.copy(src, target);

	//#region events
	$("#inputFileProducts").on("change", function() {
		blockPage;

		const inputFile = $(this).prop("files");

		if (inputFile.length == 0) {
			Swal.fire(
				"No se cargo archivo",
				"Al parecer no seleccionó un archivo",
				typeAlert.warning
			);
			unblockPage();
			return;
		}

		if (inputFile[0].type != FILE_TYPE_ALLOWED) {
			Swal.fire(
				"Archivo no valido",
				"Al parecer seleccionó un archivo no valido",
				typeAlert.warning
			);
			unblockPage();
			return;
		}

		//TODO IMPLEMENTAR VALIDACIONES MÁS RIGUROSAS Y UN ESQUEMA EN LUGAR DE UN JSON COMO MAPA

		readXlsxFile(inputFile[0], { map })
			.then(function({ rows, errors }) {
				if (rows.length > CERO) {
					initDataTable(rows);
					unblockPage();
					$scope.itIsVisible = true;
					$scope.$apply();
					return;
				}
				Swal.fire(TITLES.WARNINGLY, MSJ.THE_FILE_HAS_NOT_DATA, typeAlert.warning);
			})
			.catch((err) => {
				// Manejar errores aquí
				Swal.fire(TITLES.WRONGLY, MSJ.CAN_NOT_READ_FILE, typeAlert.error);
				console.error(err);
			});
	});

	$scope.triggerInput = function() {
		$("#inputFileProducts").trigger("click");
	};

	$scope.cleanDatatable = function() {
		Swal.fire({
			title: TITLES.SUCCESSFULLY,
			text: MSJ.QUESTION_ARE_SURE_DELETE_TABLE,
			icon: typeAlert.question,
			showCancelButton: true,
			confirmButtonText: MSJ.CONTINUE,
			cancelButtonText: MSJ.CANCEL,
		}).then(function(result) {
			if (result.value) {
				cleanDatatableActions()
			}
		});
	};

	$scope.getData = function() {
		let itemData = tableProduct.rows().data().toArray();
		if (itemDataIsValid(itemData)) {

			console.table(itemData);

			Swal.fire({
				title: TITLES.SUCCESSFULLY,
				text: MSJ.QUESTION_ARE_SURE_SAVE_DATA,
				icon: typeAlert.question,
				showCancelButton: true,
				confirmButtonText: MSJ.CONTINUE,
				cancelButtonText: MSJ.CANCEL,
				reverseButtons: true
			}).then(function(rs) {
				console.log(rs);
				if (rs.value) {
					blockPage();
					$http
						.post(ROUTES.EMPLOYEE.STORER.SAVE_ITEMS, tableProduct.rows().data().toArray())
						.then(function successCallback(httprs) {
							unblockPage();
							let rs = httprs.data;

							if (rs.type != responseType.Success) {
								Swal.fire(TITLES.WARNINGLY, rs.message, typeAlert.warning);
								return;
							}

							Swal.fire(TITLES.SUCCESSFULLY, rs.message, typeAlert.success)
								.then(cleanDatatableActions);
						},
							function errorCallback(err) {
								unblockPage();
								console.log(err);
								Swal.fire(TITLES.WRONGLY, err.message, typeAlert.error);
							}
						);
				}

			});

			return;
		}

		Swal.fire(TITLES.WARNINGLY, MSJ.VALIDATOR_FORM_INVALID, typeAlert.warning)
		return;


	};

	$scope.calculateFinalePrice = function(item) {

		priceDefault = isNullOrUndefinedOrNaN(item.price) ? CERO : item.price;
		profitPercentDefault = isNullOrUndefinedOrNaN(item.profitPercent) ? CERO : item.profitPercent;

		item.finalPrice = roundToFourDecimals((priceDefault * (1 + (profitPercentDefault / CIEN))));
	}

	$scope.updateItemData = function(item) {
		itemEditValidate.validate().then(function(status) {
			if (status != statusFormValid.Valid) {
				Swal.fire(TITLES.WARNINGLY, MSJ.VALIDATOR_FORM_INVALID, typeAlert.warning)
					.then(function() {
						KTUtil.scrollTop();
					});
				return;
			}

			tableProduct
				.row(rowSelector)
				.data(item);

			if (rowSelector.hasClass(incorrectRowClass)) {
				rowSelector.removeClass(incorrectRowClass).addClass(correctRowClass);
			}

			resetItemModel()
			tableProduct.draw();
			return;
		})
	}

	$scope.clearItemData = function() {
		resetItemModel()
		//		$scope.$apply();
	}

	//#endregion

	//#region auxFunctions
	function resetItemModel() {
		$scope.item = {
			description: '',
			finalPrice: 0,
			information: '',
			key: '',
			price: 0,
			profitPercent: 0,
			quantity: 0,
			unit: ''
		};
		rowSelector = null;
		$("#editItem").modal('hide');
		itemEditValidate.resetForm()
	}


	function initDataTable(src) {
		tableProduct = $("#inputItems").DataTable({
			data: src,
			responsive: true,
			lengthMenu: [5, 10, 25, 50],
			pageLength: 10,
			language: {
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
			},
			columns: [
				{
					data: "key",
				},
				{
					data: "description",
				},
				{
					data: "price",
				},
				{
					data: "profitPercent",
				},
				{
					data: "finalPrice",
				},
				{
					data: "quantity",
				},
				{
					data: "unit",
				},
			],
			createdRow: function(row, data, dataIndex) {
				if (validateItem(data)) {
					$(row).addClass(correctRowClass);
					return;
				}

				$(row).addClass(incorrectRowClass)
				return;
			},
			initComplete: function() {
				$("#tableDataProducts").on("click", "tbody tr", function() {

					let rowData = tableProduct
						.row($(this).closest("tr"))
						.data();

					angular.copy(rowData, $scope.item);
					rowSelector = $(this).closest("tr");

					$scope.$apply();

					$("#editItem").modal('show');
				})
			},
		});
	}

	function validateItem(item) {
		// Verificar si el objeto es nulo o no es un objeto
		return isAObject(item) && itemTypeIsCorrect(item);
	}



	function itemTypeIsCorrect(item) {
		return !ITEM_OBJECT_KEYS.some((key) => {
			// Devolver true en la primera iteración que se cumpla que el tipo de dato es distinto al que deberia ser
			return typeof item[key] !== typeof BASIC_ITEM_STRUCT[key];
		});
	}
	let xxx;
	function itemDataIsValid(items) {
		return !items.some((item) => {
			return itemDataValidPredicate(item)
		})
	}

	function itemDataValidPredicate(item) {
		xxx = isNullOrUndefinedOrEmptyString(item.description) || isNullOrUndefinedOrNaN(item.finalPrice)
			|| isNullOrUndefinedOrEmptyString(item.information)
			|| isNullOrUndefinedOrEmptyString(item.key)
			|| isNullOrUndefinedOrNaN(item.price)
			|| isNullOrUndefinedOrNaN(item.profitPercent)
			|| isNullOrUndefinedOrNaN(item.quantity)
			|| isNullOrUndefinedOrEmptyString(item.unit)
		console.log(xxx)

		return xxx;
	}

	function cleanDatatableActions() {
		tableProduct.clear();
		tableProduct.destroy();
		tableProduct = null;
		$scope.itIsVisible = false;
		$scope.$apply();
		return
	}
	//#endregion

	const itemEditValidate = FormValidation.formValidation(
		KTUtil.getById('itemEditForm'), {
		fields: {
			unit: validateTextInputs,
			quantity: validateNumberInputs,
			//finalPrice: validateNumberInputs,
			profitPercent: validateNumberInputs,
			price: validateNumberInputs,
			key: validateTextInputs,
			description: validateTextInputs,
		},
		plugins: {
			trigger: new FormValidation.plugins.Trigger(),
			submitButton: new FormValidation.plugins.SubmitButton(),
			//defaultSubmit: new FormValidation.plugins.DefaultSubmit(), // Uncomment this line to enable normal button submit after form validation
			bootstrap: new FormValidation.plugins.Bootstrap(),
			icon: new FormValidation.plugins.Icon({
				valid: 'fa fa-check',
				invalid: 'fa fa-times',
				validating: 'fa fa-refresh',
			}),
		}
	});
},
]);
