package source.pkg;
import target.pkg.TargetEnum;import java.lang.reflect.Method;
// Source enum class (non-sealed, different package, no features)non-sealed enum SourceEnum {INSTANCE;
// Method to be refactored: instance, protected, base type return, in sourceprotected int processTarget(TargetEnum targetParam) { // per_condition: contains target parameter// VariableDeclarationStatement (public, source pos, obj.field x3)public String field1 = targetParam.targetField;public String field2 = targetParam.getInner().innerField;public String field3 = targetParam.new AnonymousImpl().implField;
// Uses_outer_thisSourceEnum outerThis = SourceEnum.this;
// If statement + empty statementint result = 0;if (targetParam == TargetEnum.TARGET1) {result = 10;; // Empty statement} else {result = 20;}
// Variable call + object initialization with parent_class instance methodParentClass parent = new ParentClass();Object parentResult = parent.callParentMethod(outerThis);
// Ternary operator with call_method (instanceReference::methodName)Object ternaryResult = (result > 15) ? outerThis::callInTernary : null;
// Used_by_reflectiontry {Method targetMethod = TargetEnum.class.getDeclaredMethod("getInner");targetMethod.invoke(targetParam);} catch (ReflectiveOperationException e) {// No_new_exception: rethrow without wrappingthrow new RuntimeException(e);}
return result; // base type return}
// Call_method: source type, default modifier, instance, in ternary operatorsObject callInTernary() {return "SourceCallResult";}
// Parent class for method_feature (parent_class)protected static class ParentClass {// Instance method with super.methodName(arguments)protected Object callParentMethod(SourceEnum source) {super.toString(); // super.methodName(arguments)return source.name();}}}
package target.pkg;
// Target enum class (default modifier, target_feature: implements + anonymous inner class)enum TargetEnum implements TargetInterface {TARGET1, TARGET2;
// Target field used in obj.fieldpublic String targetField = "targetFieldValue";
// Target_inner: inner classpublic TargetInner getInner() {return new TargetInner();}
// Inner class (target_inner)public class TargetInner {public String innerField = "innerFieldValue";}
// Target_feature: anonymous inner class (implements TargetInterface)public TargetInterface new AnonymousImpl() {return new TargetInterface() {public String implField = "anonymousImplField";
@Overridepublic void interfaceMethod() {}};}
@Overridepublic void interfaceMethod() {}}
// Interface for target_class implements featureinterface TargetInterface {void interfaceMethod();}
// Test classpackage test.pkg;
import source.pkg.SourceEnum;import target.pkg.TargetEnum;
public class MoveMethodTest5237 {public static void main(String[] args) {SourceEnum source = SourceEnum.INSTANCE;TargetEnum target = TargetEnum.TARGET1;int result = source.processTarget(target);System.out.println("Refactoring test result: " + result);}}