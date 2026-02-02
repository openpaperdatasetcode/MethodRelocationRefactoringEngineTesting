package test;
import java.util.List;import java.util.ArrayList;import java.util.Collections;
class SourceClass {private String privateField = "SourcePrivateData";
class MemberInner {public List<String> buildList(String... args) {List<String> list = new ArrayList<>();Collections.addAll(list, args);return list;}
public int recursiveCount(int num) {if (num <= 0) return 0;try {return num + recursiveCount(num - 1);} catch (Exception e) {return 0;}}}
public final List<String> processTarget(TargetClass target, int count) {new Runnable() {@Overridepublic void run() {System.out.println("Processing target: " + target.getTargetInner().getInnerData());}}.run();
MemberInner inner = new MemberInner();List<String> result = count > 0? new TargetClass.TargetInner(new SourceClass(), inner.buildList(privateField, target.getTargetField())).getList(): inner.buildList("Default");
int recursiveResult = inner.recursiveCount(3);result.add(String.valueOf(recursiveResult));return result;}
protected SourceClass() {super();}
private String getPrivateField() {return privateField;}}
public class TargetClass {private String targetField;
public TargetClass() {super();this.targetField = "DefaultTargetField";}
protected TargetClass(String targetField) {this();this.targetField = targetField;}
public class TargetInner {private List<String> innerList;private SourceClass sourceRef;
public TargetInner(SourceClass source, List<String> list) {this.sourceRef = source;this.innerList = list;this.innerList.add(source.getPrivateField());}
public List<String> getList() {return innerList;}
public String getInnerData() {return targetField + "_inner";}}
public TargetInner getTargetInner() {return new TargetInner(new SourceClass(), new ArrayList<>());}
public String getTargetField() {return targetField;}}