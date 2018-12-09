/*package com.ilucky.web.javaagent.test;

package com.bs3.utils;

import com.bs3.ext.bs2.QuickAES;
import com.bs3.ext.bs2.QuickDES;
import java.io.PrintStream;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import m;

public class KeyCrypt
{
  private static final MyLog _log = MyLog.getLog(KeyCrypt.class);
  private static QuickDES m_des;
  public static final int DECRYPT_MODE = 2;
  private static final MyLog TRACE = MyLog.getLog(m.L("@GVWU;XqeVmmh!"));
  private static QuickAES m_aes;
  private static int genKeyIndex;
  public static final int ENCRYPT_MODE = 1;

  private static byte[] des3(byte[] a, int a, byte[] a, byte[] a, byte[] a)
    throws Exception
  {
    int a = 8;
    if ((a == null) || (a.length != 8)) throw new IllegalBlockSizeException(m.L("~%8ytzuau5#m"));
    if ((a == null) || (a.length != 8)) throw new IllegalBlockSizeException(m.L("~&8ytzuau5#m"));
    if ((a == null) || (a.length != 8)) throw new IllegalBlockSizeException(m.L("~'8ytzuau5#m"));
    if (a.length % a != 0) throw new IllegalBlockSizeException(m.L(".twe:/jkijrb\"1/7:e"));
    byte[] a = a;
    if (a == 1) {
      a = des(a, 1, a);
      a = des(a, 2, a);
      a = des(a, 1, a);
    } else {
      a = des(a, 2, a);
      a = des(a, 1, a);
      a = des(a, 2, a);
    }
    return a;
  }

  public static byte[] hmac_xor(int a, byte[] a, int a, byte a)
    throws Exception
  {
    byte[] a = new byte[a];
    int a;
    xor(a, a, a * a, a);

    a++;
    int a;
    if ((a = a * a) < 
      a) {
      byte[] a = hmac_pad(a, a, a, a, a);

      xor(a, a, 0, a);
    }

    _log.debug(m.L(".,f`hoUuj~.(e,@$=1>V<hG(&"), new Object[] { Integer.valueOf(a), Integer.valueOf(a.length), MyUtil.bcd(a) });
    return a;
  }

  public static byte[] md5(byte[] a)
  {
    return digest(a, 0, a.length, m.L("[S`"));
  }

  public static byte[] hmac_x9_9(byte[] a, byte[] a, int a, byte a)
    throws Exception
  {
    TRACE.debug(m.L("NL/;(I2x|>(9O?qX4#&"), new Object[] { Integer.valueOf(a.length), MyUtil.bcd(a) });
    TRACE.debug(m.L("NL/;(I2fow>(9O?qX4#&"), new Object[] { Integer.valueOf(a), MyUtil.bcd(a, 0, a) });
    int a = 8;
    byte[] a = new byte[8];
    int a;
    TRACE.debug(m.L("ZX;/<]&$m.O)h .(1=2$&"), new Object[] { Integer.valueOf(a), MyUtil.bcd(a) });
    TRACE.debug(m.L("ZX;/<]&$m.O)- j(1=2$&"), new Object[] { Integer.valueOf(a), MyUtil.bcd(a, a * 8, 8) });
    xor(a, a, a * 8, 8);
    TRACE.debug(m.L("ZX;/<]&$m.O)h^j(1=2$&"), new Object[] { Integer.valueOf(a), MyUtil.bcd(a) });
    a = des3_any(a, 1, a);
    TRACE.debug(m.L("NL/;(I20y:[=}qi<%)&0r;ipt)+&"), new Object[] { Integer.valueOf(a), MyUtil.bcd(a), MyUtil.bcd(a) });

    a++;
    int a;
    if ((a = a * 8) <= 
      a) {
      byte[] a = hmac_pad(a, a, a, 8, a);
      TRACE.debug(m.L("ZX;/<]&$m.O)}aj(1=2$&"), new Object[] { Integer.valueOf(a), MyUtil.bcd(a) });
      xor(a, a, 0, 8);
      TRACE.debug(m.L("ZX;/<]&$m.O)h^j(1=2$&"), new Object[] { Integer.valueOf(a), MyUtil.bcd(a) });
      a = des3_any(a, 1, a);
      TRACE.debug(m.L("ZX;/<]&$m.O)ie}(1=2$&"), new Object[] { Integer.valueOf(a), MyUtil.bcd(a) });
    }
    _log.debug(m.L("75yqvLl%J&<=qY=$('O%q^4)&"), new Object[] { Integer.valueOf(a), Integer.valueOf(a.length), MyUtil.bcd(a) });
    return a;
  }

  public static Key toKey(String a, byte[] a)
    throws Exception
  {
    return new SecretKeySpec(a, a);
  }

  private static byte[] digest(byte[] a, int a, int a, String a)
  {
    MessageDigest a = null;
    byte[] a = null;
    try {
      a = MessageDigest.getInstance(a);
    } catch (NoSuchAlgorithmException a) {
      a.printStackTrace();
    }
    if (a != null) {
      a.reset();
      a.update(a, a, a);
      a = a.digest();
    }

    return a;
  }

  static
  {
    m_des = null;

    m_aes = null;

    genKeyIndex = 0;
  }

  public static byte[] xor(byte[] a, byte[] a, int a, int a)
  {
    int a;
    a[a] = (byte)(a[a] ^ a[(a + a)]);

    a++;

    return a;
  }

  public static byte[] des3_any(byte[] a, int a, byte[] a)
    throws Exception
  {
    if (a == null) throw new IllegalBlockSizeException(m.L("vyg $roq9"));
    switch (a.length) {
    case 8:
      return des(a, a, a);
    case 16:
      return des3_k16(a, a, a);
    case 24:
      while (0 != 0);
      return des3_k24(a, a, a);
    }
    throw new IllegalBlockSizeException(m.L("uzd0vzwybw}") + a.length + m.L("=4*O!#o&(\b"));
  }

  public static byte[] hmac(byte[] a, byte[] a, String a)
    throws Exception
  {
    SecretKey a;
    Mac a;
    (a = Mac.getInstance((a = new SecretKeySpec(a, a))
      .getAlgorithm()))
      .init(a);
    byte[] a = a.doFinal(a);
    _log.debug(m.L("%'mkcd)#}.-;*\\,bK\"&"), new Object[] { a, Integer.valueOf(a.length), MyUtil.bcd(a) });
    return a;
  }

  public static byte[] des3_k16(byte[] a, int a, byte[] a)
    throws Exception
  {
    if ((a == null) || (a.length != 16)) throw new IllegalBlockSizeException(m.L("pn:|p}sh}>))c"));
    byte[] a = new byte[8];
    System.arraycopy(a, 0, a, 0, a.length);
    byte[] a = new byte[8];
    System.arraycopy(a, 8, a, 0, a.length);
    byte[] a = a;
    return des3(a, a, a, a, a);
  }

  public static byte[] des3_k24(byte[] a, int a, byte[] a)
    throws Exception
  {
    if ((a == null) || (a.length != 24)) throw new IllegalBlockSizeException(m.L("pn:|p}sh}>)*a"));
    byte[] a = new byte[8];
    System.arraycopy(a, 0, a, 0, a.length);
    byte[] a = new byte[8];
    System.arraycopy(a, 8, a, 0, a.length);
    byte[] a = new byte[8];
    System.arraycopy(a, 16, a, 0, a.length);
    return des3(a, a, a, a, a);
  }

  public static synchronized byte[] aes16(byte[] a, int a, byte[] a)
    throws Exception
  {
    if (m_aes == null) m_aes = new QuickAES();
    int a;
    if ((a = m_aes.getBlockSize()) != 
      a.length) throw new IllegalBlockSizeException(m.L("%#6)8~vm-#1x}{|`l|"));
    if (0 != a.length % a) throw new IllegalBlockSizeException(m.L("$4*<cgp%*m1x}{|`l06\")z") + a.length);
    m_aes.initializeKey(a);
    byte[] a = new byte[a.length];
    int a;
    int a;
    if (a == 1)
    {
      m_aes.blockEncrypt(a, a, a, a);

      a += 16;
    }
    else
    {
      m_aes.blockDecrypt(a, a, a, a);

      a += 16;
    }
    return a;
  }

  public static byte[] hmac_x9_19(byte[] a, byte[] a, int a, byte a)
    throws Exception
  {
    int a = 16;
    byte[] a = new byte[16];
    int a;
    xor(a, a, a * 16, 16);

    a = aes16(a, 1, a);

    a++;
    int a;
    if ((a = a * 16) < 
      a) {
      byte[] a = hmac_pad(a, a, a, 16, a);

      xor(a, a, 0, 16);

      a = aes16(a, 1, a);
    }

    _log.debug(m.L("64~xpwMm$K/,11~5G=&(!O'qP4+&"), new Object[] { Integer.valueOf(a), Integer.valueOf(a.length), MyUtil.bcd(a) });
    return a;
  }

  public static byte[] sha1(byte[] a)
  {
    return digest(a, 0, a.length, m.L("BXS<d"));
  }

  static byte[] hmac_pad(byte[] a, int a, int a, int a, byte a)
    throws Exception
  {
    byte[] a = new byte[a];
    int a;
    int a;
    if ((a = a + a) == 
      a) {
      a[a] = a;
    }
    else {
      if (a < a)
        a[a] = a[a];
      a++;
    }

    _log.debug(m.L(")+agohRzco)/bI,*'+B/zV=/a+塺兏3o\027"), new Object[] { Integer.valueOf(a), Integer.valueOf(a), MyUtil.bcd(a), Integer.valueOf(a + a - a) });
    return a;
  }

  public static synchronized byte[] des(byte[] a, int a, byte[] a)
    throws Exception
  {
    if (m_des == null) m_des = new QuickDES();
    int a = m_des.getBlockSize();
    if (a.length != a) throw new IllegalBlockSizeException(m.L("-5+=zqk-3x{{~`r|"));
    if (0 != a.length % a) throw new IllegalBlockSizeException(m.L("%5+=bfq-e:rpwsn} ,/z") + a.length);
    m_des.initializeKey(a);
    byte[] a = new byte[a.length];
    int a;
    int a;
    if (a == 1)
    {
      m_des.blockEncrypt(a, a, a, a);

      a += 8;
    }
    else
    {
      m_des.blockDecrypt(a, a, a, a);

      a += 8;
    }

    return a;
  }

  public static byte[] kdf(byte[] a, String a, int a)
  {
    if (a == null) a = new byte[0];
    int a = 20;
    int a = a / 20 + 1;
    byte[] a = new byte[a + 20];
    int a = 0;
    byte[] a = a == null ? new byte[0] : a.getBytes();
    byte[] a = new byte[a.length + 4 + a.length];
    int a = 0;
    if (MyUtil.assertLength(a, 1)) System.arraycopy(a, 0, a, a, a.length);
    int a;
    if ((a = 1) <= a)
    {
      a = (a = 0) + 
        a.length;
      MyUtil.int2buf(a, a, a);
      a += 4;
      System.arraycopy(a, 0, a, a, a.length);
      a += a.length;
      byte[] a;
      System.arraycopy(a = md5(a), 
        0, a, a, a.length);

      a++;
    }

    byte[] a = new byte[a];
    System.arraycopy(a, 0, a, 0, a.length);

    _log.debug(m.L("75|pv=6&x9:g15&4!&"), new Object[] { Integer.valueOf(a), a, MyUtil.bcd(a) });
    return a;
  }

  public static synchronized byte[] genKey(String a, int a) {
    if (a == null) a = m.L("D]Bp,");
    a = String.format(m.L("^qo;4p<0y:;&"), new Object[] { Integer.valueOf(++genKeyIndex), Long.valueOf(MyUtil.timeNanoSecond()), a });
    return kdf(null, a, a);
  }

  public static byte[] hmac_md5(byte[] a, byte[] a)
    throws Exception
  {
    return hmac(a, a, m.L("]ywvB\\Sd"));
  }
  public static byte[] hmac_sha1(byte[] a, byte[] a) throws Exception {
    return hmac(a, a, m.L("Z~pq[W`"));
  }

  static class Test
  {
    public static void test_des3()
      throws Exception
    {
      KeyCrypt._log.debug(m.L("n~jnAxi:2?485{"), new Object[0]);
      byte[] a = m.L("$%'$ %#$/&,'+&(g").getBytes();

      byte[] a = m.L("$&%!$\"%-$$_WZP_\023").getBytes();

      byte[] a = m.L("$&%!$\"%m").getBytes();
      byte[] a = m.L(",$WWRPW\023").getBytes();
      byte[] a = KeyCrypt.des3_any(a, 1, a);
      KeyCrypt._log.debug(m.L("~q}5,47&"), new Object[] { MyUtil.bcd(a) });
      KeyCrypt._log.debug(m.L("ffu5,47&"), new Object[] { MyUtil.bcd(a) });
      KeyCrypt._log.debug(m.L("qqe5,47&"), new Object[] { MyUtil.bcd(a) });
      KeyCrypt._log.debug(m.L("ppd'Ot}m40-p15&4!&"), new Object[] { Integer.valueOf(a.length), MyUtil.bcd(a) });
      KeyCrypt._log.debug(m.L("yym,9!:8&"), new Object[] { MyUtil.bcd(KeyCrypt.des3_any(a, 1, a)) });
      KeyCrypt._log.debug(m.L("yym/9!:8&"), new Object[] { MyUtil.bcd(KeyCrypt.des3_any(a, 1, a)) });
    }

    public static void main(String[] arg0)
      throws Exception
    {
      System.out.println(m.L("\023;9:>;=:2;1:6;5:*;):.;-:\";!:&;%:\032;\031:\036;\035:\022;\021:\026;\025:\n\022\t9\r8\0169\0018\0029\0058\006998:9=8>918295869)8*9-8.9!8\"9%8&9\031;0:\0358\0369\0218\0229\026;\026:\t8\n:\r8\0169\002;\002:\006;\006::;::>8>:2;295869)8)\023.8.9!8\"9&8%9\0328\0329\0368\0369\0228\0219\025;\0269\n8\t9\0168\r9\001;\0029\0058\006998::\027;>918295;5:);*9-;.9!;!:%8%9\031;\032:\035;\0359\0218\0219\0258\0269\t8\n9\016\022\r9\0018\0029\0058\0059:89:>8=:2819686:)8):.8-9\"8!:&8&9\0318\0329\035;4:\0218\0229\0258\0269\t8\n9\r8\0169\0018\0029\0058\006998:9=8>918295869)8*9-8-\023\"8M{cmuzXlSvS8\\`\021Y^uTlYk@8e{Km]z@lMk\005n\0257*8^\\PW>:\033;295869)8*9-8.9!8\"9%8&9\0318\0329\0358\0369\0218\0229\0258\0269\t8\n9\r8\0169\002\022\0019\0058\006998:9=8vmeh(6:oan'yfullakh6avh8&9\0318\0329\0358\0369\021;8:\0258\0269\t8\n9\r8\0169\0018\0029\0058\006998:9=8>918295869)8*9-8.9!8!\023&;%:\032;\031:\036;\035:\022;\021:\026;\025:\n;\t:\016;\r:\002;\001:\006;\005::;9:>;=:2;1:_")); test_des3();
      test_mac_x9_9();
    }
    public static void test_aes() throws Exception {
      KeyCrypt._log.debug(m.L("n~jnAzxi:2?485{"), new Object[0]);
    }

    public static void test_kdf()
      throws Exception
    {
      KeyCrypt._log.debug(m.L("n~jnApy|:2?485{"), new Object[0]);
      byte[] a = new byte[1];
      String a = m.L("HQN|`2yr8");
      KeyCrypt.kdf(a, a, 8);
      KeyCrypt.kdf(a, a, 16);
      KeyCrypt.kdf(a, a, 20);
      KeyCrypt.kdf(a, a, 24);
      KeyCrypt.kdf(a, a, 32);
      KeyCrypt.genKey(null, 8);
      KeyCrypt.genKey(null, 16);
      KeyCrypt.genKey(null, 24);
      KeyCrypt.genKey(null, 32);
    }

    public static void test_mac()
      throws Exception
    {
      KeyCrypt._log.debug(m.L("n~jnAv|y:2?485{"), new Object[0]);
      byte[] a = m.L("$&%!$\"%-$$/'* /#2,?%0&1!8\"9-0$;'\006 \003#\006,\013%\f&\r!\f\"\r-\034$\027'\022 \027#\032,\027e").getBytes();
      KeyCrypt.hmac_sha1(KeyCrypt.genKey(m.L("]yw6"), 8), a);
      KeyCrypt.hmac_md5(KeyCrypt.genKey(m.L("]yw6"), 8), a);
      KeyCrypt.hmac_x9_9(KeyCrypt.genKey(m.L("XUU,?PW\006"), 8), a, a.length, -128);
      KeyCrypt.hmac_x9_9(KeyCrypt.genKey(m.L("XUU,?PW\006"), 16), a, a.length - 1, -128);
      KeyCrypt.hmac_x9_9(KeyCrypt.genKey(m.L("XUU,?PW\006"), 24), a, a.length - 2, -128);
      KeyCrypt.hmac_x9_19(KeyCrypt.genKey(m.L("P]], 2[X\006"), 16), a, a.length - 4, -128);
      KeyCrypt.hmac_xor(8, a, a.length, -128);
      KeyCrypt.hmac_xor(16, a, a.length, -128);
      KeyCrypt.hmac_xor(20, a, a.length, -128);
      KeyCrypt.hmac_xor(11, a, a.length, -128);
    }

    public static void test_des()
      throws Exception
    {
      KeyCrypt._log.debug(m.L("n~jnAxi:2?485{"), new Object[0]);
      byte[] a = m.L("$&%!$\"%-,&-!,\"-m").getBytes();
      byte[] a = m.L("%%$&%!$b").getBytes();
      byte[] a = m.L("%%$&%!$\"%-wzp3").getBytes();
      byte[] a = m.L("%%$&%!$\"%-wzps5%4&5!4b").getBytes();
      KeyCrypt._log.debug(m.L("ppd'Ot}m40-p15&4!&"), new Object[] { Integer.valueOf(a.length), MyUtil.bcd(KeyCrypt.des3_any(a, 1, a)) });
      KeyCrypt._log.debug(m.L("ppd'Ot}m40-p15&4!&"), new Object[] { Integer.valueOf(a.length), MyUtil.bcd(KeyCrypt.des3_any(a, 1, a)) });
      KeyCrypt._log.debug(m.L("ppd'Ot}m40-p15&4!&"), new Object[] { Integer.valueOf(a.length), MyUtil.bcd(KeyCrypt.des3_any(a, 1, a)) });
    }

    public static void test_mac_x9_9()
      throws Exception
    {
      KeyCrypt._log.debug(m.L("aqeaNysvBl'J <3;+:({"), new Object[0]);

      byte[] a = m.L("$&%!$\"%-$$/'* /c").getBytes();
      byte[] a = m.L("$%'$ %#$/&,'+&(g").getBytes();
      byte[] a = m.L(",-/,(-+,-$.%)$*e").getBytes();

      byte[] a = KeyCrypt.des3_any(a, 1, a);
      byte[] a = KeyCrypt.hmac_x9_9(a, a, a.length, -128);
      KeyCrypt._log.debug(m.L("^Q]5,47&"), new Object[] { MyUtil.bcd(a) });
      KeyCrypt._log.debug(m.L("XU]5,47&"), new Object[] { MyUtil.bcd(a) });
      KeyCrypt._log.debug(m.L("XU]5,47&"), new Object[] { MyUtil.bcd(a) });
      KeyCrypt._log.debug(m.L("ZAB5,47&"), new Object[] { MyUtil.bcd(a) });
    }
  }
}*/