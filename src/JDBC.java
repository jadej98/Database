/**
 * Created by jordanj8 on 4/23/19.
 */

import java.sql.*;
import java.util.*;
import java.sql.DriverManager;

public class JDBC {
    private String userId;
    private String pass;
    private final String preConnString = "jdbc:mysql://mysql.cs.wwu.edu:3306/";
    private final String postConnString = "?useSSL=false" ;
    String name;

    public JDBC (String userId, String pass) {
        this.userId = userId;
        this.pass = pass;
    }

    public void run() {
        try {
            boolean choosing = true;
            Connection connection = getConnection();
            //System.out.println("Connection successful");
            //getTranscript(userId);
            System.out.println("To get, Transcipt enter: 1 \n To check degree requirements, enter: 2 \n To add a course, enter: 3 \n To remove a course, enter: 4 \n To exit, enter: 5");
            Scanner sc = new Scanner(System.in);
            int choice = sc.nextInt();

            while(choosing) {
                if (choice == 1)
                    getTranscript(userId);
                else if (choice == 2)
                    System.out.println("Check degree requirements");
                else if (choice == 3)
                    System.out.println("add a course");
                else if (choice == 4)
                    System.out.println("remove a course");
                else if (choice == 5) {
                    choosing = false;
                    break;
                }
                else {
                    System.out.println("Error, input wasn't recognized");
                    run();
                }
            }




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

//        private void print(LinkedList<String> linkedList) {
//            //table length and attr lengh
//            int tablelength = 0;
//            LinkedList<Integer> attrLength = new LinkedList<Integer>();
//            String tempName;
//            int format;
//            for (int i = 0; i < linkedList.size(); i++)
//            {
//                tablelength += linkedList.get(i).length() + 6;
//                attrLength.add(linkedList.get(i).length()+6);
//            }
//
//            //top line
//            for (int i = 0; i < tablelength; i++) System.out.print("*");
//
//            System.out.println("\n|");
//
//            for (int i = 0; i < linkedList.size(); i++) {
//                tempName = linkedList.get(i);
//                format = attrLength.get(i) - 2;
//                if (i+1 == linkedList.size()) //Align last |
//                    format--;
//                System.out.printf(" %-" + format + "s|", tempName);
//            }
//            System.out.println();
//
//            // ----- line after Attribute names.
//            for (int i = 0; i < tablelength; i++)
//                System.out.print("-");
//
//            //how to print values
//
//
//            // ***** Bottom line.
//            System.out.println();
//            for (int i = 0; i < tablelength; i++)
//                System.out.print("*");
//
//            //Print blank lines to make tables more readable when printing multiple relations.
//            System.out.println();
//            System.out.println();
//        }





       private LinkedList<String> getTranscript (String id) throws SQLException {
            //Prepared statements for Transcript
            //String selectTrans = "SELECT course_number, course_title, semester, year, grade, credits FROM STUDENT LEFT OUTTER JOIN COURSE WHERE USER_ID = ?"; //ask about this
           //tables: Course, table section, takes
            LinkedList<String> linkedlist = new LinkedList<String>(); //to put column labels in

            String selectTrans = "SELECT t.year, c.title, c.course_id, t.semester, t.grade, c.credits FROM student AS s, takes AS t, course AS c WHERE s.ID = 12345 AND s.ID = t.ID AND t.course_id = c.course_id ORDER BY t.year";
            PreparedStatement preparedStatement = getConnection().prepareStatement(selectTrans); //replace with connection name
            //preparedStatement.setInt(1, 1001);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String number = rs.getString("course_id");
                String title = rs.getString("title");
                String sem = rs.getString("semester");
                String year = rs.getString("year");
                String grade = rs.getString("grade");
                String credit = rs.getString("credits");

                System.out.println(number + title + sem + year + grade + credit);
            }



            linkedlist.add("Course Id");
           linkedlist.add("Title");
           linkedlist.add("Semester");
           linkedlist.add("Year");
           linkedlist.add("Grade");
           linkedlist.add("Credits");

           return linkedlist;
        }

//        private String checkReq(String id) throws SQLException {
//            //get courses that the student still needs to complete deg
//            String selectReq = "";
//            //tables: prereq
//        //course number title  for dept = student Dept from id
//
//
//        }
//
//
//        private void addCourse (int id, String courseNum) {
//
//
//        }
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
