import java.io.*;
import java.util.*;

class Account{
    //represents account number
    static int account_number=101;
    //string storing account holder name
    String account_holder_name;
//   pin
    int pin;
//    to store balance
    double balance;
//    string that stores unique user id
    String unique_id;
//    integer storing account number
    int acc_number;

    void createAccount(){
        
        acc_number=account_number;
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter account holder name:");
        account_holder_name=sc.nextLine();

        System.out.println("Enter Username:");
        unique_id=sc.nextLine();

        int length_pin=0;
            do{
                System.out.println("Enter a 4 digit pin:");
                pin= sc.nextInt();
                length_pin=String.valueOf(pin).length();
            }while(length_pin!=4);

        System.out.println("Enter initial deposit amount");
        balance=sc.nextDouble();

        System.out.println("Congratulations! Account has successfully been created!");
        System.out.println("Account details are as follows:");
        System.out.println("Account Number: "+ acc_number);
        System.out.println("Account Holder Name: "+ account_holder_name);
        System.out.println("Balance: " + balance );
        System.out.println("Thank you!");

//        creating a filw with the account number
        String filename= account_number + ".txt";
        File file = new File(filename);
        try{
            file.createNewFile();
            FileWriter writer= new FileWriter(file);
            writer.write("Account Created\n");
            writer.write("Account Number: " + account_number + "\n");
            writer.write("User ID: " + unique_id + "\n");
            writer.write("Account Holder Name: " + account_holder_name + "\n");
            writer.write("Pin: " + pin + "\n");
            writer.write("Balance: " + balance + "\n");
            writer.write("Date: " + new Date() + "\n\n");
            writer.close();

        } catch (IOException e) {
            System.out.println("Error while creating the file "+ filename);
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        try{
            Thread.sleep(5000);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        account_number++;
    }
}

class ATM {
    void withdraw(Account acc) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter money to be withdrawed in multiples of 100");
        double amount = sc.nextDouble();
        if (amount % 100 == 0) {
            if (acc.balance >= amount) {
                acc.balance -= amount;
                System.out.println("Your transaction is processing");
                try {
                    String filename = acc.acc_number + ".txt";
                    FileWriter filewriter = new FileWriter(filename, true);
                    BufferedWriter bufferedwriter = new BufferedWriter(filewriter);
                    bufferedwriter.write("Date: " + new Date() + "\n");
                    bufferedwriter.write("Withdrawal: " + amount + "\n");
                    bufferedwriter.write("Account Number: " + acc.acc_number + "\n");
                    bufferedwriter.write("Remaining Balance: " + acc.balance + "\n");
                    bufferedwriter.close();
                    filewriter.close();
                } catch (IOException e) {
                    System.out.println("Error while writing the file");
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
                System.out.println("Thank you for banking with us!");
                try {
                    Thread.sleep(6000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("Insufficient funds!");
            }
        } else {
            System.out.println("Amount not in multiple of 100!");
            System.out.println("Try Again!");
        }
    }

    void deposit_by_transfer(Account acc, double amount) {
        acc.balance+=amount;
        try{
            String filename= acc.acc_number+ ".txt";
            FileWriter filewriter = new FileWriter(filename, true);
            BufferedWriter bufferedwriter = new BufferedWriter(filewriter);
            bufferedwriter.write("Deposit: " + amount + "\n");
            bufferedwriter.write("Date: " + new Date() + "\n");
            bufferedwriter.write("Account Number: " + acc.acc_number + "\n");
            bufferedwriter.write("Remaining Balance: " + acc.balance + "\n");
            bufferedwriter.close();
            filewriter.close();
        } catch (IOException e) {
            System.out.println("Error while writing the file");
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    void deposit(Account acc){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter amount you want to deposit!");
        double amount= sc.nextDouble();
        acc.balance+=amount;
        try{
            String filename= acc.acc_number+ ".txt";
            FileWriter filewriter = new FileWriter(filename, true);
            BufferedWriter bufferedwriter = new BufferedWriter(filewriter);
            bufferedwriter.write("Deposit: " + amount + "\n");
            bufferedwriter.write("Date: " + new Date() + "\n");
            bufferedwriter.write("Account Number: " + acc.acc_number + "\n");
            bufferedwriter.write("Remaining Balance: " + acc.balance + "\n");
            bufferedwriter.close();
            filewriter.close();
        } catch (IOException e) {
            System.out.println("Error while writing the file");
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    void Transfer(Account acc1, Account acc2, double amount){
        if(acc1.balance>=amount){
            acc1.balance-=amount;
            ATM a = new ATM();
            a.deposit_by_transfer(acc2,amount);
            try{
                String filename= acc1.acc_number+ ".txt";
                FileWriter filewriter = new FileWriter(filename, true);
                BufferedWriter bufferedwriter = new BufferedWriter(filewriter);
                bufferedwriter.write("Transferred: " + amount + "\n");
                bufferedwriter.write("Date: " + new Date() + "\n");
                bufferedwriter.write("Account Number: " + acc1.acc_number + "\n");
                bufferedwriter.write("Remaining Balance: " + acc1.balance + "\n");
                bufferedwriter.close();
                filewriter.close();
            } catch (IOException e) {
                System.out.println("Error while writing the file");
                e.printStackTrace();
                throw new RuntimeException(e);
            }
            System.out.println("Processing your request, please wait!\n");
            try{
                Thread.sleep(5000);
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
            }
        }

        void display_details(Account acc){
            System.out.println("Displaying account details");
            try{
                Thread.sleep(2000);
            }catch(InterruptedException e){
                  e.printStackTrace();
            }
            String file_name= String.valueOf(acc.acc_number)+ ".txt";
            File file = new File(file_name);
            try{
                FileReader filereader= new FileReader(file);
                BufferedReader bufferedreader= new BufferedReader(filereader);
                String line= null;
                while((line=bufferedreader.readLine()) != null){
                    System.out.println(line);
                }
                bufferedreader.close();
            }catch(FileNotFoundException e){
                System.out.println("File not found: "+ e.getMessage());
            }catch(IOException e){
                System.out.println("Error reading file: "+ e.getMessage());
            }
            System.out.println("Screen will automatically timeout after 30 seconds");
            try{
                Thread.sleep(30000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
    }

    void quit(){
        System.out.println("Thank you very much for banking with us!\n");
        System.out.println("Hoping to see you again☺");
        return;
    }
}

class run_atm{
    int account_search_by_unique_id(Account[] ac, String unique_id){
        for(int i=0;i<5;i++){
            if(Objects.equals(unique_id,ac[i].unique_id)){
                return i;
            }
        }
        return -1;
    }

    int account_search_by_unique_id(Account[] ac, int account_number){
        for(int i=0;i<5;i++){
            if(Objects.equals(account_number,ac[i].acc_number)){
                return i;
            }
        }
        return -1;
    }

    void demo(Account[] ac ){
        Scanner sc = new Scanner(System.in);
        System.out.println("\n\n WELCOME TO ATM! \n");
        System.out.println("Enter your Card/Unique ID");
        String unique_id= sc.nextLine();
        int i= account_search_by_unique_id(ac,unique_id);
        if(i==-1){
            System.out.println("Account not registered yet!");
            try{
                Thread.sleep(3000);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
            return;
        }else{
            System.out.println("Enter your pin:");
            int pin= sc.nextInt();
            if(pin==ac[i].pin){
                System.out.println("Select the options as given below\n Withdraw?----1\nDeposit?----2\nAccount Transfer?----3\nDisplay Account Details?----4\nQuit?----5\n");
                int ch;
                ATM atm= new ATM();
                ch= sc.nextInt();
                switch (ch){
                    case 1->atm.withdraw(ac[i]);
                    case 2->atm.deposit(ac[i]);
                    case 3 -> {
                        System.out.println("Enter account number to transfer funds!");
                        int account_transfer= sc.nextInt();
                        int j = account_search_by_unique_id(ac, account_transfer);
                        if(j==-1){
                            System.out.println("Account not registred yet");
                            return;
                        }else{
                            System.out.println("Enter amount for transferring funds");
                            double amount= sc.nextDouble();
                            atm.Transfer(ac[i],ac[j],amount);
                        }
                    }
                    case 4-> atm.display_details(ac[i]);
                    case 5-> atm.quit();
                }
            }else{
                System.out.println("Wrong pin entered!");
                try{
                    Thread.sleep(3000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
                return;
            }
        }
    }
}

public class ATM_Interface {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Account[] ac = new Account[5];
        for(int i=0;i<5;i++){
            System.out.println("Creating Account");
            ac[i]=new Account();
            ac[i].createAccount();
        }
        run_atm ob = new run_atm();
        for(; ; ){
            ob.demo(ac);
        }

    }
}
