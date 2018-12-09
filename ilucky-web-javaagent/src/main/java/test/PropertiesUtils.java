//package test;
//
//import java.io.BufferedInputStream;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.OutputStream;
//import java.util.Properties;
//
//public class PropertiesUtils
//{
//
//  public static String GetValueByKey(String filePath, String key)
//  {
//    Properties pps = new Properties();
//    try
//    {
//      InputStream in = new BufferedInputStream(new FileInputStream(filePath));
//      pps.load(in);
//      String value = pps.getProperty(key);
//
//      return value;
//    }
//    catch (IOException e) {
//      e.printStackTrace();
//    }return null;
//  }
//
//  public static void WriteProperties(String filePath, String pKey, String pValue)
//    throws IOException
//  {
//    InputStream is = new FileInputStream(filePath);
//    // CopyOfSafeProperties pps = new CopyOfSafeProperties();
//    SafeProperties pps = new SafeProperties();
//    pps.load(is);
//
//    OutputStream out = new FileOutputStream(filePath);
//    pps.setProperty(pKey, pValue);
//    pps.store(out, null);
//    // ADD
//    // out.close();
//    // is.close();
//    
//  }
//}