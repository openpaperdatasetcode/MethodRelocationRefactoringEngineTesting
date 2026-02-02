package test;
import java.lang.reflect.Method;import java.util.ArrayList;import java.util.List;
class SourceClass extends ParentSourceClass {private TargetClass targetField = new TargetClass();private int sourceField = 0;
class SourceMemberInner {private int innerField;
@Overridepublic TargetClass callMethod() {return targetField.new TargetInnerRec().processField(sourceField);}}
void createLocalInner() {class SourceLocalInner {void useRecursion() throws Exception {Method recursiveMethod = SourceClass.class.getMethod("recursiveList", int.class);recursiveMethod.invoke(SourceClass.this, 3);}}new SourceLocalInner().useRecursion();}
public final synchronized int getInstanceValue() {return this.sourceField++;}
public List<String> recursiveList(int depth) {List<String> result = new ArrayList<>();if (depth <= 0) return result;
TargetClass.TargetInnerRec inner1 = new TargetClass.TargetInnerRec(targetField, "val1");TargetClass.TargetInnerRec inner2 = new TargetClass.TargetInnerRec(targetField, "val2");TargetClass.TargetInnerRec inner3 = new TargetClass.TargetInnerRec(targetField, "val3");
switch (depth) {case 1:result.add(inner1.field);break;case 2:result.add(inner2.field);break;case 3:result.add(inner3.field);break;}
result.addAll(recursiveList(depth - 1));sourceField = getInstanceValue();return result;}}
class ParentSourceClass {}
final class TargetClass extends ParentTargetClass {class TargetInnerRec {String field;TargetClass outer;
public TargetInnerRec(TargetClass outer, String field) {this.outer = outer;this.field = field;}
TargetClass processField(int value) {outer.targetField = value;return outer;}}
int targetField;}
class ParentTargetClass {}
