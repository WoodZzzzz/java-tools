package com.wood.tool;

import java.io.*;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class JarReader {

    public static final String GIT_VERSION_KEY = "git.build.version";

    public static void main(String[] args) throws IOException {
        String path = "E:\\tmp\\tool-check-version.jar";
        JarFile jarFile = new JarFile(new File(path));
        Enumeration<JarEntry> entries = jarFile.entries();

        while (entries.hasMoreElements()) {

            JarEntry entry = entries.nextElement();
            String entryName = entry.getName();

            if (!entry.isDirectory() && entryName.equalsIgnoreCase("git.properties")) {
                System.out.println(entry);
                readFile(path, entryName, 1);
            }

        }
    }


    //filePath是jar文件位置，name是jar文件里面文件的路径，相当于上面代码框中的entryName
    public static InputStream getJarInputStream(String filePath, String name)
            throws Exception {
        URL url = new URL("jar:file:" + filePath + "!/" + name);
        JarURLConnection jarConnection = (JarURLConnection) url
                .openConnection();
        InputStream in = jarConnection.getInputStream();

        return in;
    }

    /**
     * 读取build.version字段
     * @param filePath
     * @param entryName
     * @param index
     * @return
     */
    public static String readFile(String filePath, String entryName, Integer index) {
        InputStream in = null;
        BufferedReader br = null;
        try {
            in = getJarInputStream(filePath, entryName);
            br = new BufferedReader(new InputStreamReader(in, "UTF-8"));

            String con = null;

            while ((con = br.readLine()) != null) {
                if (GIT_VERSION_KEY.equals(con)) {
                    return con;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null)
                    br.close();
                if (in != null)
                    in.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
