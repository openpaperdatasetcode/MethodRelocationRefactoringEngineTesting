package test;
import java.util.ArrayList;import java.util.List;
/**
Javadoc for TargetClass
Contains static nested class with inner class
*/
public class TargetClass {
static class TargetStaticNested {
class TargetInner {
String value;
}
}
}
private class SourceClass<T> {static class SourceStaticNested {}
{new Object() {}; // Anonymous inner class}
class SourceInner {class SourceInnerRec {protected List<String> methodToMove(TargetClass.TargetStaticNested.TargetInner targetParam) {// Assert statementassert targetParam != null : "Target parameter must not be null";
// Super keywordssuper.toString();
// Variable callString var = targetParam.value;SourceStaticNested nested = new SourceStaticNested();String nestedStr = nested.toString();
List<String> result = new ArrayList<>();result.add(var);result.add(nestedStr);return result;}}}}