package test;
import java.util.ArrayList;import java.util.List;
interface SourceInterface {class MemberInner {class NestedInner {private final int count = 5;
public final List<String> process() {class LocalInner {final List<String> getList() {List<String> result = new ArrayList<>();int num = TargetInterface.NestedTargetInner.value;
if (num > 0) {result.add("Positive");} else {result.add("Non-positive");}
switch (num) {case 1:result.add("One");break;case 2:result.add("Two");break;default:result.add("Other");}
String str = "Item";result.add(str + num);result.add(String.valueOf(count));
return getList(10);}
final List<String> getList(int limit) {return new ArrayList<>(limit);}}return new LocalInner().getList();}}}}
default interface TargetInterface {class NestedTargetInner {static int value = 3;
class TargetLocalContainer {// Target for moving LocalInner.getList()}}}