package test;
import java.util.ArrayList;import java.util.List;
private enum SourceEnum {INSTANCE1, INSTANCE2;
static class SourceStaticNested {}
class SourceInner {private List<String> recursiveMethod(TargetEnum target, int depth, List<String> result) {if (depth <= 0) {return result;}
TypeDeclared typeDecl = new TypeDeclared();String varCall = target.getTargetField();
if (varCall.contains("break")) {break;}result.add(varCall);
return recursiveMethod(target, depth - 1, result);}
List<String> startRecursion(TargetEnum target) {return recursiveMethod(target, 3, new ArrayList<>());}}}
protected enum TargetEnum {TARGET1("val1"), TARGET2("val2");
private final String targetField;
TargetEnum(String targetField) {this.targetField = targetField;}
class TargetInner {}
String getTargetField() {return targetField;}}
class TargetSubClass {private String subRecursiveMethod(TargetEnum target, int count) {if (count <= 0) {return target.getTargetField();}return subRecursiveMethod(target, count - 1) + count;}
String callInWhile() {int i = 0;String result = "";while (i < 2) {SourceEnum.SourceInner inner = SourceEnum.INSTANCE1.new SourceInner();List<String> list = inner.startRecursion(TargetEnum.TARGET1);result += subRecursiveMethod(TargetEnum.TARGET2, i);i++;}return result;}}
class TypeDeclared {}
