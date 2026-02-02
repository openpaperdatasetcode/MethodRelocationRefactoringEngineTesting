package test;
import java.io.IOException;
class SuperClass {public void process(TargetClass target) throws IOException {}}
public class SourceClass extends SuperClass {private int outerPrivate = 100;static class StaticNested1 {}static class StaticNested2 {}
@Overridepublic void process(TargetClass target) throws IOException {// this.var = varthis.outerPrivate = target.field;
// Variable calltarget.variableCall();TargetClass.StaticNested.staticMethod();
// Access outer private fieldtarget.nestedField = outerPrivate;
// Requires throwsif (target.field < 0) {throw new IOException("Invalid field value");}}}
class TargetClass {int field;int nestedField;static class StaticNested {static void staticMethod() {}}
void variableCall() {}}