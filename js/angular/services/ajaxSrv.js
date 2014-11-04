// service (function) [use multiple times from different controllers]
// ajaxSrv for restful [Ajax functions]

/*var profile = {get:function(){}, post:function(){}}
profile.name()*/

//returns JSON
crm.factory('ajaxSrv', function($q, $http){

	return {
		get: function(Url){
			var deferred = $q.defer();
			$http({
				url:Url,
				method:"get"
			}).success(function(data){
				deferred.resolve(data);
			}).error(function(data, status) {
				deferred.reject(status);
			});
			return deferred.promise;
		},
		
		send: function(Url,d){
			var deferred = $q.defer();
			console.log("send : "+d.test)
			$http.post(Url,d).success(function(data){
				console.log("ajaxsrv recieved data : "+data.oren)
				deferred.resolve(data);
			}).error(function(data, status) {
				deferred.reject(status);
			});
			return deferred.promise;
		}
	}

});

       //$http(also a service) for ajax requests  ,  $q promise