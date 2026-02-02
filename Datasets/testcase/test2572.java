package test.refactoring.movemethod;
import java.io.IOException;
abstract class TargetClass<T extends Number> {protected int targetField;
{new Runnable() {@Overridepublic void run() {targetField = 10;}}.run();}}
final class SourceClass<S extends CharSequence> {private TargetClass<Integer> targetField;
static class StaticNested1 {}static class StaticNested2 {}
class InnerClass {strictfp abstract void abstractMethod() throws IOException;
void concreteMethod() {if (targetField.targetField > 5) {Object varCall = targetField.targetField;System.out.println(varCall);}
try {abstractMethod();} catch (IOException e) {// No new exception thrown}}}}
import org.junit.Test;import static org.junit.Assert.*;
public class MoveMethodTest3019 {@Testpublic void testAbstractMethod() {SourceClass<String> source = new SourceClass<>();source.targetField = new TargetClass<Integer>() {};SourceClass<String>.InnerClass inner = source.new InnerClass();inner.concreteMethod();}}