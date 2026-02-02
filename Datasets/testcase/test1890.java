package test;
import java.lang.reflect.Method;import java.util.ArrayList;import java.util.List;
public class SourceClass extends SourceParent {// First static nested classpublic static class StaticNested1 {}
// Second static nested classpublic static class StaticNested2 {public String getInfo() {return "static_nested_2";}}
// Member inner class containing the target methodprotected class SourceInner {@MyAnnotation("processing") // Has annotationprotected Object instanceMethod(TargetClass target) {List<String> results = new ArrayList<>();
// Variable callresults.add(target.name);
// Access instance methodresults.add(target.getDetails());
// Property assignment with instance inner_class method (superTypeReference)Runnable task = () -> {CharSequence seq = target.new InnerHandler().process(target.name);results.add(seq.toString());};task.run();
// Break statementfor (int i = 0; i < 5; i++) {if (i == 3) break;results.add("i=" + i);}
// Continue statementfor (int i = 0; i < 5; i++) {if (i % 2 == 0) continue;results.add("odd=" + i);}
// Used by reflectiontry {Method method = TargetClass.class.getMethod("getDetails");results.add((String) method.invoke(target));} catch (Exception e) {// No new exception}
return results;}}}
class SourceParent {protected String parentField = "parent_data";}
@interface MyAnnotation {String value();}
class TargetClass {public String name;
public TargetClass() {name = "target";// Anonymous inner classRunnable initializer = new Runnable() {@Overridepublic void run() {name += "_initialized";}};initializer.run();}
public String getDetails() {return "Target: " + name;}
// Inner class for superTypeReferencepublic class InnerHandler {protected CharSequence process(String input) {return input.toUpperCase();}}}