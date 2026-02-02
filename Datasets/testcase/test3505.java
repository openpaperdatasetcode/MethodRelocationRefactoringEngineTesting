package test;
import java.lang.reflect.Method;import java.util.ArrayList;import java.util.List;
public class SourceClass<T extends Number & Comparable<T>> extends ParentSource {private String outerPrivateField = "outer_private";
protected List<String> instanceMethod(FinalTargetClass<T> target) {class TypeDeclarationStatement {String processTarget() {// Access ClassName.field (FinalTargetClass.field) with value "2"return FinalTargetClass.STATIC_FIELD + "_processed";}}
TypeDeclarationStatement typeDecl = new TypeDeclarationStatement();List<String> results = new ArrayList<>();
// Variable callString targetFieldValue = target.getField().toString();results.add(targetFieldValue);
// Access outer private fieldresults.add(outerPrivateField);
// This method callresults.add(this.formatString(targetFieldValue));
// Depends on inner classInnerHelper helper = new InnerHelper();results.add(helper.assist(target));
// Assert statementassert target.getField().intValue() > 0 : "Target field must be positive";
// Used by reflectiontry {Method getFieldMethod = FinalTargetClass.class.getMethod("getField");results.add(getFieldMethod.invoke(target).toString());} catch (Exception e) {e.printStackTrace();}
// Call method: overriding + super.methodName(arguments) in exception throwing statementstry {int superResult = super.calculate(target.getField().intValue());results.add(String.valueOf(superResult));} catch (IllegalArgumentException e) {throw new IllegalArgumentException("Calculation failed", e);}
return results;}
public String formatString(String input) {return input.toUpperCase();}
private class InnerHelper {String assist(FinalTargetClass<T> target) {return target.getField().toString() + "_assisted";}}}
abstract class ParentSource {public int calculate(int value) {return value * 2;}}
final class FinalTargetClass<T extends Number> {public static final String STATIC_FIELD = "2";private T field;
public FinalTargetClass(T field) {this.field = field;}
public T getField() {return field;}}
// Test implementation for overridingclass ChildSource extends SourceClass<Integer> {@Overridepublic String formatString(String input) {return input.toLowerCase() + "_overridden";}}