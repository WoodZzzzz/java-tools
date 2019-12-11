package com.wood.tool;

import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.apache.commons.compress.archivers.tar.TarArchiveOutputStream;
import org.apache.commons.compress.compressors.gzip.GzipCompressorInputStream;
import org.apache.commons.compress.compressors.gzip.GzipCompressorOutputStream;
import org.apache.commons.compress.utils.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Tar {

    private Tar() {

    }

    /**
     * 压缩
     */
    public static void compress(String name, File... files) throws IOException {
        TarArchiveOutputStream out = getTarArchiveOutputStream(name);
        for (File file : files){
            addToArchiveCompression(out, file, ".");
        }
    }

    /**
     * 解压并找到git文件
     * @param in
     * @param out
     * @param arc ARC压缩包名
     * @param moduleNames 待寻找的组件名称
     * @throws IOException
     */
    public static void decompress(String in, File out, String arc, String ... moduleNames) throws IOException {
        TarArchiveInputStream fin = new TarArchiveInputStream(new GzipCompressorInputStream(new FileInputStream(in)));
        TarArchiveEntry entry;
        while ((entry = fin.getNextTarEntry()) != null) {
            if (entry.isDirectory()) {
                continue;
            }
            // 根据给定的组件名称从文件中找到tgz
            for (String moduleName : moduleNames) {
                // 如果是能找到当前模块的路径，则从中寻找对应的jar中的git文件
                if (entry.getName().toLowerCase().indexOf(moduleName.toLowerCase()) > -1) {
//                    findGitInJar();
                }
            }
            // 如果是汇总模块，则再解压
            if (entry.getName().toLowerCase().indexOf(arc.toLowerCase()) > -1) {
                decompress(in, out, arc, moduleNames);
//                findModulesInTgz();
            }
            System.out.println(entry.getName());
          /*  File curfile = new File(out, entry.getName());
            File parent = curfile.getParentFile();
            if (!parent.exists()) {
                parent.mkdirs();
            }
            IOUtils.copy(fin, new FileOutputStream(curfile));*/
        }
    }

    private static TarArchiveOutputStream getTarArchiveOutputStream(String name) throws IOException {
        TarArchiveOutputStream taos = new TarArchiveOutputStream(new GzipCompressorOutputStream(new FileOutputStream(name)));
        // TAR has an 8 gig file limit by default, this gets around that
        taos.setBigNumberMode(TarArchiveOutputStream.BIGNUMBER_STAR);
        // TAR originally didn't support long file names, so enable the support for it
        taos.setLongFileMode(TarArchiveOutputStream.LONGFILE_GNU);
        taos.setAddPaxHeadersForNonAsciiNames(true);
        return taos;
    }

    private static void addToArchiveCompression(TarArchiveOutputStream out, File file, String dir) throws IOException {
        String entry = dir + File.separator + file.getName();
        if (file.isFile()){
            out.putArchiveEntry(new TarArchiveEntry(file, entry));
            try (FileInputStream in = new FileInputStream(file)){
                IOUtils.copy(in, out);
            }
            out.closeArchiveEntry();
        } else if (file.isDirectory()) {
            File[] children = file.listFiles();
            if (children != null){
                for (File child : children){
                    addToArchiveCompression(out, child, entry);
                }
            }
        } else {
            System.out.println(file.getName() + " is not supported");
        }
    }


}
