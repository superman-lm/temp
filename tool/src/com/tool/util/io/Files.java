package com.tool.util.io;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

@SuppressWarnings("resource")
public class Files {
	
	public void create(File file) throws Exception {
		if (!file.exists()) {
			file.createNewFile();
		}
	}
	
	public void delete(File file) {
		file.delete();
	}
	
	public void remove(File file) {
		File[] files = file.listFiles();
		files[0].delete();
	}
	
	public int count(File file) {
		return file.listFiles().length;
	}
	
	public void mkdir(File file) {
		file.mkdir();
	}
	
	public boolean exist(File file) {
		return file.exists();
	}
	
	public String read(File file) throws Exception {
		FileInputStream fileInputStream = new FileInputStream(file);
		BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
		DataInputStream dataInputStream = new DataInputStream(bufferedInputStream);
		return dataInputStream.readUTF();
	}
	
	public void write(File file,String content) throws Exception {
		FileOutputStream fileOutputStream = new FileOutputStream(file);
		DataOutputStream dataOutputStream = new DataOutputStream(fileOutputStream);
		dataOutputStream.writeUTF(content);
	}
	
	
}
