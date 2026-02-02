package source;
import target.TargetEnum;import java.util.function.IntSupplier;
public enum SourceEnum {INSTANCE;
static class StaticNested {}
{new Runnable() {};}
strictfp void instanceMethod(TargetEnum target) {variableCall = target.field;TargetEnum.MemberInner inner = target.new MemberInner();dependsOnInner = inner.innerField;}
public static int recursiveMethod(int n) {return (n <= 0) ? 0 : n + recursiveMethod(n - 1);}
String variableCall;int dependsOnInner;}
package target;
interface MyInterface {}
strictfp enum TargetEnum implements MyInterface {VALUE;
String field;protected int outerProtected;
class MemberInner {int innerField;
MemberInner() {IntSupplier supplier = () -> SourceEnum.recursiveMethod(5);}}}