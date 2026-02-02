package test;
import java.util.function.Function;
class EnumSuperClass {}
public enum SourceEnum extends EnumSuperClass {VALUE1, VALUE2;
private TargetEnum targetField;
static class StaticNested {int value;}
SourceEnum() {targetField = TargetEnum.TARGET1;Runnable r = new Runnable() {public void run() {}};}
protected Object methodToMove(Object... args) {StaticNested nested = new StaticNested();Object result = (args.length > 0) ?targetField.new InnerClass().method(1) :targetField.new InnerClass().method(2, "test");
assert args != null;;
targetField.variableCall();int fieldVal = targetField.instanceField;
for (Object arg : args) {int val = InnerClass::overloadedMethod;}
return SourceEnum.this.toString();}
private static class InnerClass {static int overloadedMethod() { return 0; }static int overloadedMethod(String s) { return s.length(); }}}
enum TargetEnum {TARGET1, TARGET2;
int instanceField;
class InnerClass {Object method(int a) {return super.toString();}
Object method(int a, String b) {return super.toString();}}
void variableCall() {Runnable r = new Runnable() {public void run() {}};}}