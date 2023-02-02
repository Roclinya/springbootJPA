package leetCode;

import java.util.ArrayList;
import java.util.stream.Stream;

public class ListObjectGet {

	public static void main(String[] args) {

		ListObject obj = new ListObject("value1", "value2", "value3");
		ListObject obj2 = new ListObject("value4", "value5", "value6");
		ArrayList<ListObject> list = new ArrayList<ListObject>();
		list.add(obj);
		list.add(obj2);
		Stream<ListObject> stream = list.stream();
//		stream.filter(e -> e != null).forEach(t -> {
		
		/* method1 java 8 Stream*/
			stream.forEach(t -> {
			System.out.println("結果->>>" + t.getObj1() + " : " + t.getObj2() + " : " + t.getObj3());
		});
		/* method2 */
//		for(ListObject t :list) {
//			System.out.println("結果->>>" + t.getObj1() + " : " + t.getObj2() + " : " + t.getObj3());
//		}
		
		

	}

}
