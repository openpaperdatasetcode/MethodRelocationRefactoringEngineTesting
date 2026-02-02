package test;
import java.lang.reflect.Method;
strictfp class SourceClass {private int privateField = 10;
public Object overloadedMethod(TargetClass target) {class LocalInner {Object process(TargetClass t) {assert t != null : "Target cannot be null";return t.getInner().getValue();}}
LocalInner local = new LocalInner();new Runnable() {@Overridepublic void run() {System.out.println(privateField);}}.run();
try {Method method = TargetClass.class.getMethod("getInner");TargetClass.Inner inner = (TargetClass.Inner) method.invoke(target);return inner.getValue() + privateField;} catch (Exception e) {return null;}}
public Object overloadedMethod(TargetClass target, String extra) {SubTargetClass sub = new SubTargetClass();Runnable lambda = () -> sub.callSuperMethod();
lambda.run();super.toString();return overloadedMethod(target) + "_" + extra;}}
public class TargetClass extends ParentClass {public class Inner {private int value = 20;
public int getValue() {return value;}}
public Inner getInner() {return new Inner();}}
class ParentClass {protected int parentField = 5;
public int parentMethod() {return parentField;}}
class SubTargetClass extends TargetClass {protected int callSuperMethod() {return super.parentMethod();}}