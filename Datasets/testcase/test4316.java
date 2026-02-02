package same.pkg;
import com.other.DiffPackageClass;import java.util.function.Supplier;
// Source class: private modifier, with permits, static nested class, anonymous inner classprivate non-sealed class SourceClass permits SourceSubClass {// Contains target's field (per condition)private TargetClass<String> targetField = new TargetClass<>();
// Source's static nested classstatic class SourceStaticNested {}
// Inner class containing overriding methodclass SourceInner implements TargetInterface {/**
@return Base type (String) result after processing
@implNote Overrides method from TargetInterface, contains DoStatement and break logic*/@Overridepublic synchronized String overridingMethod() {variableCall();
// Type declaration statementTypeDeclaration typeDecl = new TypeDeclaration();
// Expression statementString exprResult = targetField.getGenericField() + "_processed";
// DoStatement (protected modifier) in diff_package_others, uses this.fieldprotected boolean doFlag = false;DiffPackageClass diffObj = new DiffPackageClass();do {doFlag = diffObj.process(this.targetField);if (doFlag) break; // Break statement} while (true);
// Call sub_class instance method in functional interfaces (Supplier)Supplier<String> funcSup = () -> SourceSubClass.newInstance().callSuperMethod(targetField);exprResult += funcSup.get();
// Anonymous inner class (source_class feature)Runnable anonRunnable = new Runnable() {@Overridepublic void run() {System.out.println("Anonymous inner class in SourceInner");}};anonRunnable.run();
return exprResult;}
private void variableCall() {String localVar = targetField.getGenericField();}
class TypeDeclaration {}}}
// Sub class for source's permits clauseclass SourceSubClass extends SourceClass {// Sub_class instance method (call_method: public modifier, uses super.methodName())public String callSuperMethod(TargetClass<?> target) {super.toString(); // super.methodName()return "subClass" + target.getGenericField();}
public static SourceSubClass newInstance() {return new SourceSubClass();}}
// Target interface for source inner class to implement (enables overriding)interface TargetInterface {String overridingMethod();}
// Target class: public modifier, with type parameter and static nested classpublic class TargetClass<T> {private T genericField;
public T getGenericField() {return genericField;}
public void setGenericField(T genericField) {this.genericField = genericField;}
// Target's static nested classpublic static class TargetStaticNested<T> {private T nestedField;}}
// Diff-package class for DoStatement positionpackage com.other;
import same.pkg.TargetClass;
public class DiffPackageClass {public <T> boolean process(TargetClass<T> target) {return target.getGenericField() != null;}}