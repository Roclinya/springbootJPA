package hello;

import java.util.Arrays;
import java.util.Scanner;

public class HelloWorld {
	public static void main(String[] args) {
		Greeter greeter = new Greeter();
		System.out.println(greeter.sayHello());
	      System.out.println("请输入过河人数:\n");
	        Scanner scanner=new Scanner(System.in);
	        int n=scanner.nextInt();
	        System.out.println("请分别输入这"+n+"个人过河时间");
	        int[] arr=new int[n];
	        for(int i=0;i<arr.length;i++){
	            arr[i]=scanner.nextInt();
	        }
	        Arrays.sort(arr);//数组从小到大排序
	        System.out.println(shorts(arr,n));
	}
	
	 static int shorts(int arr[],int n) {
	        int end = n;//n表示人数
	        int times = 0;//最短时间和
	        if (1 == n) {
	            return arr[n - 1];
	        }
	        if (2==n){
	            return arr[n-1];
	        }
	        if (3==n){
	            return arr[0]+arr[1]+arr[2];
	        }
	        if (n>=4){
	            //a是最快，b次最快，z最慢，y次最慢
	            int a=arr[0],
	                    b=arr[1],
	                    z=arr[n-1],
	                    y=arr[n-2];
	            //a先把b运过去，然后最快的a把z,y分别别来回运过去表示2*a+z+y
	            //a先把b运过去，然后a过来，z和y一块过去，b过来再把a运过去
	            if (2*a+z+y>2*b+a+z){
	                times=2*b+a+z;
	                end=end-2;//上面的times只是把前四个里面的最慢和次最慢运过去了，所以在下面的代码中再调用
	            }else{
	                times=2*a+z+y;
	                end=end-2;
	            }
	            times=times+shorts(arr,end);//此处递归调用方法
	        }
	        return times;
	    }
}