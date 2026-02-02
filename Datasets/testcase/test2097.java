package test;
import java.util.List;import java.util.ArrayList;
interface TestInterface {}
class SuperSource {}
protected class TargetClass<T> implements TestInterface {public String targetField;
static class StaticNested {class InnerClass {}}
void variableCall() {}}
public class SourceClass<T> extends SuperSource {static class StaticNested {}
void createLocalInner() {class LocalInner {}}
private List<String> methodToMove (TargetClass targetParam) throws Exception {TargetClass.StaticNested.InnerClass rawInner = new TargetClass.StaticNested.InnerClass ();List<String> result = new ArrayList<>();
int i = 0;while (i < 5) {targetParam.variableCall ();result.add (targetParam.targetField);i++;}
return result;}
private List<String> methodToMove(TargetClass<T> targetParam, int count) throws Exception {return new ArrayList<>();}}
