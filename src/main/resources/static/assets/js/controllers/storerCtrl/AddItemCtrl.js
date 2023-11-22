app.controller("addItemCtrl", ["$scope", "$http", function($scope, $http) {
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

	const validateTextInputs = {
		validators: {
			notEmpty: {
				message: MSJ.VALIDATOR_FILED_REQUIERED
			},
		}
	}

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
		
		
		
			$scope.clearItemData = function() {
		resetItemModel()
	}
}])