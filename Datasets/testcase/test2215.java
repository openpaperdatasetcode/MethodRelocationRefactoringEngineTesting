package source;
import target.TargetClass;import java.util.function.Supplier;
public abstract class SourceClass {class MemberInner {}
static class StaticNested {}
/**
Javadoc for methodToMove
@param targetParam parameter of TargetClass
@return Object instance*/protected Object methodToMove(TargetClass targetParam) {static int var = 3;TargetClass.field = var;
TargetClass target = new TargetClass();do {TargetClass result = helperMethod(2, target);} while (var > 0);
targetParam.someMethod();return new Object();}
public TargetClass helperMethod(int num, TargetClass target) {this.methodToMove(target);return target;}}
package target;
public class TargetClass implements Supplier<String> {public static int field;
protected String someMethod() {return "test";}
@Overridepublic String get() {return someMethod();}}