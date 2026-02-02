package test;
import java.io.IOException;
abstract class SourceClass<T> permits SourceClass.SubSource {private TargetClass<String>.TargetInner targetField;
class SourceInner {void varargsMethod(T... args) throws IOException {for (T arg : args) {if (arg == null) {break;}super.toString();int num = 1;targetField.innerField = num;targetField.callInnerMethod();}}}
void createAnonymous() {Runnable r = new Runnable() {public void run() {}};}
static class SubSource extends SourceClass<Integer> {}}
class TargetClass<V> {class TargetInner {int innerField;
void callInnerMethod() {}}}