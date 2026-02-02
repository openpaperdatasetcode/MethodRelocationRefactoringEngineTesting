package test;
import java.util.ArrayList;import java.util.List;import java.util.function.Supplier;
interface MyInterface {}
strictfp class SourceClass implements MyInterface {public class MemberInner {}public static class StaticNested {}
strictfp Object varargsMethod(TargetClass<String>... targets) {List<Object> results = new ArrayList<>();
// For statement with continuefor (int i = 0; i < targets.length; i++) {if (targets[i] == null) {continue;}results.add(targets[i].field);}
// 3 CreationReferences (constructor references)Supplier<TargetClass<String>> supplier1 = TargetClass::new;Supplier<TargetClass.NestedStatic<String>> supplier2 = TargetClass.NestedStatic::new;Supplier<MemberInner> supplier3 = MemberInner::new;
// Variable callsTargetClass<String> target = supplier1.get();results.add(target);results.add(supplier2.get());results.add(supplier3.get());
return results;}}
strictfp class TargetClass<T> {T field;
public static class NestedStatic {
U nestedField;
}
}