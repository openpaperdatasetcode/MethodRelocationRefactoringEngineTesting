package source;
import target.TargetClass;import java.util.List;import java.util.ArrayList;
final class SourceClass<T> {static class StaticNested {}
{new Runnable() {};}
public List<String> instanceMethod(TargetClass<? extends Number> target) throws Exception {try {variableCall = target.field;dependsOnStatic = TargetClass.staticField;} catch (Exception e) {throw e;}return new ArrayList<>();}
public List<String> instanceMethod(String str) throws Exception {return new ArrayList<>();}
TargetClass<?> variableCall;int dependsOnStatic;}
package target;
import java.util.List;
protected class TargetClass<S> {int field;static int staticField;
static class TargetStaticNested {}}