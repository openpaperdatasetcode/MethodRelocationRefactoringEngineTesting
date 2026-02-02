package test;
import java.io.IOException;import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface MyAnnotation {String value() default "call:TargetClass.Inner::callSuperWithArgs";}
abstract class SourceClass {public int process(TargetClass target) throws IOException {TargetClass.Inner<String> inner = target.new Inner<>();int result = 0;transient int count = 0;
do {if (inner.field == 1) {result += inner.field;}count++;} while (count < 3);
for (int i = 0; i < 3; i++) {result += inner.getValue();}
{Object methodResult = callPrivateInstanceMethod();}
return result;}
private Object callPrivateInstanceMethod() {super.toString();super.hashCode();super.equals(null);return "result";}}
protected class TargetClass extends ParentClass {class Inner<T> {int field = 1;
public int getValue() {return field;}
public String callSuperWithArgs(String arg) {return super.toString() + arg;}}}
class ParentClass {}
