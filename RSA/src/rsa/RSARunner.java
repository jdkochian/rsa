package rsa;

import java.util.Scanner;

public class RSARunner {

    public static void main(String[] args) {
        Scanner scan= new Scanner(System.in);
        boolean pPrime= false;
        boolean qPrime= false;
        int q= -1;
        int p= 2;
        long m, c, next;
        int n= -1, d= -1, e= -1;
        System.out.println("Encode or decode? (1 to encode, 2 to decode): ");
        next= scan.nextInt();

        // Encoding a message:
        if (next == 1) {
            while (n == -1) {
                System.out.println("Enter n: ");
                n= scan.nextInt();
            }

            while (e == -1) {
                System.out.print("Choose  e: ");
                e= scan.nextInt();
                d= RSAMath.computeD(p, q, e);
            }

            System.out.print("Enter your message to encode: ");
            m= scan.nextInt();
            c= RSAMath.encrypt(m, e, n);
            System.out.print("Here is the encoded message: " + c);

        }

        // Decoding a message:
        else if (next == 2) {

            while (!pPrime && !qPrime) {
                System.out.print("Choose a prime p and q: ");
                q= scan.nextInt();
                p= scan.nextInt();
                n= p * q;

                if (!RSAMath.isPrime(p) || !RSAMath.isPrime(q))
                    System.out.print("Error. A value was not" +
                        " prime. Choose again: ");
                else if (RSAMath.isPrime(p) && RSAMath.isPrime(q)) {
                    pPrime= true;
                    qPrime= true;
                }
            }

            while (d == -1) {
                System.out.print("Choose  e: ");
                e= scan.nextInt();
                d= RSAMath.computeD(p, q, e);

                if (d == -1) {
                    System.out.println("Error");
                } else {
                    System.out.println("Shhh the private key is: " + d);
                }

            }

            System.out.print("Enter a message to decode: ");
            c= scan.nextInt();
            m= RSAMath.decode(c, d, n);
            System.out.print("Here is the decoded message: " + m);
        }

        scan.close();
    }
}