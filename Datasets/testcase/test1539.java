package test;
import java.util.ArrayList;import java.util.List;import java.lang.annotation.*;
@Retention(RetentionPolicy.RUNTIME)@interface CustomAnnotation {}
protected enum SourceEnum {INSTANCE;
private TargetEnum targetField;
public static class StaticNested {String data;}
@CustomAnnotationprivate List<String> process() {List<String> result = new ArrayList<>();StaticNested nested = new StaticNested();nested.data = "source";
class LocalInner {void addItems() {result.add(targetField.value);}}new LocalInner().addItems();
int count = 0;loop: while (count < 5) {count++;if (count == targetField.count) {break loop;}if (count % 2 == 0) {continue;}; // empty statement}
final void helper() {this.process();targetField.subEnum.action();}
helper();return result;}}
/**
Target enum with javadoc and anonymous inner class*/abstract enum TargetEnum {SUB {void action() {Runnable anon = new Runnable() {@Overridepublic void run() {System.out.println(value);}};anon.run();}};
String value;int count;TargetEnum subEnum;
abstract void action();}