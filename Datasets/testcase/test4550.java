package same.pkg;
import java.util.List;import java.util.ArrayList;import java.util.function.Supplier;
interface SourceInterface {/**
Recursive method to process target interface's static nested class
@param depth Recursion depth
@return List of processed strings*/default List<String> recursiveMethod(int depth) {variableCall();access_instance_method();
TypeDeclaration typeDecl = new TypeDeclaration();TargetClass[] targetArray = {OthersClass.getTarget().m1().m2().m3(),OthersClass.getTarget().m1().m2().m3()};
if (depth <= 0) {List<String> baseCase = new ArrayList<>();baseCase.add(targetArray[0].toString());return baseCase;}
depends_on_inner_class(new TargetClass.StaticNested());
class LocalInner1 {String getInnerValue() {return "LocalInner1_" + depth;}}class LocalInner2 {String getInnerValue() {return "LocalInner2_" + depth;}}
List<String> result = recursiveMethod(depth - 1);result.add(new LocalInner1().getInnerValue());result.add(new LocalInner2().getInnerValue());return result;}
private void variableCall() {String localVar = TargetClass.StaticNested.STATIC_FIELD;}
private void access_instance_method() {TargetClass.StaticNested nested = new TargetClass.StaticNested();nested.instanceMethod();}
private void depends_on_inner_class(TargetClass.StaticNested nested) {nested.useSuperMethod();}
class TypeDeclaration {}
class OthersClass {protected static TargetClass getTarget() {return new TargetClass() {};}}}
public interface TargetInterface {static class StaticNested {public static final String STATIC_FIELD = "targetStaticField";
public void instanceMethod() {}
strictfp void useSuperMethod() {super.toString();}}
default TargetClass m1() {return new TargetClass() {@Overridestrictfp Object overridingMethod() {Supplier<Object> lambda = () -> super.overridingMethod();return lambda.get();}};}
default TargetClass m2() {return new TargetClass() {};}
default TargetClass m3() {return new TargetClass() {};}
abstract class TargetClass {strictfp Object overridingMethod() {return new Object();}}}