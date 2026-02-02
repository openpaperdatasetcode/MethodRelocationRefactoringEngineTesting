package test.refactoring.movemethod;
import java.util.ArrayList;import java.util.List;
class ParentTarget {protected int superField = 1;}
public class TargetClass extends ParentTarget {public static String staticField = "static_data";
private String name;
public TargetClass(String name) {this.name = name;}
public String getName() {return name;}
public void setName(String name) {this.name = name;}}
final class SourceClass {class SourceInner {/**
Processes the target object by updating its name and collecting data.
@param target the TargetClass instance to process*/public final void process(TargetClass target) {Object varCall = target.getName();List<String> data = new ArrayList<>();
// Accessor method in collection operationdata.add(getSuperFieldValue(target));
// Check static field dependencyif (TargetClass.staticField.equals("static_data")) {target.setName("processed_" + target.getName());}
// ReturnStatement featureif (target.superField != 1) {return;}
varCall = data;}
/**
Accessor method to get super field value
@param target the target instance
@return super field value as base type
*/
private int getSuperFieldValue(TargetClass target) {
return target.superField;
}
}
}
import org.junit.Test;
public class MoveMethodTest3093 {@Testpublic void testNormalMethod() {SourceClass source = new SourceClass();SourceClass.SourceInner inner = source.new SourceInner();TargetClass target = new TargetClass("test");inner.process(target);}}