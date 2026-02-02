package test;
import java.lang.reflect.Method;import java.util.function.Consumer;
private class TargetClass {String targetField;static class TargetStaticNested {}}
public class SourceClass {int x;static class SourceStaticNested {}
public SourceClass() {Runnable r = new Runnable() {@Overridepublic void run() {}};}
void methodToMove(TargetClass target, String ifKeyword, String elseKeyword) {// Method types parameter is:keywordsString var = target.targetField; // Variable call
// Empty statement;
// Super constructor invocation (within inner class)class Inner extends SourceStaticNested {Inner() {super();}}new Inner();
// MethodReference with numbers:1Consumer<String> consumer = String::trim;consumer.accept(var);
// OuterClass.this.xint outerX = SourceClass.this.x;
// Used by reflectiontry {Method method = TargetClass.class.getMethod("toString");method.invoke(target);} catch (Exception e) {}}}