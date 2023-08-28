import java.util.*;
import java.lang.Thread;

class Person {
    private String name;
    private int age;
    private int balance;
    private int ATMPIN;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getBalance() {
        return balance;
    }

    public void setATMPIN(int pin){
        this.ATMPIN = pin;
    }

    public int getATMPIN(){
        return ATMPIN;
    }
}

public class ATM {

    public static void deposit(Person person, int value) {
        int curr_val = person.getBalance();
        int new_val = curr_val + value;
        person.setBalance(new_val);
        System.out.println("\n"+value + " added to you account. The total balance is:- " + person.getBalance()+"\n");
    }

    public static void withdraw(Person person, int value) {
        int curr_val = person.getBalance();
        if (value > curr_val) {
            System.out.println("\nNot sufficient funds\n");
        } else {
            int new_val = curr_val - value;
            person.setBalance(new_val);
            System.out.println("\n"+value + " withdrawn from your acoount. The total balance is:- " + person.getBalance()+"\n");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        
        Scanner sc = new Scanner(System.in);
        Person p1 = new Person();
        System.out.println("Enter your name:- ");
        String name = sc.nextLine();
        p1.setName(name);
        System.out.println("Enter your age:- ");
        int age = sc.nextInt();
        p1.setAge(age);
        System.out.println("Set your ATM PIN");
        int pin = sc.nextInt();
        p1.setATMPIN(pin);
        System.out.println("\nWELCOME TO THE ATM\n"); 
        System.out.println("Welcome "+p1.getName()+"\n");       
        System.out.println("Please enter your ATM PIN "+p1.getName()+"\n");
        int Entered_pin = sc.nextInt();
        if(Entered_pin == p1.getATMPIN()){
            while (true) {
            System.out.println("Press 1 to show your balance");
            System.out.println("Press 2 to withdraw your money");
            System.out.println("Press 3 to deposit your money");
            System.out.println("Press 4 for fast cash");
            System.out.println("Press 5 to cancel your transaction");
            System.out.println("\n");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("\nYour balance is:- " + p1.getBalance()+"\n");
                    break;
                case 2:
                    System.out.println("Enter the amount you want to withdraw:- ");
                    int withdraw = sc.nextInt();
                    withdraw(p1,withdraw);
                    break;
                case 3:
                    System.out.println("Enter the amount you want to deposit:- ");
                    int deposit = sc.nextInt();
                    deposit(p1,deposit);
                    break;
                case 4:
                    System.out.println("\nEnter the fast cash amount\n");
                    System.out.println("100       500 ");
                    System.out.println("1000      2000");
                    System.out.println("Press 1 to withdraw 100rs");
                    System.out.println("Press 2 to withdraw 500rs");
                    System.out.println("Press 3 to withdraw 1000rs");
                    System.out.println("Press 4 to withdraw 2000rs");
                    int fastcash = sc.nextInt();
                    switch (fastcash) {
                        case 1:
                            withdraw(p1,100);
                            
                            break;
                        case 2:
                            withdraw(p1,500);
                            
                            break;
                        case 3:
                            withdraw(p1,1000);
                        
                            break;
                        case 4:
                            withdraw(p1,2000);
                    
                            break;

                    }
                    break;
                case 5:
                    System.out.println("Transaction Cancelled");
                    Thread.sleep(500);
                    System.out.println("Thank you for banking with us. Please visit again");
                    System.exit(0);

            }
        }

        }else{
            System.out.println("Wrong ATM PIN. Please try again");
            System.exit(0);
        }
        

    }
}
