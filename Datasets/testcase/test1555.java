package test;
interface SourceInterface extends ParentInterface, TargetInterface {private Object process(TargetInterface target) {class LocalInner1 {private Object getValue() {return super.toString();}}
class LocalInner2 {<T extends Number> T process(T input) {return input;}}
LocalInner1 inner1 = new LocalInner1();Object result = inner1.getValue();
do {result = (target.toString());} while (result == null);
LocalInner2 inner2 = new LocalInner2();int num = inner2.process(5);
Runnable lambda = () -> {try {target.execute(num);} catch (Exception e) {// requires try-catch}};lambda.run();
return process(target, num);}
private Object process(TargetInterface target, int param) {return new Object() {@Overridepublic String toString() {return target.toString() + param;}};}}
interface TargetInterface {private static void execute(int value) {this.log(value);}
private void log(int value) {System.out.println("Value: " + value);}}
interface ParentInterface {}