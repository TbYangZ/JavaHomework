import java.util.Scanner;

public class Invest {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        double totInvest, annualRate, years, future, monthlyRate;
        System.out.println("Please input the total investment, annual intrest rate (%) and years");
        totInvest = input.nextDouble();
        annualRate = input.nextDouble() / 100;
        years = input.nextDouble();
        monthlyRate = annualRate / 12;
        future = totInvest * Math.pow(1 + monthlyRate, years * 12);
        System.out.println("The future investment return amount is " + future);
        input.close();
    }
}
