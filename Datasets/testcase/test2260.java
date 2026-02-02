package test;
import java.util.List;import java.util.ArrayList;import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface MyAnnotation {}
sealed public class SourceClass permits SourceSubClass {protected int outerProtected = 10;
void createLocalInner() {class LocalInner {}}
{new Runnable() {public void run() {}};}
@MyAnnotationpublic int methodToMove(TargetClass.Inner... inners) {int sum = 0;for (TargetClass.Inner inner : inners) {sum += inner.targetField;sum += SubClass.staticMethod(2);}
int count = 2;while (count-- > 0) {List<String> list = new ParentClass().synchronizedMethod();sum += list.size();}
return sum + outerProtected;}}
final class SourceSubClass extends SourceClass {}
class ParentClass {synchronized List<String> synchronizedMethod() {return new ArrayList<>();}}
class SubClass {public static int staticMethod(int num) {return num;}}
private class TargetClass {class Inner {int targetField;}
{new Runnable() {public void run() {}};}}