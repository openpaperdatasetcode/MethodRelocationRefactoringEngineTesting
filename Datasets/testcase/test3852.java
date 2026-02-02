package test.refactoring;
import java.util.ArrayList;import java.util.Collection;import java.io.IOException;
strictfp class SourceClass implements SomeInterface {private String outerPrivate = "privateFieldValue";
/**
Recursively processes TargetClass instance
@param target TargetClass instance to process
@param depth Recursion depth
@return Processed TargetClass instance
@throws IOException If validation fails*/protected TargetClass recursiveMethod(TargetClass target, int depth) throws IOException {super.toString();
if (depth <= 0) {return target;}
Object rawObj = new ArrayList();TargetClass castTarget = (TargetClass) rawObj.getClass().cast(target);
variableCall(castTarget);castTarget.setData(outerPrivate);
try {castTarget.validate();} catch (IOException e) {throw new IOException("Validation failed at depth: " + depth, e);}
return recursiveMethod(castTarget, depth - 1);}
private void variableCall(TargetClass target) {target.incrementCount();}}
class SubSource extends SourceClass {private int processCollection(Collection<TargetClass> targets) {int count = 0;for (TargetClass target : targets) {TargetClass newTarget = new TargetClass() {@Overridevoid createLocalInner() {class LocalInner {int getCount() {return count;}}count += new LocalInner().getCount();}};newTarget.createLocalInner();count++;}return count;}}
class TargetClass {private String data;private int count;
void setData(String data) {this.data = data;}
void incrementCount() {this.count++;}
void validate() throws IOException {if (data == null || data.isBlank()) {throw new IOException("Data cannot be empty");}}
abstract void createLocalInner();}
interface SomeInterface {}