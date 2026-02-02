package sourcepkg;
import targetpkg.TargetGenericClass;import java.util.function.Supplier;
class SourceGenericClass<T> {class SourceMemberInner {}static class SourceStaticNested {}
private int varargsMethod(TargetGenericClass<T>.TargetInner... targetParams) {SourceMemberInner inner = new SourceMemberInner();SourceStaticNested nested = new SourceStaticNested();
Supplier<TargetGenericClass<T>.TargetInner> creator = TargetGenericClass.TargetInner::new;TargetGenericClass<T>.TargetInner created = creator.get();created.doAction();
try {OtherDiffPackageClass.privateTryMethod(this, targetParams[0]);} catch (Exception e) {}
TargetGenericClass rawTarget = new TargetGenericClass();return targetParams.length;}}
package targetpkg;
class ParentGenericClass {}
private class TargetGenericClass<V extends Number> extends ParentGenericClass<V> {private V targetField;
static class TargetStaticNested {}
class TargetInner {void doAction() {}}
static class InnerCaller {synchronized void callMethod(TargetGenericClass<Integer>.TargetInner inner) {new SourceGenericClass<Integer>().varargsMethod(inner);}}}
package otherpkg;
import sourcepkg.SourceGenericClass;import targetpkg.TargetGenericClass;
public class OtherDiffPackageClass {private static <T extends Number> void privateTryMethod(SourceGenericClass<T> source, TargetGenericClass<T>.TargetInner inner) {try {inner.doAction();TargetGenericClass<T> target = new TargetGenericClass<>();} catch (Exception e) {}}}