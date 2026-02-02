package test.refactoring;
/**
Target class Javadoc (target_feature: javadoc)
Generic abstract target class with member inner class*/abstract class GenericTargetClass<T> {// Target field for variable callprotected T targetField;
// Member inner class (target_feature)public class TargetInnerClass {public T innerField;
public TargetInnerClass(T value) {this.innerField = value;}}
public GenericTargetClass(T targetField) {this.targetField = targetField;}}
// Source class: generic, public, same package, has permits/local inner/static nested classpublic sealed class GenericSourceClass<S> permits SourceSubclass {protected S outerProtectedField; // For access_outer_protected
// Static nested class (source feature)public static class SourceStaticNested {}
// Member inner class (parent of source_inner_rec)public class SourceInnerClass {// Member inner class (source_inner_rec: method's original position)public class SourceInnerRecClass {// Target method: varargs, TargetClass Type return, protected, source_inner_rec position// method types parameter is:none, per_condition: contains target parameter (GenericTargetClass)protected GenericTargetClass<S> moveTargetMethod(GenericTargetClass<S> targetParam, S... varargs) {// Variable call + access_outer_protectedS var = outerProtectedField;if (var == null) {throw new NullPointerException("Outer protected field is null"); // NullPointerException}
// Super keywords (refers to outer class's implicit super)super.toString();
// Loop with continue statementfor (int i = 0; i < varargs.length; i++) {if (varargs[i] == null) {continue; // continue statement}var = varargs[i];}
// Variable call to target parameter's fieldtargetParam.targetField = var;targetParam.new TargetInnerClass(var);
// No new checked exceptionreturn targetParam;}}}
// Local inner class (source feature)public void sourceLocalMethod() {class SourceLocalInner {public void invokeInnerMethod(GenericTargetClass<S> target) {SourceInnerClass inner = new SourceInnerClass();inner.new SourceInnerRecClass().moveTargetMethod(target, outerProtectedField);}}new SourceLocalInner().invokeInnerMethod(new GenericTargetClass<>(outerProtectedField) {});}
public GenericSourceClass(S outerProtectedField) {this.outerProtectedField = outerProtectedField;}}
// Permitted subclass for source class's permits featurefinal class SourceSubclass<T> extends GenericSourceClass<T> {public SourceSubclass(T outerProtectedField) {super(outerProtectedField);}}