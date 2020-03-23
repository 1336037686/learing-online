package com.lyy.utils;

import java.io.*;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * 文件操作工具类
 * @author wsq
 * @Date 2019/12/6
 *
 */
public class FileUtil {

    /**
     * 获取文件MD5值
     * @param file
     * @return
     */
    public static String getMd5ByFile(File file) {
        String value = null;
        FileInputStream in = null;
        try {
            in = new FileInputStream(file);
            MappedByteBuffer byteBuffer = in.getChannel().map(FileChannel.MapMode.READ_ONLY, 0,
                    file.length());
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(byteBuffer);
            BigInteger bi = new BigInteger(1, md5.digest());
            value = bi.toString(16);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != in) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return value;
    }

    /**
     * 获取文件大小
     *
     * @param file
     * @return
     */
    public static long getFileLength(File file) throws IOException {
        FileInputStream fis = null;
        fis = new FileInputStream(file);
        return fis.available();
    }

    /**
     * 读取文件到二进制
     *
     * @author WikerYong Email:<a href="#">yw_312@foxmail.com</a>
     * @version 2012-3-23 上午11:47:06
     * @param file
     * @return
     * @throws IOException
     */
    public static byte[] getBytesFromFile(File file)
            throws IOException {
        InputStream is = new FileInputStream(file);
        long length = file.length();
        if (length > Integer.MAX_VALUE) {
            // File is too large
        }
        byte[] bytes = new byte[(int) length];
        // Read in the bytes
        int offset = 0;
        int numRead = 0;
        while (offset < bytes.length
                && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
            offset += numRead;
        }
        // Ensure all the bytes have been read in
        if (offset < bytes.length) {
            throw new IOException("不能读取文件: " + file.getName());
        }
        is.close();
        return bytes;
    }

    /**
     * 获取标准文件大小，如30KB，15.5MB
     *
     * @param file
     * @return
     * @throws IOException
     */
    public static String getFileSize(File file) throws IOException {
        long size = getFileLength(file);
        DecimalFormat df = new DecimalFormat("###.##");
        float f;
        if (size < 1024 * 1024) {
            f = (float) ((float) size / (float) 1024);
            return (df.format(new Float(f).doubleValue()) + " KB");
        } else {
            f = (float) ((float) size / (float) (1024 * 1024));
            return (df.format(new Float(f).doubleValue()) + " MB");
        }

    }

    /**
     * 复制文件
     *
     * @param f1
     *            源文件
     * @param f2
     *            目标文件
     * @throws Exception
     */
    public static void copyFile(File f1, File f2) throws Exception {
        int length = 2097152;
        FileInputStream in = new FileInputStream(f1);
        FileOutputStream out = new FileOutputStream(f2);
        FileChannel inC = in.getChannel();
        FileChannel outC = out.getChannel();
        ByteBuffer b = null;
        while (true) {
            if (inC.position() == inC.size()) {
                inC.close();
                outC.close();
            }
            if ((inC.size() - inC.position()) < length) {
                length = (int) (inC.size() - inC.position());
            } else {
                length = 2097152;
            }
            b = ByteBuffer.allocateDirect(length);
            inC.read(b);
            b.flip();
            outC.write(b);
            outC.force(false);
        }
    }

    /**
     * 检查文件是否存在
     *
     * @param fileName
     * @return
     * @throws IOException
     */
    public static boolean existFile(String fileName) throws IOException {
        File file = new File(fileName);
        if (!file.exists()) {
            throw new IOException("文件未找到:" + fileName);
        }
        return file.exists();
    }


    /**
     * 删除文件
     *
     * @param fileName
     */
    public static void deleteFile(String fileName) throws IOException {
        File file = new File(fileName);
        if (!file.exists()) {
            throw new IOException("文件未找到:" + fileName);
        }
        file.delete();
    }

    /**
     * 读取文件到字符串
     *
     * @param fileName
     * @return
     * @throws IOException
     */
    public static String readFile(String fileName) throws IOException {
        File file = new File(fileName);
        if (!file.exists()) {
            throw new IOException("文件未找到:" + fileName);
        }

        BufferedReader in = new BufferedReader(new FileReader(file));
        StringBuffer sb = new StringBuffer();
        String str = "";
        while ((str = in.readLine()) != null) {
            sb.append(str);
        }
        in.close();
        return sb.toString();
    }

