package test;
import java.util.ArrayList;import java.util.List;
public abstract class SourceClass<T> {protected String outerProtected = "protected_base";
// First static nested class with type parameterpublic static class StaticNested1 {
U data;
}
// Second static nested classpublic static class StaticNested2 {static String process(String input) {return input.toUpperCase();}}
public void varargsMethod(TargetClass... targets) {// Expression statementList<Object> results = new ArrayList<>();
// Constructor invocationTargetClass defaultTarget = new TargetClass() {@Overridepublic String getDetails() {return "default_impl";}};
// Variable callfor (TargetClass target : targets) {results.add(target.getName());}
// Access outer protectedresults.add(outerProtected + "_" + defaultTarget.getName());
// Exception handling with 3 normal source methods (method chaining)try {Object processed = processStep1(defaultTarget).processStep2().processStep3();results.add(processed);} catch (Exception e) {// No new exceptionresults.add("error_handling");}}
// Private normal methods for chainingprivate ProcessingContext processStep1(TargetClass target) {return new ProcessingContext(target.getName() + "_step1");}
private class ProcessingContext {private String data;
ProcessingContext(String data) {this.data = data;}
ProcessingContext processStep2() {this.data += "_step2";return this;}
Object processStep3() {return this.data + "_step3";}}}
abstract class TargetClass extends BaseClass {private String name;
public TargetClass() {this.name = "target_" + System.currentTimeMillis();}
public String getName() {return name;}
public abstract String getDetails();}
class BaseClass {protected int baseId = 100;}