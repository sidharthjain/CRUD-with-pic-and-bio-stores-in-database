/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbcex;
import java.io.*;
import static java.lang.System.exit;
import java.sql.*;
import java.util.Scanner;

/**
 *
 * @author sidharthjain
 */
public class Jdbcex {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try{
          Class.forName("com.mysql.jdbc.Driver");
          Connection con=DriverManager.getConnection
        ("jdbc:mysql://localhost:3306/signup","root","");
         /* if(con!=null){
              System.out.println("established");
          } */
          Scanner scan=new Scanner(System.in);
            System.out.println("Choices are : ");
            System.out.println("1. Insert record.");
            System.out.println("2. Fetch  record.");
          //  System.out.println(". Fetch All records.");
            System.out.println("3. Update record.");
            System.out.println("4. Delete record.");
            System.out.println("5. Exit");
            System.out.print("Enter Choice : ");
            int x=scan.nextInt();
          
            
          
          // Insert Pre-Defined Records
          if(x==1){
          PreparedStatement ps=con.prepareStatement("insert into register values ("
                  + "default,?,?,?,?,?)");
          //ps.setInt(1,1);
          ps.setString(1, "sid");
          ps.setString(2,"sid@gmail");
          ps.setString(3,"hello1234");
          
          // storing image
          
          FileInputStream fin=new FileInputStream("/home/sidharthjain/Pictures/nature.jpg");
          ps.setBinaryStream(4, fin,fin.available());
          
          // storing file
          File f=new File("/home/sidharthjain/Downloads/java/java");
          FileReader fr=new FileReader(f);
          ps.setCharacterStream(5, fr,(int)f.length());
          
          int i =ps.executeUpdate();
            System.out.println(i+" records inserted successfully.");
          }
          
          // Fetch  record
          if(x==2){
               PreparedStatement ps=con.prepareStatement("Select * from register");
                       ResultSet rs=ps.executeQuery();
                       while(rs.next()){
                           System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "
                           +rs.getString(4));
                           
                           
                           Blob b=rs.getBlob(5);
                           byte[] barr=b.getBytes(1, (int)b.length());
                           
                           FileOutputStream fout=new FileOutputStream("/home/sidharthjain/Pictures/rfile/retrieve.jpg");
                           fout.write(barr);
                           fout.close();
                           
                           
                           Clob c=rs.getClob(6);
                           Reader r=c.getCharacterStream();
                           
                           FileWriter fw=new FileWriter("/home/sidharthjain/Pictures/javatext/text.txt");
                           int j;
                           while((j=r.read())!=-1){
                               fw.write((char)j);
                              
                           }
                            fw.close();
                       }
          }
              /*System.out.println("1.Fetch single"+" "+"2. Fetch all?");
              System.out.println("Enter choice : 1 or 2 ");
              int y=scan.nextInt();
              switch(y){
                  case 1: 
                      PreparedStatement ps1=con.prepareStatement("select from register where id=?");
                      ps1.setInt(1, 3);
                      ResultSet rs1=ps1.executeQuery();
                      while(rs1.next()){
                          System.out.println("");
                      }
                              
                     
                      break;
                  case 2:
                       PreparedStatement ps=con.prepareStatement("Select * from register");
                       ResultSet rs=ps.executeQuery();
                       while(rs.next()){
                           System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "
                           +rs.getString(4));
                           Blob b=rs.getBlob(5);
                           byte[] barr=b.getBytes(1, (int)b.length());
                           
                           FileOutputStream fout=new FileOutputStream("/home/sidharthjain/Pictures/rfile/retrieve.jpg");
                           fout.write(barr);
                           
                           rs.next();
                           Clob c=rs.getClob(6);
                           Reader r=c.getCharacterStream();
                           
                           FileWriter fw=new FileWriter("/home/sidharthjain/Pictures/javatext/text.txt");
                           int j;
                           while((j=r.read())!=-1){
                               fw.write((char)j);
                               fw.close();
                           }
                                //fout.close();    
                       }   
                      break;
                  default: exit(0);
              }
             
          } */
          //fetch all records
          
          // Update Redcord
          if(x==3){
              PreparedStatement ps=con.prepareStatement("update register set name= ? where id=?");
              ps.setString(1,"hello changed name");
              ps.setInt(2,1);
              
              int i=ps.executeUpdate();
              System.out.println(i+ " "+"records updated,please check!");
          }
          //delete record
          if(x==4){
              PreparedStatement ps=con.prepareStatement("delete from register where id=?");
              ps.setInt(1,4);
              int i=ps.executeUpdate();
              System.out.println(i+ " "+"Record deleted,please check!! ");
          }
          
          //exit
          
          if(x==5){
              exit(0);
          }
          con.close();
            
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
}
