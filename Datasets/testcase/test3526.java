package test;
import diffpackage.OthersClass;import java.io.IOException;
interface TargetInterface {}
public record SourceClass(String data) {static class StaticNested {public void nestedMethod(SourceClass source) {class LocalInner {class InnerRec {protected void moveMethod(AbstractTargetClass target) throws IOException {new OthersClass(target);if (target.obj.field == 1) {}
class TypeDeclaration {}new TypeDeclaration();
System.out.println("Expression statement");
super.method1();super.method2();super.method3();
super.toString();variableCall(target);}
private void variableCall(AbstractTargetClass target) {target.doAction();}
private void method1() {}private void method2() {}private void method3() {}}}new LocalInner.InnerRec().moveMethod(new AbstractTargetClass(1) {});}}}
abstract record AbstractTargetClass(int field) implements TargetInterface {{new Runnable() {@Overridepublic void run() {}}.run();}
public void doAction() {}
@Overridepublic void moveMethod(AbstractTargetClass target) {}}
package diffpackage;
import test.AbstractTargetClass;
public class OthersClass {public OthersClass(AbstractTargetClass target) {target.obj.field = 1;}}