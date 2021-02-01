
<jsp:useBean id="employee" type="edu.dto.EmployeeDto" scope="request"></jsp:useBean>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

<body>
	<center>
		<h1>
			Welcome ,${employee.ename}<br /> and your Employee id is
			${employee.eid}
		</h1>
	</center>
</body>
</html>
