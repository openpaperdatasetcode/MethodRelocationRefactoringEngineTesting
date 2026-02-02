package test;
interface BaseInterface {}
@interface SourceClass extends BaseInterface {class InnerSource implements BaseInterface {public Object normalMethod(TargetClass.InnerTarget param) throws Exception {String label = "loop";int count = 0;loop: while (true) {if (count > 3) {break loop;}param.setValue(StaticClass.staticField);Object var = param.getValue();System.out.println(var);if (super.equals(param)) {count++;} else {break;}}new Runnable() {public void run() {System.out.println(param);}}.run();return param;}}}
@interface TargetClass implements BaseInterface {class InnerTarget implements BaseInterface {private Object value;public void setValue(Object val) {}public Object getValue() { return value; }}}
class StaticClass {static Object staticField = new Object();}