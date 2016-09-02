package com.ieven.ext.quartz.service;

import org.quartz.SchedulerException;

import com.ieven.ext.quartz.exception.ScheduleInitException;

public class DefaultScheduleServiceTest {
	
	public void scheduleServiceTest(){
		
	}
	
	public static void main(String[] args) {
		// 启动任务调度
		ScheduleService service = new DefaultScheduleService();
		try {
			service.start();
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			throw new ScheduleInitException("任务调度启动失败", e);
		}
		
//		RegisterConfig config = new TriggerConfigWithFileSystemResource();
//		((TriggerConfigWithFileSystemResource)config).addListener(new ConfigListener() {
//			
//			@Override
//			public void onModify(WatchEvent<Path> e) {
//				// TODO Auto-generated method stub
//				try {
//					service.restart();
//				} catch (SchedulerException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//					System.out.println("重启失败了");
//				}
//			}
//			
//			@Override
//			public void onDelete(WatchEvent<Path> e) {
//				// TODO Auto-generated method stub
//				
//			}
//			
//			@Override
//			public void onCreate(WatchEvent<Path> e) {
//				// TODO Auto-generated method stub
//				
//			}
//		});
//		
	}
}
