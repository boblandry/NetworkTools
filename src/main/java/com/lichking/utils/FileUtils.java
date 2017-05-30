package com.lichking.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FileUtils {

	public static final String PATH = "E:\\Cache\\NetworkTools\\NetworkReport.txt";

	public static void writeToDisk(int lost_times, int max_delay, int avg_delay, int P_lost) throws IOException {

		String content = generateContent(lost_times, max_delay, avg_delay, P_lost);
		File file = new File(PATH);
		if (!file.exists()) {
			file.createNewFile();
		}
		List<String> origin_list = getOriginContent(file);
		try {
            Writer w=new FileWriter(PATH,false);
            w.write(content);
            for(String orgin : origin_list){
            	w.write(orgin);
            	w.write("\r\n");
            }
            w.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            System.out.println(e.getMessage());
        }
		

	}

	public static String generateContent(int lost_times, int max_delay, int avg_delay, int P_lost){
		String content = "";
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date_string = sdf.format(date);
		content += date_string + "  ";
		content += "丢包次数：" +lost_times + "  ";
		content += "丢包率：" +P_lost + "  ";
		content += "最大延迟：" +max_delay + "  ";
		content += "平均延迟：" +avg_delay + "\r\n";
		return content;
	}
	
	public static List<String> getOriginContent(File file){
		List<String> list = new ArrayList<String>();
		
		BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            while ((tempString = reader.readLine()) != null) {
                list.add(tempString);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        return list;
		
	}

}
