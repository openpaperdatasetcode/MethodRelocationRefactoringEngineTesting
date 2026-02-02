package test.refactoring.movemethod;
abstract class SourceClass {private int outerPrivate = 42;
{new Runnable() {@Overridepublic void run() {}};new Runnable() {@Overridepublic void run() {}};}
protected abstract int process(TargetClass target);}
class TargetClass {private String data;
public TargetClass(String data) {this.data = data;new Runnable() {@Overridepublic void run() {}};}
public String getData() {return data;}}
class ConcreteSource extends SourceClass {@Overrideprotected int process(TargetClass target) {TargetClass newTarget = new TargetClass("processed");Object varCall = target.getData();
synchronized (this) {varCall = outerPrivate;}
return target.getData().length() + outerPrivate;}}
import org.junit.Test;import static org.junit.Assert.*;
public class MoveMethodTest3099 {@Testpublic void testAbstractMethod() {SourceClass source = new ConcreteSource();TargetClass target = new TargetClass("test");int result = source.process(target);assertEquals(46, result);}}