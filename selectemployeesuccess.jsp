<h1>
	Customer Information<br />
</h1>
<jsp:useBean id="employee" type="edu.dto.EmployeeDto" scope="request" />
<h2>
	<center>
		<table border="10">
			<tr>
				<td>EID</td>
				<td>${employee.eid}</td>
			</tr>
			<tr>
				<td>Ename</td>
				<td>${employee.ename}</td>
			</tr>
			<tr>
				<td>Salary</td>
				<td>${employee.salary}</td>
			</tr>
			<tr>
				<td>Hiredate</td>
				<td>${employee.hiredate}</td>
			</tr>
		</table>
	</center>


</h2>

