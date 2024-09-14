import java.util.Scanner;  
class Account implements Runnable{  
public int x;  
public String acno;  
public String name;  
public double balance;  
Thread t;  
String Threadname;  
Account(String tn)  
{  
Threadname=tn;  
t=new Thread(this,Threadname);  
System.out.println(t);  
}  
void getaccount()  
{  
Scanner sc=new Scanner(System.in);  
System.out.print("\nEnter the Account number : ");
 acno=sc.next();  
System.out.print("Enter the Name of account holder : "); 
name=sc.next();  
System.out.print("Enter the Balance : ");  
balance=sc.nextDouble();  
}  
void putaccount()  
{  
System.out.println("\nAccount number : " + acno);
 System.out.println("Name : " + name); 
 System.out.println("Balance : " + balance + "/-");
 }  
public synchronized void deposit(double dp)throws 
InterruptedException  
{  
balance+=dp; 
System.out.println(dp + "Successfully deposited in " +  
t.getName()); System.out.println("Available balance in" + t.getName() + ":" +  balance); Thread.sleep(1000);  
}  
public synchronized void withdrawl(double wd)throws  
InterruptedException  
{  
balance-=wd;  
System.out.println(wd + "Successfully withdrawn from " +  
t.getName()); System.out.println("Available balance in " + t.getName() + ":" +  balance); Thread.sleep(1000);  
}  
public void run()  
{  
try{  
synchronized(this){  
deposit(10000.00);  
withdrawl(2000.00);  
}  
}catch(InterruptedException e)  
{  
System.out.println(e);  
}  
}  
public static void main(String args[])  
{  
Scanner s=new Scanner(System.in);  
System.out.print("\nEnter the number of Accounts : "); 
int n=s.nextInt();  
Account a[]=new Account[n];  
for(int i=0;i<n;i++) 
{  
a[i]=new Account("Account" + (i+1));  
}  
for(int i=0;i<n;i++)  
{  
System.out.println("\nEnter details of Account " + (i+1)); 
a[i].getaccount();  
}  
for(int i=0;i<n;i++)  
{  
a[i].t.start();  
}  
for(int i=0;i<n;i++)  
{  
try{  
a[i].t.join();  
}catch(InterruptedException e)  
{  
System.out.println(e);  
}  
}  
for(int i=0;i<n;i++)  
{  
a[i].putaccount();  
}  
}  
} 
