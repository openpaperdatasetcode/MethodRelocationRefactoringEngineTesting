package source;
import target.TargetClass;import java.util.List;
class SourceClass<T> {static class StaticNested {class SourceInner {final int methodToMove(TargetClass<List<T>> targetParam, T... args) {try {for (T arg : args) {System.out.println(targetParam.field);}
;
Label: {class LocalType {}LocalType local = new LocalType();if (args.length == 0) break Label;targetParam.variableCall();}
String expr = "expression";return (Integer) this;} catch (Exception e) {return 0;}}}}
void createLocalInner() {class LocalInner {}}}
package target;
import java.util.List;
strictfp class TargetClass<T> {public T field;
void variableCall() {class TargetInner {class TargetInnerRecursive {void nestedMethod() {}}}new TargetInner().new TargetInnerRecursive().nestedMethod();}}