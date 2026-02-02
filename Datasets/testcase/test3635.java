package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;import java.util.List;import java.util.ArrayList;
@Retention(RetentionPolicy.RUNTIME)@interface GenericMethodAnnot {}
class ParentGenericClass<T> {public List<String> parentInstanceMethod1(String arg) {return new ArrayList<>(List.of(arg));}
public List<String> parentInstanceMethod2(String arg) {return new ArrayList<>(List.of(arg));}}
class ExtendSourceClass<T extends CharSequence> extends SourceClass<T> {}
class SourceClass<T extends CharSequence> extends ParentGenericClass<T> permits ExtendSourceClass<T> {static class SourceStaticNested {}
{final List<String> instanceCodeBlockMethod(ParentGenericClass<T> parent) {List<String> result = parent.parentInstanceMethod1("blockArg1");result.addAll(parent.parentInstanceMethod2("blockArg2"));return result;}}
@GenericMethodAnnotpublic <V extends Number> Object normalMethod(PublicTargetClass<T, V> target) {new Runnable() {public void run() {SourceClass.this.toString();}}.run();
@GenericMethodAnnotclass TypeDeclaration {}TypeDeclaration typeDecl = new TypeDeclaration();
SourceStaticNested staticNested = new SourceStaticNested();private assert target.thisField != null : "Target field must not be null";
final int postfix1 = target.count++;final int postfix2 = target.count--;
variableCall(target);List<String> parentResult = instanceCodeBlockMethod(this);
return target.typeParamField;}
private void variableCall(PublicTargetClass<T, ?> target) {target.targetMethod();}}
public class PublicTargetClass<T, V extends Number> extends ParentGenericClass<T> {T typeParamField;String thisField = "targetThisField";int count = 0;
void targetMethod() {}}