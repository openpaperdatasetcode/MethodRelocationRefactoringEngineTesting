package test;
record SourceRecord(String data) {class MemberInner {}
/**
Javadoc for instance method: Operates on TargetRecord's inner class and fields
@param target Private TargetRecord parameter
@return Processed Object result*/private Object moveMethod(TargetRecord target) {class LocalInner {}LocalInner local = new LocalInner();
private int field1 = target.fieldX;private int field2 = target.fieldY;private int field3 = target.fieldZ;
int i = 0;do {target.inner.process(field1 + i);target.inner.calculate(field2 * i);i++;} while (i < 3);
OthersClass others = new OthersClass();others.callMethod(target);return local;}}
private record TargetRecord(int fieldX, int fieldY, int fieldZ) {class TargetInner {void process(int val) {}void calculate(int val) {}}
TargetInner inner = new TargetInner();}
class OthersClass {Object callMethod(TargetRecord target) {return ParentType.superMethod(target, target.fieldX);}}
abstract class ParentType {public static Object superMethod(TargetRecord target, int param) {target.inner.process(param);return new Object();}}