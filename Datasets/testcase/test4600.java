package test;
public record SourceClass(String data, TargetClass target) {public static class FirstStaticNested {void nestedMethod1() {}}
public static class SecondStaticNested {void nestedMethod2() {}}
public class SourceInner {private void instanceMethod() {class LocalType {void useTarget(TargetClass targetParam) {System.out.println(targetParam.value());FirstStaticNested first = new FirstStaticNested();first.nestedMethod1();}}
LocalType localObj = new LocalType();try {localObj.useTarget(SourceClass.this.target());SecondStaticNested second = new SecondStaticNested();second.nestedMethod2();} catch (Exception e) {e.printStackTrace();}}}
private SourceInner innerInstance = new SourceInner();}
non-sealed record TargetClass(int value) {public void createLocalInner() {class TargetLocalInner {void processValue() {System.out.println(TargetClass.this.value());}}new TargetLocalInner().processValue();}}