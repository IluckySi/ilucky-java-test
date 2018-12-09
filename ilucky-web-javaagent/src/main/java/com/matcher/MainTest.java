package com.matcher;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class MainTest {

    /**
     * Converts symbol match pattern (string) to regular expression object.
     * 
     * @param symbolName
     *            symbol match pattern
     * @return regular expression pattern
     */
    private static Pattern toSymbolMatch(String symbolName) {
        if (symbolName == null) {
            return Pattern.compile(".*");
        } else if (symbolName.startsWith("~")) {
            return Pattern.compile(symbolName.substring(1));
        } else {
            String s = symbolName.replaceAll("\\.", "\\\\.")
                    .replaceAll("\\$", "\\\\\\$").replaceAll("\\*\\*", ".+")
                    .replaceAll("\\*", "[a-zA-Z0-9_]+");
            return Pattern.compile(s);
        }
    }

    public static void main(String[] args) {
        List<String> l = new ArrayList<String>();
        l.add("1");
        l.add("2");
        System.out.println(l);
        
        Pattern pattern = MainTest.toSymbolMatch("Lcom.ilucky.web.javaagent.custom.Account;");
        System.out.println(pattern);
        
        // InterceptMatcherSet中打印的:
        // Lcom\.ilucky\.web\.javaagent\.custom\.Account;===[Lcom.ilucky.web.javaagent.custom.Account;]
        // String annotation = "Lcom/ilucky/web/javaagent/custom/Account;";
        // String annotation = "Lcom/ilucky/web/javaagent/custom/Account;".replace("/", ".");
        String annotation = "Lcom.ilucky.web.javaagent.custom.Account;";
        if (pattern.matcher(annotation).matches()) {
            System.out.println(true);
        } else {
            System.out.println(false);
        }
    }
}
