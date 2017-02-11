package math.algorithm_1_2;

/**
 * @author lifeng
 * @version 2.0 on 17/1/3.
 */
public class Rational implements Comparable<Rational> {
    private static Rational zero = new Rational(0, 1);
    private long numerator;
    private long denominator;

    /**
     * @param numerator   分子
     * @param denominator 分母
     */
    public Rational(long numerator, long denominator) {
        if (denominator == 0) {
            throw new ArithmeticException("分母不能为0");
        }

        long gcd = Math.abs(gcd(numerator, denominator));
        numerator /= gcd;
        denominator /= gcd;
        //保持分母为正数
        if (denominator < 0) {
            numerator = -numerator;
            denominator = -denominator;
        }
        //分子和分母约分
        this.numerator = numerator;
        this.denominator = denominator;
    }

    /**
     * 加法运算
     *
     * @param that 相加的对象
     * @return
     */
    public Rational plus(Rational that) {
        if (that.compareTo(zero) == 0) return this;

        //找到两个数的分子的最大公约数
        long numGcd = gcd(this.numerator, that.getNumerator());
        long denGcd = gcd(this.denominator, that.getDenominator());

        checkTimesOverFlow(this.numerator / numGcd,
                that.getDenominator() / denGcd);
        checkTimesOverFlow(that.getNumerator() / numGcd,
                this.getDenominator() / denGcd);
        checkPlusOverFlow(this.numerator / numGcd * (that.getDenominator() / denGcd),
                that.getNumerator() / numGcd * (this.getDenominator() / denGcd));
        checkTimesOverFlow(this.denominator,
                (that.getDenominator() / denGcd));
        //两个分子提取公因子,再做加的计算,不容易溢出,但是这个因子需要后续乘上
        long deSum = this.denominator * (that.getDenominator() / denGcd);
        //分子和分母约分
        long numSum = this.numerator / numGcd *
                (that.getDenominator() / denGcd)
                + that.getNumerator() / numGcd *
                (this.getDenominator() / denGcd);

        return new Rational(numSum * numGcd, deSum);
    }

    /**
     * 减法运算
     *
     * @param that 相减的对象
     * @return
     */
    public Rational mines(Rational that) {
        that.setNumerator(-that.getNumerator());
        return this.plus(that);

    }

    /**
     * 乘法运算
     *
     * @param that 相乘的对象
     * @return
     */
    public Rational times(Rational that) {
        if (that.compareTo(zero) == 0) return zero;

        checkPlusOverFlow(this.numerator, that.getNumerator());
        long num = this.numerator * that.getNumerator();

        checkPlusOverFlow(this.denominator, that.getDenominator());
        long den = this.denominator * that.getDenominator();
        return new Rational(num, den);
    }

    public Rational rollback(Rational rational) {
        return new Rational(rational.getDenominator(), rational.getNumerator());
    }

    /**
     * 除法运算
     *
     * @param that 相除的对象
     * @return
     */
    public Rational divides(Rational that) {

        return this.times(rollback(that));
    }


    public static long gcd(long a, long b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }

    /**
     * 使用十字相乘法进行比较
     *
     * @param that 被比较的数
     * @return
     */
    @Override
    public int compareTo(Rational that) {
        long left = this.numerator * that.getDenominator();
        long right = this.denominator * that.getNumerator();
        if (left > right) return 1;
        if (left < right) return -1;

        return 0;
    }

    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (this.getClass() != obj.getClass()) return false;
        Rational that = (Rational) obj;
        return this.compareTo(that) == 0;

    }

    /**
     * 获取分母
     *
     * @return denominator
     */
    public long getDenominator() {
        return denominator;
    }

    public void setNumerator(long numerator) {
        this.numerator = numerator;
    }

    /**
     * 获取分子
     *
     * @return numerator
     */
    public long getNumerator() {
        return numerator;
    }

    public String toString() {
        return numerator + "/" + denominator;
    }


    private void checkPlusOverFlow(long a, long b) {
        if (a > 0 ? Long.MAX_VALUE / a < b : Long.MIN_VALUE / a > b) {
            throw new ArithmeticException("overFlow");
        }
    }

    private void checkTimesOverFlow(long a, long b) {
        if (a > 0 ? Long.MAX_VALUE - a < b : Long.MIN_VALUE - a > b) {
            throw new ArithmeticException("overFlow");
        }
    }



}
