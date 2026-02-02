package samepkg;
public class SourceClass {private TargetClass targetField; // Per condition: source contains target's field
public class MemberInnerClass {// With bounds: generic type constraint via local classclass BoundedType<T extends Number> {T value;}
public void processTarget() {BoundedType<Integer> boundedObj = new BoundedType<>();boundedObj.value = 10;
// 2 private ThisExpressionprivate SourceClass outerThis1 = SourceClass.this;private MemberInnerClass innerThis1 = this;
// Super constructor invocation (anonymous class)ParentClass parent = new ParentClass() {};
// If statementif (targetField != null) {targetField.memberInnerTarget.init();}
// While statementint count = 0;while (count < 3) {targetField.updateCount(count);count++;}
// Switch statementString type = targetField.getType();switch (type) {case "A" -> boundedObj.value += 5;case "B" -> boundedObj.value *= 2;default -> {}}
// Variable call + uses_outer_thisouterThis1.targetField.setData(boundedObj.value.toString());innerThis1.helper();
// 2nd private ThisExpressionprivate SourceClass outerThis2 = SourceClass.this;private MemberInnerClass innerThis2 = this;
// Call inner class's default method in for loopfor (int i = 0; i < 2; i++) {TargetClass.MemberInnerTarget.method(targetField, i);}
// Local inner classclass LocalInnerClass {void process() {targetField.memberInnerTarget.execute();}}new LocalInnerClass().process();}
private void helper() {}}}
abstract class TargetClass {private String type;private int count;private String data;
public class MemberInnerTarget {public void init() {}public void execute() {}
// Call method target: default normal methodpublic static void method(TargetClass target, int num) {target.count += num;}}
public MemberInnerTarget memberInnerTarget = new MemberInnerTarget();
public String getType() {return type;}
public void setType(String type) {this.type = type;}
public void updateCount(int count) {this.count = count;}
public void setData(String data) {this.data = data;}}
class ParentClass {}