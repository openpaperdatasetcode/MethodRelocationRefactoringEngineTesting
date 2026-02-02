package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;import java.io.IOException;
interface TargetInterface {}
@Retention(RetentionPolicy.RUNTIME)@interface AbsMethodAnn {}
class TargetClass implements TargetInterface {int targetField;
public TargetClass() {// Anonymous inner classRunnable r = new Runnable() {@Overridepublic void run() {}};}
public void instanceMethod() {}}
protected abstract class SourceClass {private int outerPrivateField = 100;
static class SourceStaticNested {}class MemberInner {}
// Super constructor invocation (implicit in abstract class, explicit in concrete subclass)protected SourceClass() {super();}
@AbsMethodAnn // has_annotationprivate abstract int methodToMove(TargetClass target) throws IOException; // IOException
// Concrete implementation skeleton for abstract methodpublic static class SourceConcrete extends SourceClass {@Overrideprivate int methodToMove(TargetClass target) throws IOException {// Variable callint var = target.targetField;
// Access outer privatevar += super.outerPrivateField;
// Do statementint count = 0;do {// Continue statementif (count % 2 == 0) continue;var += count;count++;} while (count < 5);
// This.methodName(arguments)this.helperMethod();
// Target class instance method calltarget.instanceMethod();
return var;}
private void helperMethod() {}}}
