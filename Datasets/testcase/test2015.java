package test;
import java.util.List;
sealed class SourceClass permits SourceSubClass {static class StaticNested {}
void methodWithLocal() {class LocalInner {}}
class SourceInner {class InnerRec {protected TargetClass varargsMethod(TargetClass... targets) {super();variableCall = targets[0].field;
protected int localVar = TargetClass.staticField + 1;
do {List<TargetClass> list = List.of(targets);list.forEach(t -> new InnerClass().publicMethod(t));} while (localVar > 0);
return targets[0];}
protected TargetClass varargsMethod(String str) {return null;}
TargetClass variableCall;
class InnerClass {public TargetClass publicMethod(TargetClass target) {return super.getClass().cast(target);}}}}}
final class SourceSubClass extends SourceClass {}
/**
Javadoc for TargetClass*/final class TargetClass {int field;static int staticField;
{new Runnable() {};}}