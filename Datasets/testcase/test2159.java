package test;
import java.util.List;import java.util.ArrayList;import java.util.function.Supplier;
interface TargetInterface {}
protected enum SourceEnum {INSTANCE;
protected protected field in source enum */protected String outerProtected = "protected_val";
class MemberInner {class InnerRecursive {@Overrideprotected TargetEnum methodToMove(TargetEnum target) {TargetEnum.MemberInner.InnerRecursive innerRec = target.new MemberInner().new InnerRecursive();
// Constructor invocationTargetEnum.StaticNested nested = new TargetEnum.StaticNested();
int i = 0;while (i < 5) {// Switch caseswitch (innerRec.state) {case 0:innerRec.variableCall();break;default:continue;}i++;}
// Access outer protected fieldinnerRec.data = SourceEnum.this.outerProtected;
// Access instance methodList<String> result = innerRec.instanceMethod();
// Functional interface with parent class method callSupplier<List<String>> supplier = () -> ParentClass.overriddenMethod(innerRec.data);supplier.get();
return target;}}}
Runnable anonymous = new Runnable() {public void run() {}};}
public enum TargetEnum implements TargetInterface {VALUE1, VALUE2;
static class StaticNested {}
class MemberInner {class InnerRecursive {String data;int state;
void variableCall() {}
List<String> instanceMethod() {return new ArrayList<>();}}}}
class ParentClass {public static List<String> overriddenMethod(String input) {return new ArrayList<>();}}