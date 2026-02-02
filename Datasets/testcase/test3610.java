package test;
import java.util.ArrayList;import java.util.List;
abstract class SourceClass {protected String outerProtectedField = "sourceProtectedData";
static class FirstStaticNested {}
static class SecondStaticNested {}
protected <T extends CharSequence> List<String> instanceMethod(PublicTargetClass<T> target) {List<String> result = new ArrayList<>();
class TypeDeclaration {}TypeDeclaration typeDecl = new TypeDeclaration();
PublicTargetClass.StaticNested nested = new PublicTargetClass.StaticNested();String assignment = target.typeParamField;
assert assignment != null : "Assignment result must not be null";
variableCall(nested);result.add(outerProtectedField);
List<String> callResult = (nested != null) ?PublicTargetClass.StaticNested::createList : new ArrayList<>();
result.addAll(callResult);return result;}
private void variableCall(PublicTargetClass.StaticNested nested) {nested.printData();}}
public class PublicTargetClass<T> {T typeParamField;
static class StaticNested {public static List<String> createList() {return List.of("targetNestedData");}
void printData() {}}}