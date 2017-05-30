package com.lichking.main;

import com.lichking.utils.Processor;

public class Main {

	public static void main(String[] args) {
		Processor p = new Processor();
		while(true){
			try{
				p.process();
				Thread.sleep(5);
			}catch (Exception e) {
				continue;
			}
		}
		
	}

}
