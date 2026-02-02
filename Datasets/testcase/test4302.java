package same.pkg;
import java.util.List;import java.util.ArrayList;
private class SourceClass implements MyInterface {static class StaticNested1 {}static class StaticNested2 {}
// Constructor with target parameter (per condition)SourceClass(TargetClass.TargetInner targetInnerParam) {super(); // Super constructor invocationvariableCall(targetInnerParam);
// Type declaration statementTypeDeclaration typeDecl = new TypeDeclaration();
// Instance code block with accessor method chain{int baseVal = targetInnerParam.getNested().getField().getValue();}
// Enhanced for statementList<String> items = new ArrayList<>();for (String item : items) {assert item != null : "Item cannot be null";}
// Raw type usageTargetClass.StaticNested rawNested = new TargetClass.StaticNested();}
private void variableCall(TargetClass.TargetInner param) {String localVar = param.innerField;}
class TypeDeclaration {}}
interface MyInterface {}
class TargetClass<T> {// Target inner class (parameter of refactored constructor)class TargetInner {String innerField = "innerValue";
TargetClass.StaticNested<T> getNested() {return new TargetClass.StaticNested<>();}}
// Target static nested class with type parameterstatic class StaticNested<T> {private T field;
StaticNested<T> getField() {return this;}
int getValue() {return 0;}}}