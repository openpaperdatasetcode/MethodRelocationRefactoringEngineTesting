package test.refactoring;
import java.lang.reflect.Method;
// Interface for source class implementationinterface SourceInterface {}
// Interface for target class implementationinterface TargetInterface {}
// Source class: private, same package, has implements/static nested/local inner classprivate class SourceClass implements SourceInterface {protected String outerProtectedField = "outer_protected"; // For access_outer_protected
// Static nested class (source feature)static class SourceStaticNested {}
// Member inner class (source_inner: method's original position)public class SourceInnerClass {// Target method: instance, TargetClass type return, public, source_inner position// per_condition: contains target parameter (TargetClass)public TargetClass moveTargetMethod(TargetClass targetParam) {String var = outerProtectedField; // variable call + access_outer_protectedexpressionStatementAction(var); // expression statement
// Used by reflectiontry {Method method = SourceInnerClass.class.getMethod("moveTargetMethod", TargetClass.class);method.invoke(this, targetParam);} catch (Exception e) {// no_new_exception (catches reflection exceptions, no new checked exception)}
// Throw statement (runtime exception, no new checked exception)if (targetParam == null) {throw new IllegalArgumentException("Target parameter cannot be null");}
// ReturnStatement (private modifier implied, pos: diff_package_others, target_feature: obj.field x1)return new TargetClass() {@Overridepublic TargetClass getTargetInstance() {String fieldVal = targetParam.targetField; // obj.field (target_feature)return targetParam;}};}
// Helper method for expression statementprivate void expressionStatementAction(String param) {System.out.println(param);}}
// Local inner class (source feature)public void sourceLocalMethod() {class SourceLocalInner {public void invokeInnerMethod() {new SourceInnerClass().moveTargetMethod(new TargetClass());}}new SourceLocalInner().invokeInnerMethod();}}
// Target class: normal, default modifier, has implements/member inner class (target_feature)class TargetClass implements TargetInterface {// Target field referenced in ReturnStatementString targetField = "target_field";
// Member inner class (target_feature)class TargetInnerClass {}
// Method for return type compatibilitypublic TargetClass getTargetInstance() {return this;}}
// Other class containing call_methodclass OtherCallerClass {// Call method: others_class, protected, instance, new ClassName().method(), pos: for, return voidprotected void callMethod(int count) {// pos: for statementfor (int i = 0; i < count; i++) {// target_feature: new ClassName().method()SourceClass source = new SourceClass();SourceClass.SourceInnerClass inner = source.new SourceInnerClass();inner.moveTargetMethod(new TargetClass());}}}