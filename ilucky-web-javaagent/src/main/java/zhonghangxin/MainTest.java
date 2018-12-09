package zhonghangxin;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class MainTest {

	public static void main(String[] args) {
		AtomicInteger currClient = new AtomicInteger(0);
		int i = currClient.incrementAndGet() % 2;
		System.out.println(i);
		i = currClient.incrementAndGet() % 2;
		System.out.println(i);
		i = currClient.incrementAndGet() % 2;
		System.out.println(i);
		
		String str = "(10.0.1.225(10.0.3.42(10.0.1.224)))";
	//	List<String> callstack = splitServiceCallStack(str);
//		System.out.println(callstack);
//		
//		callstack = new ArrayList<String>();
//		callstack.add("a");
//		callstack.add("b");
//		callstack.add("c");
//		callstack.add("d");
//		for (int i = 0; i < callstack.size() - 1; i += 2) {
//			String svc = (String) callstack.get(i);
//			String nextStack = (String) callstack.get(i + 1);
//			System.out.println(svc + "-" + nextStack);
//		}
		// 10.0.1.224-10.0.1.225
		// rval = this.invoker.request(svc, req.getRequestURI(),
		// getParameterMap(req.getParameterMap(), currHost), "HEADER_SVC",
		// nextStack);
		
//		int i = 10;
//		if(i<99) {
//			System.out.println(true);
//		} else if(i>3) {
//			System.out.println(false);
//		}

	}

	public static List<String> splitServiceCallStack(String services) {
		int braceLevel = 0;
		List rval = new ArrayList();
		StringBuilder currBuilder = null;
		for (int i = 0; i < services.length(); i++) {
			char c = services.charAt(i);
			if (c == '(') {
				braceLevel++;
				if (braceLevel == 1) {
					currBuilder = new StringBuilder();
					System.out.println("=== new StringBuilder()======>"+c);
				} else if (currBuilder != null) {
					currBuilder.append(c);
				    System.out.println("===currBuilder.append(c)======>"+c);
				}
			} else if (c == ')') {
				if (braceLevel > 0) {
					braceLevel--;
					if (braceLevel == 0) {
						if (currBuilder != null) {
							int pos = currBuilder.indexOf("(");
							if (pos > 0) {
								rval.add(currBuilder.substring(0, pos).trim());
								rval.add(currBuilder.substring(pos));
							} else {
								rval.add(currBuilder.toString());
								rval.add(null);
							}
						}
						currBuilder = null;
					} else if (currBuilder != null) {
						currBuilder.append(c);
					}
				}
			} else if (currBuilder != null) {
				currBuilder.append(c);
			}
		}

		return rval;
	}
}
