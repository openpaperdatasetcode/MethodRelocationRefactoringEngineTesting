package test;
import java.util.List;import java.util.ArrayList;import java.util.Collection;
interface SourceInterface {class SourceInner {record SourceInnerRec(String content) {}
protected List<String> instanceMethod(TargetInterface.TargetInner... inners) {List<String> result = new ArrayList<>();
public class LocalInner<T extends Collection<String>> {void processInners(TargetInterface.TargetInner[] innerArr) {for (int i = 0; i < innerArr.length; i++) {TargetInterface.TargetInner current = innerArr[i];String fieldVal = current.innerField;result.add(fieldVal);}}}
LocalInner<ArrayList<String>> local = new LocalInner<>();local.processInners(inners);
for (TargetInterface.TargetInner inner : inners) {String varCall = inner.getInnerData();result.add(varCall);}
return result;}}}
interface TargetInterface extends ParentInterface {static class TargetInner {String innerField;
String getInnerData() {return innerField;}}}
interface ParentInterface {}