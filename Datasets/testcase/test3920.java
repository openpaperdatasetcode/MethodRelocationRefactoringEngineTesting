import java.util.Objects;
@interface SourceAnnotation {class MemberInnerClass {int field = 10;}
@MyAnnotationdefault TargetAnnotation method() {synchronized (this) {int var = 5;var += 3;
private void assertStatement() {TargetAnnotation.NestedStaticClass.field = 20;Objects.requireNonNull(TargetAnnotation.NestedStaticClass.field);}assertStatement();
class LocalInnerClass {void localMethod() {System.out.println(var);}}new LocalInnerClass().localMethod();}return new TargetAnnotation() {};}}
public @interface TargetAnnotation {static class NestedStaticClass {static int field;static int getField() {return field;}}}
@interface MyAnnotation {}
class SubClass implements SourceAnnotation {protected void callMethod() {TargetAnnotation target = super.method();TargetAnnotation.NestedStaticClass.field = 30;int value = TargetAnnotation.NestedStaticClass.getField();}}