<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script
	src="http://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>
<style>
ul.csv {
	list-style: none;
	margin: 0;
	padding: 0;
}

ul.csv li {
	display: inline;
}

ul.csv li:after {
	content: ", ";
}

ul.csv li:last-child:after {
	content: ".";
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>List of Departments</title>
</head>
<body>
	<div ng-app="myApp" ng-controller="depts2Ctrl">
		<table>
			<tr>
				<th>Name</th>
				<th>Email</th>
				<th>Id</th>
				<th>Employees</th>
			</tr>
			<tr ng-repeat="x in depts">
				<td>{{x.deptName}}</td>
				<td>{{x.deptEmail}}</td>
				<td><a href="#"
					ng-click="fillForm(x.deptId,x.deptName,x.deptEmail)">{{x.deptId}}</a></td>
				<td><ul class="csv">
						<li ng-repeat="y in x.employees">{{y.firstName+"
							"+y.lastName}}</li>
					</ul></td>
			</tr>
		</table>
		<br>
		<form name="myForm" novalidate>
			<input type="hidden" name="deptId" ng-model="deptId">
			<p>
				Dept Name:<input type="text" name="deptName" ng-model="deptName">
				<span style="color: red"
					ng-show="myForm.deptName.$dirty && myForm.deptName.$invalid">
					<span ng-show="myForm.deptName.$error.required">Name is
						required.</span>
				</span>
			</p>
			<p>
				Dept Email:<input type="email" ng-model="deptEmail"> <span
					style="color: red"
					ng-show="myForm.deptEmail.$dirty && myForm.deptEmail.$invalid">
					<span ng-show="myForm.deptEmail.$error.required">Valid
						e-mail is required.</span>
				</span>
			</p>
			<p>
				<button type="submit" ng-click="sendPost()"
					ng-disabled="myForm.deptName.$dirty && myForm.deptName.$invalid ||
	  myForm.deptEmail.$dirty && myForm.deptEmail.$invalid">Create</button>
				<button type="submit" ng-click="deletePost()">Delete</button>
			</p>
		</form>
	</div>


	<script>
		var app = angular.module('myApp', []);
		app.controller(
			'depts2Ctrl',
			function($scope, $http) {
				$http
						.get(
								"http://localhost:8090/CompanyApp/department/")
						.then(function(response) {
							$scope.depts = response.data;
						});

				$scope.sendPost = function() {
					var data = {
						deptName : $scope.deptName,
						deptEmail : $scope.deptEmail
					};
					$http
							.post(
									"http://localhost:8090/CompanyApp/department/",
									data).success(
									function(data, status) {
										$scope.depts.push(data);
									}).error(
									function(data, status) {
										alert("failure message: "
												+ data);
									});
				};

				$scope.fillForm = function(deptId, deptName,
						deptEmail) {
					$scope.deptId = deptId;
					$scope.deptName = deptName;
					$scope.deptEmail = deptEmail;
				};

				$scope.deletePost = function() {
					$http
							.delete(
									"http://localhost:8090/CompanyApp/department/"
											+ $scope.deptId, "")
							.success(
									function(data, status) {
										$scope.depts = $scope.depts.filter(function(item) {
										    return item.deptId !== $scope.deptId;
										});										//							$scope.depts.push(data);
/* 										$http
												.get(
														"http://localhost:8090/CompanyApp/department/")
												.then(
														function(
																response) {
															$scope.depts = response.data;
														});
 */
									}).error(
									function(data, status) {
										alert("failure message: "
												+ data);
									});
				};
			});
	</script>
</body>
</html>