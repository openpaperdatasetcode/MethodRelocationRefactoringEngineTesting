package test;
interface Processable {void execute();}
protected class SourceClass implements Processable {private int counter = 0;
public class SourceInner {synchronized void processTarget(TargetClass<String> target, int limit) {private class TargetHandler {private void handleLoop(TargetClass<String> t) {while (counter < limit) {String data = t.getInner().getNested().process();System.out.println("Processed: " + data + ", Counter: " + counter);counter++;}}}
TargetHandler handler = new TargetHandler();handler.handleLoop(target);}}
@Overridepublic void execute() {TargetClass<String> target = new TargetClass<>("Initial Data");SourceInner inner = new SourceInner();inner.processTarget(target, 5);}}
public class TargetClass<T> {private T data;
public TargetClass(T data) {this.data = data;}
public TargetInner getInner() {return new TargetInner();}
public class TargetInner {public TargetNested getNested() {return new TargetNested();}}
private static class TargetNested {public String process() {try {return SourceClass.StaticHelper.formatData();} catch (Exception e) {return "Error: " + e.getMessage();}}}
public static class StaticHelper {private static String formatData() {return "Formatted-" + System.currentTimeMillis();}}}