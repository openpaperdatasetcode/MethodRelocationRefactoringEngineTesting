package test;
import java.util.List;import java.util.ArrayList;import java.util.function.Function;
/**
Javadoc for TargetClass
Contains static nested class and fields for refactoring test
*/
protected class TargetClass {
String targetField;
static class TargetStaticNested {}
}
public class SourceClass {static class SourceStaticNested {}class SourceInner {record SourceInnerRec(String recField) {/**
Overriding method to process TargetClass
@param target The target class instance
@return List of strings including target field*/@Overrideprotected List<String> methodToMove(TargetClass target) {List<String> result = new ArrayList<>();TargetClass.TargetStaticNested nested = new TargetClass.TargetStaticNested();
// MethodReference with numbers:1Function<String, String> func = String::toUpperCase;result.add(func.apply(target.targetField));
// Variable callString var = target.targetField;result.add(var);
// Return thisreturn this;}}}}
class SourceSubClass extends SourceClass.SourceInner.SourceInnerRec {public SourceSubClass(String recField) {super(recField);}
@Overrideprotected List<String> methodToMove(TargetClass target) {return super.methodToMove(target);}}
