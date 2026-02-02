package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;import java.util.ArrayList;import java.util.List;
@Retention(RetentionPolicy.RUNTIME)@interface MethodAnnot {}
class ParentClass {protected String parentField = "parentData";}
public class TargetClass {public String targetField = "targetField";
static class TargetStaticNested extends ParentClass {TargetClass recursionMethod(TargetClass target) {if (target == null) return new TargetClass();return superTypeReference.recursionMethod(target);}}
static TargetStaticNested superTypeReference = new TargetStaticNested();}
public class SourceClass extends ParentClass {static class SourceStaticNested {static String staticField = "staticData";}
class MemberInner {class NestedInner {@MethodAnnotprivate List<String> instanceMethod (TargetClass target) {class TypeDeclaration {}TypeDeclaration typeDecl = new TypeDeclaration ();
TargetClass newTarget = new TargetClass ();public TargetClass instanceCreation = new TargetClass ();
target.targetField = "updated";SourceStaticNested.staticField = "modified";
 superSystem.out.println (super.parentField);
variableCall (target.superTypeReference);
List<String> result = new ArrayList<>();result.add(target.targetField);
TargetClass initialized = new TargetClass () {{target.superTypeReference.recursionMethod (this);}};
return result;}
private void variableCall(TargetClass.TargetStaticNested nested) {nested.recursionMethod(new TargetClass());}}}}