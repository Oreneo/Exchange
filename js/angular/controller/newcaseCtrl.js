crm.controller("newcaseCtrl", function($scope, ajaxSrv){

	console.log("newcase controller loaded");
	$scope.case_opened = 'false';
	$scope.case_error = 'false';
	$scope.$parent.select = "newcase";
	
	//$scope.formData.problemType = "none";
	/*$scope.formData.area = 'undefined';
	$scope.formData.username = 'undefined';
	$scope.formData.caseDescription = 'undefined';*/
	
	ajaxSrv.get("json/problems.json").then(function(data) {
		$scope.problems = data;
		console.log(data);
	})
	
	$scope.change = function() {
		console.log("Changed selecter : " + $scope.formData.problemType.problem);
	}
	
	$scope.clickedSubmit = function() {
		/*
		console.log("clicked submit");
		console.log("problemType = " + $scope.formData.problemType);
		console.log("area = " + $scope.formData.area);
		console.log("username = " + $scope.formData.username);
		console.log("caseDescription = " + $scope.formData.caseDescription);
		*/
		//console.log($scope.formData);
		//send formData to python (save to file.JSON)   //check if all fields not empty..........
		/*if( (typeof($scope.formData.problemType) !== 'undefined') && (typeof($scope.formData.area) !== 'undefined') && (typeof($scope.formData.username) !== 'undefined') && (typeof($scope.formData.caseDescription) !== 'undefined') 
		   &&      ($scope.formData.problemType !== null) && ($scope.formData.area !== null) && ($scope.formData.username !== null) && ($scope.formData.caseDescription !== null) )
		{*/
			console.log("all fields not empty");
			$.ajax({
			  type: "POST",
			  url: "http://localhost/python/hello.py",
			  data: $scope.formData,
			  success: function(data) {console.log("[Ajax-Success] recived data : "+data.Error); $scope.case_opened = 'true'; $scope.$apply(); },
			  error: function(data) {console.log("[Ajax-Error] recived data : "+data.status); $scope.case_error = 'true'; },
			  dataType: "json"
			});
		//}
	}

});