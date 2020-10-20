package rsa;

public class RSAMath {

    /** Checks if a number is prime iteratively, there is probably a more efficient approach </br>
     * Time complexity O(x/2) */
    public static boolean isPrime(int x) {
        for (int i= 2; i < x / 2; i++ ) {
            if (x % i == 0) { return false; }
        }
        return true;
    }

    /** Encrypts using fast exponentiation and a public key */
    public static long encrypt(long m, int publicE, int publicN) {
        return fastExpo(m, publicE, publicN);
    }

    /** Decodes using private key */
    public static long decode(long c, int privateD, int publicN) {
        return fastExpo(c, privateD, publicN);
    }

    /** Computes the private key of totient function using modInverse() */
    public static int computeD(int p, int q, int e) {
        int phi= (p - 1) * (q - 1);
        return modInverse(e, phi);
    }

    /** Calculates modular inverse of euler totient function iteratively, which is slow </br>
     * Time complexity O(phi) */
    public static int modInverse(int e, int phi) {
        e= e % phi;
        for (int d= 1; d < phi; d++ )
            if (e * d % phi == 1)
                return d;
        return -1;
    }

    /** Uses binary exponentiation to deal with overflow </br>
     * Time complexity O((logn)^2 * log(n)) */
    public static long fastExpo(long x, int y, int n) {
        // Initialize result
        long res= 1;

        // Update x if it is more
        // than or equal to n
        x= x % n;

        if (x == 0) return 0; // In case x is divisible by p;

        while (y > 0) {
            // If y is odd, multiply x
            // with result
            if ((y & 1) == 1)
                res= res * x % n;

            // y must be even now
            // y = y / 2 using binary shift
            y= y >> 1;
            x= x * x % n;
        }
        return res;
    }
}
