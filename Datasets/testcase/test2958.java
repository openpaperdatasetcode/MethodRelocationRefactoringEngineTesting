package test;
import java.util.List;import java.util.regex.Pattern;
sealed class SourceClass<T> permits SubSource {TargetClass<String> targetField = new TargetClass<>();
class SourceInner {class SourceInnerRec {int methodToMove() {try {new SubTarget().overriddenMethod();} catch (Exception e) {e.printStackTrace();}
int i = 1;while (i < targetField.count) {Pattern pattern = Pattern.compile("1");targetField.process(this);i++;}return 0;}}}
static class StaticNestedSource {}}
class SubSource extends SourceClass<Integer> {}
/**
Javadoc for TargetClass*/abstract class TargetClass extends ParentTarget {
protected int count = 5;
static class StaticNestedTarget {}
void process(SourceClass.SourceInner.SourceInnerRec inner) {}
protected List<String> overriddenMethod() {return List.of();}}
class SubTarget extends TargetClass<Long> {@Overrideprivate List<String> overriddenMethod() {return List.of("sub");}}
class ParentTarget {}