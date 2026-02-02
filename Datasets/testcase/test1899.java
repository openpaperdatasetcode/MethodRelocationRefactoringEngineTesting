package test;
import java.util.ArrayList;import java.util.List;
class SourceClass {// Member inner classpublic class SourceInner {private String innerData;
public SourceInner(String data) {this.innerData = data;}
public String getInnerData() {return innerData;}}
// Anonymous inner classprivate Runnable initializer = new Runnable() {@Overridepublic void run() {processTargets(new ProtectedTarget("init"));}};
/**
Processes multiple ProtectedTarget instances using varargs
Handles target fields and requires try-catch for potential exceptions
@param targets Varargs of ProtectedTarget instances to process*/void processTargets(ProtectedTarget... targets) {// Constructor invocationSourceInner inner = new SourceInner("source_inner");
// Variable callfor (ProtectedTarget target : targets) {System.out.println("Processing: " + target.name);}
// Raw typeList rawList = new ArrayList();for (ProtectedTarget target : targets) {rawList.add(target.getValue());}
// Requires try-catchtry {int length = ((String) rawList.get(0)).length();System.out.println("First value length: " + length);} catch (NullPointerException | IndexOutOfBoundsException e) {System.out.println("Error processing list: " + e.getMessage());}
// Instance code blocks with inner_class synchronized method referencefor (ProtectedTarget target : targets) {target.new TargetInner().process(inner::getInnerData);}
// VariableDeclarationStatement with 1 obj.field reference (same package target)targetFieldProcessor(targets[0]);}
private void targetFieldProcessor(ProtectedTarget target) {// Private VariableDeclarationStatement accessing target's fieldclass FieldHandler {private String processedField = target.name + "_processed"; // obj.field reference
void print() {System.out.println("Processed field: " + processedField);}}new FieldHandler().print();}}
protected class ProtectedTarget {String name;private int value;
public ProtectedTarget(String name) {this.name = name;this.value = name.length();
// Anonymous inner classRunnable validator = new Runnable() {@Overridepublic void run() {if (name == null || name.isEmpty()) {throw new IllegalArgumentException("Name cannot be empty");}}};validator.run();}
public int getValue() {return value;}
// Inner class for call_methodpublic class TargetInner {/**
Synchronized method using method reference
@param supplier Data supplier from source inner class
*/
public synchronized void process(Supplier<String> supplier) {
System.out.println("Synchronized processing: " + supplier.get());
}
}
}
@FunctionalInterfaceinterface Supplier<T> {T get();}