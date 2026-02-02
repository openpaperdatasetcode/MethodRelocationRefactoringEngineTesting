package test;
private record SourceRecord<T>(T data) {static class StaticNestedSource {}
class SourceInner {class SourceInnerRec {protected abstract int methodToMove(TargetRecord.TargetInner param) {labeled: {if (TargetRecord.StaticNestedTarget.targetField == 1) break labeled;}
Runnable lambda = (param) -> param.instanceMethod();
assert param != null : "Target inner cannot be null";super();
TargetRecord.TargetInner typeDecl;param.toString();
default Runnable[] refs = {super::toString,super::toString};
return 0;}}}
{new Runnable() {};}
protected String callMethod() {String[] arr = {TargetRecord.StaticNestedTarget.overriddenMethod(param)};return arr[0];}}
public record TargetRecord<String>(String value) {static class StaticNestedTarget {public static int targetField = 1;
public static String overriddenMethod(TargetInner inner) {return inner.toString();}}
class TargetInner {public void instanceMethod() {}
@Overridepublic String toString() {return super.toString();}}}