package controler;

import java.util.ArrayList;
import java.util.Vector;

public class SystemDataControler extends Controller {

        
        private ArrayList<Object> orders= new ArrayList<Object>();
        private VcpInfo vcpInfo;
        public SystemDataControler(String host, int port,VcpInfo vcpInfo){
                super(host,port);
                this.vcpInfo=vcpInfo;
        }
        
        
        protected void getRowData(){
                
                
                
                
                
        }

/**
 * get all orders from DB
 * @return Vector<Vector<Object>> result
 */
        public Vector<Vector<Object>> getOrders() {
                Object[] obj ={"SELECT * FROM vcp_db.order ;"};
                sendQueryToServer(obj);
                if(!getResult().get(0).equals("No Result")){}
                Vector<Vector<Object>> result = new Vector<Vector<Object>>();
                Vector<Object> row=new Vector<Object>(14);
                int j=1;
                for(int i=0;i<getResult().size();i++){
                        row.add(getResult().get(i));
                        if(row.size()==14){
                                result.add(row);
                                row=new Vector<Object>(14);
                        }
                }
                return result;
        }
        /**
         * set name to JTable Orders column
         * @return Vector<Object> s
         */
        public Vector<Object> OrdersRowName(){
                Vector<Object> s=new Vector<Object>(14);
                 s.add("Order ID");
                 s.add("Car Number");
                 s.add("Client ID");
                 s.add("Parking ID");
                 s.add("Arrival Date");
                 s.add("Arrival Time");
                 s.add("Departure Date");
                 s.add("Departure Time");
                 s.add("Check In Date");
                 s.add("Check In Time");
                 s.add("Check Out Date");
                 s.add("Check Out Time");
                 s.add("Status");
                 s.add("Client Type");
                return s;
        }
        /**
         * return all employee data from DB
         * @return Vector<Vector<Object>> result
         */
        public Vector<Vector<Object>> getEmployees() {
                Object[] obj ={"SELECT * FROM vcp_employ.employ ;"};
                sendQueryToServer(obj);
                if(!getResult().get(0).equals("No Result")){}
                Vector<Vector<Object>> result = new Vector<Vector<Object>>();
                Vector<Object> row=new Vector<Object>(9);
                for(int i=0;i<getResult().size();i++){
                        row.add(getResult().get(i));
                        if(row.size()==9){
                                result.add(row);
                                row=new Vector<Object>(9);
                        }
                }
                return result;
        }
        /**
         * set name to JTable Employee column
         * @return Vector<Object> s
         */
        public Vector<Object> EmployeeRowName(){
                Vector<Object> s=new Vector<Object>(14);
                 s.add("Employee ID");
                 s.add("First Name");
                 s.add("Last Name");
                 s.add("Role");
                 s.add("User Name");
                 s.add("Password");
                 s.add("Email");
                 s.add("Login");
                 s.add("Relevence");
                return s;
        }
        /**
         * return all complains data from DB
         * @return Vector<Vector<Object>> result
         */
        public Vector<Vector<Object>> getComplains() {
                Object[] obj ={"SELECT * FROM vcp_db.complain ;"};
                sendQueryToServer(obj);
                if(!getResult().get(0).equals("No Result")){}
                Vector<Vector<Object>> result = new Vector<Vector<Object>>();
                Vector<Object> row=new Vector<Object>(8);
                for(int i=0;i<getResult().size();i++){
                        row.add(getResult().get(i));
                        if(row.size()==8){
                                result.add(row);
                                row=new Vector<Object>(8);
                                
                        }
                }
                return result;
        }
        /**
         * set name to JTable Complain column
         * @return Vector<Object> s
         */
        public Vector<Object> ComplainRowName(){
                Vector<Object> s=new Vector<Object>(14);
                
                s.add("Complain Num");
                 s.add("Client ID");
                 s.add("Description");
                 s.add("Status");
                 s.add("Opened On Date");
                 s.add("Lot ID");
                return s;
        }
        
        
        
        

        
}