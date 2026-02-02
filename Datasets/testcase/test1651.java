package test;
import java.io.IOException;import com.other.DiffPackageProcessor;
protected class SourceClass<T> extends SourceParent<T> {// Static nested class with type parameterpublic static class SourceStatic {
public static final String STATIC_FIELD = "source_static";
}
// Member inner class with type parameterpublic class SourceInner<V> {private V data;
public SourceInner(V data) {this.data = data;}
public V getData() {return data;}}
// Strictfp instance method returning TargetClass Typestrictfp PrivateTarget<T> instanceMethod(PrivateTarget<T> target) {// Variable callT baseData = target.getData();PrivateTarget.StaticNested<T> targetStatic = new PrivateTarget.StaticNested<>(baseData);
// Uses outer thisSourceInner<T> inner = SourceClass.this.new SourceInner<>(baseData);target.setAuxData(inner.getData());
// For statementfor (int i = 0; i < 3; i++) {target.addLog("Loop: " + i);}
// If statementif (target.getData() == null) {target.setData((T) "default_data");}
// Switch caseswitch (target.getLogCount()) {case 0 -> target.addLog("Empty logs");case 3 -> target.addLog("Full logs");default -> target.addLog("Partial logs");}
// Handle IOExceptiontry {if (target.getLogCount() > 5) {throw new IOException("Log overflow");}} catch (IOException e) {// No new exceptiontarget.addLog("Error handled: " + e.getMessage());}
// Diff package others with ThrowStatement (1 ClassName.field reference)DiffPackageProcessor.process(target);
return target;}}
class SourceParent<T> {protected String parentField = "parent_base";}
/**
Javadoc for target generic enum
Handles generic data and static nested class operations
@param <T> Type of data stored in the enum*/private class PrivateTarget<T> {private T data;private T auxData;private int logCount = 0;
public PrivateTarget(T data) {this.data = data;}
public T getData() {return data;}
public void setData(T data) {this.data = data;}
public void setAuxData(T auxData) {this.auxData = auxData;}
public void addLog(String log) {logCount++;}
public int getLogCount() {return logCount;}
// Static nested class (target feature)public static class StaticNested {
private U data;
public StaticNested(U data) {this.data = data;}
public U getData() {return data;}}}
// Diff package: com.otherpackage com.other;
import test.PrivateTarget;
public class DiffPackageProcessor {public static <T> void process(PrivateTarget<T> target) {class ThrowHandler {public void execute() {try {// 1 ClassName.field reference (PrivateTarget's static nested class)PrivateTarget.StaticNested<T> staticNested = new PrivateTarget.StaticNested<>(target.getData());if (staticNested.getData() == null) {throw new IllegalArgumentException("Null data in static nested class");}} catch (IllegalArgumentException e) {// No new exception propagation}}}new ThrowHandler().execute();}}