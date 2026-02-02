package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface MyAnnotation {}
class SuperGenericClass<T> {public T process(TargetClass<T> target) {return null;}}
protected class SourceClass<T> extends SuperGenericClass<T> {static class StaticNested {}class MemberInner {}
@MyAnnotation@Overridepublic TargetClass<T> process(TargetClass<T> target) {super();int i = 0;
do {try {if (target.dataField == null) {continue;}target.variableCall();i++;} finally {System.out.println("Loop iteration: " + i);}} while (i < 5);
return target;}}
public class TargetClass<T> {T dataField;
void variableCall() {class LocalInner {}}}