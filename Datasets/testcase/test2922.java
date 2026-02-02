import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;import java.util.List;
@Retention(RetentionPolicy.RUNTIME)@interface MethodAnnotation {}
protected class SourceClass {static class StaticNested {static int staticField = 5;}
public void outerMethod() {class LocalInner {void useStaticField() {System.out.println(StaticNested.staticField);}}new LocalInner().useStaticField();}
@MethodAnnotationpublic TargetClass methodToMove(TargetClass target, List<String> strList) {if (target == null) {throw new NullPointerException();}
protected class ConstructorHelper {int field1;int field2;
ConstructorHelper(TargetClass t) {this.field1 = 2;this.field2 = 2;t.targetField1 = field1;t.targetField2 = field2;}}new ConstructorHelper(target);
this.outerMethod();TargetClass.MemberInner inner = target.new MemberInner();inner.process(strList);
return new TargetClass(target.targetField1, target.targetField2);}}
class TargetClass {int targetField1;int targetField2;
public TargetClass(int f1, int f2) {this.targetField1 = f1;this.targetField2 = f2;}
class MemberInner {void process(List<String> list) {System.out.println(list.size());}}}