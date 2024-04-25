import java.util.Scanner;

public class ATMMachine {
    double balance = 0;
    int PIN = 8874;
    Scanner sc = new Scanner(System.in);

    void checkPin() { //it can be replaced by constructor(default constructor)
        System.out.print("Enter your PIN :");
        int inputPin = sc.nextInt();
        if (inputPin == PIN) {
            menu();
        } else {
            System.out.print("Pin is incorrect. Try Again.\n");
            checkPin();
        }
    }

    void menu() {
        System.out.println("Choose an option :-");
        System.out.println("1.Check Balance");
        System.out.println("2.Deposit Balance");
        System.out.println("3.Withdrew Balance");
        System.out.println("4.Exit");
        System.out.print("Enter your choice:");
        int choice = sc.nextInt();
        switch (choice) {
            case 1: {
                checkBalance();
                break;
            }
            case 2: {
                depositMoney();
                break;
            }
            case 3: {
                WithdrewMoney();
            }
            case 4:
                return;
            default: {
                System.out.print("Enter a Valid option\n");
                menu();
            }
        }

    }
    void checkBalance(){
        System.out.print("Acount Balance :"+balance+"\n");
        System.out.println("---------------------------------------------");
        menu();
    }
    void depositMoney(){
        System.out.print("Enter amount to Deposite :");
        double depositAmount=sc.nextDouble();
        balance+=depositAmount;
        System.out.println("Amount Credited successfully");
        checkBalance();
    }
    void WithdrewMoney(){
        System.out.print("Enter amount to Withdrew :");
        double WithdrewAmount=sc.nextDouble();
        if(balance>=WithdrewAmount){
            balance-=WithdrewAmount;
            System.out.println("Amount Debited successfully");

        }
        else{
            System.out.println("Not sufficient fund");
        }
        checkBalance();
    }

    public static void main(String[] args) {
        ATMMachine user=new ATMMachine();
        user.checkPin();
    }
}
