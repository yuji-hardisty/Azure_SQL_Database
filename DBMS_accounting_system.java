import java.sql.Connection;
import java.sql.Statement;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.util.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class IP_Task5 {
public static void main(String[] args) throws SQLException {
// Connect to database
final String hostName = "hostName";
final String dbName = "dbName";
final String user = "user";
final String password = "password";
final String url =
String.format("jdbc:sqlserver://"hostName", "dbName", "user", "password",
hostName, dbName, user, password);
     boolean bool = true;
     List<String> customerData = new ArrayList<String>();// list to store customer data 
	 Scanner input = new Scanner(System.in);     
     while(bool) {
         System.out.println("\nSelect one integer among from 1 to 18: \n");    	 
    	 String inputNum = input.nextLine();  // input option as integer 1- 18     	
    	 System.out.println(inputNum+"\n"); 
    	 switch(inputNum) {
       	 case "1": //Option 1:  execute query1
     		 System.out.println("input customer's name, address and category from datafile\n"); 
     		 String line = customerData.get(0);   		 
     		 customerData.remove(0);
     		 String[] inputParameters1 = lineToListString(line);    		 
    		 option1(url, inputParameters1[0], inputParameters1[1], inputParameters1[2]);
    		 break;    		 
       	 case "2": //Option 2:  execute query2
     		 System.out.println("input department's number and data :\n"); 
     		 String inputLine2 = input.nextLine();   
        	 System.out.println(inputLine2+"\n");      		 
     		 String[] inputParameters2 = lineToListString(inputLine2);    		    		 
    		 option2(url, inputParameters2[0], inputParameters2[1]);
    		 break;      		 
       	 case "3": //Option 3:  execute query3
     		 System.out.println("input assembly_id, date_ordered, assembly_detail, and cname :\n"); 
     		 String inputLine3 = input.nextLine();  
        	 System.out.println(inputLine3+"\n");          		 
     		 String[] inputParameters3 = lineToListString(inputLine3);    		 
    		 option3(url, inputParameters3[0], inputParameters3[1],inputParameters3[2], inputParameters3[3]);
    		 break;  
       	 case "4": //Option 4:  execute query4
     		 System.out.println("input process_id, process_data, dept_number, assembly_id, process_type and additional info depending on process_type:\n"); 
     		 String inputLine4 = input.nextLine();  
        	 System.out.println(inputLine4+"\n");          		 
     		 String[] inputParameters4 = lineToListString(inputLine4);     		 
     		 if (inputParameters4[4].equals("paint")) {     	 
    		 option4(url, inputParameters4[0], inputParameters4[1],inputParameters4[2], inputParameters4[3],
    				 inputParameters4[4],inputParameters4[5], inputParameters4[6], "null", "null", "null");
     		 }
     		 else if (inputParameters4[4].equals("fit")) {     			 
    		 option4(url, inputParameters4[0], inputParameters4[1],inputParameters4[2], inputParameters4[3],
    				 inputParameters4[4],"null", "null", inputParameters4[5], "null", "null");
     		 }
     		 else if (inputParameters4[4].equals("cut")) {	
     			 option4(url, inputParameters4[0], inputParameters4[1],inputParameters4[2], inputParameters4[3],
    				 inputParameters4[4],"null", "null", "null", inputParameters4[5], inputParameters4[6]);
     		 }
    		 break;  
       	 case "5": //Option 5:  execute query5
     		 System.out.println("input account number, date_established, account_type, and addtioanl info depending on account type:\n"); 
     		 String inputLine5 = input.nextLine();  
        	 System.out.println(inputLine5+"\n");          		 
     		 String[] inputParameters5 = lineToListString(inputLine5);      		 
     		 if (inputParameters5[2].equals("assembly")) {  			 
    		 option5(url, inputParameters5[0], inputParameters5[1],inputParameters5[2], inputParameters5[3],
    				  "0", "0");
     		 }
     		 else if (inputParameters5[2].equals("department")) {    			 
        		 option5(url, inputParameters5[0], inputParameters5[1],inputParameters5[2], "0",
        				 inputParameters5[3], "0");
        		 }
     		 else if (inputParameters5[2].equals("process")) {
        		 option5(url, inputParameters5[0], inputParameters5[1],inputParameters5[2], "0",
       				  "0", inputParameters5[3]);
        		 }
    		 break;      		 
       	 case "6": //Option 6:  execute query6
     		 System.out.println("input job_no, date_commenced, dept_number, assembly_id, process_id :\n"); 
     		 String inputLine6 = input.nextLine();
        	 System.out.println(inputLine6+"\n");          		 
     		 String[] inputParameters6 = lineToListString(inputLine6);    		       		 
    		 option6(url, inputParameters6[0], inputParameters6[1],inputParameters6[2], inputParameters6[3],inputParameters6[4]);
    		 break;  
       	 case "7": //Option 7:  execute query7
     		 System.out.println("input job_no, date_completed, job_type, and additional info depending on job_type:\n"); 
     		 String inputLine7 = input.nextLine(); 
        	 System.out.println(inputLine7+"\n");          		 
     		 String[] inputParameters7 = lineToListString(inputLine7);  
     		 if (inputParameters7[2].equals("cut")) {       			 
    		     option7(url, inputParameters7[0], inputParameters7[1],inputParameters7[2], inputParameters7[3],
    				 inputParameters7[4],inputParameters7[5], inputParameters7[6], "null", "0", "0", "0");
     		 }
     		 else if (inputParameters7[2].equals("paint")) {       			 
        		 option7(url, inputParameters7[0], inputParameters7[1],inputParameters7[2], "null", "0", "null", "0",
        				 inputParameters7[3],inputParameters7[4], inputParameters7[5], "0");
         		 }
     		 else if (inputParameters7[2].equals("fit")) {
        		 option7(url, inputParameters7[0], inputParameters7[1],inputParameters7[2],"null",
        				 "0", "null", "0", "null", "0", "0", inputParameters7[3]);
         		 }
    		 break;     		   		 
       	 case "8": //Option 8:  execute query8
     		 System.out.println("input transaction number, sup_cost, and account_number :\n"); 
     		 String inputLine8 = input.nextLine();      
        	 System.out.println(inputLine8+"\n");          		 
     		 String[] inputParameters8 = lineToListString(inputLine8);           		 
    		 option8(url, inputParameters8[0], inputParameters8[1],inputParameters8[2]);
    		 break;      		 
       	 case "9": //Option 9:  execute query9
     		 System.out.println("input assembly_id:\n");          		 
     		 String inputLine9 = input.nextLine();            		 
             System.out.println(inputLine9+ "\n");       		 
    		 option9(url, inputLine9);
    		 break;     
       	 case "10": //Option 10:  execute query10
     		 System.out.println("input date and dept_number:\n"); 
     		 String inputLine10 = input.nextLine(); 
     		 String[] inputParameters10 = lineToListString(inputLine10);    
             System.out.println(inputLine10+ "\n");       		 
    		 option10(url, inputParameters10[0], inputParameters10[1]);
    		 break;     		 
       	 case "11": //Option11:  execute query11
     		 System.out.println("input assembly_id:\n"); 
     		 String inputLine11 = input.nextLine();      
             System.out.println(inputLine11+ "\n");       		 
    		 option11(url, inputLine11);
    		 break;  
       	 case "12": //Option 12:  execute query12
     		 System.out.println("input date and dept_number:\n"); 
     		 String inputLine12 = input.nextLine();       		 
     		 String[] inputParameters12 = lineToListString(inputLine12);    
             System.out.println(inputLine12+ "\n");  		 
    		 option12(url, inputParameters12[0], inputParameters12[1]);
    		 break; 
       	 case "13": //Option 13:  execute query13
     		 System.out.println("input category range:\n"); 
     		 String inputLine13 = input.nextLine();       		 
     		 String[] inputParameters13 = lineToListString(inputLine13);  
             System.out.println(inputLine13+ "\n");     		 
    		 option13(url, inputParameters13[0], inputParameters13[1]);
    		 break; 
       	 case "14": //Option 14:  execute query14
     		 System.out.println("input job_no range:\n"); 
     		 String inputLine14 = input.nextLine();       		 
     		 String[] inputParameters14 = lineToListString(inputLine14);    	
             System.out.println(inputLine14+ "\n");         		 
    		 option14(url, inputParameters14[0], inputParameters14[1]);
    		 break;    		 
       	 case "15": //Option 15:  execute query15
     		 System.out.println("input job_no and new color:\n"); 
     		 String inputLine15 = input.nextLine();       		 
     		 String[] inputParameters15 = lineToListString(inputLine15);    
             System.out.println(inputLine15+ "\n");           		 
    		 option15(url, inputParameters15[0], inputParameters15[1]);
    		 break; 
    		 
    	 case "16": // Option 16. Import: enter new customers from a data file until the file is empty
    		 System.out.println("input customer data file name to import:\n");     		 
      		 String file1 = input.nextLine(); 
      		 file1 = "/Users/yujikim/eclipse-workspace/DBMS_project/src/" + file1;
      		 System.out.println(file1);
    		 try {
				customerData = importCustomerData(file1);		
	      		 System.out.println("The data are imported from " + file1);				
			} catch (IOException e) {
				e.printStackTrace();
			}  
    		 break;      		 
    	 case "17": // Option 17. Export Retrieve the customers (in name order) whose category is in a given range and output them  
    		 System.out.println("input category range to retrieve equivalent customers(category_from, category_to):\n");     		 
     		 String inputLine17  = input.nextLine();    
             System.out.println(inputLine17+ "\n");     		 
     		 String[] inputParameters17 = lineToListString(inputLine17);    
    		 System.out.println("input customer data file name to export:\n");     		 
      		 String file2 = input.nextLine();     
      		 file2 = "/Users/yujikim/eclipse-workspace/DBMS_project/src/" +file2;     
      		 System.out.println(file2);      		 
    		 try {	    			 
				exportCustomerData(file2, url, inputParameters17[0], inputParameters17[1]);
			} catch (IOException e) {
				e.printStackTrace();
			} 
    		 System.out.println("customer data are saved in " + file2 + "\n");      		 
    		 break;      		 
    	 case "18": // Option 18. Exit the program
        	 input.close();   
    		 exitProgram();  
    		 bool = false;
    		 break;    		 
    	default:
    		System.out.println("input interger from 1 to 18\n");
    		break;
    	
    	 }	 
     }
	}

//Option 1:  execute query1
static void option1(String url,String cname,String address,String category) throws SQLException {  
	System.out.println(cname +" "+ address +" "+ category+"\n");
	System.out.println("enter a new customer");		
    // insert the new customer and information
    try (final Connection connection = DriverManager.getConnection(url)) {
        final CallableStatement cstmt = connection.prepareCall("{call dbo.insert_new_customer(?,?,?)}");{
	        cstmt.setString("@cname", cname);
	        cstmt.setString("@address", address);	
	        cstmt.setString("@category", category);	
	        cstmt.execute();  
        }
	}
    System.out.println("new customer is inserted");	
} // end of subroutine option1

//Option 2:  execute query2
static void option2(String url,String dept_number,String dept_data) throws SQLException {  
	System.out.println("enter a new department");	
    // insert the new department and its information
    try (final Connection connection = DriverManager.getConnection(url)) {
        final CallableStatement cstmt = connection.prepareCall("{call dbo.insert_new_department(?,?)}");{
            cstmt.setString("@dept_number", dept_number);
            cstmt.setString("@dept_data", dept_data);	
            cstmt.execute();  
        }
	}
    System.out.println("new department is inserted");	
} // end of subroutine option2

//Option 3:  execute query3
static void option3(String url,String assembly_id,String date_ordered,String assembly_detail, String cname) throws SQLException {  
	System.out.println("enter a new assembly");		
    // insert the new assembly and information
    try (final Connection connection = DriverManager.getConnection(url)) {
    	final CallableStatement cstmt = connection.prepareCall("{call dbo.insert_new_assembly(?,?,?,?)}");{
    		cstmt.setString("@assembly_id", assembly_id);
    		cstmt.setString("@date_ordered", date_ordered);	
    		cstmt.setString("@assembly_detail", assembly_detail);	
    		cstmt.setString("@cname", cname);		
    		cstmt.execute();  
        }
    }
    System.out.println("new assembly is inserted");	
} // end of subroutine option3

//Option 4:  execute query4
static void option4(String url,String process_id,String process_data, String dept_number, String assembly_id, String process_type,
		String paint_type, String painting_method, String fit_type, String cutting_type, String machine_type) throws SQLException {  
	System.out.println("enter a new process");			
    // insert the new process and its information
    try (final Connection connection = DriverManager.getConnection(url)) {
        final CallableStatement cstmt = connection.prepareCall("{call dbo.insert_new_process(?,?,?,?,?,?,?,?,?,?)}");{
            cstmt.setString("@process_id", process_id);
            cstmt.setString("@process_data", process_data);	
            cstmt.setString("@dept_number", dept_number);     
            cstmt.setString("@assembly_id", assembly_id);
            cstmt.setString("@process_type", process_type);	
            cstmt.setString("@paint_type", paint_type);
            cstmt.setString("@painting_method", painting_method);	
            cstmt.setString("@fit_type", fit_type);	
            cstmt.setString("@cutting_type", cutting_type);
            cstmt.setString("@machine_type", machine_type);	      
            cstmt.execute();  
        }
	}
    System.out.println("new process is inserted");	
} // end of subroutine option4

//Option 5:  execute query5
static void option5(String url,String account_number,String date_established,String account_type, String assembly_id,
		String dept_number, String process_id) throws SQLException {  
	System.out.println("enter a new account");		
    // insert the new account and information
    try (final Connection connection = DriverManager.getConnection(url)) {
    	final CallableStatement cstmt = connection.prepareCall("{call dbo.insert_new_account(?,?,?,?,?,?)}");{
    		cstmt.setString("@account_number", account_number);
    		cstmt.setString("@date_established", date_established);	
    		cstmt.setString("@account_type", account_type);	
    		cstmt.setString("@assembly_id", assembly_id);		
    		cstmt.setString("@dept_number", dept_number);	
    		cstmt.setString("@process_id", process_id);	    		
    		cstmt.execute();  
        }
    }
    System.out.println("new account is inserted");	
} // end of subroutine option5


//Option 6:  execute query6
static void option6(String url,String job_no,String date_commenced,String dept_number, String assembly_id, String process_id) throws SQLException {  
	System.out.println("enter a new job");		
  // insert the new job and information
  try (final Connection connection = DriverManager.getConnection(url)) {
  	final CallableStatement cstmt = connection.prepareCall("{call dbo.insert_new_job(?,?,?,?,?)}");{
  		cstmt.setString("@job_no", job_no);
  		cstmt.setString("@date_commenced", date_commenced);	
		cstmt.setString("@dept_number", dept_number);	
		cstmt.setString("@assembly_id", assembly_id);	
		cstmt.setString("@process_id", process_id);				
  		cstmt.execute();  
      }
  }
  System.out.println("new job is inserted");	
} // end of subroutine option6


//Option 7:  execute query7
static void option7(String url,String job_no,String date_completed, String job_type, String type_of_machine, String time_machine_used,
		String material_used, String labor_time_c, String color, String volume, String labor_time_p, String labor_time_f) throws SQLException {  
	System.out.println("enter a additional job information");		
  // insert the addtional job information 
  try (final Connection connection = DriverManager.getConnection(url)) {
      final CallableStatement cstmt = connection.prepareCall("{call dbo.insert_job_info(?,?,?,?,?,?,?,?,?,?,?)}");{
          cstmt.setString("@job_no", job_no);
          cstmt.setString("@date_completed", date_completed);	
          cstmt.setString("@job_type", job_type);     
          cstmt.setString("@type_of_machine", type_of_machine);
          cstmt.setString("@time_machine_used", time_machine_used);	
          cstmt.setString("@material_used", material_used);
          cstmt.setString("@labor_time_c", labor_time_c);	
          cstmt.setString("@color", color);	
          cstmt.setString("@volume", volume);
          cstmt.setString("@labor_time_p", labor_time_p);	    
          cstmt.setString("@labor_time_f", labor_time_f);	            
          cstmt.execute();  
      }
	}
  System.out.println("addtional job information is inserted");	
} // end of subroutine option7

//Option 8:  execute query8
static void option8(String url,String transaction_number,String sup_cost,String account_number) throws SQLException {  
	System.out.println("enter a new transaction and update equivalent account");		
  // insert the new transaction and information
  try (final Connection connection = DriverManager.getConnection(url)) {
  	final CallableStatement cstmt = connection.prepareCall("{call dbo.insert_new_transaction(?,?,?)}");{
  		cstmt.setString("@transaction_number", transaction_number);
  		cstmt.setString("@sup_cost", sup_cost);	
  		cstmt.setString("@account_number", account_number);	
  		cstmt.execute();  
      }
  }
  System.out.println("new transaction is inserted and account is updated");	
} // end of subroutine option8

//Option 9:  execute query9
static void option9(String url,String assembly_id) throws SQLException {  
	System.out.println("retrieve the cost incurred on an assembly-id");		
  // execute query with assembly_id input
  try (final Connection connection = DriverManager.getConnection(url)) {
      final CallableStatement cstmt = connection.prepareCall("{call dbo.find_cost_with_assembly_id(?)}");{
          cstmt.setString("@assembly_id", assembly_id);
          cstmt.execute();  
  		  final ResultSet resultSet = cstmt.getResultSet(); {
  		  	  System.out.println("cost");	
  			  while (resultSet.next()) {
  			  System.out.println(String.format("%s",
  			  resultSet.getString(1)));
  			  }
          }
	  }
  }   
} // end of subroutine option9


//Option 10:  execute query10
static void option10(String url,String given_date,String given_dept_number) throws SQLException {  
	System.out.println("retrieve the total labor time");		
	// execute query with given_date and given_dept_number input
	try (final Connection connection = DriverManager.getConnection(url)) {
		final CallableStatement cstmt = connection.prepareCall("{call dbo.find_labor_time(?,?)}");{
			cstmt.setString("@given_date", given_date);    
			cstmt.setString("@given_dept_number", given_dept_number);      			
			cstmt.execute();  
	  		  final ResultSet resultSet = cstmt.getResultSet(); {
	  		  	  System.out.println("total labor time");	
	  			  while (resultSet.next()) {
	  			  System.out.println(String.format("%s",
	  			  resultSet.getString(1)));
	  			  }
	          }			
		}
	}
} // end of subroutine option10

//Option 11:  execute query11
static void option11(String url,String assembly_id) throws SQLException {  
	System.out.println("retrieve the processes and department");		
    // execute query with assembly_id input
    try (final Connection connection = DriverManager.getConnection(url)) {
        final CallableStatement cstmt = connection.prepareCall("{call dbo.find_processes(?)}");{
            cstmt.setString("@assembly_id", assembly_id);
            cstmt.execute();  
    		  final ResultSet resultSet = cstmt.getResultSet(); {
      		  	  System.out.println("process and dept_number");	
      			  while (resultSet.next()) {
      			  System.out.println(String.format("%s | %s",
      			  resultSet.getString(1),
      			resultSet.getString(2)));
      			  }
              }            
        }
	}
} // end of subroutine option11


//Option 12:  execute query12
static void option12(String url,String given_date, String given_dept_number) throws SQLException {  
	System.out.println("Retrieve the jobs completed during a given date in a given department");		
    //execute query with given_date, given_dept_number
    try (final Connection connection = DriverManager.getConnection(url)) {
        final CallableStatement cstmt = connection.prepareCall("{call dbo.find_jobs_completed(?,?)}");{
            cstmt.setString("@given_date", given_date);  	  
            cstmt.setString("@given_dept_number", given_dept_number);      
            cstmt.execute();  
	  		  final ResultSet resultSet = cstmt.getResultSet(); {
	  		  	  System.out.println("job_no, job_type and  assembly_id");	
	  			  while (resultSet.next()) {
	  			  System.out.println(String.format("%s  |  %s |  %s",
	  			  resultSet.getString(1),
	  			  resultSet.getString(2),	  			  
	  			  resultSet.getString(3)));
	  			  }
	          }		           
  		}
	}
} // end of subroutine option12


//Option 13:  execute query13
static void option13(String url,String category_from, String category_to) throws SQLException {  
	System.out.println("Retrieve the customers with category range");		
//execute query with category range
    try (final Connection connection = DriverManager.getConnection(url)) {
	    final CallableStatement cstmt = connection.prepareCall("{call dbo.find_customers_with_category_range(?,?)}");{
    	    cstmt.setString("@category_from", category_from);  	  
    	    cstmt.setString("@category_to", category_to);      
    	    cstmt.execute();  
	  		  final ResultSet resultSet = cstmt.getResultSet(); {
	  		  	  System.out.println("customer name, address, category");	
	  			  while (resultSet.next()) {
	  			  System.out.println(String.format("%s  | %s |  %s ",
	  			  resultSet.getString(1),
	  			  resultSet.getString(2),
	  			  resultSet.getString(3)));
	  			  }
	          }			    	    
		}
	}
} // end of subroutine option13


//Option 14:  execute query14
static void option14(String url,String job_no_from, String job_no_to) throws SQLException {  
	System.out.println("Delete all cut-jobs whose job-no is in a given range ");		
    //execute query with job_no range
    try (final Connection connection = DriverManager.getConnection(url)) {
    	final CallableStatement cstmt = connection.prepareCall("{call dbo.delete_cut_jobs_with_range(?,?)}");{
    		cstmt.setString("@job_no_from", job_no_from);  	  
    		cstmt.setString("@job_no_to", job_no_to);      
    		cstmt.execute();  
    	}
	}
} // end of subroutine option14

//Option 15:  execute query15
static void option15(String url,String job_no, String color_new) throws SQLException {  
	System.out.println("Change the color of a given paint job");		
    //execute query with job_no, color_new
    try (final Connection connection = DriverManager.getConnection(url)) {
  	    final CallableStatement cstmt = connection.prepareCall("{call dbo.change_color_of_paint_job(?,?)}");{
  	    	cstmt.setString("@job_no", job_no);  	  
  	    	cstmt.setString("@color_new", color_new);      
  	    	cstmt.execute();  
  	    }
	}
} // end of subroutine option15


//Option 16:  read customer data file
static List<String> importCustomerData(String file) throws IOException {	      
		     BufferedReader reader = new BufferedReader(new FileReader(file));
		     List<String> tokensIn = new ArrayList<String>();
		     String currentLine;
		     while ((currentLine = reader.readLine()) != null) {
			     System.out.println(currentLine);
          	     tokensIn.add(currentLine);		  		
				} 		     
		     reader.close();		
		     return tokensIn;
}
//Option 16:  export customer whose category is in a given range to data file
static void exportCustomerData(String file, String url, String category_from, String category_to) throws IOException, SQLException {
	System.out.println("Retrieve the customers with category range");		
	//execute query with category range
    BufferedWriter writer = new BufferedWriter(new FileWriter(file));	
	    try (final Connection connection = DriverManager.getConnection(url)) {
		    final CallableStatement cstmt = connection.prepareCall("{call dbo.find_customers_with_category_range(?,?)}");{
	    	    cstmt.setString("@category_from", category_from);  	  
	    	    cstmt.setString("@category_to", category_to);      
	    	    cstmt.execute();  
	    		System.out.println("Writing the customer information to file");	    	    
		  		  final ResultSet resultSet = cstmt.getResultSet(); {
		  		  	  System.out.println("customer name");	
	  				  writer.write(String.format("customer name"));		  		  	  
		  			  while (resultSet.next()) {
		  				  writer.write(String.format("%s  \n",resultSet.getString(1)));
			  		  	  System.out.println(String.format("%s",resultSet.getString(1)));		  				
		  			  }
		          }			    	    
			}	
	    }	

		    writer.close();
		}

//Option 18:  exit the program
static void exitProgram() {
	System.out.println("Exit the program\n");
    return;
}
// Subroutine to change a line of data to input parameters
static String[] lineToListString(String line){   
    String[] inputParameters = line.split(",");
	return inputParameters;
}


}