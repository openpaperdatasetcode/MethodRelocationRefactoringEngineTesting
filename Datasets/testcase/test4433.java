package test;
import java.util.function.Supplier;
private class SourceClass {class SourceInner {private static class Helper {private int helperField;private Helper(int val) { this.helperField = val; }}
public final Object varargsMethod(TargetClass.Inner... targetInners) {class LocalType {}LocalType local1 = new LocalType();LocalType local2 = new LocalType();
Helper helper = new Helper(1);int superFieldVal = helper.helperField;
TargetClass target = new TargetClass();TargetClass.Inner targetInner = new TargetClass.Inner();
Supplier<Object> supplier1 = TargetClass.Inner::new;Supplier<Object> supplier2 = TargetClass::new;
Object result = null;int i = 0;while (i < targetInners.length) {result = this.callMethod(targetInners[i]);i++;}return result;}
private Object callMethod(TargetClass.Inner targetInner) {return targetInner.innerField;}
private Object callMethod(String str) {return str;}}
{new Runnable() {@Overridepublic void run() {SourceInner inner = new SourceInner();}};}}
class TargetClass implements SomeInterface {class Inner {String innerField;}
@Overridepublic void doSomething() {}}
interface SomeInterface {void doSomething();}