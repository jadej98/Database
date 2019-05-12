/**
 * Created by jordanj8 on 4/23/19.
 */

import java.sql.*;
import java.util.*;

public class JDBCdb {
    private String userId;
    private String pass;
    private final String preConnString = "jdbc:mysql://mysql.cs.wwu.edu:3306/";
    private final String postConnString = "?useSSL=false" ;
    String name;

    public JDBCdb (String userId, String pass) {
        this.userId = userId;
        this.pass = pass;
    }

    public void run() {
        try {
            Connection connection = getConnection();
            System.out.println("Connection successful");


//            List<Student> students = getAllStudents();
//            for (Student s : students) {
//                System.out.println(s);
            }

        catch(Exception e){
                e.printStackTrace();
            }
        }

        public String getName() {
            return this.name;
        }

        private void print(LinkedList<String> linkedList) {
            //table length and attr lengh
            int tablelength = 0;
            LinkedList<Integer> attrLength = new LinkedList<Integer>();
            String tempName;
            int format;
            for (int i = 0; i < linkedList.size(); i++)
            {
                tablelength += linkedList.get(i).length() + 6;
                attrLength.add(linkedList.get(i).length()+6);
            }

            //top line
            for (int i = 0; i < tablelength; i++) System.out.print("*");

            System.out.println("\n|");

            for (int i = 0; i < linkedList.size(); i++) {
                tempName = linkedList.get(i);
                format = attrLength.get(i) - 2;
                if (i+1 == linkedList.size()) //Align last |
                    format--;
                System.out.printf(" %-" + format + "s|", tempName);
            }
            System.out.println();

            // ----- line after Attribute names.
            for (int i = 0; i < tablelength; i++)
                System.out.print("-");

            //how to print values


            // ***** Bottom line.
            System.out.println();
            for (int i = 0; i < tablelength; i++)
                System.out.print("*");

            //Print blank lines to make tables more readable when printing multiple relations.
            System.out.println();
            System.out.println();
        }





       private String getTranscript (int id) throws SQLException {
            //Prepared statements for Transcript
            //String selectTrans = "SELECT course_number, course_title, semester, year, grade, credits FROM STUDENT LEFT OUTTER JOIN COURSE WHERE USER_ID = ?"; //ask about this
           //tables: Course, table section, takes
            LinkedList<String> linkedlist = new LinkedList<String>(); //to put column labels in

            String selectTrans = "SELECT c.course_id, c.title, t.semester, t.year, t.grade, c.credits FROM student AS s, takes AS t, course AS c WHERE s.ID = id AND s.ID = t.ID AND t.course_id = c.course_id GROUP BY t.year";
            PreparedStatement preparedStatement = dbConnection.prepareStatement(selectTrans); //replace with connection name
            preparedStatement.setInt(1, 1001);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String number = rs.getString("course_id");
                String title = rs.getString("title");
                String sem = rs.getString("semester");
                String year = rs.getString("year");
                String grade = rs.getString("grade");
                String credit = rs.getString("credits");
            }

            linkedlist.add("Course Id");
           linkedlist.add("Title");
           linkedlist.add("Semester");
           linkedlist.add("Year");
           linkedlist.add("Grade");
           linkedlist.add("Credits");

        }

        private String checkReq(int id) throws SQLException {
            //get courses that the student still needs to complete deg
            String selectReq = "";
            //tables: prereq
        //course number title  for dept = student Dept from id


        }


        private void addCourse (int id, String courseNum) {


        }
//    private List<Student> getAllStudents() {
//            Connection connection = getConnection();
//
//            Statement query = connection.createStatement();
//            ResultSet result = query.executeQuery("SLECT * FROM student")
//            List<Student> = new ArrayList<>();
//            Student s = null;
//            //Student s = new Student(result.getInt(1), result.getString(2));
//
//            while(result.next()) {
//                result.getInt(1);
//                result.getString(2);
//                result.getString(3);
//                System.out.println("ds");
//            }
//            return s;
//        }

        //good
//        private Student getStudents(int studentId) throws SQLException {
//            Connection connection = getConnection();
//
//            Statement query = connection.createStatement();
//            ResultSet result = query.executeQuery("SELECT * FROM student")
//            List<Student> = new ArrayList<>();
//            Student s = null;
//            //Student s = new Student(result.getInt(1), result.getString(2));
//
//            while(result.next()) {
//                result.getInt(1);
//                result.getString(2);
//                result.getString(3);
//                System.out.println("ds");
//            }
//            return s;
//        }




        private String getConnectionString() {
            return preConnString + userId + postConnString;
        }

        private Connection getConnection() throws SQLException {
            return DriverManager.getConnection(getConnectionString(), userId, pass);
        }
    }
