package test;
import java.io.IOException;
private class SourceClass<T> {static class StaticNested {}
void instanceMethod(TargetClass<String>.MemberInner.InnerRec param) throws IOException {super();class LocalInner {}
variableCall();param.instanceMethod();}
private void variableCall() {}}
public class TargetClass {
class MemberInner {
class InnerRec {
void instanceMethod() {}
}
}
}