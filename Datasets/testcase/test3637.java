package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;import java.util.List;import java.util.ArrayList;import java.util.Collection;
@Retention(RetentionPolicy.RUNTIME)@interface EnumAnnot {}
class ParentEnum {protected List<String> parentMethod() {return new ArrayList<>();}}
sealed enum SourceEnum permits ExtendedSourceEnum {INSTANCE;
static class StaticNested {}
@EnumAnnotpublic int varargsMethod(PublicTargetEnum<String> target, List<String>... listArgs) throws NullPointerException {super();new Runnable() {public void run() {SourceEnum.this.toString();}}.run();
class TypeDeclaration {}TypeDeclaration typeDecl = new TypeDeclaration();
Collection rawCollection = new ArrayList();rawCollection.forEach(item -> target.overriddenMethod());
try {variableCall(target.memberInner);List<String> result1 = target.overriddenMethod();List<String> result2 = target.overriddenMethod("arg1");List<String> result3 = target.overriddenMethod("arg1", "arg2");} catch (NullPointerException e) {throw e;}
return listArgs.length;}
private void variableCall(PublicTargetEnum.MemberInner inner) {inner.innerMethod();}}
non-sealed enum ExtendedSourceEnum implements SourceEnum {}
public enum PublicTargetEnum<T> extends ParentEnum {TARGET_INSTANCE;
class MemberInner {void innerMethod() {}}MemberInner memberInner = new MemberInner();
@Overrideprotected List<String> overriddenMethod() {super.parentMethod();return new ArrayList<>();}
protected List<String> overriddenMethod(String arg1) {super.parentMethod();return new ArrayList<>(List.of(arg1));}
protected List<String> overriddenMethod(String arg1, String arg2) {super.parentMethod();return new ArrayList<>(List.of(arg1, arg2));}}
