package leetCode;

import java.util.PriorityQueue;

public class Test {

	public static void main(String[] args) {
		
		   //创建优先队列
        PriorityQueue<Integer> numbers = new PriorityQueue<>();
        //使用add()方法
        numbers.add(4);
        numbers.add(2);       
        //使用offer()方法
        numbers.offer(1);
        System.out.println("PriorityQueue: " + numbers);

        int numberQuery = numbers.peek();
        System.out.println("訪問元素: " + numberQuery);
        
        //使用remove()方法
        boolean result = numbers.remove(2);
        System.out.println("元素2是否已删除? " + result);

        //使用poll()方法
        int numberPoll = numbers.poll();
        System.out.println("使用poll()删除的元素: " + numberPoll);
        
        System.out.println("PriorityQueue: " + numbers);
        
        
        
	    }
	}

