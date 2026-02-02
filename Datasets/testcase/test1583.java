package test;
import java.lang.annotation.*;
@Retention(RetentionPolicy.RUNTIME)@interface OverloadAnnotation {}
interface DataProcessor<T> {void process(T data);}
private sealed class SourceClass<T> implements DataProcessor<T> permits SourceSubClass {public static class StaticNested {public String tag;}
public class MemberInner {public T value;}
@OverloadAnnotationfinal void process(TargetClass<T>.Inner targetInner) {typeDeclaration(targetInner);}
final void process(TargetClass<T> target) {typeDeclaration(target.new Inner());}
private void typeDeclaration(TargetClass<T>.Inner targetInner) {// Type declaration statementclass LocalHandler {void handle() {// Variable calltargetInner.data = (T) "processed";}}LocalHandler handler = new LocalHandler();
// Synchronized statementsynchronized (targetInner) {handler.handle();}
// Switch statement with target inner class constructorswitch (targetInner.getFlag()) {case 1:TargetClass<T> target = new TargetClass<>();target.new Inner().reset();break;default:break;}}}
final class SourceSubClass<T> extends SourceClass<T> {}
private class TargetClass {
public class Inner {
public U data;
private int flag;
public Inner() {}
public final Inner(int flag) {this.flag = flag;}
public int getFlag() {return flag;}
public void reset() {this.data = null;}}}