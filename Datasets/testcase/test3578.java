package test;
import java.lang.reflect.Method;
public class SourceClass {private TargetClass targetField = new TargetClass();
{Runnable anon1 = new Runnable() {@Overridepublic void run() {}};Runnable anon2 = new Runnable() {@Overridepublic void run() {}};}
/**
Javadoc for overloaded moveMethod with int parameter
@param value input integer*/protected void moveMethod(int value) {TargetClass.TargetInner.TargetInnerRec innerRec = targetField.new TargetInner().new TargetInnerRec();innerRec.process(value);super.toString();
try {Method method = SourceClass.class.getMethod("moveMethod", int.class);method.invoke(this, value);} catch (Exception e) {}
try {innerRec.action();} catch (Exception e) {}}
/**
Javadoc for overloaded moveMethod with String parameter
@param str input string*/protected void moveMethod(String str) {TargetClass.TargetInner.TargetInnerRec innerRec = new TargetClass().new TargetInner().new TargetInnerRec();innerRec.process(str);super.hashCode();
try {Method method = SourceClass.class.getMethod("moveMethod", String.class);method.invoke(this, str);} catch (Exception e) {}
try {innerRec.action();} catch (Exception e) {}}}
public class TargetClass {class TargetInner {class TargetInnerRec {void process(int val) {}void process(String s) {}void action() throws Exception {}}}}
