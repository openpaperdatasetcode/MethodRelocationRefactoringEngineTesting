package test;
import java.util.ArrayList;import java.util.List;
private sealed class SourceClass permits SourceSub1, SourceSub2 {// Static nested classpublic static class SourceStaticNested {}
// Local inner classpublic void useLocal() {class LocalHandler {TargetClass process(TargetClass target) {return varargsMethod(target);}}new LocalHandler().process(new TargetClass());}
TargetClass varargsMethod(TargetClass... targets) {if (targets.length == 0) {// Constructor invocationreturn new TargetClass();}TargetClass result = targets[0];
// Variable callresult.data = "combined:";for (TargetClass t : targets) {result.data += t.data;}
// Try statementtry {RecursiveProcessor processor = new RecursiveProcessor();result.lines = processor.process(targets, 0);} catch (IndexOutOfBoundsException e) {result.lines = new ArrayList<>();}
return result;}
// Inner class with recursion (3 recursive calls in switch)public class RecursiveProcessor {public List<String> process(TargetClass[] targets, int index) {List<String> list = new ArrayList<>();if (index >= targets.length) {return list;}
// Switch with recursionswitch (index % 3) {case 0:list.add("case0:" + targets[index].data);list.addAll(this.process(targets, index + 1));break;case 1:list.add("case1:" + targets[index].data);list.addAll(this.process(targets, index + 1));break;case 2:list.add("case2:" + targets[index].data);list.addAll(this.process(targets, index + 1));break;}return list;}}}
final class SourceSub1 extends SourceClass {}final class SourceSub2 extends SourceClass {}
public class TargetClass {String data;List<String> lines;
public TargetClass() {// Local inner classclass DataInitializer {String init() {return "default";}}this.data = new DataInitializer().init();}}