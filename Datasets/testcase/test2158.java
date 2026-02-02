package test;
import java.util.List;import java.util.ArrayList;import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface MyAnnotation {}
private class SourceClass {private int outerPrivateField = 10;
class MemberInner {@MyAnnotationList<String> methodToMove(TargetClass target) {TargetClass.Inner inner = target.new Inner();
// Super constructor invocation in raw typeRawTypeSubclass rawSub = new RawTypeSubclass();super();
// Access outer private fieldint val = SourceClass.this.outerPrivateField;
// Variable callinner.variableCall();
// Use target's inner class fieldList<String> result = new ArrayList<>(inner.data);return result;}}}
class SuperClass {}
class RawTypeSubclass extends SuperClass {}
public class TargetClass {class Inner {List data; // Raw type field
void variableCall() {class LocalInner {}}}}