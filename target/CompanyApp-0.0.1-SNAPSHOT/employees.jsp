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
<title>List of Employees</title>
</head>
<body>
	<div ng-app="myApp" ng-controller="depts2Ctrl">
		<table>
			<tr>
				<th>First Name</th>
				<th>Last Name</th>
				<th>Department</th>
				<th>Id</th>
				<th>Projects</th>
			</tr>
			<tr ng-repeat="x in emps">
				<td>{{x.firstName}}</td>
				<td>{{x.lastName}}</td>
				<td>{{x.department.deptName}}</td>
				<td><a href="#"
					ng-click="fillForm(x.firstName,x.lastName,x.age,x.department,x.empId)">{{x.empId}}</a></td>
				<td><ul class="csv">
						<li ng-repeat="y in x.projects">{{y.projectName</li>
					</ul></td>
			</tr>
		</table>
		<br>
		<form name="myForm" novalidate>
			<input type="hidden" name="empId" ng-model="empId">
			<p>
				First Name:<input type="text" name="firstName" ng-model="firstName">
				<span style="color: red"
					ng-show="myForm.deptName.$dirty && myForm.deptName.$invalid">
					<span ng-show="myForm.deptName.$error.required">Name is
						required.</span>
				</span>
			</p>
			<p>
				Last Name:<input type="text" name="lastName" ng-model="lastName">
				<span style="color: red"
					ng-show="myForm.deptName.$dirty && myForm.deptName.$invalid">
					<span ng-show="myForm.deptName.$error.required">Name is
						required.</span>
				</span>
			</p>
			<p>
				Age:<input type="number" ng-model="age"> <span
					style="color: red"
					ng-show="myForm.deptEmail.$dirty && myForm.deptEmail.$invalid">
					<span ng-show="myForm.deptEmail.$error.required">Valid
						age is required.</span>
				</span>
			</p>
			<p>
				Department: {{depat}}
<!-- 				<select ng-model="deptId">
				<option ng-repeat="x in depts" value="x.deptId">{{x.deptName}}</option>
				</select>
 -->				<select ng-model="depat" ng-options="x.deptId as x.deptName for x in depts"> </select>
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

				$http
						.get(
								"http://localhost:8090/CompanyApp/employee/")
						.then(function(response) {
							$scope.emps = response.data;
						});

				$scope.sendPost = function() {
					var depart = $scope.depts.filter(function(item) {
					    return item.deptId === $scope.depat;
					});
					
					var data = {
						firstName : $scope.firstName,
						lastName : $scope.lastName,
						age : $scope.age,
						department : depart[0],
						projects : []
					};
					
					$http
							.post(
									"http://localhost:8090/CompanyApp/employee/",
									data).success(
									function(data, status) {
										$scope.emps.push(data);
									}).error(
									function(data, status) {
										alert("failure message: "
												+ data);
									});
				};

				$scope.fillForm = function(firstName,lastName,age,department,empId) {
					$scope.firstName = firstName;
					$scope.lastName = lastName;
					$scope.age = age;
					$scope.empId = empId;
					$scope.depat = department.deptId;
					
				};

				$scope.deletePost = function() {
					$http
							.delete(
									"http://localhost:8090/CompanyApp/employee/"
											+ $scope.empId, "")
							.success(
									function(data, status) {
										$scope.emps = $scope.emps.filter(function(item) {
										    return item.empId !== $scope.empId;
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