import org.mindrot.jbcrypt.BCrypt;
import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;

class Admin{
    private String name;
    private String admin_id;
    private String email;
    private String password;

    public void setAdmin_id(String admin_id){
        this.admin_id = admin_id;
    }

    public String getAdmin_id(){
        return admin_id;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getEmail(){
        return email;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getPassword(){
        return password;
    }
}

class Student{
    private String name;
    private  String student_id;
    private  int roll;
    private String grade;
    private  String phone;
    private  String stream;



    public void setName(String name){
        this.name = name;
    }

    public  String getName(){
        return name;
    }

    public void setStudent_id(String student_id){
        this.student_id = student_id;
    }

    public  String getStudent_id(){
        return student_id;
    }

    public void setRoll(int roll){
        this.roll = roll;
    }

    public  int getRoll(){
        return roll;
    }

    public  void setGrade(String grade){
        this.grade = grade;
    }

    public String getGrade(){
        return  grade;
    }

    public void setPhone(String phone){
        this.phone = phone;
    }

    public String getPhone(){
        return phone;
    }

    public void setStream(String stream){
        this.stream = stream;
    }

    public String getStream(){
        return stream;
    }
}

public class Main{

    private static final  String url = "jdbc:mysql://localhost:3306/student_management";
    private static final String username="root";
    private static final String password = "";

    public static String hashpwd(String pwd){
        String  originalPassword = pwd;
        String generatedSecuredPasswordHash = BCrypt.hashpw(originalPassword,BCrypt.gensalt());
        return generatedSecuredPasswordHash;
    }


    public static void addAdmin(){

        Admin admin = new Admin();
        try{

            Scanner sc = new Scanner(System.in);

            Class.forName("com.mysql.jdbc.Driver");

            Connection connection = DriverManager.getConnection(url,username,password);
            System.out.println("Enter your name");
            String name = sc.nextLine();
            if(name.isEmpty()){
                System.out.println("Name is required");
                return;
            }
            admin.setName(name);
            System.out.println("Enter your admin id");
            String id = sc.nextLine();
            if(id.isEmpty()){
                System.out.println("Admin id is required");
                return;
            }
            admin.setAdmin_id(id);
            System.out.println("Enter your Email");
            String email = sc.nextLine();
            if(email.isEmpty()){
                System.out.println("Email is required");
                return;
            }
            admin.setEmail(email);
            System.out.println("Enter your password");
            String password = sc.nextLine();
            if(password.isEmpty()){
                System.out.println("Password is required");
                return;
            }
            String hashedpwd = hashpwd(password);
            admin.setPassword(hashedpwd);

            String query = "INSERT INTO admin(name,admin_id,email,password) VALUES(?,?,?,?)";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,admin.getName());
            preparedStatement.setString(2,admin.getAdmin_id());
            preparedStatement.setString(3,admin.getEmail());
            preparedStatement.setString(4,admin.getPassword());
            preparedStatement.executeUpdate();
            connection.close();

            System.out.println("Admin successfully added");




        }catch (Exception e){
            System.out.println(e);
        }

    }

    public static void delAdmin(){
        Scanner sc = new Scanner(System.in);
        Admin admin = new Admin();
        String email = "";
        String password = "";
        System.out.println("You need to log in before you can delete your account\n");
        System.out.println("Please log in\n");
        System.out.println("Enter your email id");
        String email1 = sc.nextLine();
        System.out.println("Enter your password");
        String pass1 = sc.nextLine();
        try {
            System.out.println("Please enter your admin id");
            String admin_id = sc.nextLine();
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);

            String query = "SELECT email,password FROM admin WHERE admin_id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, admin_id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                email = resultSet.getString("email");
                password = resultSet.getString("password");
            }

            Boolean decoded_pass = BCrypt.checkpw(pass1, password);

            if (decoded_pass.equals(true) && email.equals(email1)) {


                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    System.out.println("Enter the admin id you want to delete ");
                    String admin_id1 = sc.nextLine();
                    System.out.println("Are you sure you want to delete. This can't be undone");
                    System.out.println("Press yes to confirm this operation");
                    System.out.println("Press no to cancel this operation");
                    String del = sc.nextLine().toLowerCase();
                    if (del.equals("yes")) {

                        String query1 = "DELETE FROM admin WHERE admin_id=?";
                        PreparedStatement preparedStatement1 = connection.prepareStatement(query1);
                        preparedStatement1.setString(1, admin_id1);
                        preparedStatement1.executeUpdate();
                        connection.close();
                        System.out.println("ADMIN SUCCESSFULLY DELETED");
                    } else {
                        System.out.println("Operation Cancelled");


                    }
                } catch (Exception e) {
                    System.out.println("FAILED TO DELETE");
                }


            }



