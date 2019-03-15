package com.wjk.base.java.classloader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class MyClassLoader extends ClassLoader {

	private String packageName = "com.wjk.base.java.classloader";
	private String classPath;

	public MyClassLoader(String classPath) {
		super();
		this.classPath = classPath;
	}
	
	protected Class<?> findClass(String name) throws ClassNotFoundException {
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
            @SuppressWarnings("resource")
			InputStream is = new FileInputStream(path);

			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			
			byte[] buffer = new byte[2048];
			
			int num = 0;
			
			while ((num = is.read(buffer)) != -1) {
				bos.write(buffer,0,num);
			}
			
			return bos.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
}
