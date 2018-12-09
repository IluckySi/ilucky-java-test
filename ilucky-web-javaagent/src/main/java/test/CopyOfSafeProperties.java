//package test;
//
//import java.io.BufferedWriter;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.io.OutputStream;
//import java.io.OutputStreamWriter;
//import java.io.Reader;
//import java.io.Writer;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.LinkedHashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Map.Entry;
//import java.util.Properties;
//import java.util.Set;
//
//public class CopyOfSafeProperties
//{
//  private List<String> keys = new ArrayList();
//  private Map<String, String> valueMap = new HashMap();
//  private final Properties props;
//  private final LinkedHashMap<String, String> keyCommentMap = new LinkedHashMap();
//  private static final String BLANK = "";
//  private static final char[] hexDigit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
//
//  public CopyOfSafeProperties()
//  {
//    this.props = new Properties();
//  }
//
//  public CopyOfSafeProperties(Properties defaults)
//  {
//    this.props = new Properties(defaults);
//  }
//
//  public String setProperty(String key, String value)
//  {
//    return setProperty(key, value, "");
//  }
//
//  public synchronized String setProperty(String key, String value, String comment)
//  {
//    Object oldValue = this.props.setProperty(key, value);
//    if ("".equals(comment)) {
//      if (!this.keyCommentMap.containsKey(key))
//        this.keyCommentMap.put(key, comment);
//    }
//    else {
//      this.keyCommentMap.put(key, comment);
//    }
//    return (String)oldValue;
//  }
//
//  public String getProperty(String key)
//  {
//    return this.props.getProperty(key);
//  }
//
//  public String getProperty(String key, String defaultValue)
//  {
//    return this.props.getProperty(key, defaultValue);
//  }
//
//  public synchronized void load(Reader reader)
//    throws IOException
//  {
//    load0(new LineReader(reader));
//  }
//
//  public synchronized void load(InputStream inStream)
//    throws IOException
//  {
//    try
//    {
//      List<String> lines = IOUtils.toLines(inStream);
//
//      for (String l : lines) {
//        if (l.trim().startsWith("#")) {
//          this.keys.add(l);
//        }
//        else if (l.indexOf("=") > -1) {
//          String k = l.substring(0, l.indexOf("=")).trim();
//          String v = l.substring(l.indexOf("=") + 1).trim();
//          this.keys.add(k);
//          this.valueMap.put(k, v);
//        } else {
//          this.keys.add(l);
//        }
//
//      }
//
//    }
//    catch (Exception e)
//    {
//      e.printStackTrace();
//    }
//    load0(new LineReader(inStream));
//    
//    // ADD
//    // inStream.close();
//  }
//
//  public synchronized void load(InputStream inStream, String charset)
//    throws IOException
//  {
//    InputStreamReader reader = new InputStreamReader(inStream, charset);
//    load0(new LineReader(reader));
//  }
//
//  public synchronized void load(File file, String charset)
//    throws IOException
//  {
//    FileInputStream inputStream = new FileInputStream(file);
//
//    InputStreamReader reader = new InputStreamReader(inputStream, charset);
//    load0(new LineReader(reader));
//  }
//
//  public synchronized void load(File file)
//    throws IOException
//  {
//    FileInputStream inputStream = new FileInputStream(file);
//    InputStreamReader reader = new InputStreamReader(inputStream, "utf-8");
//    try {
//      IOUtils.toString(new FileInputStream(file));
//    }
//    catch (Exception e) {
//      e.printStackTrace();
//    }
//
//    load0(new LineReader(reader));
//  }
//
//  public void store(Writer writer)
//    throws IOException
//  {
//    store0((writer instanceof BufferedWriter) ? (BufferedWriter)writer : new BufferedWriter(writer), false);
//  }
//
//  public void store(OutputStream out)
//    throws IOException
//  {
//    store0(new BufferedWriter(new OutputStreamWriter(out, "utf-8")), true);
//  }
//
//  public boolean containsValue(String value)
//  {
//    return this.props.containsValue(value);
//  }
//
//  public boolean containsKey(String key)
//  {
//    return this.props.containsKey(key);
//  }
//
//  public int size()
//  {
//    return this.props.size();
//  }
//
//  public boolean isEmpty()
//  {
//    return this.props.isEmpty();
//  }
//
//  public synchronized void clear()
//  {
//    this.props.clear();
//    this.keyCommentMap.clear();
//  }
//
//  public Set<String> propertyNames()
//  {
//    return this.props.stringPropertyNames();
//  }
//
//  public synchronized String toString() {
//    StringBuffer buffer = new StringBuffer();
//    Iterator kvIter = this.keyCommentMap.entrySet().iterator();
//    buffer.append("[");
//    while (kvIter.hasNext()) {
//      buffer.append("{");
//      Map.Entry entry = (Map.Entry)kvIter.next();
//      String key = (String)entry.getKey();
//      String val = getProperty(key);
//      String comment = (String)entry.getValue();
//      buffer.append("key=" + key + ",value=" + val + ",comment=" + comment);
//      buffer.append("}");
//    }
//    buffer.append("]");
//    return buffer.toString();
//  }
//
//  public boolean equals(Object o)
//  {
//    return this.props.equals(o);
//  }
//
//  public int hashCode() {
//    return this.props.hashCode();
//  }
//
//  private void load0(LineReader lr) throws IOException {
//    char[] convtBuf = new char[1024];
//
//    StringBuffer buffer = new StringBuffer();
//    int limit;
//    while ((limit = lr.readLine()) >= 0) {
//      char c = '\000';
//      int keyLen = 0;
//      int valueStart = limit;
//      boolean hasSep = false;
//
//      c = lr.lineBuf[keyLen];
//      if ((c == '#') || (c == '!')) {
//        String comment = loadConvert(lr.lineBuf, 1, limit - 1, convtBuf);
//        if (buffer.length() > 0) {
//          buffer.append("\n");
//        }
//        buffer.append(comment);
//        continue;
//      }
//      boolean precedingBackslash = false;
//      while (keyLen < limit) {
//        c = lr.lineBuf[keyLen];
//
//        if (((c == '=') || (c == ':')) && (!precedingBackslash)) {
//          valueStart = keyLen + 1;
//          hasSep = true;
//          break;
//        }if (((c == ' ') || (c == '\t') || (c == '\f')) && (!precedingBackslash)) {
//          valueStart = keyLen + 1;
//          break;
//        }
//        if (c == '\\')
//          precedingBackslash = !precedingBackslash;
//        else {
//          precedingBackslash = false;
//        }
//        keyLen++;
//      }
//      while (valueStart < limit) {
//        c = lr.lineBuf[valueStart];
//        if ((c != ' ') && (c != '\t') && (c != '\f')) {
//          if ((hasSep) || ((c != '=') && (c != ':'))) break;
//          hasSep = true;
//        }
//
//        valueStart++;
//      }
//      String key = loadConvert(lr.lineBuf, 0, keyLen, convtBuf);
//      String value = loadConvert(lr.lineBuf, valueStart, limit - valueStart, convtBuf);
//      setProperty(key, value, buffer.toString());
//
//      buffer = new StringBuffer();
//    }
//  }
//
//  private String loadConvert(char[] in, int off, int len, char[] convtBuf)
//  {
//    if (convtBuf.length < len) {
//      int newLen = len * 2;
//      if (newLen < 0) {
//        newLen = 2147483647;
//      }
//      convtBuf = new char[newLen];
//    }
//
//    char[] out = convtBuf;
//    int outLen = 0;
//    int end = off + len;
//
//    while (off < end) {
//      char aChar = in[(off++)];
//      if (aChar == '\\') {
//        aChar = in[(off++)];
//        if (aChar == 'u')
//        {
//          int value = 0;
//          for (int i = 0; i < 4; i++) {
//            aChar = in[(off++)];
//            switch (aChar) {
//            case '0':
//            case '1':
//            case '2':
//            case '3':
//            case '4':
//            case '5':
//            case '6':
//            case '7':
//            case '8':
//            case '9':
//              value = (value << 4) + aChar - 48;
//              break;
//            case 'a':
//            case 'b':
//            case 'c':
//            case 'd':
//            case 'e':
//            case 'f':
//              value = (value << 4) + 10 + aChar - 97;
//              break;
//            case 'A':
//            case 'B':
//            case 'C':
//            case 'D':
//            case 'E':
//            case 'F':
//              value = (value << 4) + 10 + aChar - 65;
//              break;
//            case ':':
//            case ';':
//            case '<':
//            case '=':
//            case '>':
//            case '?':
//            case '@':
//            case 'G':
//            case 'H':
//            case 'I':
//            case 'J':
//            case 'K':
//            case 'L':
//            case 'M':
//            case 'N':
//            case 'O':
//            case 'P':
//            case 'Q':
//            case 'R':
//            case 'S':
//            case 'T':
//            case 'U':
//            case 'V':
//            case 'W':
//            case 'X':
//            case 'Y':
//            case 'Z':
//            case '[':
//            case '\\':
//            case ']':
//            case '^':
//            case '_':
//            case '`':
//            default:
//              throw new IllegalArgumentException("Malformed \\uxxxx encoding.");
//            }
//          }
//          out[(outLen++)] = (char)value;
//          continue;
//        }if (aChar == 't')
//          aChar = '\t';
//        else if (aChar == 'r')
//          aChar = '\r';
//        else if (aChar == 'n')
//          aChar = '\n';
//        else if (aChar == 'f')
//          aChar = '\f';
//        out[(outLen++)] = aChar; continue;
//      }
//
//      out[(outLen++)] = aChar;
//    }
//
//    return new String(out, 0, outLen);
//  }
//
//  private void store0(BufferedWriter bw, boolean escUnicode) throws IOException {
//    synchronized (this) {
//      Iterator kvIter = this.keyCommentMap.entrySet().iterator();
//      while (kvIter.hasNext()) {
//        Map.Entry entry = (Map.Entry)kvIter.next();
//        String key = (String)entry.getKey();
//        String val = getProperty(key);
//        String comment = (String)entry.getValue();
//        key = saveConvert(key, true, escUnicode);
//
//        val = saveConvert(val, false, escUnicode);
//        if (!comment.equals(""))
//          writeComments(bw, comment);
//        bw.write(key + "=" + val);
//        bw.newLine();
//      }
//    }
//    bw.flush();
//  }
//
//  private static void writeComments(BufferedWriter bw, String comments) throws IOException {
//    bw.write("#");
//    int len = comments.length();
//    int current = 0;
//    int last = 0;
//    while (current < len) {
//      char c = comments.charAt(current);
//      if ((c > 'ÿ') || (c == '\n') || (c == '\r')) {
//        if (last != current)
//          bw.write(comments.substring(last, current));
//        if (c > 'ÿ') {
//          bw.write(c);
//        } else {
//          bw.newLine();
//          if ((c == '\r') && (current != len - 1) && (comments.charAt(current + 1) == '\n')) {
//            current++;
//          }
//          if ((current == len - 1) || (
//            (comments
//            .charAt(current + 1) != 
//            '#') && (comments.charAt(current + 1) != '!')))
//            bw.write("#");
//        }
//        last = current + 1;
//      }
//      current++;
//    }
//    if (last != current)
//      bw.write(comments.substring(last, current));
//    bw.newLine();
//  }
//
//  private String saveConvert(String theString, boolean escapeSpace, boolean escapeUnicode)
//  {
//    int len = theString.length();
//    int bufLen = len * 2;
//    if (bufLen < 0) {
//      bufLen = 2147483647;
//    }
//    StringBuffer outBuffer = new StringBuffer(bufLen);
//
//    for (int x = 0; x < len; x++) {
//      char aChar = theString.charAt(x);
//
//      if ((aChar > '=') && (aChar < '')) {
//        if (aChar == '\\') {
//          outBuffer.append('\\');
//          outBuffer.append('\\');
//        }
//        else {
//          outBuffer.append(aChar);
//        }
//      }
//      else switch (aChar) {
//        case ' ':
//          if ((x == 0) || (escapeSpace))
//            outBuffer.append('\\');
//          outBuffer.append(' ');
//          break;
//        case '\t':
//          outBuffer.append('\\');
//          outBuffer.append('t');
//          break;
//        case '\n':
//          outBuffer.append('\\');
//          outBuffer.append('n');
//          break;
//        case '\r':
//          outBuffer.append('\\');
//          outBuffer.append('r');
//          break;
//        case '\f':
//          outBuffer.append('\\');
//          outBuffer.append('f');
//          break;
//        case '!':
//        case '#':
//        case ':':
//        case '=':
//          outBuffer.append('\\');
//          outBuffer.append(aChar);
//          break;
//        default:
//          if ((((aChar < ' ') || (aChar > '~')) & escapeUnicode)) {
//            outBuffer.append('\\');
//            outBuffer.append('u');
//            outBuffer.append(toHex(aChar >> '\f' & 0xF));
//            outBuffer.append(toHex(aChar >> '\b' & 0xF));
//            outBuffer.append(toHex(aChar >> '\004' & 0xF));
//            outBuffer.append(toHex(aChar & 0xF));
//          } else {
//            outBuffer.append(aChar);
//          }
//        } 
//    }
//    return outBuffer.toString();
//  }
//
//  private static char toHex(int nibble)
//  {
//    return hexDigit[(nibble & 0xF)];
//  }
//
//  class LineReader
//  {
//    byte[] inByteBuf;
//    char[] inCharBuf;
//    char[] lineBuf = new char[1024];
//    int inLimit = 0;
//    int inOff = 0;
//    InputStream inStream;
//    Reader reader;
//
//    public LineReader(InputStream inStream)
//    {
//      this.inStream = inStream;
//      this.inByteBuf = new byte[8192];
//    }
//
//    public LineReader(Reader reader) {
//      this.reader = reader;
//      this.inCharBuf = new char[8192];
//    }
//
//    int readLine()
//      throws IOException
//    {
//      int len = 0;
//      char c = '\000';
//
//      boolean skipWhiteSpace = true;
//      boolean isNewLine = true;
//      boolean appendedLineBegin = false;
//      boolean precedingBackslash = false;
//      boolean skipLF = false;
//      while (true)
//      {
//        if (this.inOff >= this.inLimit) {
//          this.inLimit = (this.inStream == null ? this.reader.read(this.inCharBuf) : this.inStream.read(this.inByteBuf));
//          this.inOff = 0;
//          if (this.inLimit <= 0) {
//            if (len == 0) {
//              return -1;
//            }
//            return len;
//          }
//        }
//        if (this.inStream != null)
//        {
//          c = (char)(0xFF & this.inByteBuf[(this.inOff++)]);
//        }
//        else c = this.inCharBuf[(this.inOff++)];
//
//        if (skipLF) {
//          skipLF = false;
//          if (c == '\n') {
//            continue;
//          }
//        }
//        if (skipWhiteSpace) {
//          if ((c == ' ') || (c == '\t') || (c == '\f') || (
//            (!appendedLineBegin) && ((c == '\r') || (c == '\n')))) {
//            continue;
//          }
//          skipWhiteSpace = false;
//          appendedLineBegin = false;
//        }
//        if (isNewLine) {
//          isNewLine = false;
//        }
//
//        if ((c != '\n') && (c != '\r')) {
//          this.lineBuf[(len++)] = c;
//          if (len == this.lineBuf.length) {
//            int newLength = this.lineBuf.length * 2;
//            if (newLength < 0) {
//              newLength = 2147483647;
//            }
//            char[] buf = new char[newLength];
//            System.arraycopy(this.lineBuf, 0, buf, 0, this.lineBuf.length);
//            this.lineBuf = buf;
//          }
//
//          if (c == '\\') {
//            precedingBackslash = !precedingBackslash; continue;
//          }
//          precedingBackslash = false; continue;
//        }
//
//        if (len == 0) {
//          isNewLine = true;
//          skipWhiteSpace = true;
//          len = 0;
//          continue;
//        }
//        if (this.inOff >= this.inLimit) {
//          this.inLimit = (this.inStream == null ? this.reader.read(this.inCharBuf) : this.inStream.read(this.inByteBuf));
//          this.inOff = 0;
//          if (this.inLimit <= 0) {
//            return len;
//          }
//        }
//        if (!precedingBackslash) break;
//        len--;
//
//        skipWhiteSpace = true;
//        appendedLineBegin = true;
//        precedingBackslash = false;
//        if (c == '\r') {
//          skipLF = true;
//        }
//      }
//      return len;
//    }
//  }
//}