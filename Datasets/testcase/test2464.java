package test.refactoring.movemethod;
sealed class TargetParent permits ConcreteTarget {protected String name;
public TargetParent(String name) {this.name = name;}
protected TargetParent create(String name) {return new ConcreteTarget(name);}
protected TargetParent create(String name, int id) {return new ConcreteTarget(name, id);}}
abstract class TargetClass extends TargetParent {public TargetClass(String name) {super(name);}
public abstract String getDetails();}
final class ConcreteTarget extends TargetClass {private int id;
public ConcreteTarget(String name) {super(name);this.id = 0;}
public ConcreteTarget(String name, int id) {super(name);this.id = id;}
@Overridepublic String getDetails() {return name + "_" + id;}}
private non-sealed class SourceClass permits ExtendedSource {static class SourceStaticNested {}class SourceMemberInner {}
public void process(TargetClass... targets) {if (targets == null || targets.length == 0) {return;}
// Variable callObject varCall = targets[0].name;
// Super keywords in inner contextclass Derived extends SourceMemberInner {void check() {super.toString();}}new Derived().check();
// If/else conditions with parent class overloading methodsfor (TargetClass target : targets) {TargetParent parent;if (target.getDetails().length() > 5) {parent = target.create(target.name); // Overloaded method 1} else {parent = target.create(target.name, 100); // Overloaded method 2}System.out.println("Created: " + parent.name);}}}
class ExtendedSource extends SourceClass {}
import org.junit.Test;import static org.junit.Assert.*;
public class MoveMethodTest3158 {@Testpublic void testVarargsMethod() {SourceClass source = new ExtendedSource();TargetClass target1 = new ConcreteTarget("short");TargetClass target2 = new ConcreteTarget("longer_name");
source.process(target1, target2);// Verify through console output or additional assertions as needed}}