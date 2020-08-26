<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Retrieve the customers whose category is in a range</title>
    </head>
    <body>
        <h2>Find customers with category range</h2>
        <!--
            Form for collecting user input for the new movie_night record.
            Upon form submission, add_movie.jsp file will be invoked.
        -->
        <form action="retrieve_customers2.jsp">
            <!-- The form organized in an HTML table for better clarity. -->
            <table border=1>
                <tr>
                    <th colspan="2">Enter the category range:</th>
                </tr>
                <tr>
                    <td>Category from:</td>
                    <td><div style="text-align: center;">
                    <input type=text name=category_from>
                    </div></td>
                </tr>
                <tr>
                    <td>Category_to:</td>
                    <td><div style="text-align: center;">
                    <input type=text name=category_to>
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