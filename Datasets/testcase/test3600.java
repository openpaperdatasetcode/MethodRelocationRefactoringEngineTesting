package test;
public class SourceClass {public static int staticField;private TargetClass targetParam;
public Object instanceMethod(TargetClass target) {this.targetParam = target;int count = 0;
class LocalInner {void increment() {count++;}}LocalInner local = new LocalInner();
new Runnable() {public void run() {staticField = 100;}}.run();
for (int i = 0; i < 2; i++) {local.increment();}
private class SwitchHelper {void check() {switch (target.obj.field) {case 1:staticField++;break;}}}new SwitchHelper().check();
@MyAnnotation(method = "new SubClass().protectedMethod()")class AnnotationHolder {}
OthersClass.execute(() -> {if (targetParam != null) {System.out.println("Target exists");} else {System.out.println("Target missing");}});
return this;}}
class TargetClass {public Obj obj = new Obj();}
class Obj {public int field;}
class SubClass {protected void protectedMethod() {}}
final class OthersClass {static void execute(Runnable r) {r.run();}}
@interface MyAnnotation {String method();}
