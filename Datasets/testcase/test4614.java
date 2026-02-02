package test;
import java.lang.annotation.*;
@Retention(RetentionPolicy.RUNTIME)@interface TestAnnotation {}
class Source<T> {class Inner {T innerField;}
void methodWithAnonymous() {Runnable r = new Runnable() {public void run() {}};}
@TestAnnotationprivate Object instanceMethod(Target<String>.Inner.InnerRec param) {int i = 0;while (i < 5) {i++;}Object val = param.recField;return val;}}
private class Target implements Runnable {
class Inner {
class InnerRec {
U recField;
}
}
public void run() {}}