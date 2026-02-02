package test;
import java.util.ArrayList;import java.util.List;
class SourceClass {private String sourceField;private TargetClass targetInstance;
static class StaticNested {private int staticNestedField;}
class MemberInner {public List<String> methodToMove() {List<String> list = new ArrayList<>();list.add(sourceField); // access_outer_privatelist.add(targetInstance.targetField); // source contains target's fieldStaticNested sn = new StaticNested(); // variable callint val = sn.staticNestedField;InnerOfTarget iot = new InnerOfTarget(3); // ConstructorInvocation with 3return list;}}}
public class TargetClass {private String targetField;
public class InnerOfTarget {private int num;
private InnerOfTarget(int n) { // private constructorthis.num = n;this.targetField = "test"; // this.field}
public List<String> methodToMove() { return new ArrayList<>(); } // override_violation}}