package source;
import target.TargetClass;
public class SourceClass {static class StaticNested {}
@Overrideprotected Object methodToMove() throws Exception {TargetClass target = new TargetClass();int field1 = TargetClass.staticField1;String field2 = TargetClass.staticField2;
new Object() {{System.out.println(SourceClass.this.toString());}};
synchronized (target) {target.localInnerMethod();}
return target;}}
package target;
class ParentClass {public Object methodToMove() {return null;}}
protected class TargetClass extends ParentClass {static int staticField1;static String staticField2;
void localInnerMethod() {class LocalInner {}new LocalInner();}
@Overrideprotected Object methodToMove() { // Override violation (weaker access than parent)return null;}}