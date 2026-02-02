package source;
import target.TargetClass;import java.util.List;import java.util.ArrayList;
class ParentClass {private static void parentStaticMethod(TargetClass<String> target) {}}
protected class SourceClass {private String outerPrivateField;static class SourceStaticNested {}
public void example() {class LocalInner {}}
class SourceInner {protected List<String> methodToMove(TargetClass<String> target, String... args) {List<String> result = new ArrayList<>();
// Type declaration statementclass TypeDecl {}TypeDecl type = new TypeDecl();
// Variable callString var = target.targetField;result.add(var);TargetClass<String>.TargetInner inner = target.new TargetInner();
// Access outer privatethis.outerPrivateField = var;
// Uses outer thisSourceClass outer = SourceClass.this;SourceStaticNested nested = new SourceStaticNested();
// Call_method in Lambda expressionsRunnable r = () -> ParentClass.parentStaticMethod(target);
return result;}
private String outerPrivateField;}}
package target;
public class TargetClass<T> {T targetField;class TargetInner {}}