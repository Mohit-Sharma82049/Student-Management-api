<jsp:useBean id="customer" type="edu.dto.CustomerDto" scope="request"></jsp:useBean>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

<body>
	<center>
		<h1>WelCome,${customer.cname}</h1>
		<br />
		<h1>and your Customer is ${customer.accno}</h1>
	</center>
</body>
</html>
