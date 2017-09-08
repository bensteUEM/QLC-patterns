package stein.team.qlc.helper;

public class Helper {

    /**
     * recursive GCD calculation from https://stackoverflow.com/questions/4009198/java-get-greatest-common-divisor#4009247
     * @param a first number
     * @param b second number
     * @return greatest common divisor of A and B
     */
    public static int gcd(int a, int b) {
        if (b==0) return a;
        return gcd(b,a%b);
    }

    /**
     * https://stackoverflow.com/questions/4201860/how-to-find-gcd-lcm-on-a-set-of-numbers#4202114
     * @param a first number
     * @param b second number
     * @return least common multiply of a and b
     */
    public static int lcm(int a, int b)
    {
        return a * (b / gcd(a, b));
    }

}
