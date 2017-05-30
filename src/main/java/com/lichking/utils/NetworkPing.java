package com.lichking.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class NetworkPing {
    
	public static final String DELAY="delay";
	
	public static final String TIMEOUT="timeout";
	
    public static List<Integer> ping(String ipAddress, int pingTimes, int timeOut) {
    	List<Integer> list = new ArrayList<Integer>();
    	
    	BufferedReader in = null;  
        Runtime r = Runtime.getRuntime();
        // 将要执行的ping命令,此命令是windows格式的命令  
        String pingCommand = "ping " + ipAddress + " -n " + pingTimes    + " -w " + timeOut;
        try {    
            //System.out.println(pingCommand);
            // 执行命令并获取输出 
            Process p = r.exec(pingCommand);   
            if (p == null) {    
                return null;   
            }
            // 逐行检查输出,计算类似出现=23ms TTL=62字样的次数  
            in = new BufferedReader(new InputStreamReader(p.getInputStream(),Charset.forName("GBK")));   
            
            String line = null;   
            while ((line = in.readLine()) != null) {    
            	String str = getDelay(line);
            	if(str == null)
            		continue;
            	if(str.equals(""))
            		list.add(-1);
            	else
            		list.add(Integer.parseInt(str));
            }   
        } catch (Exception ex) {   
            ex.printStackTrace();   // 出现异常则返回假  
            return null;  
        } finally {   
            try {    
                in.close();   
            } catch (IOException e) {    
                e.printStackTrace();   
            }  
        }
    	
    	return list;
    }
    
    private static String getDelay(String line){
    	if(line.contains("时间") && line.contains("TTL")){
    		return line.substring(line.indexOf("时间")+3, line.indexOf("ms"));
    	}else if(line.contains("请求超时")){
    		return "";
    	}else
    		return null;
    }
    
    public static List<Integer> pingRouter(){
    	return ping("192.168.1.1", 20, 5000);
    }
	
}
