package same;
import java.util.List;
record SourceRecord<T extends Number>(T id) {static class StaticNested {}
public int process(TargetRecord<String> target) {class LocalType {}LocalType local = new LocalType();
TargetRecord<String>.Inner inner = new TargetRecord<String>.Inner(target, target.value);int result = inner.count;
{TargetRecord.Inner.staticMethod();}
try {TargetRecord.Inner.synchronizedStaticMethod();} catch (Exception e) {e.printStackTrace();}
return result + TargetRecord.STATIC_FIELD;}}
package same;
strictfp record TargetRecord<V>(V value) {static int STATIC_FIELD = 10;
static class Inner {int count;
Inner(TargetRecord<?> outer, Object val) {this.count = outer.value.hashCode();}
static void staticMethod() {}
static synchronized void synchronizedStaticMethod() {}}}
