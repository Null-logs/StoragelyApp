/**
 *
 */

const loginApp = angular.module("loginApp", []);

loginApp.controller("loginCtrl", ["$scope", "$timeout", "$http", '$window', ($scope, $timeout, $http, $window) => {
	//#region variables

	const validation = FormValidation.formValidation(
		KTUtil.getById('kt_login_signin_form'), {
		fields: {
			username: {
				validators: {
					notEmpty: {
						message: 'Se requiere nombre de usuario'
					}
				}
			},
			password: {
				validators: {
					notEmpty: {
						message: 'Se requiere contraseña'
					}
				}
			}
		},
		plugins: {
			trigger: new FormValidation.plugins.Trigger(),
			submitButton: new FormValidation.plugins.SubmitButton(),
			//defaultSubmit: new FormValidation.plugins.DefaultSubmit(), // Uncomment this line to enable normal button submit after form validation
			bootstrap: new FormValidation.plugins.Bootstrap()
		}
	});

	$scope.User = {
		username: "",
		password: "",
		rol: ""
	}

	$scope.roles = [];

	//#endregion

	$scope.login = function() {
		validation.validate().then(function(status) {
			if (status != statusFormValid.Valid) {
				Swal.fire({
					text: "Lo sentimos, parece que se han detectado algunos errores. Inténtalo de nuevo.",
					icon: "error",
					buttonsStyling: false,
					confirmButtonText: "¡Ok lo tengo!",
					customClass: {
						confirmButton: "btn font-weight-bold btn-light-primary"
					}
				}).then(function() {
					KTUtil.scrollTop();
				});

				return;
			}


			$http.post(ROUTES.ACCESS.LOGIN, $scope.User).then(function successCallback(response) {
				try {
					const rs = response.data;

					if (rs.type != responseType.Success)
						throw new Error(MSJ.WARNINGLY_ACCESS);


					Swal.fire(TITLES.SUCCESSFULLY, MSJ.SUCCESSFUL_ACCESS, typeAlert.success).then(
						() => {
							$window.location.href = ROUTES.EMPLOYEE[ROLES[$scope.User.rol]].HOME;
						}
					);
				} catch (e) {
					console.log(e)
					swal(TITLES.WARNINGLY, e.message, typeAlert.warning)
				}
			}, function errorCallback(error) {
				Swal.fire(TITLES.WRONGLY, MSJ.WRONGLY_ACCESS, typeAlert.error)
				console.log(error);
			});
		});
	}

	$scope.getRoles = function() {
		$http.get(ROUTES.ACCESS.GET_ROLES).then(function(httprs) {
			let rs = httprs.data

			try {
				if (rs.type != responseType.Success) {
					throw new Error(rs.message);
				}

				$scope.roles = rs.data;
				$scope.User.rol = rs.data[0];
			} catch (e) {
				console.log(e.message)
			}

		}, function(error) {
			console.log(error)
		})
	}

	$timeout(() => {
		$scope.getRoles();
	});

}
]);
