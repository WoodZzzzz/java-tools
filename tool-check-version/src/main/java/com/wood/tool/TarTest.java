package com.wood.tool;


import java.io.File;
import java.io.IOException;

public class TarTest {

    private static final String OUTPUT_DIRECTORY = "E:\\tmp";
    private static final String JAR_SUFFIX = ".tar.gz";

    private static final String MULTIPLE_RESOURCES = "\\apache-maven-3.6.1-bin";
    private static final String RECURSIVE_DIRECTORY = "\\example-recursive-directory";

    private static final String MULTIPLE_RESOURCES_PATH = OUTPUT_DIRECTORY + MULTIPLE_RESOURCES + JAR_SUFFIX;
    private static final String RECURSIVE_DIRECTORY_PATH = OUTPUT_DIRECTORY + RECURSIVE_DIRECTORY;


    public static void main(String... args) throws IOException {

        // class for resource classloading
        Class clazz = TarTest.class;

        // get multiple resources files to compress
      /*  File resource1 = new File(clazz.getResource("/resource1.txt").getFile());
        File resource2 = new File(clazz.getResource("/resource2.txt").getFile());
        File resource3 = new File(clazz.getResource("/resource3.txt").getFile());*/

        // compress multiple resources
//        Tar.compress(MULTIPLE_RESOURCES_PATH, );

        // 将MULTIPLE_RESOURCES_PATH文件解压到 OUTPUT_DIRECTORY + MULTIPLE_RESOURCES目录中
        Tar.decompress(MULTIPLE_RESOURCES_PATH, new File(OUTPUT_DIRECTORY + MULTIPLE_RESOURCES), "", "");

//        String files = clazz.getResource("E:\\tmp\\apache-maven-3.6.1-bin").getFile();

        // get directory file to compress
//        File directory = new File("E:\\tmp\\apache-maven-3.6.1-bin");

        // 压缩directory文件到RECURSIVE_DIRECTORY_PATH目录下
//        Tar.compress(RECURSIVE_DIRECTORY_PATH, directory);

        // decompress recursive directory
//        Tar.decompress(RECURSIVE_DIRECTORY_PATH, new File(OUTPUT_DIRECTORY + RECURSIVE_DIRECTORY));
    }
}
