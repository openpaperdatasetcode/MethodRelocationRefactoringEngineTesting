package source.package;
import target.package.TargetClass;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// Annotation for "has_annotation" feature
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@interface StaticMethodAnnot {}

// Parent generic class for SourceClass to extend (source_class feature: extends)
class ParentGenericSource<T> {
protected T parentGenericField;

public ParentGenericSource(T parentGenericField) {
this.parentGenericField = parentGenericField;
}

protected T getParentGenericData() {
return parentGenericField;
}
}

// SourceClass: generic, protected, extends ParentGenericSource (source_class features)
protected class SourceClass<T extends TargetClass<String>> extends ParentGenericSource<T> {
// Inner recursive class (method_position: source_inner_rec)
public static class SourceInnerRec<U extends TargetClass<String>> {
@StaticMethodAnnot // "has_annotation" feature
public static String staticMethod(U targetParam) {
// Type declaration statement: define local generic type-related class
class LocalTypeProcessor {
String processTarget(U target) {
// Access target's generic data (base type: String)
return "Processed: " + target.getTargetGenericData();
}
}

// Type declaration statement: initialize local type instance
LocalTypeProcessor typeProcessor = new LocalTypeProcessor();
// Source contains target field (via method parameter: per_condition)
String baseResult = typeProcessor.processTarget(targetParam);

// Variable call (method feature)
variableCall(targetParam, "Static method processing completed");

// Return base type (String)
return baseResult + " | Base type result";
}

// Variable call (method feature)
private static void variableCall(U target, String message) {
System.out.printf("[SourceInnerRec] %s | Target data: %s%n",
message, target.getTargetGenericData());
}
}

public SourceClass(T parentGenericField) {
super(parentGenericField);
}

// Trigger inner recursive class's static method
public String callInnerStaticMethod() {
return SourceInnerRec.staticMethod(this.parentGenericField);
}
}
</doubaocanvas-part>

plaintext
&#x3C;doubaocanvas-part path=&#x22;target/package/TargetClass.java&#x22; language=&#x22;java&#x22; description=&#x22;TargetClass (generic, public, has local inner class)&#x22;&#x3E;

package target.package;

// TargetClass: generic, public (target_class type/modifier)
public class TargetClass<V> {
private V targetGenericData;

public TargetClass(V targetGenericData) {
this.targetGenericData = targetGenericData;
// Local inner class (target_class feature: local inner class)
class TargetLocalInner {
V getLocalProcessedData() {
return (V) ("LocalInner:" + targetGenericData);
}
}
TargetLocalInner localInner = new TargetLocalInner();
// Use local inner class to initialize target data
this.targetGenericData = localInner.getLocalProcessedData();
}

// Get generic target data (base type for method return: V)
public V getTargetGenericData() {
return targetGenericData;
}

// Set generic target data
public void setTargetGenericData(V targetGenericData) {
this.targetGenericData = targetGenericData;
}
}
</doubaocanvas-part>

plaintext
&#x3C;doubaocanvas-part path=&#x22;test/package/TestEntry.java&#x22; language=&#x22;java&#x22; description=&#x22;Test entry to validate structure&#x22;&#x3E;

package test.package;

import source.package.SourceClass;
import target.package.TargetClass;

public class TestEntry {
public static void main(String[] args) {
// Initialize target (generic type String, public access)
TargetClass<String> target = new TargetClass<>("init_target_data");

// Initialize source (generic, protected, different package)
SourceClass<TargetClass<String>> source = new SourceClass<>(target);

// Call source's inner recursive class static method (returns base type String)
String staticMethodResult = SourceClass.SourceInnerRec.staticMethod(target);
System.out.println("Static method result (base type): " + staticMethodResult);

// Call source's wrapper method
String wrapperResult = source.callInnerStaticMethod();
System.out.println("Wrapper method result: " + wrapperResult);
}
}