package test;
import java.lang.reflect.Method;import java.util.ArrayList;import java.util.List;
abstract class SourceClass implements Runnable {class MemberInner<T> {strictfp Object genericMethod(T param) {return param;}}
{new Runnable() {@Overridepublic void run() {}};}
private void process(TargetClass target) {super();class TypeDeclaration {}OthersClass others = new OthersClass();TargetClass.Inner inner = target.new Inner();TargetClass.Inner.RecursiveInner recursiveInner = inner.new RecursiveInner();
try {Method method = TargetClass.Inner.RecursiveInner.class.getMethod("getData");} catch (NoSuchMethodException e) {throw new RuntimeException(e);}
int count = 0;while (count < 3) {List<String> data = others.recursiveMethod(1, recursiveInner);Object result = new MemberInner<String>().genericMethod(data);count++;}}
@Overridepublic void run() {}}
protected class TargetClass {class Inner {class RecursiveInner {List<String> getData() {return new ArrayList<>();}}}}
class OthersClass {List<String> recursiveMethod(int depth, TargetClass.Inner.RecursiveInner inner) {if (depth <= 0) {return inner.getData();}return recursiveMethod(depth - 1, inner);}}