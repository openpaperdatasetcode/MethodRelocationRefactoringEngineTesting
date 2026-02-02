package source;
strictfp class SourceClass permits SubSource {private List<String> processData(List<Integer> genericParam) {protected class TempType {int value = target.TargetClass.Nested.targetField;}super();TempType temp = new TempType();List<String> result = new ArrayList<>();result.add(String.valueOf(temp.value + genericParam.size()));return result;}
class MemberInner {}
static class StaticNested {}}
package target;
protected class TargetClass {static class Nested {static int targetField = 1;}}
package source;
class SubSource extends SourceClass {}