package test;
import java.util.List;import java.util.ArrayList;
interface Action {void execute();}
sealed class SourceClass permits SourceSubClass {public class MemberInner {public TargetClass.Inner createInner() {return new TargetClass().new Inner();}}
// Abstract method with List parameter typeprotected abstract TargetClass process(List<TargetClass.Inner> innerList);
{// Anonymous inner classAction initializer = new Action() {@Overridepublic void execute() {MemberInner inner = new MemberInner();TargetClass.Inner targetInner = inner.createInner();targetInner.data = "init";}};initializer.execute();}}
final class SourceSubClass extends SourceClass {@Overrideprotected TargetClass process(List<TargetClass.Inner> innerList) {// Constructor invocationTargetClass target = new TargetClass();MemberInner helper = new MemberInner();
// Enhanced for statementfor (TargetClass.Inner inner : innerList) {// Variable call - access target inner's fieldtarget.items.add(inner.data);
// Access instance fieldinner.counter++;}
return target;}}
final class TargetClass implements Action {public List<String> items = new ArrayList<>();
public class Inner {public String data;public int counter;}
@Overridepublic void execute() {}}