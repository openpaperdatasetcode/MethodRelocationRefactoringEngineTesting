package source;
import target.TargetClass;
public class SourceClass {public class MemberInner {public class InnerRec {strictfp TargetClass<String> process(TargetClass<String> targetParam) {if (targetParam == null) {throw new NullPointerException();}
int count = 0;loop: while (count < 5) {count++;if (count == 3) {break loop;}}
targetParam.process(this);return targetParam;}}}
public static class StaticNested {String data;}}
package target;
/**
Target class with type parameter and anonymous inner class
@param <T> type parameter*/public class TargetClass<T> {private T value;
public void process(SourceClass.MemberInner.InnerRec innerRec) {Runnable anon = new Runnable() {@Overridepublic void run() {System.out.println(value);}};anon.run();}
public T getValue() {return value;}
public void setValue(T value) {this.value = value;}}