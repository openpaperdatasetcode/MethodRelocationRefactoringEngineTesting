package test;
import java.util.ArrayList;import java.util.List;import java.io.IOException;
protected class SourceClass {public static class StaticNested {public static List<String> processList(List<String> list) {return new ArrayList<>(list);}}
private List<String> process(TargetClass target) throws IOException {return process(target, new ArrayList<>());}
private List<String> process(TargetClass target, List<String> initial) throws IOException {// Local inner classclass TargetProcessor {List<String> processInner(TargetClass.StaticNested.InnerRec rec) {List<String> result = new ArrayList<>();result.add(rec.data);return result;}}TargetProcessor processor = new TargetProcessor();
// Super constructor invocation in subclassTargetSubClass subTarget = new TargetSubClass();
// For statementList<String> result = new ArrayList<>(initial);for (int i = 0; i < 2; i++) {// Access instance fieldtarget.counter++;result.add(String.valueOf(target.counter));}
// Static method from sub_class in collection operationsresult.addAll(TargetSubClass.staticProcess(target.getNestedList()));
// Static SwitchStatement with 2 cases (same package target)static switch (target.type) {case 1:TargetClass.StaticNested.InnerRec rec1 = target.new StaticNested().new InnerRec();rec1.data = this.toString(); // this.field referenceresult.addAll(processor.processInner(rec1));break;case 2:TargetClass.StaticNested.InnerRec rec2 = target.new StaticNested().new InnerRec();rec2.data = this.toString(); // this.field referenceresult.addAll(processor.processInner(rec2));break;}
// Variable call - access target's inner rec fieldfor (TargetClass.StaticNested.InnerRec rec : target.nestedRecs) {result.add(rec.data);}
return result;}}
non-sealed class TargetClass extends ParentClass {public int counter;public int type;public List<StaticNested.InnerRec> nestedRecs = new ArrayList<>();
public static class StaticNested {public class InnerRec {public String data;}}
public List<String> getNestedList() {List<String> list = new ArrayList<>();nestedRecs.forEach(rec -> list.add(rec.data));return list;}}
class TargetSubClass extends TargetClass {public TargetSubClass() {super(); // Super constructor invocation}
public static List<String> staticProcess(List<String> list) {List<String> processed = new ArrayList<>();list.forEach(item -> processed.add("sub_" + item));return processed;}}
class ParentClass {}