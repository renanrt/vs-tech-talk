package br.com.videosoft.pinpad.util;

import java.util.PriorityQueue;
import java.util.Queue;

import junit.framework.TestCase;

public class QueueTest extends TestCase{

	
	public void testQueue(){

		Queue<String> q = new PriorityQueue<String>();
		q.add("1");
		q.add("2");
		q.add("3");
		q.add("4");
		
		
		for (int i = 0; i < 5; i++) {
			System.err.println(q.poll());
		}
		
	}
	
}
