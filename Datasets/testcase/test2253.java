package test;
import java.lang.reflect.Method;import java.util.ArrayList;
protected abstract class SourceClass<T> {private Object methodToMove(TargetClass<String> target) throws Exception {TargetClass<String>.StaticNested inner = target.new StaticNested();int field = inner.this.field; // Access target's inner class field via 'this'
// Assert statement checking target's inner field equals 1assert field == 1 : "Field value mismatch";
// Raw type usageObject rawList = new ArrayList();
// Variable call to target's inner class methodObject result = inner.innerMethod();
// Used by reflectionMethod method = TargetClass.StaticNested.class.getMethod("innerMethod");method.invoke(inner);
return result;}}
public abstract class TargetClass<K> {// Static nested class as target featureclass StaticNested {int field = 1; // Field referenced as "this.field"
Object innerMethod() {return new Object();}}}