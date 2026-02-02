package test;
@FunctionalInterfaceinterface Processable {Object process(Object data);}
sealed private class SourceClass permits SourceSubClass {public record SourceInnerRec(String recField) {@MyCustomAnnotationprotected Object processTarget(TargetClass<String> target) {super.toString();
String targetField = target.innerClass.innerField;Object result;
switch (targetField.length()) {case 3:result = targetField + "_short";break;case 5:result = targetField + "_medium";break;default:result = targetField + "_long";}
new Runnable() {@Overridepublic void run() {System.out.println("Processed: " + result);}}.run();
class LocalProcessor implements Processable {@Overridepublic Object process(Object data) {return data.toString().toUpperCase();}}LocalProcessor processor = new LocalProcessor();return processor.process(result);}}}
final class SourceSubClass extends SourceClass {}
protected class TargetClass<T> {public TargetInnerClass<T> innerClass;
public TargetClass(T fieldValue) {this.innerClass = new TargetInnerClass<>(fieldValue);}
public class TargetInnerClass<T> {public T innerField;
public TargetInnerClass(T innerField) {this.innerField = innerField;}}}
@interface MyCustomAnnotation {}