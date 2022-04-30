package biginteger;

import java.math.BigInteger;
import java.nio.ByteBuffer;

// https://www.baeldung.com/java-biginteger
public class BigIntegerExample {
    public static void main(String[] args) {
        // BigInteger properties
        assert 1 == BigInteger.TEN.signum();
        assert -1 == BigInteger.TEN.negate().signum();
        assert 0 == BigInteger.ZERO.signum();

        assert new BigInteger("1").compareTo(new BigInteger(new byte[]{0b1})) == 0;
        assert new BigInteger("2").compareTo(new BigInteger(new byte[]{0b10})) == 0;
        assert new BigInteger("4").compareTo(new BigInteger(new byte[]{0b100})) == 0;

        byte[] bytes = { -128 }; // 1000 0000
        assert new BigInteger("128").compareTo(new BigInteger(1, bytes)) == 0;
        assert new BigInteger("-128").compareTo(new BigInteger(-1, bytes)) == 0;

        assert "10000000".equals(new BigInteger(1, bytes).toString());
        assert "-10000000".equals(new BigInteger(-1, bytes).toString());

        assert 0 == BigInteger.ZERO.bitCount();
        assert BigInteger.ZERO.compareTo(new BigInteger(0, new byte[]{})) == 0;

        // BigInteger Larger Than Long.MAX_VALUE
        BigInteger bi1 = BigInteger.ZERO.setBit(63);
        String str = bi1.toString(2);

        assert 64 == bi1.bitLength();
        assert 1 == bi1.signum();
        assert "9223372036854775808".equals(bi1.toString());
        assert BigInteger.ONE.compareTo(bi1.subtract(BigInteger.valueOf(Long.MAX_VALUE))) == 0;
        assert 64 == str.length();
        assert str.matches("^10{63}$"); // 1000000000000000000000000000000000000000000000000000000000000000

        byte[] bytes2 = ByteBuffer.allocate(Long.BYTES).putLong(Long.MIN_VALUE).array();
        BigInteger bi2 = new BigInteger(1, bytes2);
        assert bi1.compareTo(bi2) == 0;
        // mag[0] = -2147483648 => 10000000 00000000 00000000 00000000
        // mag[1] = 0 => 나머지 0 8 바이트
    }
}
