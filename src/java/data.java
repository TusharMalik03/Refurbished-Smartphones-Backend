import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.*;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import com.google.gson.*;
import static java.nio.file.Files.list;
import static java.util.Collections.list;

public class data extends HttpServlet {
    Connection co;
    public void init(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            co=(Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/Mobilices","root",null);
        }catch(Exception j){
            System.out.println("At time of Driver Loading: "+j);
        }
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out=response.getWriter();
        try{
            PreparedStatement ps;
            ResultSet rs;
            String s1=request.getParameter("Company");
            String s2=request.getParameter("Model");
            
            System.out.println("s1 is: "+s1);
            System.out.println("s2 is:" +s2);
            
            if(s1.equals("Select Company") && s2.equals("Choose1")){
                out.print(getAll());
            }
            else if(s2.equals("Choose")){
                out.print(getModels(s1));
            }else if(s2.equals("Choose1")){
                out.print(getAllMobiles(s1));
            }
            else{
                out.print(getMobiles(s1,s2));
            }
            
        }catch(Exception j){
            out.println("At time of Checking Models:  "+j);
        }
    }
    
    public String getModels(String Company){
        PreparedStatement ps;
        Gson gson=new Gson();
        String json="";
        ArrayList<titles> l1=new ArrayList<titles>();
        ArrayList<String> l2=new ArrayList<String>();
        try{
                ps=co.prepareStatement("Select Model,MobileUrl from data where Company=?");
                ps.setString(1,Company);
                ResultSet rs=ps.executeQuery();
                while(rs.next()){
                    titles t1=new titles();
                    if(l2.contains(rs.getString(1))){
                    }
                    else{
                       l2.add(rs.getString(1));
                       t1.setModel(rs.getString(1));
                       t1.setMobileUrl(rs.getString(2));
                       l1.add(t1);                            
                    }
                     json=gson.toJson(l1);
                }
                json=gson.toJson(l1);
                ps.close();
                rs.close();
            }catch(Exception j){
                System.out.println("At time of getModels:  "+j);
            }
            return json;
    }
    
    public String getMobiles(String s1,String s2){
        PreparedStatement ps;
        ResultSet rs;
        ArrayList<Mobiles> l1=new ArrayList<Mobiles>();
        String Json="";
        String Json1="";
        try{
            Gson gson=new Gson();
            ps=co.prepareStatement("Select * from data where Company=? and Model=?");
            ps.setString(1,s1);
            ps.setString(2,s2);
            rs=ps.executeQuery();
                while(rs.next()){
                    Mobiles m1=new Mobiles();
                    String mSite=rs.getString(1);
                    String mImageUrl=rs.getString(3);
                    String mModel=rs.getString(4);
                    String mPrice=rs.getString(5);
                    String mMobileUrl=rs.getString(6);
                    String mCondition=rs.getString(7);
                    String mStorage=rs.getString(8);
                    String mColor=rs.getString(9);
                    
                    m1.setSite(mSite);
                    m1.setImageUrl(mImageUrl);
                    m1.setModel(mModel);
                    m1.setPrice(mPrice);
                    m1.setMobileUrl(mMobileUrl);
                    m1.setCondition(mCondition);
                    m1.setStorage(mStorage);
                    m1.setColor(mColor);

                    l1.add(m1);
                    Json1=gson.toJson(l1);
                    System.out.println(Json);
                }
        }catch(Exception g){
            System.out.println("Error at Time of Getting Mobile Records : "+g);
        }
        return Json1;
    }
    
        public String getAllMobiles(String s1){
            PreparedStatement ps;
            ResultSet rs;
            String Json1="";
            Gson gson=new Gson();
            
                try{
                    ArrayList<Mobiles> l1=new ArrayList<Mobiles>();
                    ps=co.prepareStatement("Select * from data where Company=?");
                    ps.setString(1,s1);
                    rs=ps.executeQuery();
                    while(rs.next()){
                     
                    Mobiles m1=new Mobiles();
                    String mSite=rs.getString(1);
                    String mImageUrl=rs.getString(3);
                    String mModel=rs.getString(4);
                    String mPrice=rs.getString(5);
                    String mMobileUrl=rs.getString(6);
                    String mCondition=rs.getString(7);
                    String mStorage=rs.getString(8);
                    String mColor=rs.getString(9);
                    
                    m1.setSite(mSite);
                    m1.setImageUrl(mImageUrl);
                    m1.setModel(mModel);
                    m1.setPrice(mPrice);
                    m1.setMobileUrl(mMobileUrl);
                    m1.setCondition(mCondition);
                    m1.setStorage(mStorage);
                    m1.setColor(mColor);
                    
                    l1.add(m1);
                    Json1=gson.toJson(l1);
                    System.out.println(Json1);     
                    }
                }catch(Exception j){
                    System.out.println("At time of getAllMobiles: "+j);
                }
                return Json1;
        }
        
        public String getAll(){
            PreparedStatement ps;
            ResultSet rs;
            String Json1="";
            Gson gson=new Gson();
            
                try{
                    ArrayList<Mobiles> l1=new ArrayList<Mobiles>();
                    ps=co.prepareStatement("Select * from data LIMIT 8");
//                    ps.setString(1,s1);
                    rs=ps.executeQuery();
                    while(rs.next()){
                     
                    Mobiles m1=new Mobiles();
                    String mSite=rs.getString(1);
                    String mImageUrl=rs.getString(3);
                    String mModel=rs.getString(4);
                    String mPrice=rs.getString(5);
                    String mMobileUrl=rs.getString(6);
                    String mCondition=rs.getString(7);
                    String mStorage=rs.getString(8);
                    String mColor=rs.getString(9);
                    
                    m1.setSite(mSite);
                    m1.setImageUrl(mImageUrl);
                    m1.setModel(mModel);
                    m1.setPrice(mPrice);
                    m1.setMobileUrl(mMobileUrl);
                    m1.setCondition(mCondition);
                    m1.setStorage(mStorage);
                    m1.setColor(mColor);
                    
                    l1.add(m1);
                    Json1=gson.toJson(l1);
                    System.out.println(Json1);     
                    }
                }catch(Exception j){
                    System.out.println("At time of getAll: "+j);
                }
                return Json1;
        }
        
    
}