            else {
                System.out.println("email or password  is incorrect");
            }

        }catch(Exception e){
                System.out.println("Admin id is incorrect");
            }



    }

    public static void addStudent(Student student){
        try{
            Class.forName("com.mysql.jdbc.Driver");

            Connection connection = DriverManager.getConnection(url,username,password);

            String query = "INSERT INTO Student(name,student_id,roll,grade,phone,stream) VALUES(?,?,?,?,?,?)";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,student.getName());
            preparedStatement.setString(2,student.getStudent_id());
            preparedStatement.setInt(3,student.getRoll());
            preparedStatement.setString(4,student.getGrade());
            preparedStatement.setString(5,student.getPhone());
            preparedStatement.setString(6,student.getStream());

            preparedStatement.executeUpdate();

            connection.close();



        }catch (Exception e){
            System.out.println(e);
        }

    }
    public  static void delStudent(String student){

        Student student1 = new Student();

        try {
            Class.forName("com.mysql.jdbc.Driver");

            Connection connection = DriverManager.getConnection(url,username,password);

            String query = "DELETE FROM student WHERE student_id='"+student+"'";

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.executeUpdate();

            System.out.println("SUCCESSFULLY DELETED STUDENT");

            connection.close();
//            System.out.println(query);

        } catch (Exception e) {
            System.out.println("WRONG STUDENT_ID OR STUDENT_ID IS INVALID");
        }

    }
    public static void searchStudent(String studentid) {

        try {

            String name = "",student_id = "",phone = "",grade ="",stream="";
            int roll = 0;

            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);

            String query = "SELECT * FROM student WHERE student_id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,studentid);

            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                name = resultSet.getString("name");
                student_id = resultSet.getString("student_id");
                roll = resultSet.getInt("roll");
                grade = resultSet.getString("grade");
                phone = resultSet.getString("phone");
                stream = resultSet.getString("stream");

                System.out.println("name student_id roll grade phone stream");
                System.out.println(name+" "+student_id+" "+roll+" "+grade+" "+phone+" "+stream+"\n");

            }else {
                System.out.println("NO STUDENTS FOUND\n");
            }

            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }

    }
    public static void editStudent(String studentid){
        Scanner sc = new Scanner(System.in);
        Student student = new Student();
        System.out.println("Type 'name' to edit the name");
        System.out.println("Type 'roll' to edit the name");
        System.out.println("Type 'phone' to edit the name");
        System.out.println("Type 'stream' to edit the name");
        System.out.println("Type 'grade' to edit the name");
        String edit = sc.nextLine().toLowerCase();
        switch (edit){
            case "name":
                try{
                    System.out.println("Enter the new name for the student");
                    String name = sc.nextLine();
                    Class.forName("com.mysql.jdbc.Driver");

                    Connection connection = DriverManager.getConnection(url,username,password);

                    String query = "UPDATE student SET student_id=?,name=?";

                    PreparedStatement preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setString(1,studentid);
                    preparedStatement.setString(2,name);
                    preparedStatement.executeUpdate();
                    System.out.println("SUCCESSFULLY UPDATED THE NAME");

                }catch (Exception e){
                    System.out.println(e);
                }
                break;


            case "roll":
                try{
                    System.out.println("Enter the new roll no for the student");
                    int roll = sc.nextInt();
                    Class.forName("com.mysql.jdbc.Driver");

                    Connection connection = DriverManager.getConnection(url,username,password);

                    String query = "UPDATE student SET student_id=?,roll=?";

                    PreparedStatement preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setString(1,studentid);
                    preparedStatement.setInt(2,roll);
                    preparedStatement.executeUpdate();
                    System.out.println("SUCCESSFULLY UPDATED THE ROLL NO");

                }catch (Exception e){
                    System.out.println(e);
                }
                break;
            case "phone":
                try{
                    System.out.println("Enter the new phone no for the student");
                    String phone = sc.nextLine();
                    Class.forName("com.mysql.jdbc.Driver");

                    Connection connection = DriverManager.getConnection(url,username,password);

                    String query = "UPDATE student SET student_id=?,phone=?";

                    PreparedStatement preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setString(1,studentid);
                    preparedStatement.setString(2,phone);
                    preparedStatement.executeUpdate();
                    System.out.println("SUCCESSFULLY UPDATED THE PHONE NO");

                }catch (Exception e){
                    System.out.println(e);
                }
                break;
            case "stream":
                try{
                    System.out.println("Enter the new stream for the student");
                    String stream = sc.nextLine();
                    Class.forName("com.mysql.jdbc.Driver");

                    Connection connection = DriverManager.getConnection(url,username,password);

                    String query = "UPDATE student SET student_id=?,stream=?";

                    PreparedStatement preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setString(1,studentid);
                    preparedStatement.setString(2,stream);
                    preparedStatement.executeUpdate();
                    System.out.println("SUCCESSFULLY UPDATED THE STREAM");

                }catch (Exception e){
                    System.out.println(e);
                }
                break;
            case "grade":
                try{
                    System.out.println("Enter the new grade for the student");
                    String grade = sc.nextLine();
                    Class.forName("com.mysql.jdbc.Driver");

                    Connection connection = DriverManager.getConnection(url,username,password);

                    String query = "UPDATE student SET student_id=?,grade=?";

                    PreparedStatement preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setString(1,studentid);
                    preparedStatement.setString(2,grade);
                    preparedStatement.executeUpdate();
                    System.out.println("SUCCESSFULLY UPDATED THE GRADE");

                }catch (Exception e){
                    System.out.println(e);
                }
                break;
            default:
                System.out.println("FIELD NOT FOUND IN DATABASE");
        }


    }

    public static void display_all_students(){
        try {

            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);

            String query = "SELECT * FROM student";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
//                String name = resultSet.getString("name");
//                String student_id = resultSet.getString("student_id");
//                String roll = resultSet.getString("roll");
//                String grade = resultSet.getString("grade");
//                String phone = resultSet.getString("phone");
//                String stream = resultSet.getString("stream");

                System.out.println("name"+" "+"student_id"+" "+"roll"+" "+"grade"+" "+"phone"+" "+"stream");
                System.out.println(resultSet.getString(1)+" "+resultSet.getString(2)+" "+resultSet.getInt(3)+" "+resultSet.getString(4)+" "+resultSet.getString(5)+" "+resultSet.getString(6)+"\n");

            }


        }catch (Exception e){
            System.out.println(e);
        }

    }



    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("\nWELCOME TO THE STUDENT_MANAGEMENT_SYSTEM\n");

        System.out.println("please enter 'admin' if you are admin or else please enter 'student' if you are a student");
        String choice1 = sc.nextLine().toLowerCase();
        if(choice1.equals("admin")){
            while (true) {
                System.out.println("Press 1 to create an account");
                System.out.println("Press 2 to log in to an existing account");
                System.out.println("Press 3 to delete your account");
                System.out.println("Press 4 to exit");
                int choice2 = sc.nextInt();
                if (choice2 == 1) {
                    addAdmin();
                } else if (choice2 == 2) {

                        String email = "";
                        String password = "";
                        System.out.println("Enter your email id");
                        sc.nextLine();
                        String email1 = sc.nextLine();
                        System.out.println("Enter your password");
                        String pass1 = sc.nextLine();
                    try {
                        System.out.println("Please enter your admin id");
                        String admin_id = sc.nextLine();
                        Class.forName("com.mysql.jdbc.Driver");
                        Connection connection = DriverManager.getConnection(url, username, password);

                        String query = "SELECT email,password FROM admin WHERE admin_id=?";
                        PreparedStatement preparedStatement = connection.prepareStatement(query);
                        preparedStatement.setString(1, admin_id);

                        ResultSet resultSet = preparedStatement.executeQuery();

                        if (resultSet.next()) {
                            email = resultSet.getString("email");
                            password = resultSet.getString("password");
                        }

                        Boolean decoded_pass = BCrypt.checkpw(pass1, password);

                        if (decoded_pass.equals(true) && email.equals(email1)) {
                            while (true) {

                                System.out.println("Please press '1' to add a student");
                                System.out.println("Please press '2' to remove a student");
                                System.out.println("Please press '3' to search a student");
                                System.out.println("Please press '4' to edit the details of an existing student");
                                System.out.println("Please press '5' to display all students");
                                System.out.println("Please press '6' to exit");


                                int choice = sc.nextInt();

                                switch (choice) {
                                    case 1:

                                        Student newstudent = new Student();

                                        System.out.println("Enter the name of the student");
                                        sc.nextLine();
                                        String name = sc.nextLine();
                                        if (name.isEmpty()) {
                                            System.out.println("Name is required.");
                                            return;
                                        }
                                        newstudent.setName(name);

                                        System.out.println("Enter the student_id of the student");
                                        String student_id = sc.nextLine();
                                        if (student_id.isEmpty()) {
                                            System.out.println("Student ID is required.");
                                            return;
                                        }
                                        newstudent.setStudent_id(student_id);

                                        System.out.println("Enter the roll of the student");
                                        int roll;
                                        while (true) {
                                            try {
                                                roll = sc.nextInt();
                                                sc.nextLine(); // Consume the newline character
                                                break;
                                            } catch (InputMismatchException e) {
                                                System.out.println("Invalid input. Please enter a valid roll.");
                                                sc.nextLine(); // Clear the invalid input from the buffer
                                            }
                                        }
                                        newstudent.setRoll(roll);

                                        System.out.println("Enter the grade of the student");
                                        String grade = sc.nextLine();
                                        if (grade.isEmpty()) {
                                            System.out.println("Grade is required.");
                                            return;
                                        }
                                        newstudent.setGrade(grade);

                                        System.out.println("Enter the phone no of the student");
                                        String phone = sc.nextLine();
                                        if (phone.isEmpty()) {
                                            System.out.println("Phone number is required.");
                                            return;
                                        }
                                        newstudent.setPhone(phone);

                                        System.out.println("Enter the stream of the student");
                                        String stream = sc.nextLine();
                                        if (stream.isEmpty()) {
                                            System.out.println("Stream is required.");
                                            return;
                                        }
                                        newstudent.setStream(stream);

                                        addStudent(newstudent);

                                        System.out.println("Added new student");
                                        break;
                                    case 2:
                                        System.out.println("Enter the student id of the student you want to delete");
                                        sc.nextLine();
                                        String id = sc.nextLine();
                                        System.out.println("Are you sure you want to delete. This can't be undone");
                                        System.out.println("Press yes to confirm this operation");
                                        System.out.println("Press no to cancel this operation");
                                        String del = sc.nextLine().toLowerCase();
                                        if(del.equals("yes")){
                                            delStudent(id);
                                        }else{
                                            System.out.println("Operation Cancelled");
                                        }
                                        break;
                                    case 3:
                                        System.out.println("Enter the student id of the student you want to search");
                                        sc.nextLine();
                                        String stid = sc.nextLine();
                                        searchStudent(stid);
                                        break;

                                    case 4:
                                        System.out.println("Enter the student id of the student you want to edit");
                                        sc.nextLine();
                                        String std = sc.nextLine();
                                        editStudent(std);
                                        break;

                                    case 5:
                                        display_all_students();
                                        break;

                                    case 6:
                                        System.out.println("thank you for using");
                                        System.exit(0);
                                        break;
                                }

                            }
                        } else {
                            System.out.println("email or password  is incorrect");
                        }

                    } catch (Exception e) {
                        System.out.println("Admin id is incorrect");
                    }

                } else if (choice2 == 3) {
                    delAdmin();

                }
                else if (choice2 == 4){
                    System.out.println("Thank you for visiting");
                    System.exit(0);
                }
            }
        }
        else if (choice1.equals("student")) {
            while (true) {
                System.out.println("\nPress 1 to display all the students");
                System.out.println("Press 2 to display a specific student");
                System.out.println("press 3 to exit");
                int choice = sc.nextInt();
                switch (choice) {
                    case 1:
                        display_all_students();
                        break;
                    case 2:
                        System.out.println("Enter the student id of the student you want to search");
                        sc.nextLine();
                        String stid = sc.nextLine();
                        searchStudent(stid);
                        break;
                    case 3:
                        System.out.println("Thank you for visiting");
                        System.exit(0);

                }
            }

        }else {
            System.out.println("YOU ARE NOT ELIGIBLE TO ACCESS THIS DATABASE");
        }


    }
}
