import java.util.ArrayList;import java.util.List;
public class SourceClass<T> {static class Nested1 {}static class Nested2 {}
final int methodToMove(TargetClass... params) throws Exception {List rawList = new ArrayList();TargetClass tc = new TargetClass();
for (TargetClass param : params) {rawList.add(param.getValue());}
int value = tc.getNum();switch (value) {case 1:System.out.println("One");break;case 2:System.out.println("Two");break;default:break;}
if (rawList.isEmpty()) {throw new Exception("Empty list");}
return value;}}
class TargetClass {private int num;private String value;
int getNum() { return num; }String getValue() { return value; }
Runnable runner = new Runnable() {public void run() {}};}