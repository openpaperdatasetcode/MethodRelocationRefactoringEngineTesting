package test;
import java.util.List;import java.util.ArrayList;
interface TargetInterface {}
public class SourceClass {protected String outerProtectedField = "protected_value";class MemberInner {}Runnable anonymous = new Runnable() {public void run() {}};
private abstract List<String> methodToMove(TargetClass target);
// Concrete implementation demonstrating featuresprivate class ConcreteImpl {private List<String> process(TargetClass target) {// Overriding method in property assignmenttarget.stringProcessor = new SourceClass() {@Overridepublic List<String> processStrings() {return new ArrayList<>();}}.processStrings();
// While statement with variable callint i = 0;while (i < 3) {target.variableCall();i++;}
// Access outer protected fieldString protectedVal = SourceClass.this.outerProtectedField;
// Access target's static nested class fieldList<String> nestedData = TargetClass.StaticNested.nestedList;
return new ArrayList<>(nestedData);}}
public List<String> processStrings() {return new ArrayList<>();}}
class TargetClass implements TargetInterface {List<String> stringProcessor;static class StaticNested {static List<String> nestedList = new ArrayList<>();}
void variableCall() {}}