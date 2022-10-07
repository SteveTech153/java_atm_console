import java.util.*;

public  class ATM {
    static Scanner S = new Scanner(System.in);
    static ArrayList<String> names = new ArrayList<>();
    static ArrayList<Integer> accNo = new ArrayList<>();
    static ArrayList<Integer> pin = new ArrayList<>();
    static Long ATMBalance=0L;
    static ArrayList<Double> balance  = new ArrayList<>();
    static ArrayList<String> ATMDenomination = new ArrayList<>();
    static LinkedHashMap<Integer, String> denominations = new LinkedHashMap<>();
    static LinkedHashMap<Integer,Integer> indexOfAcc = new LinkedHashMap<>();

    public void newCustomer(){
        System.out.println("Enter Name : ");
        names.add(S.next());
        System.out.println("Enter Account number : ");
        int acc = S.nextInt();
        accNo.add(acc);
        System.out.println("set pin : ");
        pin.add(S.nextInt());
        balance.add(0.0);
        indexOfAcc.put(acc,names.size()-1);
    }
    public void loadCash(){
            System.out.println("Enter number of denominations : ");
            int ind = S.nextInt();
            int den,num,val;
            long sum=0;
            String tmp="";
            for (int i1=0;i1<ind;i1++){
                tmp="";
                den=S.nextInt();
                num=S.nextInt();
                val=S.nextInt();
                tmp+=den;
                tmp+="\t\t\t   ";
                tmp+=num;
                tmp+="\t ";
                tmp+=val;
                ATMDenomination.add(tmp);
                sum+= val;
            }
            ATMBalance += sum;
            System.out.println("Money Added!");
    }
    public void checkAtmBalance(){
        System.out.println("ATM Balance : "+ATMBalance);
    }
    public void showDetails(){
        System.out.print("Acc No\t");
        System.out.print("Account holder\t");
        System.out.print("Pin number\t");
        System.out.print("Account Balance\n");
        for(int i1=0;i1<names.size();i1++){
            System.out.print(accNo.get(i1)+"\t\t");
            System.out.print(names.get(i1)+"\t\t\t");
            System.out.print(pin.get(i1)+"\t\t\t");
            System.out.print(balance.get(i1)+"\n");
        }
        System.out.println("");
    }
    public void withdraw(int acc){
        System.out.println("Enter pin : ");
        int pn = S.nextInt();
        if(pin.get(indexOfAcc.get(acc))==pn) {
            System.out.println("Enter amount to withdraw : ");
            int amt = S.nextInt();
            int amtcpy = amt;
            if (amt < 100 || amt > 10000)
                System.out.println("Amt should be lesser than 10000 and greater than 100 ");
            else {
                if(amt>balance.get(indexOfAcc.get(acc))){
                    System.out.println("Insufficient Balance in the account !");
                }else {
                    System.out.println("Collect Cash : ");
                    if(amt<5000){
                        int tmpsum=0;
                        if(amt<1000){
                            if(amt>=500){
                                System.out.println("500 X "+amt/500);
                                amt=amt%500;
                                if(amt>=100){
                                    System.out.println("100 X "+amt/100);
                                    amt=amt%100;
                                }

                            }
                        }
                        else if(amt<=1500){
                            System.out.println("500 X "+amt/500);
                            amt=amt%500;
                            if(amt>=100){
                            System.out.println("100 X "+amt/100);
                            amt=amt%100;}

                        }else if(amt <=3000){
                            System.out.println("100 X "+( 10+(((amt-1000)%500)/100)));
                            int amt1 = amt;
                            amt1= amt1-1000;
                            amt1=((amt1-1000)%500)%100;
                            System.out.println("500 X "+ ((amt-1000)/500));

                        }else {
                            System.out.println("2000 X "+amt/2000);
                            amt=amt%2000;
                            if(amt>=500){
                                System.out.println(amt/500);
                                amt=amt%500;
                            }
                            if(amt>=100){
                                System.out.println(amt/100);
                                amt=amt%100;
                            }
                        }

                    }else{
                        System.out.println(amt/2000);
                        amt=amt%2000;
                        if(amt>=500){
                            System.out.println(amt/500);
                            amt=amt%500;
                        }
                        if(amt>=100){
                            System.out.println(amt/100);
                            amt=amt%100;
                        }
                    }
                    String tmpstr = Integer.toString(amtcpy).substring(0,Integer.toString(amtcpy).length()-2);
                    if(tmpstr.length()>2) {
                        int tmp = Integer.parseInt(tmpstr) * 100;
                        ATMBalance -= tmp;
                        balance.add(indexOfAcc.get(acc),balance.get(indexOfAcc.get(acc))-tmp);

                    }

                }
            }
        }else
            System.out.println("Pin invalid !");

    }
    public void transferMoney(int senderAcc){
        System.out.println("Enter account number to send");
        int acc= S.nextInt();
        if(accNo.contains(acc)){
            System.out.println("Enter amount : ");
            int amt = S.nextInt();
            if(amt>10000||amt<1000)
                System.out.println("Amt should be greater than 1000 and lesser than 10000");
            else {
                if(amt<=ATMBalance) {
                    if (amt <= balance.get(indexOfAcc.get(senderAcc))) {
                        balance.add(indexOfAcc.get(senderAcc), balance.get(indexOfAcc.get(senderAcc)) - amt);
                        balance.add(indexOfAcc.get(acc), balance.get(indexOfAcc.get(acc)) + amt);
                        System.out.println("Amount transferred successfully !");
                    } else
                        System.out.println("Insufficient balance in account !");
                }else
                    System.out.println("Insufficient Balance in ATM");

            }
        }else
            System.out.println("Account number doesn't exists !");
    }
    public void deposit(int acc){
        System.out.println("Enter amt to deposit : ");
        int amt = S.nextInt();
        balance.add(indexOfAcc.get(acc),balance.get(indexOfAcc.get(acc))+Math.abs(amt));
        ATMBalance+=amt;
        System.out.println("Amount successfully deposited !");
    }
    public void existingCustomer(){
        System.out.println("Enter acc no : ");
        int acc = S.nextInt();
        int ch=0;
        if(accNo.contains(acc)){
            System.out.println("Enter Pin ");
            int pn = S.nextInt();
            if(pin.get(indexOfAcc.get(acc))==pn){
                while(ch!=6){
                    System.out.println("1 -> Check Balance");
                    System.out.println("2 -> withdraw money");
                    System.out.println("3 -> Transfer money");
                    System.out.println("4 -> Check ATM balance");
                    System.out.println("5 -> Deposit");
                    System.out.println("6 -> Exit");
                    ch=S.nextInt();
                    switch (ch) {
                        case 1:
                            System.out.println("Balance : "+balance.get(indexOfAcc.get(acc)));
                            break;
                        case 2:
                            withdraw(acc);
                            break;
                        case 3:
                            transferMoney(acc);
                            break;
                        case 4:
                            checkAtmBalance();
                            break;
                        case 5:
                            deposit(acc);
                            break;
                        case 6:
                            break;
                        default:
                            System.out.println("Enter valid choice\n");
                    }
                }
            }else
                System.out.println("Pin Invalid !");
        }else
            System.out.println("Account number doesn't exist");


    }
    public void showOperations(){
        int ch;
        System.out.println("1 -> Existing customer");
        System.out.println("2 -> New customer");
        ch = S.nextInt();
        switch (ch) {
            case 1:
                existingCustomer();
                break;
            case 2:
                newCustomer();
                break;
        }
    }

    public void exit(){
        System.out.println("Thank you come again !");
    }
    public static void main(String[] args) {
        ATM atm = new ATM();
        char ch='0';
        while (ch!='4'){
            System.out.println("1 -> Load cash to ATM");
            System.out.println("2 -> Show customer details");
            System.out.println("3 -> Show ATM operations");
            System.out.println("4 -> Exit");
            ch=S.next().charAt(0);
            switch (ch) {
                case '1':
                    atm.loadCash();
                    break;
                case '2':
                    atm.showDetails();
                    break;
                case '3':
                    atm.showOperations();
                    break;
                case '4':
                    atm.exit();
                    break;
                default:
                    System.out.println("Enter valid choice ");
            }
        }

    }
}