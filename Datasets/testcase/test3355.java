package test;
import java.util.List;
private class SourceClass extends ParentClass {public static class StaticNested {}
/**
Overriding method with generic parameter
@param target Protected target class
@param list Generic list parameter*/@Overrideprivate <T> void process(ProtectedTarget target, List<T> list) {class LocalInner {}new LocalInner();
// Access target's fieldString targetField = target.data;
// Super keywordsuper.log(targetField);
// Variable call and this method invocationfor (T item : list) {this.handleItem(item);target.process(item.toString() + targetField);}}
private <T> void handleItem(T item) {}}
abstract class ParentClass {protected abstract <T> void process(ProtectedTarget target, List<T> list);
protected void log(String message) {}}
protected class ProtectedTarget {String data = "targetData";
{// Anonymous inner classRunnable anon = new Runnable() {@Overridepublic void run() {System.out.println(data);}};}
public void process(String input) {}}