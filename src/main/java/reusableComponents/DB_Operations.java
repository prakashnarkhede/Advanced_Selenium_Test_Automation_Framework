package reusableComponents;

import java.sql.*;
import java.util.HashMap;  
/**
 * @author: Prakash Narkhede
 * @Youtube: https://www.youtube.com/automationtalks
 * @LinkedIn: https://www.linkedin.com/in/panarkhede89/
 */
public class DB_Operations {
	
		public synchronized HashMap<String, String> getSqlResultInMap(String sql) {  
            HashMap<String, String> data_map = new HashMap<>();

			try{  
				Class.forName("com.mysql.cj.jdbc.Driver");  
				Connection con=DriverManager.getConnection(  
						"jdbc:mysql://localhost:3306/qdpm_qa?serverTimezone=UTC","root",""); 		

				Statement stmt=con.createStatement();  
				ResultSet rs=stmt.executeQuery(sql);  
	            ResultSetMetaData md = rs.getMetaData();

	            while (rs.next()) {            
	                for (int i = 1; i <= md.getColumnCount(); i++) {
	                    data_map.put(md.getColumnName(i), rs.getString(i));
	                }
	            }
	            System.out.println(data_map);
				con.close();  
			}catch(Exception e){ System.out.println(e);}
			return data_map;  
		}  

}
