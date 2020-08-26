<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Add Customer</title>
    </head>
    <body>
        <h2>Add Customer</h2>
        <!--
            Form for collecting user input for the new customer record.
            Upon form submission, add_customer.jsp file will be invoked.
        -->
        <form action="add_customer1.jsp">
            <!-- The form organized in an HTML table for better clarity. -->
            <table border=1>
                <tr>
                    <th colspan="2">Enter the customer information:</th>
                </tr>
                <tr>
                    <td>Customer name:</td>
                    <td><div style="text-align: center;">
                    <input type=text name=cname>
                    </div></td>
                </tr>
                <tr>
                    <td>Customer address:</td>
                    <td><div style="text-align: center;">
                    <input type=text name=address>
                    </div></td>
                </tr>
                <tr>
                    <td>Category:</td>
                    <td><div style="text-align: center;">
                    <input type=text name=category>
                    </div></td>
                </tr>
                <tr>
                    <td><div style="text-align: center;">
                    <input type=reset value=Clear>
                    </div></td>
                    <td><div style="text-align: center;">
                    <input type=submit value=Insert>
                    </div></td>
                </tr>
            </table>
        </form>
    </body>
</html>