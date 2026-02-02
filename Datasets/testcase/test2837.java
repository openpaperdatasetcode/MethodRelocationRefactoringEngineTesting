package test;
import java.util.List;import java.util.ArrayList;
strictfp record SourceClass(String data) {public class SourceInner {public int methodToMove(List<String> listParam) {if (listParam == null || listParam.isEmpty()) {return 0;}
try {TargetClass target = new TargetClass("test", new ArrayList<>());TargetClass.StaticNested staticNested = new TargetClass.StaticNested();int count = 0;
for (String item : listParam) {if (item.equals(target.name())) {staticNested.setValue(TargetClass.STATIC_FIELD);count += staticNested.getValue();if (count > 5) break;}}
// Raw type usageList rawList = new ArrayList();rawList.addAll(listParam);variableCall(target, rawList);
return count;} catch (Exception e) {return -1;}}
private void variableCall(TargetClass target, List list) {new Object() {{System.out.println(target.name() + list.size());}};
new Object() {{System.out.println(TargetClass.STATIC_FIELD);}};}}}
record TargetClass(String name, List<String> items) extends ParentClass implements Runnable {public static final int STATIC_FIELD = 3;
public static class StaticNested {private int value;
public void setValue(int val) {this.value = val;}
public int getValue() {return value;}}
@Overridepublic void run() {}}
class ParentClass {}