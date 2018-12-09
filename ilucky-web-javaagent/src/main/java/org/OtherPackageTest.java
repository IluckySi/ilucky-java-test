package org;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;


public class OtherPackageTest {

    public static void main(String[] args) {
        File f = new File("D:/t.txt");
        System.out.println(f);
        System.out.println(findPath());
    }
    public void testOther(long a, int b, String c) {
        try {
            System.out.println("=============testOther");
            testOtherPrivate();
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    private static File findPath() {
        String classResourcePath = OtherPackageTest.class.getName().replaceAll("\\.", "/") + ".class";

        URL resource = OtherPackageTest.class.getClassLoader().getSystemClassLoader().getResource(classResourcePath);
        if (resource != null) {
            String urlString = resource.toString();


            int insidePathIndex = urlString.indexOf('!');
            boolean isInJar = insidePathIndex > -1;

            if (isInJar) {
                urlString = urlString.substring(urlString.indexOf("file:"), insidePathIndex);
                File agentJarFile = null;
                try {
                    agentJarFile = new File(new URL(urlString).getFile());
                } catch (MalformedURLException e) {
                }
                if (agentJarFile.exists()) {
                    return agentJarFile.getParentFile();
                }
            } else {
                String classLocation = urlString.substring(urlString.indexOf("file:"), urlString.length() - classResourcePath.length());
                return new File(classLocation);
            }
        }
        return null;
    }

    private void testOtherPrivate() {
        try {
            System.out.println("=============testOtherPrivate");
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
