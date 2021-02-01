<
<!DOCTYPE h1 PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>
	<h1>
		Customer Information<br />
	</h1>
	<jsp:useBean id="customer" type="edu.dto.CustomerDto" scope="request" />
	<h2>
		<center>
			<table border="10">
				<tr>
					<td>ACCNO:</td>
					<td>${customer.accno}</td>
				</tr>
				<tr>
					<td>CNAME</td>
					<td>${customer.cname}</td>
				</tr>
				<tr>
					<td>BALANCE</td>
					<td>${customer.balance}
				</tr>
				<tr>
					<td>DOB</td>
					<td>${customer.dob}</td>
				</tr>
				<tr>
					<td>ADDRESS</td>
					<td>${customer.address}</td>
				</tr>
			</table>
		</center>

	</h2>
</body>
</html>