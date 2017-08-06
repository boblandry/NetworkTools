package com.lichking.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Processor {

	public void process(){
		
		List<Integer> list = NetworkPing.pingRouter();
		//丢包次数
		int lost_times = getLostTimes(list);
		List<Integer> delay_list = getDelayList(list);
		//最大延迟
		int max_delay = getMaxDelay(delay_list);
		//平均延迟
		int avg_delay = getAvgDelay(delay_list);
		//丢包率  XX%
		int P_lost = 100*lost_times/list.size();
		
		if(lost_times > 0 || max_delay > 50 || avg_delay > 20){
			try {
				FileUtils.writeToDisk(lost_times, max_delay, avg_delay, P_lost);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public int getLostTimes(List<Integer> list){
		int count = 0;
		for(int i : list){
			if(i == -1)
				count++;
		}
		return count;
	}
	
	public List<Integer> getDelayList(List<Integer> list){
		List<Integer> delay_list = new ArrayList<Integer>();
		for(int i : list){
			if(i != -1)
				delay_list.add(i);
		}
		return delay_list;
	}
	
	public int getMaxDelay(List<Integer> list){
		int delay = 0;
		for(int i : list){
			if(i > delay)
				delay = i;
		}
		return delay;
	}
	
	public int getAvgDelay(List<Integer> list){
		int delay = 0;
		for(int i : list){
			delay += i;
		}
		return delay/list.size();
	}
	
}
