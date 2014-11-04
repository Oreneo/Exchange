var crm = angular.module("crm", ["ui.router"]);

crm.config(function($stateProvider){   //constructor for module crm

	var homepage = {
		name:"homepage",
		url:"",
		templateUrl:"js/angular/template/homepage.html"
	}

	var newcase = {
		name:"newcase",
		url:"/newcase",
		templateUrl:"js/angular/template/newcase.html",
		controller: "newcaseCtrl"
	}
	
	var casedetails = {
		name:"casedetails",
		url:"/casedetails/:id",
		//localhost//casedetails/oren  --> {id:"oren"}
		templateUrl:"js/angular/template/casedetails.html",
		controller: "casedetailsCtrl"
	}
	
	var opencases = {
		name:"opencases",
		url:"/opencases",
		templateUrl:"js/angular/template/opencases.html",
		controller: "opencasesCtrl"	
	}
	
	var caseshistory = {
		name:"caseshistory",
		url:"/caseshistory",
		templateUrl:"js/angular/template/caseshistory.html"
	}
	
	$stateProvider.state("homepage", homepage);
	$stateProvider.state("newcase", newcase);
	$stateProvider.state("casedetails", casedetails);
	$stateProvider.state("opencases", opencases);
	$stateProvider.state("caseshistory", caseshistory);
})