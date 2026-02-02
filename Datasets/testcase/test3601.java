package test;
interface SourceInterface {class InnerClass1 {public int baseMethod(TargetInterface.InnerClass param) {return param.value;}
public int baseMethod(String str) {OthersClass oc = new OthersClass();return oc::abstractMethod;}}
class InnerClass2 {protected int field;
public void callSuper() {super.toString();}}}
abstract interface TargetInterface {class InnerClass {int value;
void process() {class LocalInner {void useValue() {System.out.println(value);}}new LocalInner().useValue();}}}
class OthersClass {default String abstractMethod() {return "";}}