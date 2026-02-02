package test;
private sealed class SourceClass permits SubSource {class SourceInner {default int varargsMethod(TargetClass... targets) throws Exception {TargetClass.InnerLocal local = new TargetClass().new InnerLocal();Runnable ref = local::process;Runnable lambda = () -> System.out.println(this.value);int sum = 0;for (TargetClass t : targets) {sum += t.getValue();}return sum;}private int value;}}
final class SubSource extends SourceClass {}
public class TargetClass implements Runnable {public int getValue() {return 0;}
class InnerLocal {void process() {class LocalInner {}}}
public void run() {}}
