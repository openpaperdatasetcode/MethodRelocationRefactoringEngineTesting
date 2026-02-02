package same.pkg;
import java.lang.reflect.Method;import java.util.Objects;
public sealed record Source(String sourceField) permits SubSource {static class StaticNested {}
public class SourceInner {public Object instanceMethod(Target targetParam) {for (int i = 0; i < 1; i++) {DefaultConstructor dc = new DefaultConstructor();int val = dc.getValue();}
Target.Inner targetInner = targetParam.new Inner();Source source = new Source("test");String typeDecl;
try {Method method = SourceInner.class.getMethod("instanceMethod", Target.class);method.invoke(this, targetParam);} catch (Exception e) {}
Object varCall = targetInner.innerField;boolean overrideViolation = targetParam.check();Object fieldAccess = sourceField;
return null;}}
void localInnerMethod() {class LocalInner {}}
private static class DefaultConstructor {public DefaultConstructor() {super();}
public int getValue() {return 1;}}}
record SubSource(String sourceField) implements Source {public SubSource {Objects.requireNonNull(sourceField);}}
abstract record Target(Integer targetField) {public Target {OthersClass others = new OthersClass();String param = others.callMethod(new SourceInner());}
class Inner {Object innerField;
public boolean check() {class LocalInner {}return true;}}}
class OthersClass {public String callMethod(Source.SourceInner inner) {return inner.instanceMethod(new Target(5)) + "";}}