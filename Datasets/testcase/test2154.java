package test;
import java.util.List;import java.util.ArrayList;
interface SourceInterface {}
private class SourceClass implements SourceInterface {class MemberInner {}
void createLocalInner() {class LocalInner {}}
strictfp List<String> methodToMove(TargetClass target) {List<String> result = new ArrayList<>();
TargetClass.MemberInner.InnerRecursive innerRec = target.new MemberInner().new InnerRecursive();
// Access static field dependencyresult.add(TargetClass.STATIC_FIELD);
// Expression statementinnerRec.count++;
// Variable call and access instance methodinnerRec.variableCall();result.add(innerRec.instanceMethod());
return result;}}
public class TargetClass {static String STATIC_FIELD = "static_value";
class MemberInner {class InnerRecursive {int count;
void variableCall() {class LocalInner {}}
String instanceMethod() {class LocalInner {}return "instance_method_result";}}}}