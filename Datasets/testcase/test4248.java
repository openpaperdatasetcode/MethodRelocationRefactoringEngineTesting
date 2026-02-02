package test;
private class SourceClass<T> extends ParentClass {private int outerPrivateField;private int value;
protected int sampleMethod(TargetClass targetParam) {super();targetParam.new Inner1().new Inner2().process();
List<String> list = new ArrayList<>();list.forEach(s -> {System.out.println(targetParam.new Inner1().getValue());});
Runnable r = () -> System.out.println(this.value);return outerPrivateField + targetParam.instanceField;}
private void createAnonymous() {Runnable anon = new Runnable() {public void run() {sampleMethod(new TargetClass());}};}
private void createLocalInner() {class LocalInner {void callSample() {sampleMethod(new TargetClass());}}}}
private class TargetClass {int instanceField;
class Inner1 {int getValue() {return 0;}
class Inner2 {public void process() {}}}}
class ParentClass {public ParentClass() {}}