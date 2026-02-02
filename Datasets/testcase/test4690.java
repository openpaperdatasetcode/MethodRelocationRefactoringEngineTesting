package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface RecursiveMethodAnn {}
/**
Public target enum with member inner class
Stores field used by source enum's recursive method*/public enum TargetEnum {TARGET_CONST1, TARGET_CONST2;
public int thisField = 1;public TargetInner inner = new TargetInner();
/**
Member inner class of TargetEnum
Provides auxiliary data for recursion
*/
public class TargetInner {
int innerField;
}
}
protected enum SourceEnum {SOURCE_CONST1, SOURCE_CONST2;
protected String outerProtectedField = "protected_data";public static class SourceStaticNested {}
void sampleLocalClass() {class SourceLocalInner {}SourceLocalInner local = new SourceLocalInner();}
@RecursiveMethodAnnpublic Object recursiveMethod (TargetEnum target, int depth) {
private int currentDepth = depth;if (currentDepth <= 0) {return target.thisField;}
SourceStaticNested staticNested = new SourceStaticNested ();target.inner.innerField = currentDepth;Object result = target.inner.innerField + outerProtectedField.length ();
result = recursiveMethod (target, currentDepth - 1);return result;}
private String callMethod (TargetEnum target) {int depth = 3;do {
String str = new SourceStaticNested ().toString ().concat (target.inner.toString ()).toUpperCase ();depth--;} while (depth > 0);return target.name ();}}
class SamePackageOtherClass {public void useSourceEnum () {SourceEnum source = SourceEnum.SOURCE_CONST1;TargetEnum target = TargetEnum.TARGET_CONST1;source.recursiveMethod (target, 2);source.callMethod (target);}}