app.controller("allItemCtrl", ["$scope", "$http", function($scope, $http) {

	let rowData = {};
	let itemSale = {}
	let rowDataSale = {};

	const dataTableProducts = $("#tableDataProducts").DataTable({
		processing: true,
		serverSide: true,
		ajax: ROUTES.EMPLOYEE.STORER.GET_ALL_ITEMS,
		responsive: true,
		lengthMenu: [5, 10, 25, 50],
		pageLength: 10,
		language: dataTableLanguaje,
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
		initComplete: function() {
			$("#tableDataProducts").on("click", "tbody tr", function() {

				rowData = dataTableProducts
					.row($(this).closest("tr"))
					.data();

				itemSale = {
					description: rowData.description,
					price: rowData.finalPrice,
					quantity: 1,
					total: rowData.finalPrice,
					unit: rowData.unit
				}

				dataTableSale.row.add(itemSale).draw();

			})
		},
	});


	const dataTableSale = $("#tableDataSale").DataTable({
		dom: 'lrt',
		paging: false,
		scrollCollapse: true,
		scrollY: '400px',
		lengthMenu: [5, 10],
		language: dataTableLanguaje,
		columns: [
			{
				data: "description",
				orderable: false,
			},
			{
				data: "price",
				render: function(data) {
					return formatAsCurrencyMXN(data);
				},
				orderable: false
			},
			{
				data: "quantity",
				render: function(data) {
					return `<input class="form-control qtySale" type="number" value="${data}">`;
				},
				orderable: false
			},
			{
				data: "total",
				render: function(data) {
					return formatAsCurrencyMXN(data);
				},
				orderable: false
			},
			{
				data: "unit",
				orderable: false,
			},
		],
		initComplete: function() {
			$("#tableDataSale").on("change", "input.qtySale", function() {
				rowDataSale = dataTableSale
					.row($(this).closest("tr"))
					.data();

				rowData.quantity = parseFloat($(this).val());

				rowData.total =
					rowData.price * rowData.quantity;

				dataTableSale
					.row($(this).closest("tr"))
					.data(rowData)

				dataTableSale.draw();
			});
		},
		footerCallback: function(row, data, start, end, display) {
			let api = this.api();

			// Remove the formatting to get integer data for summation
			let intVal = function(i) {
				return typeof i === 'string'
					? i.replace(/[\$,]/g, '') * 1
					: typeof i === 'number'
						? i
						: 0;
			};

			// Total over all pages
			total = api
				.column(3)
				.data()
				.reduce((a, b) => intVal(a) + intVal(b), 0);

			// Total over this page
			pageTotal = api
				.column(3, { page: 'current' })
				.data()
				.reduce((a, b) => intVal(a) + intVal(b), 0);

			// Update footer
			api.column(4).footer().innerHTML =
				`${formatAsCurrencyMXN(pageTotal)} ( ${formatAsCurrencyMXN(total)} total)`;
		}
	});

}])