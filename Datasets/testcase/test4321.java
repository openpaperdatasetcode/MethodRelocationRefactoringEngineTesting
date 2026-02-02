package same;
abstract class Source permits SourceImpl1, SourceImpl2 {static class SourceStaticNested1 {public static Object staticMethod(Object param) {return param;}}static class SourceStaticNested2 {}
class SourceInner {class SourceInnerRec {strictfp Object instanceMethod(Target targetParam) {assert targetParam != null : "Target parameter cannot be null";
Target.TargetStaticNested targetNested = new Target.TargetStaticNested();SourceStaticNested2 sourceNested = new SourceStaticNested2();
Object var = targetParam;Object nestedResult = targetNested.nestedMethod(var);
SourceStaticNested1 nested1 = new SourceStaticNested1(SourceStaticNested1::staticMethod);return nested1.process(nestedResult);}}}
private static Object privateCallMethod(Object param) {return "processed_" + param;}
static class SourceStaticNested1 {private final java.util.function.Function<Object, Object> methodRef;
public SourceStaticNested1(java.util.function.Function<Object, Object> methodRef) {this.methodRef = methodRef;}
public Object process(Object param) {return methodRef.apply(param);}}}
final class SourceImpl1 extends Source {}final class SourceImpl2 extends Source {}
protected class Target {static class TargetStaticNested {public Object nestedMethod(Object param) {return param.toString();}}}