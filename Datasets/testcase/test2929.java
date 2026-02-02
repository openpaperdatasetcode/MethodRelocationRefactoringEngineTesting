import java.util.ArrayList;import java.util.List;
strictfp class SourceClass {class InnerOne {}class InnerTwo {}
protected static List<String> methodToMove(TargetClass target) {OthersClass others = new OthersClass();if (target.targetField > 0) {Object result = others.privateInstanceMethod(target);} else {Object result = others.privateInstanceMethod(null);}
TargetClass.StaticNested nested = new TargetClass.StaticNested();InnerOne inner = new InnerOne();target.staticNestedMethod(nested);
List<String> list = new ArrayList<>();list.add(target.targetField.toString());return list;}}
public class TargetClass implements Runnable {int targetField;static class StaticNested {}
public void staticNestedMethod(StaticNested nested) {}
@Overridepublic void run() {}}
class OthersClass {private Object privateInstanceMethod(TargetClass target) {if (target != null) {return Runnable.super.toString();}return null;}}