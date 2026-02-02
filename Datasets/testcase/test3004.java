package test;
import java.util.List;import java.util.ArrayList;import java.util.Collection;
@interface SourceAnnotation extends BaseAnnotation {public List<String> instanceMethod(TargetAnnotation param) {class LocalInner {}int i = 0;do {i++;} while (i < 5);int num1 = 10;long num2 = 20L;double num3 = 30.5;param.value();BaseAnnotation.super.protectedMethod();return SourceAnnotation.this.createList();}
static class StaticNested {}
private List<String> createList() {return new ArrayList<>();}}
protected @interface TargetAnnotation<T extends Collection<String>> extends AnotherAnnotation {/**
Javadoc for value method.
*/
String value();
default void sample() {Runnable r = new Runnable() {public void run() {}};}}
interface BaseAnnotation {protected void protectedMethod();}
interface AnotherAnnotation {}