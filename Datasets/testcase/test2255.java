package test;
import java.lang.reflect.Method;import java.util.List;
strictfp class SourceClass implements Runnable {public void run() {}
void createLocalInners() {class FirstLocalInner {}class SecondLocalInner {}}
private int methodToMove(TargetClass target) {TargetClass.InnerClass.Rec innerRec = target.new InnerClass().new Rec(1);int field = innerRec.targetField;
try {TargetClass result = innerRec.overloadedMethod(1);result = innerRec.overloadedMethod("str");} catch (Exception e) {// No new exception}
try {Method method = TargetClass.InnerClass.Rec.class.getMethod("overloadedMethod", int.class);method.invoke(innerRec, 1);} catch (Exception e) {}
return field;}}
public class TargetClass extends SuperClass implements List<String> {class InnerClass {final TargetClass overloadedMethod(int num) {super.toString();return new TargetClass();}
final TargetClass overloadedMethod(String str) {super.toString();return new TargetClass();}
record Rec(int val) {int targetField = val;
void createLocalInner() {class LocalInner {}}}}
public void run() {}}
class SuperClass {}