    /**
     * 获取目录所有所有文件和文件夹
     * @param fileName
     * @return
     * @throws IOException
     */
    public static List<File> listFiles(String fileName)
            throws IOException {
        File file = new File(fileName);
        if (!file.exists()) {
            throw new IOException("文件未找到:" + fileName);
        }
        return Arrays.asList(file.listFiles());
    }

    /**
     * 创建目录
     * @param dir
     */
    public static void mkdir(String dir) {
        String dirTemp = dir;
        File dirPath = new File(dirTemp);
        if (!dirPath.exists()) {
            dirPath.mkdir();
        }
    }

    /**
     * 新建文件
     *
     * @param fileName String 包含路径的文件名 如:E:\phsftp\src\123.txt
     * @param content String 文件内容
     */
    public static void createNewFile(String fileName, String content) throws IOException {
        String fileNameTemp = fileName;
        File filePath = new File(fileNameTemp);
        if (!filePath.exists()) {
            filePath.createNewFile();
        }
        FileWriter fw = new FileWriter(filePath);
        PrintWriter pw = new PrintWriter(fw);
        String strContent = content;
        pw.println(strContent);
        pw.flush();
        pw.close();
        fw.close();

    }


    /**
     * 复制单个文件，传统方式
     *
     * @param srcFile 包含路径的源文件 如：E:/phsftp/src/abc.txt
     * @param dirDest 目标文件目录；若文件目录不存在则自动创建 如：E:/phsftp/dest
     * @throws IOException
     */
    public static void copyFile(String srcFile, String dirDest)
            throws IOException {
        FileInputStream in = new FileInputStream(srcFile);
        mkdir(dirDest);
        FileOutputStream out = new FileOutputStream(dirDest + "/" + new File(srcFile).getName());
        int len;
        byte buffer[] = new byte[1024];
        while ((len = in.read(buffer)) != -1) {
            out.write(buffer, 0, len);
        }
        out.flush();
        out.close();
        in.close();
    }


    /**
     * 读取数据
     *
     * @param inSream
     * @param charsetName
     * @return
     * @throws Exception
     */
    public static String readData(InputStream inSream, String charsetName)
            throws IOException {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = -1;
        while ((len = inSream.read(buffer)) != -1) {
            outStream.write(buffer, 0, len);
        }
        byte[] data = outStream.toByteArray();
        outStream.close();
        inSream.close();
        return new String(data, charsetName);
    }

    /**
     * 一行一行读取文件，适合字符读取，若读取中文字符时会出现乱码
     *
     * @param path
     * @return
     * @throws Exception
     */
    public static Set<String> readFileLine(String path)
            throws IOException {
        Set<String> datas = new HashSet<String>();
        FileReader fr = new FileReader(path);
        BufferedReader br = new BufferedReader(fr);
        String line = null;
        while ((line = br.readLine()) != null) {
            datas.add(line);
        }
        br.close();
        fr.close();
        return datas;
    }

    /**
     * 读取文件内容，作为字符串返回
     */
    public static String readFileAsString(String filePath) throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            throw new FileNotFoundException(filePath);
        }

        if (file.length() > 1024 * 1024 * 1024) {
            throw new IOException("File is too large");
        }

        StringBuilder sb = new StringBuilder((int) (file.length()));
        // 创建字节输入流
        FileInputStream fis = new FileInputStream(filePath);
        // 创建一个长度为10240的Buffer
        byte[] bbuf = new byte[10240];
        // 用于保存实际读取的字节数
        int hasRead = 0;
        while ( (hasRead = fis.read(bbuf)) > 0 ) {
            sb.append(new String(bbuf, 0, hasRead));
        }
        fis.close();
        return sb.toString();
    }

    /**
     * 根据文件路径读取byte[] 数组
     */
    public static byte[] readFileByBytes(String filePath) throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            throw new FileNotFoundException(filePath);
        } else {
            ByteArrayOutputStream bos = new ByteArrayOutputStream((int) file.length());
            BufferedInputStream in = null;

            try {
                in = new BufferedInputStream(new FileInputStream(file));
                short bufSize = 1024;
                byte[] buffer = new byte[bufSize];
                int len1;
                while (-1 != (len1 = in.read(buffer, 0, bufSize))) {
                    bos.write(buffer, 0, len1);
                }

                byte[] var7 = bos.toByteArray();
                return var7;
            } finally {
                try {
                    if (in != null) {
                        in.close();
                    }
                } catch (IOException var14) {
                    var14.printStackTrace();
                }

                bos.close();
            }
        }
    }



}


