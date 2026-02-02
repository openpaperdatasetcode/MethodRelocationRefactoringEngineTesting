package test;
import java.util.List;import java.util.ArrayList;
protected class SourceClass<T> {class SourceInner1 {}class SourceInner2 {}
public static <T> int staticMethod(TargetClass<T>.TargetStaticNested.TargetInnerRec param) {// Super keywordsclass SubSource extends SourceClass<T> {SubSource() {super();}}
// Variable callT var = param.data;int count = param.count;TargetClass<T>.TargetStaticNested nested = param.parent;
return count;}}
private class TargetClass {
static class TargetStaticNested {
class TargetInnerRec {
U data;
int count;
TargetStaticNested parent;
}
}
synchronized List<String> callMethod() throws Exception {// Exception throwing statementsif (true) {throw new Exception("Error", new TargetClass.TargetStaticNested<>());
}
// Super type reference method callList<String> list = super.getClass().getSimpleName();return new ArrayList<>(list);}}