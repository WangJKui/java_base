package com.wjk.base.java.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;

import org.junit.Test;

public class StreamTest {

	/**
	 * 字节流
	 */
	@Test
	public void test1()  {
		/**
		 * 将 data.txt 文件 复制到 b.txt 中
		 */
		try {
			//1、创建源和目标
			File srcFile = new File("D:/test"+File.separator+"data.txt");
			File descFile = new File("D:/test"+File.separator+"b.txt");
			//2、创建输入输出流对象
			InputStream in = new FileInputStream(srcFile);
			OutputStream out = new FileOutputStream(descFile);
			//3、读取和写入操作
			byte[] buffer = new byte[10];//创建一个容量为 10 的字节数组，存储已经读取的数据
			int len = -1;//表示已经读取了多少个字节，如果是 -1，表示已经读取到文件的末尾
			while((len=in.read(buffer))!=-1){
				//打印读取的数据
				System.out.println(new String(buffer,0,len));
				//将 buffer 数组中从 0 开始，长度为 len 的数据读取到 b.txt 文件中
				out.write(buffer, 0, len);
			}
			//4、关闭流资源
			out.close();
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 字符流
	 */
	@Test
	public void test2()  {
		/**
		 * 将 data.txt 文件 复制到 c.txt 中
		 */
		try {
			File srcFile = new File("D:/test"+File.separator+"data.txt");
			File descFile = new File("D:/test"+File.separator+"c.txt");
			//2、创建字符输入输出流对象
			Reader in = new FileReader(srcFile);
			Writer out = new FileWriter(descFile);
			//3、读取和写入操作
			char[] buffer = new char[10];//创建一个容量为 10 的字符数组，存储已经读取的数据
			int len = -1;//表示已经读取了多少个字节，如果是 -1，表示已经读取到文件的末尾
			while((len=in.read(buffer))!=-1){
				out.write(buffer, 0, len);
			}

			//4、关闭流资源
			out.close();
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * 字节缓冲流
	 */
	@Test
	public void test3(){
		try {
			//字节缓冲输入流
			BufferedInputStream bis = new BufferedInputStream(
					new FileInputStream("D:/test"+File.separator+"data.txt"));
			//定义一个字节数组，用来存储数据
			byte[] buffer = new byte[1024];
			int len = -1;//定义一个整数，表示读取的字节数
			while((len=bis.read(buffer))!=-1){
				System.out.println(new String(buffer,0,len));
			}
			//关闭流资源
			bis.close();

			//字节缓冲输出流
			BufferedOutputStream bos = new BufferedOutputStream(
					new FileOutputStream("D:/test"+File.separator+"d.txt"));
			bos.write("ABCD".getBytes());
			bos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * 字符缓冲流
	 */
	@Test
	public void test4(){
		try {
			//字符缓冲输入流
			BufferedReader br = new BufferedReader(
					new FileReader("D:/test"+File.separator+"data.txt"));
			char[] buffer = new char[10];
			int len = -1;
			while((len=br.read(buffer))!=-1){
				System.out.println(new String(buffer,0,len));
			}
			br.close();

			//字符缓冲输出流
			BufferedWriter bw = new BufferedWriter(
					new FileWriter("D:/test"+File.separator+"e.txt"));
			bw.write("ABCD");
			bw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 转换流
	 */
	@Test
	public void test5() {
		try {
			/**
	         * 将 a.txt 文件 复制到 b.txt 中
	         */
	        //1、创建源和目标
	        File srcFile = new File("D:/test"+File.separator+"data.txt");
	        File descFile = new File("D:/test"+File.separator+"f.txt");
	        //2、创建字节输入输出流对象
	        InputStream in = new FileInputStream(srcFile);
	        OutputStream out = new FileOutputStream(descFile);
	        //3、创建转换输入输出对象
	        Reader rd = new InputStreamReader(in);
	        Writer wt = new OutputStreamWriter(out);
	        //3、读取和写入操作
	        char[] buffer = new char[10];//创建一个容量为 10 的字符数组，存储已经读取的数据
	        int len = -1;//表示已经读取了多少个字符，如果是 -1，表示已经读取到文件的末尾
	        while((len=rd.read(buffer))!=-1){
	            wt.write(buffer, 0, len);
	        }
	        //4、关闭流资源
	        rd.close();
	        wt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
