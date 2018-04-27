package com.dxc.pai.service;

import java.io.File;

import com.dxc.pai.util.FileIO;


public class RetrieveData {

	public static void main(String[] args) {
		long cuurr = System.currentTimeMillis();
		System.out.println(cuurr);
		System.out.println(cuurr+1000*60*60*24);
		System.out.println((1523808000000L - 1523721600000L)/(1000*24));
		
		File f = new File("C:/Users/wenc/Desktop/retrievedData.txt");			
		FileIO.appendWriteFileWithString(f,"jisso\r\n");

	}
	public void run() {
		while(true) {
			
		}
	}

}
