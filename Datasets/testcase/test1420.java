package test;
import java.io.IOException;import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;import java.util.function.Consumer;
@Retention(RetentionPolicy.RUNTIME)@interface ProcessAnnotation {String innerMethod() default "SourceClass.MemberInner::processVoid";}
private class SourceClass<T> {class MemberInner {public void processVoid(String arg) {System.out.println(arg);}}
{new Runnable() {@Overridepublic void run() {}};}
@ProcessAnnotationpublic int process(TargetClass<Integer> target) throws IOException {if (target.value == null) {throw new IOException("Target value is null");}
int result = target.value;Consumer<String> consumer = new MemberInner()::processVoid;consumer.accept(String.valueOf(result));
return result;}}
class TargetClass<T> {T value;}