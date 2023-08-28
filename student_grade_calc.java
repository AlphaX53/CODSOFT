import java.util.*;

public class student_grade_calc {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("\nSTUDENTS GRADE CALCULATOR\n");
        System.out.println("Enter the total no of subjects");
        int total_marks = 0;
        int total_percentage = 0;
        int total_sub = sc.nextInt();
        String[] subjects = new String[total_sub];
        int[] marks = new int[total_sub];
        for(int i=0;i<total_sub;i++){
            System.out.println("Enter subject "+(i+1)+" :");
            subjects[i] = sc.next();
            
        }

        for (int i=0;i<total_sub;i++) {
            System.out.println("Enter marks of:- "+subjects[i]);
            marks[i] = sc.nextInt();
        }

        for(int i=0;i<total_sub;i++){
            System.out.println("The marks of "+subjects[i]+" is:- "+marks[i]);
        }
        for(int i=0;i<total_sub;i++){
            total_marks+=marks[i];
        }
        total_percentage = total_marks/total_sub;
        System.out.println("total marks of all subjects are:- "+total_marks);
        System.out.println("The average percentage is:- "+total_percentage);
        if(total_percentage>90){
            System.out.println("you have got grade:- 'A+'");
        }
        else if(total_percentage>80 && total_percentage<90){
            System.out.println("you have got grade:- 'A'");
        }
        else if(total_percentage>70 && total_percentage<80){
            System.out.println("you have got grade:- 'B'");
        }
        else if(total_percentage>60 && total_percentage<70){
            System.out.println("you have got grade:- 'C'");
        }
        else if(total_percentage>50 && total_percentage<60){
            System.out.println("you have got grade:- 'D'");
        }
        else if(total_percentage>40 && total_percentage<50){
            System.out.println("you have got grade:- 'E'");
        }
        else{
            System.out.println("you got grade 'F' and you have failed");
        }
        sc.close();

    }   
}
