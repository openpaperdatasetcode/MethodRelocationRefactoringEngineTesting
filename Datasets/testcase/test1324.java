package test.refactor.movemethod;
import java.lang.reflect.Method;import java.util.function.Supplier;
// Parent class for target_class extends featureclass TargetParentClass {protected String parentField = "TargetParentField";public TargetParentClass() {}}
// Interface for source_class implements featureinterface Processable {int process(TargetEnum target);}
// Others_class for overriding method featureabstract class OtherAbstractClass {public abstract Object otherMethod(String arg);}
class OtherConcreteClass extends OtherAbstractClass {@Overridepublic Object otherMethod(String arg) {return "Overridden:" + arg;}}
// Source enum class (protected, same package, implements + local inner + anonymous inner class)protected enum SourceEnum implements Processable {INSTANCE;
// Private field for access_outer_privateprivate String outerPrivateField = "SourcePrivateValue";
// Member inner class (source_inner - method position)protected class SourceInner {// Method to be refactored: instance, protected, base type returnpublic int processTarget(TargetEnum targetParam) { // per_condition// Per_condition: source contains target's fieldString targetField = targetParam.targetField;
// Access_outer_privateString privateValue = SourceEnum.this.outerPrivateField;
// Type declaration statementTargetEnum.TargetInner targetInner = targetParam.new TargetInner();OtherConcreteClass other = new OtherConcreteClass();
// Super constructor invocation (implicit in enum, explicit via inner class)class SuperConstructorHelper extends TargetParentClass {public SuperConstructorHelper() {super(); // Super constructor invocation}}SuperConstructorHelper superHelper = new SuperConstructorHelper();
// Local inner class (source feature)class LocalInner {public String useTargetInner() {return targetInner.getInnerData(targetField);}}
// Anonymous inner class (source feature)Runnable anon = new Runnable() {@Overridepublic void run() {System.out.println("SourceAnon: " + privateValue + "_" + targetField);}};
// Lambda expressions with overriding method feature (3 occurrences)Supplier<Object> lambda1 = () -> other.otherMethod(targetField); // 1stSupplier<Object> lambda2 = () -> other.otherMethod(privateValue); // 2ndSupplier<Object> lambda3 = () -> other.otherMethod(superHelper.parentField); // 3rd
try {anon.run();LocalInner local = new LocalInner();String innerResult = local.useTargetInner();
// Variable call: overriding method resultsObject override1 = lambda1.get();Object override2 = lambda2.get();Object override3 = lambda3.get();
// Used_by_reflectionMethod targetMethod = TargetEnum.class.getDeclaredMethod("getTargetField");String reflected = (String) targetMethod.invoke(targetParam);
return targetField.length() + privateValue.length() + reflected.length();} catch (ReflectiveOperationException e) {// no_new_exception: rethrow without wrappingthrow new RuntimeException(e);}}}
@Overridepublic int process(TargetEnum target) {SourceInner inner = new SourceInner();return inner.processTarget(target);}}
// Target enum class (default modifier, target_feature: extends + member inner class)enum TargetEnum extends TargetParentClass {TARGET_INSTANCE;
// Target field (per_condition)public String targetField = "TargetEnumField";
// Target_feature: member inner class (target_inner)public class TargetInner {public String getInnerData(String input) {return "TargetInner:" + input;}}
public String getTargetField() {return targetField;}}
// Test classpublic class MoveMethodTest5268 {public static void main(String[] args) {SourceEnum source = SourceEnum.INSTANCE;TargetEnum target = TargetEnum.TARGET_INSTANCE;int result = source.process(target);System.out.println("Refactoring test result: " + result);}}