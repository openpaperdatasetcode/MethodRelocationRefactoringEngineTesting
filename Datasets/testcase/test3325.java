package test;
final class SourceClass extends ParentClass {public static class StaticNested {}
class MemberInner {}
Object moveMethod(ProtectedTarget<String> target) {try {target.process(SourceClass.this.getOuterData());target.staticNested.handle(target.getData());return new Object();} catch (Exception e) {e.printStackTrace();return null;}}
private String getOuterData() {return "outerData";}
@Overrideprotected int callMethod(int param) {return new ProtectedTarget<Integer>("constructorParam", param).calculate(param);}}
abstract class ParentClass {protected abstract int callMethod(int param);}
protected class ProtectedTarget<T> {private T data;private int param;
public static class StaticNested {public void handle(Object obj) {}}
public ProtectedTarget(T data, int param) {this.data = data;this.param = param;}
public void process(String outerData) {}
public T getData() {return data;}
public int calculate(int num) {return num * param;}}