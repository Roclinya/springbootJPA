package leetCode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Java8Stream {

	public static void main(String[] args) {
		// Creating an IntStream
		IntStream stream = IntStream.range(0, 5);

		// 測試mapToObj by Int
//		// Creating a Stream of Strings
//		// Using IntStream mapToObj(IntFunction mapper)
//		// to store binary representation of
//		// elements in IntStream
//		Stream<String> stream1 = stream.mapToObj(num -> Integer.toBinaryString(num));
//		// Displaying an object-valued Stream
//		// consisting of the results of
//		// applying the given function.
//		stream1.forEach(System.out::println);

		// 測試mapToObj by map
//		Stream<Object> stream2 = stream.mapToObj(i -> {
//			HashMap<String, String> map = new HashMap<String, String>();
//			map.put("Anderson", String.valueOf(i + 1));
//			map.put("Wang", String.valueOf(i + 2));
//			map.put("Lin", String.valueOf(i + 3));
//
//			return map;
//		});
//
////		stream2.forEach(System.out::println);
//		System.out.println(stream2.collect(Collectors.toList()));

		// 測試mapToObj by object
		Stream<Object> stream3 = stream.mapToObj(i -> {
			Column column = new Column();
			column.setHeader("Header" + i);
			column.setType("Type" + i);
			return column;
		});
		List<Object> columnList = stream3.collect(Collectors.toList());
		System.out.println(columnList);

		Map[] columns = new Map[columnList.size()];
		columns = columnList.toArray(columns);
//			System.out.println(columns);

	

	}
}
