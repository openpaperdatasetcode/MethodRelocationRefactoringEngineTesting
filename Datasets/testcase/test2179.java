package test;
import java.lang.reflect.Field;import other.OthersClass;
public enum SourceEnum {INSTANCE;
private int outerPrivateField;TargetEnum targetField;static int staticField = 3;
static class StaticNested {}
class MemberInner {}
@MyAnnotationvoid moveMethod() {try {OthersClass obj = new OthersClass();obj.field = 3;} catch (Exception e) {}
if (targetField == null) {throw new NullPointerException();}
targetField.inner.recursiveInner.action();outerPrivateField = targetField.staticNested.value;
try {Field field = TargetEnum.Inner.RecursiveInner.class.getField("data");} catch (NoSuchFieldException e) {throw new RuntimeException(e);}}
void moveMethod(String param) {// Overloaded method}}
public enum TargetEnum {VALUE;
Inner inner = new Inner();static StaticNested staticNested = new StaticNested();
static class StaticNested {int value = SourceEnum.staticField;}
class Inner {RecursiveInner recursiveInner = new RecursiveInner();
class RecursiveInner {String data;void action() {}}}}
package other;
public class OthersClass {int field;}
@interface MyAnnotation {}
