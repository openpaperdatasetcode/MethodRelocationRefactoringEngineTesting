package same;
import java.lang.reflect.Method;import java.util.function.Consumer;import other.DiffPackageOthers;
public class Source<T> {private Target<T> sourceTargetField = new Target<>();
static class SourceStaticNested {public static void staticNestedMethod(U param) {}
}
class SourceInner {protected void varargsMethod(Consumer... consumers) {
public void varDeclInDiff() {
DiffPackageOthers diffObj = new DiffPackageOthers();
String field1 = DiffPackageOthers.STATIC_FIELD_1;
String field2 = DiffPackageOthers.STATIC_FIELD_2;
String field3 = DiffPackageOthers.STATIC_FIELD_3;
}
varDeclInDiff();
SourceInnerStatic.innerStaticMethod(this::processConsumer);
try {Method targetMethod = Target.TargetInner.class.getMethod("innerInstanceMethod", Object.class);Target.TargetInner inner = sourceTargetField.new TargetInner();targetMethod.invoke(inner, "reflection_param");} catch (Exception e) {e.printStackTrace();}
Object var = sourceTargetField;for (Consumer consumer : consumers) {
consumer.accept((U) "varargs_param");
}
}
private void processConsumer(Consumer consumer) {
consumer.accept((U) "processed_param");
}
private static class SourceInnerStatic {private static void innerStaticMethod(Consumer<Consumer> processor) {
processor.accept((U) -> {});
}
}
}
}
public class Target<T> {static class TargetStaticNested {public static void targetStaticMethod(U param) {}
}
public class TargetInner {public void innerInstanceMethod(U param) {}
}
}
package other;
public class DiffPackageOthers {public static final String STATIC_FIELD_1 = "diff_field_1";public static final String STATIC_FIELD_2 = "diff_field_2";public static final String STATIC_FIELD_3 = "diff_field_3";
public <T> void diffMethod(T param) {}}