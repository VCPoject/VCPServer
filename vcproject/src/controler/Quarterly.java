package controler;


import java.util.ArrayList;
import java.util.Vector;

public class Quarterly extends Controller {
        
        ArrayList <Object> orderReport = new ArrayList<Object>();
        ArrayList <Object> complainReport = new ArrayList<Object>();
        ArrayList <Object> parkingDownReport = new ArrayList<Object>();
        private VcpInfo vcpInfo;
        public Quarterly(String host, int port,VcpInfo vcpInfo){
                super(host,port);
                this.vcpInfo=vcpInfo;
        }
        
        
        
        
        /**
         * get all data the is needed to show from DB
         * @param start
         * @param end
         * @param lotID
         */
        protected void getRowData(String start,String end,int lotID){

                Object[] obj ={"SELECT * FROM vcp_db.order WHERE arrivalDate < ? AND arrivalDate > ? AND idparking = ? ORDER BY type;",end,start,lotID};
                sendQueryToServer(obj);
                if(!getResult().get(0).equals("No Result")){}
                for(int i=0;i<getResult().size();i++)
                        orderReport.add(getResult().get(i));
                Object[] obj1 ={"SELECT * FROM vcp_db.complain WHERE date < ? AND date > ? AND lot_id = ?  ORDER BY status;",end,start,lotID};
                sendQueryToServer(obj1);
                if(!getResult().get(0).equals("No Result")){}
                for(int i=0;i<getResult().size();i++)
                        complainReport.add(getResult().get(i));
                Object[] obj2 ={"SELECT COUNT(parkingPlaceNum) FROM vcp_db.not_working_places WHERE endDate < ? AND startDate > ? AND idparking = ? AND parkingPlaceNum !=0 ;",end,start,lotID};
                sendQueryToServer(obj2);
                if(!getResult().get(0).equals("No Result")){}
                for(int i=0;i<getResult().size();i++)
                        parkingDownReport.add(getResult().get(i));
                Object[] obj3 ={"SELECT idparking FROM vcp_db.not_working_places WHERE endDate < ? AND startDate > ?  AND parkingPlaceNum = 0 AND idparking = ?;",end,start,lotID};
                sendQueryToServer(obj3);
                if(!getResult().get(0).equals("No Result")){}
                for(int i =0;i<getResult().size();i++)
                        parkingDownReport.add(vcpInfo.getParkingLotInfo().get(Integer.parseInt(getResult().get(i).toString())).getDepth()*
                                        vcpInfo.getParkingLotInfo().get(Integer.parseInt(getResult().get(i).toString())).getHight()*
                                        vcpInfo.getParkingLotInfo().get(Integer.parseInt(getResult().get(i).toString())).getWidth());   
                
        }
        
        /**
         * id selected to show Order Status - set appropriate query
         * @param qurater
         * @param year
         * @param lotID
         * @return Vector<Vector<Object>> result
         */
        public Vector<Vector<Object>> OrderStatus(int qurater, int year,int lotID){
                Vector<Vector<Object>> result = new Vector<Vector<Object>>();
                Vector<Object> row=new Vector<Object>(14);
                if(qurater ==1){
                        getRowData(year + "-01-01", year + "-03-31", lotID);
                }
                if(qurater ==2){
                        getRowData(year + "-04-01", year + "-06-31", lotID);
                }
                if(qurater ==3){
                        getRowData(year + "-07-01", year + "-09-31", lotID);
                }
                if(qurater ==4){
                        getRowData(year + "-10-01", year + "-12-31", lotID);
                }
                
                for(int i=0;i<orderReport.size();i++)
                {
                        row.add(orderReport.get(i));
                        if(row.size()==14){
                                result.add(row);
                                row=new Vector<Object>(14);
                        }
                }
                result.get(0);
                return result;
        }
        
        /**
         * id selected to show Complain Status - set appropriate query
         * @param qurater
         * @param year
         * @param lotID
         * @return Vector<Vector<Object>> result
         */
        public Vector<Vector<Object>> ComplainStatus(int qurater, int year,int lotID){
                if(qurater ==1){
                        getRowData(year + "-01-01", year + "-03-31", lotID);
                }
                if(qurater ==2){
                        getRowData(year + "-04-01", year + "-06-31", lotID);
                }
                if(qurater ==3){
                        getRowData(year + "-07-01", year + "-09-31", lotID);
                }
                if(qurater ==4){
                        getRowData(year + "-10-01", year + "-12-31", lotID);
                }
                Vector<Vector<Object>> result = new Vector<Vector<Object>>();
                Vector<Object> row=new Vector<Object>(6);
                for(int i=0;i<complainReport.size();i++)
                {
                        row.add(complainReport.get(i));
                        if(row.size()==6){
                                result.add(row);
                                row=new Vector<Object>(6);
                        }
                }
                return result;
        }
        /**
         * id selected to show Not Working Places Status - set appropriate query
         * @param qurater
         * @param year
         * @param lotID
         * @return Vector<Object> result
         */
        public Vector<Object> NotWorking(int qurater, int year,int lotID){
                if(qurater ==1){
                        getRowData(year + "-01-01", year + "-03-31", lotID);
                }
                if(qurater ==2){
                        getRowData(year + "-04-01", year + "-06-31", lotID);
                }
                if(qurater ==3){
                        getRowData(year + "-07-01", year + "-09-31", lotID);
                }
                if(qurater ==4){
                        getRowData(year + "-10-01", year + "-12-31", lotID);
                }
                
                Vector<Object> result = new Vector<Object>();
                result.add(lotID);
                int sum=0;
                for(int i=0;i<parkingDownReport.size();i++)
                        sum=sum + Integer.parseInt(parkingDownReport.get(i).toString());
                result.add(sum);
                return result;
        }
        

        

}
