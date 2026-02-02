package test;
import java.lang.reflect.Method;
abstract class Source<T> {T sourceField;
class Inner {private final Object field;
final Inner(Target<Integer>.InnerRec param) throws Exception {super();Target<String> target = new Target<>();Target.Inner targetInner = target.new Inner();this.field = targetInner.targetField;
if (param == null) {throw new IllegalArgumentException();}
Method method = Inner.class.getMethod("toString");variableCall(param);}
private void variableCall(Target<Integer>.InnerRec param) {int val = param.innerRecField;}}
void anonymousInner() {Runnable r = new Runnable() {public void run() {}};}}
class Target {
static class Nested {}
class Inner {U targetField;
class InnerRec {int innerRecField;}}}