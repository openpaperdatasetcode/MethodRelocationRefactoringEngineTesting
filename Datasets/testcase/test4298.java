package same.pkg;
public class TargetClass {// Target's static nested classpublic static class TargetStaticNested {// Strictfp instance method for call_methodstrictfp int nestedInstanceMethod() {return 42;}}
// Static field for depends_on_static_fieldpublic static String TARGET_STATIC_FIELD = "targetStaticValue";}
class SourceClass {// Inner class containing abstract methodclass SourceInner {// Target parameter (per condition)private TargetClass targetParam;
// Abstract method (refactored method)private abstract TargetClass method(TargetClass targetParam);
// Concrete implementation (for feature demonstration)private TargetClass concreteMethod(TargetClass targetParam) {this.targetParam = targetParam;variableCall();depends_on_static_field();
// Type declaration statementTypeDeclaration typeDecl = new TypeDeclaration();
// 1. IfStatement with this.field (public modifier)public boolean hasTarget = false;if (this.targetParam != null) {hasTarget = true;}
// 2. Additional if statementif (hasTarget) {try {// Call target's inner class method in exception throwing statementsint nestedResult = TargetClass.TargetStaticNested.newInstance().nestedInstanceMethod();} catch (Exception e) {throw new IllegalArgumentException("Call failed", e);}}
// return this; (returns inner class instance, aligns with logic)return this.targetParam;}
private void variableCall() {String localVar = targetParam.toString();}
private void depends_on_static_field() {// Use target's static fieldString staticVal = TargetClass.TARGET_STATIC_FIELD;}
class TypeDeclaration {}}
// override_violation: Example of method overriding violationclass ChildInner extends SourceInner {// Violates override: parent method is private (cannot be overridden)@Overrideprivate TargetClass concreteMethod(TargetClass targetParam) {return targetParam;}}}