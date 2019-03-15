package com.wjk.base.java.classloader;

import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandlerFactory;

public class MyURLClassLoader extends URLClassLoader {

	private String packageName = "com.wjk.base.java.classloader";

	public MyURLClassLoader(URL[] urls) {
		super(urls);
	}
	
	/*public MyURLClassLoader(URL[] urls,ClassLoader parent) {
		super(urls,parent);
	}
	public MyURLClassLoader(URL[] urls,ClassLoader parent, URLStreamHandlerFactory factory) {
		super(urls,parent,factory);
	}*/

	
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		
		Class<?> aClass = findLoadedClass(name);
		
		if (aClass != null) {
			
			return aClass;
		}
	
		if (!packageName.startsWith(name)) {
			return super.loadClass(name);
			
		}else {
			return findClass(name);
		}
		
		
	}
	
}
