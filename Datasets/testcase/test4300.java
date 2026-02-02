package same.pkg;
import java.util.List;import java.util.ArrayList;import java.util.function.Predicate;
protected non-sealed class SourceClass permits SourceSubClass {protected static String STATIC_FIELD = "staticValue";
class SourceInner {final List<String> overloadedMethod(TargetClass targetParam) {return overloadedMethod(targetParam, 5);}
final List<String> overloadedMethod(TargetClass targetParam, int limit) {variableCall(targetParam);depends_on_static_field();List<String> result = new ArrayList<>();
for (int i = 0; i < limit; i++) {result.add(targetParam.field + "_" + i);}
int count = 0;while (count < limit / 2) {result.add(SourceClass.this.STATIC_FIELD + "_" + count);count++;}
class LocalInner {void process() {Predicate<String> predicate = s -> s.contains(targetParam.field);result.removeIf(predicate);}}new LocalInner().process();
new Runnable() {@Overridepublic void run() {result.add("anonymous_" + targetParam.field);}}.run();
return result;}
private void variableCall(TargetClass param) {String localVar = param.field;}
private void depends_on_static_field() {String val = STATIC_FIELD;}}}
class SourceSubClass extends SourceClass {}
protected class TargetClass {String field = "targetField";
void methodWithAnonymous() {new Runnable() {@Overridepublic void run() {}};}}