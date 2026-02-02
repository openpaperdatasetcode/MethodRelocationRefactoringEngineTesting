package test.refactoring;
import java.util.ArrayList;import java.util.List;
// Interface for source class implementationinterface TestInterface {}
// Source class: normal, private, same package, has implements/static nested/member inner classprivate class SourceClass implements TestInterface {protected String outerProtectedField = "outer_protected"; // For access_outer_protected// Source contains target's field (per_condition)private TargetClass targetField = new TargetClass();private String sourceVar = "source_variable";
// Static nested class (source feature)private static class SourceStaticNested {}
// Member inner class (source feature)private class SourceMemberInner {}
// Target method: instance, List<String>, default access, source positionList<String> moveTargetMethod() {List<String> result = new ArrayList<>();String var = sourceVar; // variable call
// For statementfor (int i = 0; i < 5; i++) {// Continue statementif (i % 2 == 0) {continue;}
// Expression statementvar = outerProtectedField + "-" + i; // access_outer_protected + variable callresult.add(var);result.add(targetField.targetInstanceField); // variable call (target field)}
// No new checked exceptionreturn result;}
// Override violation: target has same-signature method with stricter accessList<String> moveTargetMethod(String param) {return new ArrayList<>();}}
// Target class: normal, private, has member inner class (target_feature)private class TargetClass {// Target field referenced by source (per_condition)String targetInstanceField = "target_field";
// Member inner class (target_feature)private class TargetMemberInner {}
// Override violation: same method signature with private access (stricter than default)private List<String> moveTargetMethod() {return new ArrayList<>();}}