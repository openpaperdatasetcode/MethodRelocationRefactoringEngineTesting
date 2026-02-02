package test;

import java.lang.reflect.Method;
import java.util.function.Function;

// Parent interface for source's implements feature
interface DataConverter<T, R> {
R convert(T data);
}

// Source: abstract generic class with implements, permits, static nested class, local inner class
sealed abstract class SourceClass<T> implements DataConverter<T, String> permits SourceSubClass1, SourceSubClass2 {
// Source instance field (access_instance_field)
protected T sourceInstanceField;
// Source contains target's field (per_condition)
private TargetClass<T> targetField;

// Static nested class (source_feature)
public static class SourceStaticNested {
public U processStatic(U data) {
return data;
}
}

// Recursive inner class (source_inner_rec)
public class SourceInnerRec {
// Final overriding method (implements DataConverter's abstract method implicitly)
@Override
public final String convert(T data) {
// Variable call: use target field and parameter
TargetClass<T>.TargetInner targetInner = targetField.createTargetInner(data);
T innerData = targetInner.getInnerData();

// Access instance field (source's instance field)
sourceInstanceField = innerData;
String instanceFieldStr = sourceInstanceField.toString();

// Expression statement: process via static nested class + this.methodName(arguments)
SourceStaticNested<T> staticNested = new SourceStaticNested<>();
T processedData = staticNested.processStatic(innerData);
String exprResult = this.formatData(processedData); // this.methodName(arguments)

// Local inner class (source_feature)
class SourceLocalInner {
public String localProcess(T data) {
return "local_" + data.toString();
}
}
SourceLocalInner localInner = new SourceLocalInner();
String localResult = localInner.localProcess(processedData);

// Used by reflection: access target inner class method
try {
Method reflectMethod = TargetClass.TargetInner.class.getMethod("getInnerData");
T reflectedData = (T) reflectMethod.invoke(targetInner);
exprResult += "_reflected:" + reflectedData;
} catch (Exception e) {
// No new exception thrown
}

// Recursive invocation (source_inner_rec: convert method is recursive via this.convert)
if (processedData instanceof String && ((String) processedData).length() > 3) {
return exprResult + "_recursive:" + this.convert((T) ((String) processedData).substring(1));
}

// Return TargetClass Type-related result (String converted from target inner data)
return exprResult + "_local:" + localResult;
}

// Helper method for this.methodName(arguments)
private String formatData(T data) {
return "formatted_" + data.toString();
}

// Setter for target field (per_condition: method contains target parameter)
public void setTargetField(TargetClass<T> target) {
targetField = target;
}
}

// Abstract method (required for abstract class)
public abstract T getSourceData();

// Helper method to create inner recursive class instance
public SourceInnerRec createInnerRec() {
return new SourceInnerRec();
}
}

// Permitted subclasses of SourceClass (satisfy "permits" feature)
final class SourceSubClass1 extends SourceClass<String> {
@Override
public String getSourceData() {
return "source1_data";
}
}

final class SourceSubClass2 extends SourceClass<Integer> {
@Override
public Integer getSourceData() {
return 100;
}
}

// Target: generic normal class with static nested class (target_feature)
class TargetClass<T> {
// Static nested class (target_feature)
public static class TargetStatic<T> {
public String staticFormat(T data) {
return "target_static_" + data;
}
}

// Member inner class (target_inner)
public class TargetInner {
private T innerData;

public TargetInner(T data) {
this.innerData = data;
}

// Getter for variable call
public T getInnerData() {
return innerData;
}

// Use target's static nested class
public String getStaticFormattedData() {
return TargetStatic.<T>staticFormat(innerData);
}
}

// Helper method to create TargetInner instance
public TargetInner createTargetInner(T data) {
return new TargetInner(data);
}
}