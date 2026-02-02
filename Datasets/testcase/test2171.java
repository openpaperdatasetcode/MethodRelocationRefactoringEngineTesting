package source;
import target.TargetClass;import java.util.List;
private class SourceClass<T extends Number> extends ParentClass<T> {static class StaticNested {}
protected TargetClass<String> methodToMove(TargetClass<String>.InnerRec innerRec) {// Super constructor invocationsuper();
// Local inner classclass LocalInner {}new LocalInner();
// StringLiteral with modifier private and numbers=1private String literal = "sourceLiteral";
// Access target fieldString targetField = innerRec.targetField();
// Variable call with boundsT value = innerRec.boundedValue();TargetClass<String> target = innerRec.targetInstance();
return target;}}
class ParentClass<T> {}
package target;
public class TargetClass<V> {static class StaticNested {}
record InnerRec(V targetField, V boundedValue, TargetClass<V> targetInstance) {}}