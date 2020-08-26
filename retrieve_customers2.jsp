<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Query Result</title>
</head>
    <body>
    <%@page import="DBMS_project.DataHandler"%>
    <%@page import="java.sql.ResultSet"%>
    <%@page import="java.sql.Array"%>
    <%
    // The handler is the one in charge of establishing the connection.
    DataHandler handler = new DataHandler();
    
    // Get the attribute values passed from the input form.
    String category_fromString = request.getParameter("category_from");
    String category_toString = request.getParameter("category_to");
    int category_from = Integer.parseInt(category_fromString);
    int category_to = Integer.parseInt(category_toString);    
    final ResultSet customers = handler.retrieveCustomersResults(category_from,category_to);
    /*
     * If the user hasn't filled out all the time, movie name and duration. This is very simple checking.
     */
    if (category_fromString.equals("") || category_toString.equals("")) {
        response.sendRedirect("add_form2.jsp");
    } else {
        
        // Now perform the query with the data from the form.
        boolean success = handler.retrieveCustomers(category_from, category_to);
        if (!success) { // Something went wrong
            %>
                <h2>There was a problem inserting the course</h2>
            <%
        } else { // Confirm success to the user
            %>
            
            <h2>retrieve customer with category range:</h2>

            <ul>

                <li>category_from: <%= category_fromString %></li>
                <li>category_to: <%= category_toString %></li>
            </ul>

            <h2>is successful.</h2>

        <!-- The table for displaying retrieved customer information -->
        <table cellspacing="2" cellpadding="2" border="1">
            <tr> <!-- The table headers row -->
              <td align="center">
                <h4>Customer Name</h4>
              </td>
              <td align="center">
                <h4>Address</h4>
              </td>
              <td align="center">
                <h4>Category</h4>

              </td>
            </tr>

            <%
                while(customers.next()) { // For each movie_night record returned...
                    // Extract the attribute values for every row returned
                    final String cname = customers.getString("cname");
                    final String address = customers.getString("address");
                    final String category = customers.getString("category");

                    out.println("<tr>"); // Start printing out the new table row
                    out.println( // Print each attribute value
                         "<td align=\"center\">" + cname +
                         "</td><td align=\"center\"> " + address +             
                         "</td><td align=\"center\"> " + category + "</td>");
                    out.println("</tr>");
               }
            %>
          </table>
          
          
          
          
            <%
        }
    }
    %>
    </body>
</html>