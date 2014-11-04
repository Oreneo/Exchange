crm.controller("crmCtrl",function($scope,ajaxSrv){
	//alert("working");
	
	$scope.tab = "homepage";   //changed from select
	
	$scope.selectTab = function(setTab) {
		$scope.tab = setTab;
	};
	
	$scope.isSelected = function(checkTab) {
		return ($scope.tab) === checkTab;
	}
	
	/*
	$scope.list1 = [{name:"oren",papa:"google.com"},{name:"tomer",papa:"zap.co.il"},{name:"yosi",papa:"walla.co.il"}];
	$scope.list2 = ["blue","yellow","red","green","black"];
	
	$scope.oren = function(val){
		list2.push(val);
	} */
	
	/* angular fail save to JSON
	ajaxSrv.send("http://localhost/python/hello.py",{test:"working"}).then(function(data){
		console.log("recived data : "+data.oren)
	})
	*/
	
	//$.getJSON("http://localhost/python/hello.py",{test:"working"},function(data){console.log("recived data : "+data.oren)})
	
	/*jQuery save data to JSON
	$.ajax({
	  type: "POST",
	  url: "http://localhost/python/hello.py",
	  data: {username: "ffffffffff", area: "מרכז", problemType: "Hardware issue", caseDescription: "aaaaaaaaa"},
	  success: function(data) {console.log("recived data : "+data.oren)},
	  dataType: "json"
	});*/
	
	//	console.log("changin class");
		//$scope.select = "current_page_item";

})
