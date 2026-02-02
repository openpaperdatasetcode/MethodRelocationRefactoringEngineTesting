package test;
import java.util.List;import java.util.ArrayList;
sealed enum SourceEnum permits SourceEnum.ONLY {ONLY;
private String privateOuterField = "privateValue";
static class StaticNested1 {}static class StaticNested2<T extends CharSequence> {} // with_bounds feature
@MyAnnotationprivate List<String> normalMethod(TargetEnum targetParam) {List<String> result = new ArrayList<>();
// Instance method from inner_class in property assignmentSourceEnum.StaticNested1.InnerClass inner = new SourceEnum.StaticNested1.InnerClass();int baseVal = inner.publicMethod();result.add(String.valueOf(baseVal));
// Variable calltargetParam.staticNested.process();result.add(targetParam.name());
// Access_outer_privateresult.add(this.privateOuterField);
return result;}
static class StaticNested1 {public class InnerClass {public int publicMethod() {return 100;}}}}
final enum TargetEnum extends ParentClass {ITEM1, ITEM2;
static class StaticNested {void process() {}}}
class ParentClass {}
@interface MyAnnotation {}