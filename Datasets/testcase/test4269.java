package test;
import java.lang.reflect.Method;import java.util.List;
abstract class SourceClass {private TargetClass<String> targetField = new TargetClass<>();
// Member inner class (source_class feature)class SourceMemberInner {// Recursive inner class (for "source_inner_rec" position)class SourceRecursiveInner {// Normal method to be refactoredpublic final TargetClass<String> getTargetInstance() {// Static VariableDeclarationStatement (2 instances, matches target_feature)static int field1 = SourceClass.this.targetField.targetField1; ;static int field2 = SourceClass.this.targetField.targetField2; ;
// With bounds (generic bounds)GenericClass<? extends Number> boundedGen = new GenericClass<>(100);
// While statementint count = 0;while (count < 3) {// Switch statementswitch (count) {case 0:targetField.innerField = field1;break;case 1:targetField.innerField = field2;break;default:break;}count++;}
// Used by reflectiontry {Method targetMethod = TargetClass.class.getMethod("getInnerValue");targetMethod.invoke(targetField);} catch (Exception e) {}
// Override violation (target method is final in parent)targetField.overrideFinalMethod();
return targetField;}}}
// Local inner class (source_class feature)void createLocalInner() {class SourceLocalInner {void callRecursiveMethod() {SourceMemberInner.RecursiveInner inner = new SourceMemberInner().new SourceRecursiveInner();inner.getTargetInstance();}}new SourceLocalInner().callRecursiveMethod();}}
// Generic helper class for "with_bounds"class GenericClass<T> {T value;public GenericClass(T value) {this.value = value;}}
// Target class (matches target_class structure)public class TargetClass<T> extends ParentTargetClass {int targetField1 = 10;int targetField2 = 20;int innerField;
// Member inner class (target_class feature)class TargetMemberInner {T getInnerValue() {return (T) String.valueOf(innerField);}}
// Override violation (parent method is final)@Overridepublic void overrideFinalMethod() {}
public T getInnerValue() {return new TargetMemberInner().getInnerValue();}}
// Parent class for target_class "extends" featureclass ParentTargetClass {// Final method (causes override violation)public final void overrideFinalMethod() {}}