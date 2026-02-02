import java.util.List;import java.util.ArrayList;import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface MethodAnnotation {String value() default "overload";}
sealed abstract class SourceClass permits SourceSubClass {protected TargetClass<String> targetField = new TargetClass<>();
static class StaticNested {final List<String> overloadMethod(SourceClass source) {return source.new MemberInner().m1().m2().m3();}}
class MemberInner {public List<String> m1() { return new ArrayList<>(); }public List<String> m2() { return new ArrayList<>(); }public List<String> m3() { return new ArrayList<>(); }}
@MethodAnnotation("target_inner_rec")private Object process(TargetClass<String>... targets) throws Exception {super();TypeDeclaration typeDecl = new TypeDeclaration();
List<String> result = new ArrayList<>();StaticNested staticNested = new StaticNested();
for (TargetClass<String> target : targets) {result.addAll(staticNested.overloadMethod(this));result.addAll(target.StaticNested.getNestedData(target.instanceField));target.StaticNested.processCollection(result);}
return result;}
class TypeDeclaration {}}
class SourceSubClass extends SourceClass {}
public abstract class TargetClass<T> {public T instanceField;
public static class StaticNested<T> {public static <T> List<String> getNestedData(T field) {return List.of(field.toString());}
public void processCollection(List<String> collection) {collection.add("processed");}}}