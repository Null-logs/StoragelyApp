app.controller("allItemCtrl", ["$scope", "$http", function($scope, $http) {

	$("#tableDataProducts").DataTable({
		processing: true,
		serverSide: true,
		ajax: ROUTES.EMPLOYEE.STORER.GET_ALL_ITEMS,
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
	});
	
	
}])