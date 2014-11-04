crm.controller("opencasesCtrl", function($scope, ajaxSrv, $state) {
	$scope.$parent.select = "opencases";
	console.log("open cases controller loaded");
	
	//$scope.oren = "dff";
	$scope.data = undefined;
		
	$scope.row_click = function(case_id) {
		console.log("case id is : " + case_id);
		$state.go("casedetails", {id:case_id});
		//highlight tipul takalaffffffffff
		$scope.$parent.select = "casedetails";
		
	}
		
	ajaxSrv.get("json/opencases.json").then(function(data) {
		$scope.data = data;
	})
	
})