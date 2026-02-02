package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface MyAnnotation {}
public sealed class SourceClass permits SubSourceClass {public TargetClass targetField;
public void someMethod() {class LocalInner {class InnerRec {@MyAnnotationprotected Object moveMethod(int param) {assert TargetClass.StaticField == 1;
switch (param) {case 1:return new OthersClass() {protected abstract int abstractMethod();{this.abstractMethod();}};default:return targetField.staticNestedClass.getValue();}}
protected Object moveMethod(String param) {return null;}}}}
static class StaticNested {void useTarget() {targetField.staticNestedClass.process();}}}
final class SubSourceClass extends SourceClass {}
public class TargetClass {public static int StaticField = 1;
public static class StaticNestedClass {class TargetInner {protected Object moveMethod(int param) { return null; }protected Object moveMethod(String param) { return null; }}
Object getValue() { return new Object(); }void process() {}}}
class OthersClass {}
