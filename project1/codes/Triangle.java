import java.util.Scanner;

public class Triangle {
    public static void main(String[] args) {
        Point A, B, C;
        A = new Point();
        B = new Point();
        C = new Point();
        System.out.println("Input the cordinates of three points:");
        Scanner input = new Scanner(System.in);        
        A.Input(input);
        B.Input(input);
        C.Input(input);
        Point AB = B.Minus(A);
        Point AC = C.Minus(A);
        Point BC = C.Minus(B);
        double Area = 0.5 * Math.abs(AB.Cross(AC));
        double a = BC.length(), b = AC.length(), c = AB.length();
        double p = (a + b + c) / 2;
        double Area2 = Math.sqrt(p * (p - a) * (p - b) * (p - c));
        System.out.println("The area of the triangle is " + Area + " " + Area2);
    }
}
class Point {
    double x, y;
    Point() {
        x = y = 0;
    }
    Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
    public void Input(Scanner input) {
        this.x = input.nextDouble();
        this.y = input.nextDouble();
    }
    public void Print(String format) {
        System.out.printf("(%f, %f)"+format, x, y);
    }
    public void Print() {
        System.out.printf("(%f, %f)", x, y);
    }
    public Point Minus(Point other) {
        return new Point(this.x - other.x, this.y - other.y);
    }
    public Point Plus(Point other) {
        return new Point(this.x + other.x, this.y + other.y);
    }
    public Point Dot(double k) {
        return new Point(this.x * k, this.y * k);
    }
    public double Cross(Point other) {
        return this.y * other.x - this.x * other.y;
    }
    public double length() {
        return Math.sqrt(this.x * this.x + this.y * this.y);
    }
}