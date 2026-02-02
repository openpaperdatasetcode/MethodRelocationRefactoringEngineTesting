package test;
import java.util.ArrayList;import java.util.List;import java.util.function.Consumer;
public class SourceClass<T> permits SubSourceClass {private int outerPrivateField;TargetClass targetField;
class MemberInner
{new Runnable() {public void run() {}};}
@MyAnnotationList<String> moveMethod(TargetClass.Inner targetInner) {List<String> result = new ArrayList<>();int i = 0;while (i < 5) {try {targetInner.instanceMethod();result.add(targetField.outerPrivateField + "");Consumer<String> consumer = s -> OthersClass.privateStaticMethod(this.process(s));consumer.accept("test");} catch (NullPointerException e) {// handle exception}i++;}return result;}
private String process(String s) {return s.toUpperCase();}}
sealed class SubSourceClass extends SourceClass<String> permits {}
protected class TargetClass {int outerPrivateField;
class Inner {void instanceMethod() {new Runnable() {public void run() {}};}}}
class OthersClass {private static void privateStaticMethod(String s) {}}
@interface MyAnnotation {}