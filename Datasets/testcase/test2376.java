package source;
import target.TargetClass;import java.io.IOException;
protected class SourceClass {class SourceInner {}
{new Runnable() {}; // Anonymous inner class}
private int methodToMove(TargetClass<String> target) throws IOException {// Overriding method in array initializationParentClass[] parents = {new ChildClass() {@Overridepublic int overriddenMethod() {return new TargetClass<String>().getValue();}}};int parentVal = parents[0].overriddenMethod();
// Super constructor invocationclass SubSource extends SourceClass {SubSource() {super();}}SubSource sub = new SubSource();
// Variable callint var = target.count;TargetClass<String>.TargetStaticNested nested = target.new TargetStaticNested();String nestedVal = nested.data;
if (var < 0) {throw new IOException("Invalid count"); // Requires throws}
return var + parentVal;}}
class ParentClass {public int overriddenMethod() {return 0;}}
class ChildClass extends ParentClass {}
package target;
private class TargetClass<T> {int count;
static class TargetStaticNested {T data;}
public int getValue() {return count * 2;}}