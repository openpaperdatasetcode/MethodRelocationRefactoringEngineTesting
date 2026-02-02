package test;
import java.util.List;
protected class SourceClass implements Runnable {public static class StaticNested implements Cloneable {int id;}
@Overridepublic void run() {}
public void process(TargetClass target) {class LocalProcessor {void handle(int a, int b, int c) {super.toString();target.validate(a, b, c);}}
new LocalProcessor().handle(1, 2, 3);this.process(target, 0);}
public void run(TargetClass target, int count) {StaticNested nested = new StaticNested();nested.id = count;
loop: for (int i = 0; i < 5; i++) {if (i == 3) break loop;if (i % 2 == 0) continue;target.action(i);}}}
class TargetClass implements AutoCloseable {public TargetClass() {super();}
void validate(int x, int y, int z) {class LocalValidator {boolean check() {return x > 0 && y > 0 && z > 0;}}if (!new LocalValidator().check()) {throw new IllegalArgumentException();}}
void action(int val) {}
@Overridepublic void close() {}}