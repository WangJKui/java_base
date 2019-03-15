package com.wjk.base.java.classloader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class MyNetClassLoader extends ClassLoader {

	private String packageName = "com.wjk.base.java.classloader";
	private String classPath;

	public MyNetClassLoader(String classPath) {
		super();
		this.classPath = classPath;
	}

	protected Class<?> findClass(String name) throws ClassNotFoundException {

		Class<?> aClass = findLoadedClass(name);

		if (aClass != null) {

			return aClass;
		}

		if (packageName.startsWith(name)) {
			byte[] classData = getData(name);
			if(classData == null) {
				throw new ClassNotFoundException();
			}else {
				return defineClass(name,classData,0,classData.length);
			}
		}else {
			return super.loadClass(name);
		}
	}

	private byte[] getData(String className) {

		String path = classPath +File.separatorChar + className.replace('.', File.separatorChar) + ".class";

		try {
			
			URL url = new URL(path);
			
			InputStream is = url.openStream();

			ByteArrayOutputStream bos = new ByteArrayOutputStream();

			byte[] buffer = new byte[2048];

			int num = 0;

			while ((num = is.read(buffer)) != -1) {
				bos.write(buffer,0,num);
			}

			//deCode
			
			return bos.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}
	
	private byte[] deCode(byte[] src) {
		byte[] decode = null;
	
		//对src解码
		return decode;
		
	}

}
