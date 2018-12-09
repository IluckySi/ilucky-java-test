//package test;
//
//import java.io.BufferedReader;
//import java.io.ByteArrayOutputStream;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.io.PrintWriter;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.zip.ZipEntry;
//import java.util.zip.ZipOutputStream;
//
//public class IOUtils
//{
//  public static byte[] toBytes(InputStream input)
//    throws Exception
//  {
//    byte[] data = null;
//    try {
//      ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
//      byte[] b = new byte[1024];
//      int read = 0;
//      while ((read = input.read(b)) > 0) {
//        byteOut.write(b, 0, read);
//      }
//      data = byteOut.toByteArray();
//    } catch (Exception e) {
//      e.printStackTrace();
//    } finally {
//      input.close();
//    }
//    return data;
//  }
//
//  public static String toString(File file)
//    throws Exception
//  {
//    return toString(new FileInputStream(file));
//  }
//
//  public static String toString(InputStream input)
//    throws Exception
//  {
//    return toStringWithLineBreak(input, null);
//  }
//
//  public static List<String> toLines(InputStream input, String encoding)
//    throws Exception
//  {
//    InputStreamReader insreader = new InputStreamReader(input, encoding);
//    BufferedReader bin = new BufferedReader(insreader);
//    List lines = new ArrayList();
//    String line;
//    while ((line = bin.readLine()) != null) {
//      lines.add(line);
//    }
//    bin.close();
//    insreader.close();
//    return lines;
//  }
//
//  public static List<String> toLines(InputStream input)
//    throws Exception
//  {
//    return toLines(input, "GBK");
//  }
//
//  public static String toStringWithLineBreak(InputStream input, String lineBreak)
//    throws Exception
//  {
//    List<String> lines = toLines(input);
//    StringBuilder sb = new StringBuilder(20480);
//    for (String line : lines) {
//      sb.append(line);
//      if (lineBreak != null) {
//        sb.append(lineBreak);
//      }
//    }
//    return sb.toString();
//  }
//
//  public static void toFile(File saveFile, String content)
//  {
//    File parent = saveFile.getParentFile();
//    if (!parent.exists()) {
//      parent.mkdirs();
//    }
//    PrintWriter out = null;
//    try {
//      out = new PrintWriter(new FileWriter(saveFile));
//      out.print(content);
//      out.flush();
//    } catch (Exception e) {
//      e.printStackTrace();
//    } finally {
//      if (out != null)
//        out.close();
//    }
//  }
//
//  public static void filesToZip(List<File> srcFiles, String targetFileName)
//    throws IOException
//  {
//    String fileOutName = targetFileName + ".zip";
//    byte[] buf = new byte[1024];
//    FileInputStream in = null;
//    FileOutputStream fos = null;
//    ZipOutputStream out = null;
//    try {
//      fos = new FileOutputStream(fileOutName);
//      out = new ZipOutputStream(fos);
//      for (File file : srcFiles) {
//        in = new FileInputStream(file);
//        out.putNextEntry(new ZipEntry(file.getName()));
//        int len;
//        while ((len = in.read(buf)) != -1) {
//          out.write(buf, 0, len);
//        }
//        if (in != null)
//          in.close();
//      }
//    }
//    catch (Exception e) {
//      e.printStackTrace();
//    } finally {
//      if (in != null) {
//        in.close();
//      }
//      if (fos != null) {
//        out.closeEntry();
//        out.close();
//        fos.close();
//      }
//    }
//  }
//
//  public static void main(String[] args) {
//    try {
//      File doc1 = new File("/Users/yanxifeng/Downloads/agent.properties");
//
//      toString(new FileInputStream(doc1));
//    } catch (Exception e) {
//      e.printStackTrace();
//    }
//  }
//}