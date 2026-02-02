package samepkg;
interface ActionInterface {void execute();}
private class SourceClass implements ActionInterface {public static class StaticNested1 {}public static class StaticNested2 {}
@Overridepublic void execute() {}
// Overloading methods (to be refactored)protected void process(TargetClass targetParam) {targetParam.innerStaticNested.process();StaticNested1 nested1 = new StaticNested1();}
protected void process(TargetClass targetParam, String arg) {targetParam.innerStaticNested.process(arg);StaticNested2 nested2 = new StaticNested2();}}
package samepkg;
sealed class TargetClass extends ParentTarget permits TargetSubclass {public static class InnerStaticNested {public void process() {}public void process(String arg) {}}
public InnerStaticNested innerStaticNested = new InnerStaticNested();}
class ParentTarget {}final class TargetSubclass extends TargetClass {}