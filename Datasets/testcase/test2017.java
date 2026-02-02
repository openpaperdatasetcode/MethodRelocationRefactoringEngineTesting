package test;
import java.util.List;import java.util.ArrayList;
private class SourceClass<T> {protected int outerProtected;static class StaticNested {}
{new Runnable() {};this.protectedMethod(1);}
List<String> instanceMethod(TargetClass<?>.TargetStaticNested.InnerRec innerRec) throws Exception {class TypeDecl {}TargetClass<String> target = new TargetClass<>();
variableCall = innerRec.field;innerRec.instanceMethod();outerProtected = variableCall;
return new ArrayList<>();}
List<String> instanceMethod(Integer num) throws Exception {return new ArrayList<>();}
protected int protectedMethod(int num) {return num;}
int variableCall;}
protected class TargetClass<S> {static class TargetStaticNested {class InnerRec {int field;void instanceMethod() {}}}}