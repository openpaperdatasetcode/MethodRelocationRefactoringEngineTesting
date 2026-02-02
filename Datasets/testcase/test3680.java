package test;
class SourceClass {public TargetClass instanceMethod(TargetClass target) {class LocalInnerFirst {TargetClass process(TargetClass t) {return t;}}
class LocalInnerSecond {String getInfo(TargetClass t) {return t.toString();}}
LocalInnerFirst first = new LocalInnerFirst();LocalInnerSecond second = new LocalInnerSecond();
TargetClass processed = first.process(target);String info = second.getInfo(processed);
return processed;}
{TargetClass target = new SubTargetClass();String result = new SubTargetClass().abstractMethod();}}
class TargetClass {// Target class with no specific features}
abstract class SubTargetClass extends TargetClass {protected abstract String abstractMethod();}