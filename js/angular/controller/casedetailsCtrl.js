crm.controller("casedetailsCtrl", function($scope, ajaxSrv, $stateParams) {

	if($stateParams.id != -1 && $stateParams.id != null) {
					
		console.log("case details controller loaded");
		console.log("[casedetails controller] id = " + $stateParams.id + "hello");
		$scope.id = $stateParams.id;
		$scope.data = undefined;
		//caseDetails.html
	
		ajaxSrv.get("json/caseDetails"+$scope.id+".json").then(function(data) {
			$scope.data = data;
			console.log(data);
		})
	}
	
	//get row by id from json
	
})